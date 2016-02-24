package model;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import Exception.LoopEdgeException;

public class Graph extends Observable {

	// private static final double UNIT_DISTANCE = 5;

	private final String IDname = "Graph";

	Set<Node> nodesLocation;

	Set<Node> choose;

	Choosable highlight = null;

	private Edge newEdge;

	private Set<Edge> edges;

	public Graph() {
		nodesLocation = new HashSet<Node>();
		choose = new HashSet<Node>();
	}

	// ------------nodes-----------------
	public void addNode(double x, double y) {
		Node node = new Node(x, y);
		nodesLocation.add(node);
		setChanged();
		notifyObservers();
	}

	public Node[] getNodes() {
		Node n[] = null;
		int size = nodesLocation.size();
		if (size != 0) {
			n = new Node[size];
			Iterator<Node> it = nodesLocation.iterator();
			int i = 0;
			while (it.hasNext()) {
				n[i++] = it.next();
			}
		}
		return n;
	}

	// --------------------Edges------------------

	public void createEdge(double x, double y) {
		Choosable choose = chooseWhithoutPaint(x, y);
		if (choose instanceof Node) {
			if (newEdge == null) {
				newEdge = new Edge((Node) choose, null);
				setChanged();
				notifyObservers();
				return;
			}
			try {
				newEdge.addNode((Node) choose);
			} catch (LoopEdgeException e) {
				newEdge = null;
				setChanged();
				notifyObservers();
				return;
			}
			if(edges==null){
				edges = new HashSet<Edge>();
			}
			edges.add(newEdge);
			newEdge = null;
			return;
		}
		newEdge.addPoint(new Point2D.Double(x, y));
		setChanged();
		notifyObservers();
	}

	public Edge[] getEdges() {
		int i = 0;
		if (newEdge != null) {
			i++;
		}
		if (edges != null) {
			i += edges.size();
		}
		Edge s[] = new Edge[i];
		i = 0;
		if (newEdge != null) {
			s[i++] = newEdge;
		}
		if (edges != null) {
			Iterator<Edge> it = edges.iterator();
			while (it.hasNext()) {
				s[i++] = it.next();
			}
		}
		return s;
	}

	// ----------------Drag----------------
	public void dragChoosenElementOn(double dx, double dy) {
		Iterator<Node> it = choose.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			double x = n.getX() + dx;
			double y = n.getY() + dy;
			n.setX(x);
			n.setY(y);
		}
		setChanged();
		notifyObservers();
	}

	// ---------------choose---------------
	public boolean choose(double x, double y) {
		Iterator<Node> it = nodesLocation.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if ((Math.abs(n.getX() - x) < 20) && (Math.abs(n.getY() - y) < 20)) {
				n.setChoosed(true);
				choose.add(n);
				setChanged();
				notifyObservers();
				return true;
			}
		}
		return false;
	}

	public Choosable chooseWhithoutPaint(double x, double y) {
		Iterator<Node> it = nodesLocation.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if ((Math.abs(n.getX() - x) < 20) && (Math.abs(n.getY() - y) < 20)) {
				return n;
			}
		}
		return null;
	}

	public boolean choose(Rectangle rect) {
		double x = rect.getX();
		double y = rect.getY();
		double width = rect.getWidth();
		double height = rect.getHeight();
		Iterator<Node> it = nodesLocation.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			double dx = n.getX();
			double dy = n.getY();
			if (((dx - x) < width) && ((dy - y) < height)) {
				n.setChoosed(true);
				choose.add(n);
				setChanged();
				notifyObservers();
				// return true;
			}
		}
		return false;
	}

	public void highlight(double x, double y) {
		Iterator<Node> it = nodesLocation.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if ((Math.abs(n.getX() - x) < 20) && (Math.abs(n.getY() - y) < 20)) {
				n.setHighlight(true);
				highlight = n;
				setChanged();
				notifyObservers();
				break;
			}
		}
	}

	public void clearChoose() {
		Iterator<Node> it = choose.iterator();
		while (it.hasNext()) {
			Choosable n = it.next();
			n.setChoosed(false);
		}
		choose.clear();
		setChanged();
		notifyObservers();
	}

	public void clearHighlight() {
		if (highlight != null) {
			highlight.setHighlight(false);
			setChanged();
			notifyObservers();
		}
	}

	public String getIDName() {
		return IDname;
	}

}
