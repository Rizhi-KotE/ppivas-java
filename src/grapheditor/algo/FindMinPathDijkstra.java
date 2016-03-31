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

	ArrayList<Double> distanceToNodes;

	ArrayList<ViewNode> nodesArray, parent;
	private Graph graphModel;
	private int numberOfIteration;

	private Iterator<ViewEdge> incidentEdgeIt;

	boolean isInit = false;
	Method nextMethod;
	private int startNodeIndex;
	private int toNodeIndex;
	ArrayList<Boolean> usedArray;
	private int currentNodeIndex;

	public FindMinPathDijkstra() {
	}

	private GraphPath buildPath(ViewNode start, ViewNode end) throws NoPathException {
		GraphPath path = new MinPath();
		if (!path.add(end))
			throw new NoPathException();
		ViewNode previos = end;
		while (!previos.equals(start)) {
			ViewNode curr = parent.get(nodesArray.indexOf(previos));
			if (curr == null) {
				curr = start;
			}
			ViewEdge edge = graphModel.getEdge(previos, curr);
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
		currentNodeIndex = -1;
		for (int nodeIndex = 0; nodeIndex < nodesArray.size(); nodeIndex++)
			if (!usedArray.get(nodeIndex) && (currentNodeIndex == -1 || distanceToNodes.get(nodeIndex) < distanceToNodes.get(currentNodeIndex)))
				currentNodeIndex = nodeIndex;

		if (distanceToNodes.get(currentNodeIndex) == Integer.MAX_VALUE) {
			nextMethod = null;
			return;
		}

		usedArray.set(currentNodeIndex, true);
		highliteNearestNode(nodesArray.get(currentNodeIndex));
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
			int rgbColor = 0xff << 8;
			node.setFixColor(false);
			node.setColor(new Color(rgbColor));
			node.setFixColor(true);
		}
	}

	private void init(Graph graph) {
		nodesArray = new ArrayList<>(graph.getNodes());
		distanceToNodes = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < nodesArray.size(); i++) {
			distanceToNodes.add(Double.MAX_VALUE);
		}
		parent = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < nodesArray.size(); i++) {
			parent.add(null);
		}
		usedArray = new ArrayList<>(graph.getNodes().size());
		for (int i = 0; i < nodesArray.size(); i++) {
			usedArray.add(false);
		}
		this.graphModel = graph;
		isInit = true;
	}

	private void calcNodeColor() {
		double max = (double) Collections.max(distanceToNodes, new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				if ((o1 == Double.MAX_VALUE) || (o2 == Double.MAX_VALUE)) {
					return 0;
				}
				return Double.compare(o1, o2);
			}
		});
		ListIterator<ViewNode> nodeIt = nodesArray.listIterator();
		while (nodeIt.hasNext()) {
			int index = nodeIt.nextIndex();
			Color color = Rainbow.getColor(distanceToNodes.get(index) / max);
			ViewNode node = nodeIt.next();
			node.setFixColor(false);
			node.setColor(color);
			node.setFixColor(true);
		}
	}

	ViewEdge edge;

	void nextIncidentEdge() {
		if (incidentEdgeIt == null) {
			incidentEdgeIt = graphModel.incidentEdgeIterator(nodesArray.get(currentNodeIndex));
		}
		if (edge != null) {
			revert(edge);
		}
		if (incidentEdgeIt.hasNext()) {
			edge = incidentEdgeIt.next();
			highight(edge);
			int to = nodesArray.indexOf(edge.getUnnotherNode(nodesArray.get(currentNodeIndex)));
			int len = 1;
			try {
				len = Integer.parseInt(edge.getContent());
			} catch (NumberFormatException e) {

			}
			if (distanceToNodes.get(currentNodeIndex) + len < distanceToNodes.get(to)) {
				distanceToNodes.set(to, distanceToNodes.get(currentNodeIndex) + len);
				parent.set(to, nodesArray.get(currentNodeIndex));
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
		if (currentNodeIndex != -1)
			highight(nodesArray.get(currentNodeIndex));
		calcNodeColor();
		numberOfIteration++;
		if (numberOfIteration < nodesArray.size()) {
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
			int s = nodesArray.indexOf(start);
			distanceToNodes.set(s, 0d);
			numberOfIteration = -1;
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
