package grapheditor.model.main;

import java.util.Collection;
import java.util.LinkedList;

import grapheditor.model.elements.Edge;
import grapheditor.model.elements.Node;
import grapheditor.view.main.ViewGraph;

public class Graph {
	Collection<Node> nodes;
	Collection<Edge> edges;

	ViewGraph view;

	private Graph() {
		nodes = new LinkedList<Node>();
		edges = new LinkedList<Edge>();
	}

	public Graph(ViewGraph anView) {
		this();
		view = anView;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public void addNode(Node n) {
		nodes.add(n);
	}
}
