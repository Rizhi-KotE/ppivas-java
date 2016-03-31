package frm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.events.NotationDeclaration;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class ClipGraph {
	private Map<Integer, ViewNode> nodes;
	private Set<ViewEdge> edges;

	public ClipGraph() {
		nodes = new HashMap<Integer, ViewNode>();
		edges = new HashSet<ViewEdge>();
	}

	public void addNode(Integer hash, ViewNode n) {
		ViewNode newelem = new ViewNode(n);
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

	public Collection<ViewGraphElement> getElements(){
		Collection<ViewGraphElement> out = new ArrayList<>(nodes.size()+edges.size());
		HashMap<ViewNode, ViewNode> newNodes = new HashMap<>();
		Set<ViewEdge> newEdges = new HashSet<>();
		for(ViewEdge edge:edges){
			ViewNode edgesNode1 = edge.getNode1();
			ViewNode edgesNode2 = edge.getNode2();
			ViewNode newEdgesNode1 = newNodes.get(edgesNode1);
			ViewNode newEdgesNode2 = newNodes.get(edgesNode2);
			if(newEdgesNode1 == null){
				newEdgesNode1 = new ViewNode(edgesNode1);
				newNodes.put(edgesNode1, newEdgesNode1);
			}
			if(newEdgesNode2 == null){
				newEdgesNode2 = new ViewNode(edgesNode2);
				newNodes.put(edgesNode2, newEdgesNode2);
			}
			newEdges.add(new ViewEdge(edge, newEdgesNode1, newEdgesNode2));
		}
		out.addAll(newNodes.values());
		out.addAll(newEdges);
		return out;
	}
	public Collection<ViewEdge> getEdges() {

		ArrayList<ViewEdge> list = new ArrayList<>(edges.size());
		for(ViewEdge edge : edges)
				list.add(new ViewEdge(edge));
		return list;
	}

	public Collection<ViewNode> getNodes() {

		ArrayList<ViewNode> list = new ArrayList<>(nodes.size());
		for(ViewNode node : nodes.values())
				list.add(new ViewNode(node));

		return list;
	}

	public boolean isEmpty() {
		return !(edges.isEmpty() && nodes.isEmpty());
	}
}