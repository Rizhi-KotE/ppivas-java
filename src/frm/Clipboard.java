package frm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class Clipboard {
	private Document doc;
	private static Clipboard clb;
	private Graph graph;

	private Clipboard() {
		doc = newDoc();
	}

	public static Clipboard getInstance() {
		if (clb == null) {
			clb = new Clipboard();
		}
		return clb;
	}

	private Document newDoc() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document doc = null;
		try {
			doc = factory.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public void addToClipboard(Collection<ViewGraphElement> elements) {
		graph = new Graph();
		List<ViewEdge> edgeList = new LinkedList<ViewEdge>();
		for (ViewGraphElement el : elements) {
			switch (el.getType()) {
			case "Node": {
				ViewNode n = (ViewNode) el;
				graph.addNode(n);
			}
				break;
			case "Edge": {
				ViewEdge n = (ViewEdge) el;
				edgeList.add(n);
			}
				break;
			}
		}
		for (ViewEdge n : edgeList) {
			graph.addEdge(n);
		}
		/*
		 * doc = newDoc(); Element root = doc.createElement("root");
		 * doc.appendChild(root);
		 * 
		 * Element nodes = doc.createElement("nodes"); doc.appendChild(nodes);
		 * 
		 * Element arcs = doc.createElement("arcs"); doc.appendChild(arcs);
		 * 
		 * for (ViewGraphElement el : elements) { switch (el.getType()) { case
		 * "Node": { Element n = doc.createElement(el.getType());
		 * n.setAttribute("Content", el.getContent()); n.setAttribute("Hash",
		 * new Integer(el.hashCode()).toString()); nodes.appendChild(n); break;
		 * } case "Edge": { Element n = doc.createElement(el.getType());
		 * ViewEdge edge = (ViewEdge) el; n.setAttribute("Content",
		 * el.getContent()); n.setAttribute("Hash", new
		 * Integer(el.hashCode()).toString()); n.setAttribute("Node1", new
		 * Integer(edge.getNode1().hashCode()).toString());
		 * n.setAttribute("Node2", new
		 * Integer(edge.getNode2().hashCode()).toString()); arcs.appendChild(n);
		 * break; } } }
		 */ }

	public Graph pasteFromClipboard() {
		return graph;
	}

	public boolean isEmpty() {
		return graph != null ? graph.isEmpty() : false;
	}

	public class Graph {
		private Map<Integer, ViewNode> nodes;
		private Set<ViewEdge> edges;

		public Graph() {
			nodes = new HashMap<Integer, ViewNode>();
			edges = new HashSet<ViewEdge>();
		}

		public void addNode(ViewNode n) {
			ViewNode newelem = new ViewNode(n.getX(), n.getY());
			nodes.put(n.hashCode(), newelem);
		}

		public void addEdge(ViewEdge n) {
			ViewNode n1 = n.getNode1();
			ViewNode n2 = n.getNode2();
			edges.add(new ViewEdge(nodes.get(n1.hashCode()), nodes.get(n2.hashCode())));
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
}