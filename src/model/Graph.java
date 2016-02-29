package model;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import Exception.LoopEdgeException;

public class Graph extends Observable {

	private final String IDname = "Graph";

	Set<Node> nodes;

	Set<Node> choose;

	Choosable highlight = null;

	private Edge newEdge;
	
	private Set<Edge> edges;

	public Graph() {
		nodes = new HashSet<Node>();
		choose = new HashSet<Node>();
		edges = new HashSet<Edge>();
	}

	// ------------nodes-----------------
	public void addNode(double x, double y) {
		Node node = new Node(x, y);
		nodes.add(node);
		setChanged();
		notifyObservers();
	}
	
	public void addNode(Node n){
		nodes.add(n);
	}

	public void removeNode(Node n){
		nodes.remove(n);
	}
	public Node[] getNodes() {
		Node n[] = null;
		int size = nodes.size();
		if (size != 0) {
			n = new Node[size];
			Iterator<Node> it = nodes.iterator();
			int i = 0;
			while (it.hasNext()) {
				n[i++] = it.next();
			}
		}
		return n;
	}

	// --------------------Edges------------------

	public void addEdge(Edge e){
		edges.add(e);
	}
	public void deleteEdge(Edge e){
		edges.remove(e);
	}
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
		Iterator<Node> it = nodes.iterator();
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
		Iterator<Node> it = nodes.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if ((Math.abs(n.getX() - x) < 20) && (Math.abs(n.getY() - y) < 20)) {
				return null;
			}
		}
		return null;
	}

	public boolean choose(Rectangle rect) {
		Iterator<Node> it = nodes.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if(n.getShape().intersects(rect)){
				n.setChoosed(true);
			}
		}
		Iterator<Edge> et = edges.iterator();
		while (et.hasNext()) {
			Edge n = et.next();
			if(n.getShape().intersects(rect)){
				n.setChoosed(true);
			}
		}
		return false;
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
