package grapheditor.view.main;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import Exception.LoopEdgeException;
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
		ShapedComponent s = new ShapedComponent(n);
		panel.add(s);
		n.addObserver(s);
		addNode(n);
	}

	public void addNode(ViewNode n) {
		// TODO
		nodes.add(n);
	}

	public void setCurrentNode(Component component) {
		if (component.getName().equals("ShapedComponent")) {
			ViewGraphElement el = ((ShapedComponent) component).getElement();
			if (el.getName().equals("Node")) {
				currentNode = (ViewNode) el;
			}
		}
	}

	public void removeNode(ViewNode n) {
		// TODO
		nodes.remove(n);
	}

	// --------------------Edges------------------
	private ViewEdge newEdge;

	public void addEdge() {
		if (currentNode != null) {
			if (newEdge == null) {
				newEdge = new ViewEdge(null, null);
				addEdge(newEdge);
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
				newEdge = null;
				currentNode = null;
				return;
			}
		}
		if (newEdge != null) {
			if (newEdge.isComplete())
				newEdge = null;
		}
		currentNode = null;
	}

	public void addEdge(ViewEdge e) {
		if (currentNode != null) {
			if (newEdge == null) {
				newEdge = new ViewEdge(null, null);
		
				ShapedComponent s = new ShapedComponent(newEdge);
				newEdge.addObserver(s);
				panel.add(s);
			}
			completeEdge(newEdge);
		}
		if (newEdge != null) {
			try {
				newEdge.addNode(currentNode);
			} catch (LoopEdgeException e1) {
				deleteEdge(newEdge);
				newEdge = null;
				currentNode = null;
				return;
			}
		}
		if (newEdge != null) {
			if (newEdge.isComplete())
				newEdge = null;
		}
		currentNode = null;
	}

	private void completeEdge(ViewEdge e) {
		edges.add(e);

	}

	public void deleteEdge(ViewEdge e) {
		// TODO
		edges.remove(e);
	}

	public void setExtraEdgePoint(int x, int y) {
		if (newEdge != null) {
			newEdge.setLastPoint(x, y);
			//panel.revalidate();
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

	public boolean choose(Rectangle rect) {
		Iterator<ViewNode> it = nodes.iterator();
		while (it.hasNext()) {
			ViewNode n = it.next();
			if (n.getShape().intersects(rect)) {
				n.setChoosed(true);
				choose.add(n);
			}
		}
		Iterator<ViewEdge> et = edges.iterator();
		while (et.hasNext()) {
			ViewEdge n = et.next();
			if (n.getShape().intersects(rect)) {
				n.setChoosed(true);
				choose.add(n);
			}
		}
		return false;
	}

	public void choose(ShapedComponent E) {
		if (choose == null) {
			choose = new HashSet<ViewGraphElement>();
		}
		if (E.getElement().isChoosed() == false) {
			E.getElement().setChoosed(true);
			choose.add(E.getElement());
		}
	}

	public void clearChoose() {
		if (choose == null) {
			choose = new HashSet<ViewGraphElement>();
		}
		Iterator<ViewGraphElement> it = choose.iterator();
		while (it.hasNext()) {
			ViewGraphElement s = it.next();
			s.setChoosed(false);
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
		setChanged();
		notifyObservers();
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
}
