package model;

import java.util.Collection;
import java.util.LinkedList;

import view.grapheditor.ViewGraph;

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
