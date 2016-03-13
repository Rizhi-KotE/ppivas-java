package frm;

import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class Clipboard {
	private Document doc;
	private static Clipboard clb;

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
		doc = newDoc();
		Element root = doc.createElement("root");
		doc.appendChild(root);

		Element nodes = doc.createElement("nodes");
		doc.appendChild(nodes);

		Element arcs = doc.createElement("arcs");
		doc.appendChild(arcs);

		for (ViewGraphElement el : elements) {
			switch (el.getType()) {
			case "Node": {
				Element n = doc.createElement(el.getType());
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				nodes.appendChild(n);
				break;
			}
			case "Edge": {
				Element n = doc.createElement(el.getType());
				ViewEdge edge = (ViewEdge) el;
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				n.setAttribute("Node1", new Integer(edge.getNode1().hashCode()).toString());
				n.setAttribute("Node2", new Integer(edge.getNode2().hashCode()).toString());
				arcs.appendChild(n);
				break;
			}
			}
		}
	}

	public void pasteFromClipboard(Collection<ViewGraphElement> elements) {
		Element root = doc.createElement("root");
		doc.appendChild(root);

		Element nodes = doc.createElement("nodes");
		doc.appendChild(nodes);

		Element arcs = doc.createElement("arcs");
		doc.appendChild(arcs);

		for (ViewGraphElement el : elements) {
			switch (el.getType()) {
			case "Node": {
				Element n = doc.createElement(el.getType());
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				nodes.appendChild(n);
				break;
			}
			case "Edge": {
				Element n = doc.createElement(el.getType());
				ViewEdge edge = (ViewEdge) el;
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				n.setAttribute("Node1", new Integer(edge.getNode1().hashCode()).toString());
				n.setAttribute("Node2", new Integer(edge.getNode2().hashCode()).toString());
				arcs.appendChild(n);
				break;
			}
			}
		}
	}
}
