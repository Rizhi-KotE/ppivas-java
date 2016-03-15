package grapheditor.model.main;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;
import grapheditor.view.main.ViewGraph;

public class Graph {
	Collection<ViewNode> nodes;
	Collection<ViewEdge> edges;
	// -------------------------неориентированный граф----------------
	Map<ViewNode, Set<ViewEdge>> structure;
	ViewGraph view;

	private Graph() {
		structure = new HashMap<ViewNode, Set<ViewEdge>>();
	}

	public Graph(ViewGraph anView) {
		this();
		view = anView;
	}

	public void addEdge(ViewEdge e) {
		ViewNode n1 = e.getNode1();
		ViewNode n2 = e.getNode2();
		addNode(n1);
		addNode(n2);
		structure.get(n1).add(e);
		structure.get(n2).add(e);
	}

	public void addNode(ViewNode n) {
		Set<ViewEdge> set = structure.get(n);
		if (set == null) {
			set = new HashSet<ViewEdge>();
			structure.put(n, set);
		}
	}

	public void deleteEdge(ViewEdge e) {
		ViewNode n1 = e.getNode1();
		ViewNode n2 = e.getNode2();
		addNode(n1);
		addNode(n2);
		structure.get(n1).remove(e);
		structure.get(n2).remove(e);
	}

	public void deleteNode(ViewNode n) {
		structure.remove(n);
	}

	public IncidentEdgeIterator incidentEdgeIterator(ViewNode n) {
		return new IncidentEdgeIterator(n);
	}

	private class IncidentEdgeIterator implements Iterator<ViewEdge> {

		Set<ViewEdge> edges;
		Iterator<ViewEdge> it;

		public IncidentEdgeIterator(ViewNode n) {
			edges = structure.get(n);
			it = edges.iterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public ViewEdge next() {
			return it.next();
		}

	}
}
