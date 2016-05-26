package grapheditor.model.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

	public void used(ViewNode node) {
		if (used == null) {
			used = new HashSet<>();
		}
		used.add(node);
	}

	public boolean isUsed(ViewNode node) {
		if (used == null) {
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
		for (Set<ViewEdge> set : structure.values()) {
			out.addAll(set);
		}
		return out;
	}

	public void clearUsed() {
		used = null;
	}

	public ViewEdge getEdge(ViewNode n1, ViewNode n2) {
		for (ViewEdge edge : structure.get(n1)) {
			if (n2.equals(edge.getUnnotherNode(n1)))
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

	public ArrayList<ArrayList<Integer>> createAdjustmenMatrix() {
		ArrayList<ViewNode> nodes = new ArrayList<>(structure.keySet());
		Map<ViewNode, Integer> nodesToIntMap = new HashMap<>();
		for (int i = 0; i < nodes.size(); i++) {
			nodesToIntMap.put(nodes.get(i), i);
		}
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		Iterator<ViewNode> it = nodes.iterator();
		while (it.hasNext()) {
			ViewNode currNode = it.next();
			Iterator<ViewNode> adjustNodeIt = adjustNodeIterator(currNode);
			int[] udjustRow = new int[nodes.size()];
			while (adjustNodeIt.hasNext()) {
				ViewNode adjustNode = adjustNodeIt.next();
				udjustRow[nodesToIntMap.get(adjustNode)] = 1;
			}
			ArrayList<Integer> listRow = new ArrayList<>(udjustRow.length);
			for (Integer i : udjustRow) {
				listRow.add(i);
			}
			matrix.add(listRow);
		}
		return matrix;
	}
	
	public ArrayList<ArrayList<Integer>> createAdjustmenList() {
		ArrayList<ViewNode> nodes = new ArrayList<>(structure.keySet());
		Map<ViewNode, Integer> nodesToIntMap = new HashMap<>();
		for (int i = 0; i < nodes.size(); i++) {
			nodesToIntMap.put(nodes.get(i), i);
		}
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		Iterator<ViewNode> it = nodes.iterator();
		while (it.hasNext()) {
			ViewNode currNode = it.next();
			Iterator<ViewNode> adjustNodeIt = adjustNodeIterator(currNode);
			ArrayList<Integer> listRow = new ArrayList<>();
			while (adjustNodeIt.hasNext()) {
				ViewNode adjustNode = adjustNodeIt.next();
				listRow.add(nodesToIntMap.get(adjustNode));
			}
			list.add(listRow);
		}
		return list;
	}

	public ArrayList<ArrayList<Integer>> createIncidentMatrix() {
		ArrayList<ViewNode> nodes = new ArrayList<>(structure.keySet());
		Map<ViewNode, Integer> nodesToIntMap = new HashMap<>();
		for (int i = 0; i < nodes.size(); i++) {
			nodesToIntMap.put(nodes.get(i), i);
		}
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		Iterator<ViewNode> it = nodes.iterator();
		while (it.hasNext()) {
			ViewNode firstNode = it.next();
			Iterator<ViewEdge> incidentEdgeIt = structure.get(firstNode).iterator();
			while (incidentEdgeIt.hasNext()) {
				ViewEdge edge = incidentEdgeIt.next();
				int[] incidentRow = new int[nodes.size()];
				switch (edge.getEdgeType()) {
				case ViewEdge.ORIENT: {
					ViewNode node1 = edge.getNode1();
					ViewNode node2 = edge.getNode2();
					incidentRow[nodesToIntMap.get(node1)] = -1;
					incidentRow[nodesToIntMap.get(node2)] = 1;
					break;
				}
				default: {
					ViewNode node1 = edge.getNode1();
					ViewNode node2 = edge.getNode2();
					incidentRow[nodesToIntMap.get(node1)] = 1;
					incidentRow[nodesToIntMap.get(node2)] = 1;
				}
				}
				ArrayList<Integer> incidentList = new ArrayList<>(nodes.size());
				for (int num : incidentRow) {
					incidentList.add(num);
				}
				matrix.add(incidentList);
			}
		}
		return matrix;
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

	public AdjustNodeIterator adjustNodeIterator(ViewNode n) {
		return new AdjustNodeIterator(n);
	}

	private class AdjustNodeIterator implements Iterator<ViewNode> {

		private List<ViewEdge> edges;
		private ViewNode node;
		private ViewEdge nextEdge;
		private ListIterator<ViewEdge> it;

		public AdjustNodeIterator(ViewNode n) {
			edges = new ArrayList(structure.get(n));
			it = edges.listIterator();
			node = n;
		}

		@Override
		public boolean hasNext() {
			if (!it.hasNext()) {
				return it.hasNext();
			}
			nextEdge = it.next();
			while (!nextEdge.hasUnnotherNode(node)) {
				if (!it.hasNext()) {
					return it.hasNext();
				}
				nextEdge = it.next();
			}
			it.previous();
			return it.hasNext();
		}

		@Override
		public ViewNode next() {
			return it.next().getUnnotherNode(node);
		}
	}
}
