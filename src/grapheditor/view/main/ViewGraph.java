package grapheditor.view.main;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import Exception.LoopEdgeException;
import frm.ClipGraph;
import frm.Clipboard;
import frm.SaveGraph;
import grapheditor.model.main.Graph;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class ViewGraph extends Observable {

	private final String IDname = "ViewGraph";

	private PaintingPanel panel;
	private Graph graph;

	private Set<ViewEdge> edges;
	private Collection<ViewNode> nodes;

	private ViewGraph() {
		nodes = new HashSet<ViewNode>();
		choose = new HashSet<ViewGraphElement>();
		edges = new HashSet<ViewEdge>();
		graph = new Graph(this);
	}

	public ViewGraph(PaintingPanel p) {
		this();
		panel = p;
	}

	private ViewNode currentNode;

	// ---------------------node--------------------------

	public void addNode(double x, double y) {
		ViewNode n = new ViewNode(x, y);
		addNode(n);

	}

	public void addNode(ViewNode n) {
		ShapedComponent s = new ShapedComponent(n);
		n.addObserver(s);
		panel.add(s);
		nodes.add(n);
		panel.validate();
		graph.addNode(n);
	}

	public void setCurrentNode(Component component) {
		if (component.getName().equals("ShapedComponent")) {
			ViewGraphElement el = ((ShapedComponent) component).getElement();
			if (el.getType().equals("Node")) {
				currentNode = (ViewNode) el;
			}
		}
	}

	public void removeNode(ViewNode n) {
		nodes.remove(n);
		choose.remove(n);
	}

	// --------------------Edges------------------
	private ViewEdge newEdge;

	public void addEdge() {
		if (currentNode != null) {
			if (newEdge == null) {
				newEdge = new ViewEdge(null, null);
				ShapedComponent s = new ShapedComponent(newEdge);
				newEdge.addObserver(s);
				panel.add(s);
			}

		}
		if (newEdge != null) {
			try {
				newEdge.addNode(currentNode);
			} catch (LoopEdgeException e) {
				deleteEdge(newEdge);
				newEdge.setDeleted(true);
				currentNode = null;
				return;
			}
		}
		if (newEdge != null) {
			if (newEdge.isComplete()) {
				completeEdge(newEdge);
				newEdge = null;
			}
		}
		currentNode = null;
	}

	public void addEdge(ViewEdge e) {
		if (e.isComplete()) {
			ShapedComponent s = new ShapedComponent(e);
			e.addObserver(s);
			panel.add(s);
			edges.add(e);

			panel.validate();
			graph.addEdge(e);
		}
	}

	private void completeEdge(ViewEdge e) {
		edges.add(e);
		graph.addEdge(e);
	}

	public void setExtraEdgePoint(int x, int y) {
		if (newEdge != null) {
			newEdge.setLastPoint(x, y);
			// panel.revalidate();
		}
	}

	public void fixEdgePoint() {
		if (currentNode != null) {
			addEdge();
		} else if (newEdge != null) {
			newEdge.fixLastPoint();
		}
		panel.revalidate();
	}

	// ---------------choose---------------

	Set<ViewGraphElement> choose;

	private boolean pressButton = false;

	private void choose(ViewGraphElement e, boolean b) {
		if (b) {
			e.setChoosed(b);
			choose.add(e);
		} else {
			e.setChoosed(b);
			choose.remove(e);
		}
		revalidateActions();
	}

	public boolean choose(Rectangle rect) {
		pressButton = true;
		Iterator<ViewNode> it = nodes.iterator();
		while (it.hasNext()) {
			ViewNode n = it.next();
			if (n.getShape().intersects(rect)) {
				choose(n, true);
			}
		}
		Iterator<ViewEdge> et = edges.iterator();
		while (et.hasNext()) {
			ViewEdge n = et.next();
			if (n.getShape().intersects(rect)) {
				choose(n, true);
			}
		}
		pressButton = false;
		return false;
	}

	public void choose(ShapedComponent E) {
		if (!pressButton) {
			clearChoose();
		}
		if (E.getElement().isChoosed() == false) {
			choose(E.getElement(), true);
		}
	}

	public void clearChoose() {
		if (choose == null) {
			choose = new HashSet<ViewGraphElement>();
		}
		Object[] it = choose.toArray();
		for (Object s : it) {
			choose((ViewGraphElement) s, false);
		}
		choose.clear();
	}

	// ----------------Drag----------------
	public void dragChoosenElementOn(double dx, double dy) {
		Iterator<ViewGraphElement> it = choose.iterator();
		while (it.hasNext()) {
			ViewGraphElement n = it.next();
			n.drag(dx, dy);
		}
	}

	public String getIDName() {
		return IDname;
	}

	// -----------elements-------------

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	// -----------names-----------------

	public void addName(String s) {
		Iterator<ViewGraphElement> it = choose.iterator();
		while (it.hasNext()) {
			ViewGraphElement e = it.next();
			e.setContent(s);
		}
	}

	// -----------------------delete-------
	public void delete() {
		Object[] el = choose.toArray();
		for (Object a : el) {
			if (a.getClass().getName() == ViewNode.class.getName()) {
				ViewNode n = (ViewNode) a;
				deleteNode(n);

			}
			if (a.getClass().getName() == ViewEdge.class.getName()) {
				ViewEdge n = (ViewEdge) a;
				deleteEdge(n);
				n.setDeleted(true);
			}

		}
		revalidateActions();
		choose.clear();
	}

	private void revalidateActions() {
		if (choose.size() == 1) {
			panel.getActionEvent("IdentifierAction").setEnabled(true);
		} else {
			panel.getActionEvent("IdentifierAction").setEnabled(false);
		}
		if (choose.size() > 0) {
			panel.getActionEvent(PaintingPanel.DELETE_ACTION).setEnabled(true);
			panel.getActionEvent(PaintingPanel.COPY_ACTION).setEnabled(true);
		} else {
			panel.getActionEvent(PaintingPanel.DELETE_ACTION).setEnabled(false);
			panel.getActionEvent(PaintingPanel.COPY_ACTION).setEnabled(false);

		}
		if (Clipboard.getInstance().isEmpty()) {
			panel.getActionEvent(PaintingPanel.PASTE_ACTION).setEnabled(true);
		} else {
			panel.getActionEvent(PaintingPanel.PASTE_ACTION).setEnabled(false);
		}
	}

	private void deleteNode(ViewNode a) {
		Iterator<ViewEdge> it = graph.incidentEdgeIterator(a);
		while (it.hasNext()) {
			ViewEdge e = it.next();
			deleteEdge(e);
		}
		graph.deleteNode(a);
		nodes.remove(a);
		a.setDeleted(true);

	}

	public void deleteEdge(ViewEdge e) {
		edges.remove(e);
		e.setDeleted(true);
	}

	// -------------------corrections-------
	public void copy() {
		Clipboard.getInstance().addToClipboard(choose);
		revalidateActions();
	}

	public void paste() {
		ClipGraph g = Clipboard.getInstance().pasteFromClipboard();
		paste(g);
	}
	
	public void paste(ClipGraph g) {
		for (ViewNode e : g.getNodes()) {
			addNode(e);
		}
		for (ViewEdge e : g.getEdges()) {
			addEdge(e);
		}
	}

	public void cut() {
		revalidateActions();
	}

	public void changeListener() {
		clearChoose();
		currentNode = null;
		if (newEdge != null) {
			newEdge.setDeleted(true);
			newEdge = null;
		}
	}

	// -----------------------------------save-open----------
	public void save(String s){
		SaveGraph save = new SaveGraph();
		Collection<ViewGraphElement> elements = new ArrayList<ViewGraphElement>(nodes);
		elements.addAll(edges);
		save.save(s, elements);
	}
	
	public void open(String s){
		SaveGraph open = new SaveGraph();
		Collection<ViewGraphElement> elements = new ArrayList<ViewGraphElement>(nodes);
		elements.addAll(edges);
		ClipGraph graph = open.loadGraph(s);
		paste(graph);
	}
}
