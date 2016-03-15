package frm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public class ClipGraph {
	private Map<Integer, ViewNode> nodes;
	private Set<ViewEdge> edges;

	public ClipGraph() {
		nodes = new HashMap<Integer, ViewNode>();
		edges = new HashSet<ViewEdge>();
	}

	public void addNode(Integer hash, ViewNode n) {
		ViewNode newelem = new ViewNode(n.getX(), n.getY());
		nodes.put(hash, newelem);
	}

	public void addEdge(ViewEdge n) {
		ViewNode n1 = n.getNode1();
		ViewNode n2 = n.getNode2();
		edges.add(new ViewEdge(nodes.get(n1.hashCode()), nodes.get(n2.hashCode())));
	}
	
	public void addEdge(Integer n1, Integer n2) {
		edges.add(new ViewEdge(nodes.get(n1), nodes.get(n2)));
	}

	public Collection<ViewEdge> getEdges() {
		ArrayList<ViewEdge> l = new ArrayList<ViewEdge>();
		for (ViewEdge e : edges) {
			l.add(new ViewEdge(e));
		}
		return l;
	}

	public Collection<ViewNode> getNodes() {
		ArrayList<ViewNode> l = new ArrayList<ViewNode>();
		for (ViewNode e : nodes.values()) {
			l.add(new ViewNode(e));
		}
		return l;
	}

	public boolean isEmpty() {
		return !(edges.isEmpty() && nodes.isEmpty());
	}
}