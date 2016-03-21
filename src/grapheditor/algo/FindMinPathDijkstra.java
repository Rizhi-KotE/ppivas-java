package grapheditor.algo;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import Exception.NoPathException;
import catchers.ReflectionCatcher;
import grapheditor.model.main.Graph;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class FindMinPathDijkstra implements FindMinPathAlgo {

	private static final String FIND_NEAREST_NODE = "findNearestNode";

	private static final String NEXT_INCIDENT_EDGE = "nextIncidentEdge";

	private static final String NEXT_NODE = "nextNode";

	ArrayList<Double> d;

	ArrayList<ViewNode> g, p;
	private Graph graph;
	private int i;

	private Iterator<ViewEdge> incidentEdgeIt;

	boolean isInit = false;
	Method nextMethod;
	private int s;
	private int to;
	ArrayList<Boolean> u;
	private int v;

	public FindMinPathDijkstra() {
	}

	private GraphPath buildPath(ViewNode start, ViewNode end) throws NoPathException {
		GraphPath path = new MinPath();
		if (!path.add(end))
			throw new NoPathException();
		ViewNode previos = end;
		while (!previos.equals(start)) {
			ViewNode curr = p.get(g.indexOf(previos));
			if (curr == null) {
				curr = start;
			}
			ViewEdge edge = graph.getEdge(previos, curr);
			if (edge != null) {
				if (!path.add(edge))
					throw new NoPathException();
			}
			if (!path.add(curr))
				throw new NoPathException();
			previos = curr;
		}
		highlightPath(path);
		return path;
	}

	private void highlightPath(GraphPath path) {
		for (ViewGraphElement el : path) {
			el.setFixColor(false);
			el.setColor(Color.green);
			el.setFixColor(true);
		}
	}

	@Override
	public GraphPath find(Graph graph, ViewNode start, ViewNode end) throws NoPathException {
		do {
			nextStep(graph, start, end);
		} while (nextMethod != null);
		GraphPath path = buildPath(start, end);
		return path;
	}

	void findNearestNode() {
		v = -1;
		for (int j = 0; j < g.size(); j++)
			if (!u.get(j) && (v == -1 || d.get(j) < d.get(v)))
				v = j;

		if (d.get(v) == Integer.MAX_VALUE) {
			nextMethod = null;
			return;
		}

		u.set(v, true);
		highliteNearestNode(g.get(v));
		try {
			nextMethod = this.getClass().getDeclaredMethod(NEXT_INCIDENT_EDGE, new Class<?>[0]);
		} catch (NoSuchMethodException e) {
			ReflectionCatcher.noSuchMethodException(e);
		} catch (SecurityException e) {
			ReflectionCatcher.securityException(e);
		}
	}

	private void highliteNearestNode(ViewNode curr) {
		curr.setFixColor(false);
		curr.setColor(Color.magenta);
		curr.setFixColor(true);
	}

	private void revert(ViewGraphElement el) {
		el.setFixColor(false);
		el.setColor(ViewGraphElement.INIT_COLOR);
		el.setFixColor(true);
	}

	private void highight(ViewEdge edge) {
		edge.setFixColor(false);
		edge.setColor(new Color(0xff << 3));
		edge.setFixColor(true);
	}

	private void highight(ViewNode node) {
		if (node != null) {

			int v = g.indexOf(node);
			int rgb = 0xff << 8;
			node.setFixColor(false);
			node.setColor(new Color(rgb));
			node.setFixColor(true);
		}
	}

	private void init(Graph graph) {
		g = new ArrayList<>(graph.getNodes());
		d = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < g.size(); i++) {
			d.add(Double.MAX_VALUE);
		}
		p = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < g.size(); i++) {
			p.add(null);
		}
		u = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < g.size(); i++) {
			u.add(false);
		}
		this.graph = graph;
		isInit = true;
	}

	private void calcNodeColor() {
		double max = (double) Collections.max(d, new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				if ((o1 == Double.MAX_VALUE) || (o2 == Double.MAX_VALUE)) {
					return 0;
				}
				return Double.compare(o1, o2);
			}
		});
		ListIterator<ViewNode> it = g.listIterator();
		while (it.hasNext()) {
			int index = it.nextIndex();
			Color color = Rainbow.getColor(d.get(index) / max);
			ViewNode node = it.next();
			node.setFixColor(false);
			node.setColor(color);
			node.setFixColor(true);
		}
	}

	ViewEdge edge;

	void nextIncidentEdge() {
		if (incidentEdgeIt == null) {
			incidentEdgeIt = graph.incidentEdgeIterator(g.get(v));
		}
		if (edge != null) {
			revert(edge);
		}
		if (incidentEdgeIt.hasNext()) {
			edge = incidentEdgeIt.next();
			highight(edge);
			int to = g.indexOf(edge.getUnnotherNode(g.get(v)));
			int len = 1;
			try {
				len = Integer.parseInt(edge.getContent());
			} catch (NumberFormatException e) {

			}
			if (d.get(v) + len < d.get(to)) {
				d.set(to, d.get(v) + len);
				p.set(to, g.get(v));
			}
			try {
				nextMethod = this.getClass().getDeclaredMethod(NEXT_INCIDENT_EDGE, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}
		} else {
			incidentEdgeIt = null;
			try {
				nextMethod = this.getClass().getDeclaredMethod(NEXT_NODE, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}
		}
	}

	void nextNode() {
		if (v != -1)
			highight(g.get(v));
		calcNodeColor();
		i++;
		if (i < g.size()) {
			try {
				nextMethod = this.getClass().getDeclaredMethod(FIND_NEAREST_NODE, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}
		} else {
			nextMethod = null;
		}
	}

	@Override
	public void nextStep(Graph graph, ViewNode start, ViewNode end) throws NoPathException {
		if (!isInit) {
			init(graph);
			int s = g.indexOf(start);
			d.set(s, 0d);
			i = -1;
			try {
				nextMethod = this.getClass().getDeclaredMethod(NEXT_NODE, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}
		}
		if (nextMethod != null) {
			try {
				nextMethod.setAccessible(true);
				nextMethod.invoke(this, new Object[0]);
			} catch (IllegalAccessException e) {
				ReflectionCatcher.illegalAccessException(e);
			} catch (IllegalArgumentException e) {
				ReflectionCatcher.illegalArgumentException(e);
			} catch (InvocationTargetException e) {
				ReflectionCatcher.invocationTargetException(e);
			}
		} else {
			buildPath(start, end);
		}
	}

	void highliteCurrentNode(ViewNode curr) {
		curr.setFixColor(false);
		curr.setColor(new Color(0xff << 8));
		curr.setFixColor(true);
	}
}
