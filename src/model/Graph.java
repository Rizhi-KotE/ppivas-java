package model;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

public class Graph extends Observable {

	//private static final double UNIT_DISTANCE = 5;

	private final String IDname = "Graph";

	Set<Node> nodesLocation;

	Set<Node> choose;

	Choosable highlight = null;

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
		/*
		 * if (highlight != null) { highlight.setHighlight(false); highlight =
		 * null; }
		 */
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
