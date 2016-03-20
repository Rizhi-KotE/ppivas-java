package grapheditor.algo;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingWorker;

import catchers.ReflectionCatcher;
import grapheditor.model.main.Graph;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class FindMinPathDijkstra implements FindMinPathAlgo {

	private static final String FIND_NEAREST_NODE = "findNearestNode";

	private static final String NEXT_INCIDENT_EDGE = "nextIncidentEdge";

	private static final String NEXT_NODE = "nextNode";

	ArrayList<Integer> d;

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

	private GraphPath buildPath(ViewNode start, ViewNode end) {
		GraphPath path = new MinPath();
		path.add(end);
		ViewNode previos = end;
		while (!previos.equals(start)) {
			ViewNode curr = p.get(g.indexOf(previos));
			if (curr == null) {
				curr = start;
			}
			ViewEdge edge = graph.getEdge(previos, curr);
			if (edge != null) {
				path.add(edge);
			}
			path.add(curr);
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
	public GraphPath find(Graph graph, ViewNode start, ViewNode end) {
		do {
			nextStep(graph, start, end);
		} while (nextMethod != null);
		return buildPath(start, end);
	}

	void findNearestNode() {
		v = -1;
		for (int j = 0; j < g.size(); j++)
			if (!u.get(j) && (v == -1 || d.get(j) < d.get(v)))
				v = j;
		/*
		 * if (d.get(v) == Integer.MAX_VALUE) { nextMethod = null; return; }
		 */
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

	private void highight(ViewEdge edge) {

	}

	private void highight(ViewNode node) {
		if (node != null) {

			int v = g.indexOf(node);
			Color color = Color.cyan;
			int rgb = color.getRGB() + 255 * d.get(v);
			node.setFixColor(false);
			node.setColor(new Color(rgb));
			node.setFixColor(true);
		}
	}

	private void init(Graph graph) {
		g = new ArrayList<>(graph.getNodes());
		d = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < g.size(); i++) {
			d.add(Integer.MAX_VALUE);
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

	void nextIncidentEdge() {
		if (incidentEdgeIt == null) {
			incidentEdgeIt = graph.incidentEdgeIterator(g.get(v));
		}
		if (incidentEdgeIt.hasNext()) {
			ViewEdge edge = incidentEdgeIt.next();
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
			highight(g.get(to));
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
		if (i != -1)
			highight(g.get(i));
		i++;
		if (i < g.size()) {
			try {
				nextMethod = this.getClass().getDeclaredMethod(FIND_NEAREST_NODE, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}
			highliteCurrentNode(g.get(i));
		} else {
			nextMethod = null;
		}
	}

	@Override
	public void nextStep(Graph graph, ViewNode start, ViewNode end) {
		if (!isInit) {
			init(graph);
			int s = g.indexOf(start);
			d.set(s, 0);
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
		curr.setColor(Color.pink);
		curr.setFixColor(true);
		ViewNode path = p.get(g.indexOf(curr));
		highight(path);
	}
}
