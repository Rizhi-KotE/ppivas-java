package model;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

import frm.Counter;

public class Edge implements Choosable {
	// ------------Fields------------------
	private Node node1;
	private Node node2;

	private boolean choosed;
	private boolean highLight;

	private int hash;

	private List<Point2D> extraPoints;

	// --------------Constructors--------
	private Edge() {
		hash = Counter.getNextNum(this);
	}

	private Edge(Node n1, Node n2) {
		this();
		node1 = n1;
		node2 = n2;
	}

	public Node getNode1() {
		return node1;
	}

	// ----------------Methods------------
	// ------------Seters & Getters-------

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	// ---------------Choosed------------
	public boolean isChoosed() {
		return choosed;
	}

	@Override
	public void setChoosed(boolean is) {
		choosed = is;

	}

	@Override
	public boolean isHighlight() {
		return highLight;
	}

	@Override
	public void setHighlight(boolean is) {
		highLight = is;

	}

	public Shape getShape(float scale) {
		GeneralPath s = new GeneralPath();
		Node n1 = null;
		Node n2 = null;
		if (node1 != null) {
			n1 = node1;
		}
		if (n1 == null) {
			n1 = node2;
		} else {
			n2 = node2;
		}
		s.moveTo(n1.getX() * scale, n1.getY() * scale);
		if (extraPoints != null) {
			Iterator<Point2D> it = extraPoints.iterator();
			while (it.hasNext()) {
				Point2D p = it.next();
				s.lineTo(p.getX() * scale, p.getY() * scale);
			}
		}
		if (n2 != null) {
			s.lineTo(n2.getX() * scale, n2.getY() * scale);
		}
		return s;
	}

	// --------------For sets--------------
	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bl = obj instanceof Edge;
		return bl && (hashCode() == obj.hashCode());
	}

}
