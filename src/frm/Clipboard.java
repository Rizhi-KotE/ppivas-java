package frm;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class Clipboard {
	private static Clipboard clb;
	private ClipGraph clipGraph;

	private Clipboard() {

	}

	public static Clipboard getInstance() {
		if (clb == null) {
			clb = new Clipboard();
		}
		return clb;
	}

	public void addToClipboard(Collection<ViewGraphElement> elements) {
		clipGraph = new ClipGraph();
		List<ViewEdge> edgeList = new LinkedList<ViewEdge>();
		for (ViewGraphElement el : elements) {
			switch (el.getType()) {
			case "Node": {
				ViewNode n = (ViewNode) el;
				clipGraph.addNode(n.hashCode(), n);
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
			clipGraph.addEdge(n);
		}
	}

	public ClipGraph pasteFromClipboard() {
		return clipGraph;
	}

	public boolean isEmpty() {
		return clipGraph != null ? clipGraph.isEmpty() : false;
	}
}