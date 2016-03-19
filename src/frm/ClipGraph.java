package frm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
			edges.add(n);
	}

	public void addEdge(Integer n1, Integer n2) {
		edges.add(new ViewEdge(nodes.get(n1), nodes.get(n2)));
	}

	public ViewNode getNode(int n) {
		return nodes.get(n);
	}

	public Collection<ViewEdge> getEdges() {

		ArrayList<ViewEdge> list = new ArrayList<>(edges.size());
		for(ViewEdge edge : edges){
			try {
				list.add(edge.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public Collection<ViewNode> getNodes() {

		ArrayList<ViewNode> list = new ArrayList<>(nodes.size());
		for(ViewNode node : nodes.values()){
			try {
				list.add(node.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean isEmpty() {
		return !(edges.isEmpty() && nodes.isEmpty());
	}
}