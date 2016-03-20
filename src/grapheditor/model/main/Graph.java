package grapheditor.model.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;
import grapheditor.view.main.ViewGraph;

public class Graph {

	// -------------------------неориентированный граф----------------
	Map<ViewNode, Set<ViewEdge>> structure;
	ViewGraph view;
	Set<ViewNode> used;
	
	public void used(ViewNode node){
		if(used==null){
			used = new HashSet<>();
		}
		used.add(node);
	}
	public boolean isUsed(ViewNode node){
		if(used==null){
			used = new HashSet<>();
		}
		return used.contains(node);
	}
	/**
	 * @return the nodes
	 */
	public Collection<ViewNode> getNodes() {
		return structure.keySet();
	}
	/**
	 * @return the edges
	 */
	public Collection<ViewEdge> getEdges() {
		List<ViewEdge> out = new ArrayList<>();
		for(Set<ViewEdge> set : structure.values()){
			out.addAll(set);
		}
		return out;
	}
	public void clearUsed(){
		used = null;
	}

	public ViewEdge getEdge(ViewNode n1, ViewNode n2){
		for(ViewEdge edge : structure.get(n1)){
			if(n2.equals(edge.getUnnotherNode(n1)))
				return edge;
		}
		return null;
	}
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

	public static void Algo(ViewNode start, ViewNode end) {

	}
}
