package view.grapheditor.elements;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import Exception.LoopEdgeException;
import frm.Counter;

public class Edge extends Observable implements GraphElement {
	private final String name = "Edge";
	// ------------Fields------------------
	private Node node1;
	private Node node2;

	private boolean choosed;
	private boolean highLight;
	private int hash;
	private List<Point2D> extraPoints;
	private Point2D lastPoint;

	// --------------Constructors--------
	private Edge() {
		hash = Counter.getNextNum(this);
	}

	public Edge(Node n1, Node n2) {
		this();
		node1 = n1;
		node2 = n2;
	}

	// ----------------Methods------------
	public void addNode(Node n) throws LoopEdgeException {
		if (node1 == null) {
			node1 = n;
		} else {
			if (node1.equals(n)) {
				throw new LoopEdgeException();
			}
			node2 = n;
		}
	}

	public void addPoint(Point2D p) {
		if (extraPoints != null)
			extraPoints.add(p);
	}
	// ------------Seters & Getters-------

	public Node getNode1() {
		return node1;
	}

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

	public void setChoosed(boolean is) {
		choosed = is;

	}

	public boolean isHighlight() {
		return highLight;
	}

	public void setHighlight(boolean is) {
		highLight = is;

	}

	public Shape getShape(float radius, float scale) {
		LinkedList<Point2D> points = new LinkedList<Point2D>(extraPoints);
		if (node1 != null)
			points.addFirst(node1.getPoint());
		if (lastPoint != null) {
			points.addLast(lastPoint);
		}
		if (node2 != null)
			points.addLast(lastPoint);
		Iterator<Point2D> it = points.iterator();
		Point2D first = null;
		if (it.hasNext())
			first = it.next();
		GeneralPath s = new GeneralPath();
		s.moveTo(first.getX(), first.getY());
		Point2D second = null;
		while (it.hasNext()) {
			s.lineTo(second.getX(), second.getY());
			first = second;
		}
		/*
		 * Node n1 = null; Node n2 = null;
		 * 
		 * Point2D p = null; if (node1 != null) { n1 = node1; } if (n1 == null)
		 * { n1 = node2; } else { n2 = node2; } if (extraPoints != null) {
		 * Iterator<Point2D> it = extraPoints.iterator(); if (it.hasNext()) { p
		 * = it.next(); Point2D start = calcRadiusPoint(n1.getPoint(), p,
		 * radius); s.moveTo(start.getX(), start.getY()); } else { if (node2 !=
		 * null) { Point2D start = calcRadiusPoint(n1.getPoint(), n2.getPoint(),
		 * radius); Point2D end = calcRadiusPoint(n2.getPoint(), n1.getPoint(),
		 * radius); s.moveTo(start.getX() * scale, start.getY() * scale);
		 * s.lineTo(end.getX() * scale, end.getY() * scale); return s; } } while
		 * (it.hasNext()) { p = it.next(); s.lineTo(p.getX() * scale, p.getY() *
		 * scale); } } else { if (node2 != null) { Point2D start =
		 * calcRadiusPoint(n1.getPoint(), n2.getPoint(), radius); Point2D end =
		 * calcRadiusPoint(n2.getPoint(), n1.getPoint(), radius);
		 * s.moveTo(start.getX() * scale, start.getY() * scale);
		 * s.lineTo(end.getX() * scale, end.getY() * scale); return s; } }
		 */
		return s;
	}

	public Shape getShape() {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		if (extraPoints != null) {
			points.addAll(extraPoints);
		}
		if (node1 != null)
			points.addFirst(node1.getPoint());
		if (lastPoint != null) {
			points.addLast(lastPoint);
		}
		if (node2 != null)
			points.addLast(lastPoint);
		Iterator<Point2D> it = points.iterator();
		Point2D first = null;
		if (it.hasNext())
			first = it.next();
		GeneralPath s = new GeneralPath();
		s.moveTo(first.getX(), first.getY());
		Point2D second = null;
		while (it.hasNext()) {
			second = it.next();
			s.lineTo(second.getX(), second.getY());
			first = second;
		} /*
			 * GeneralPath s = new GeneralPath(); Node n1 = null; Node n2 =
			 * null; if (node1 != null) { n1 = node1; } if (n1 == null) { n1 =
			 * node2; } else { n2 = node2; } s.moveTo(n1.getX(), n1.getY()); if
			 * (extraPoints != null) { Iterator<Point2D> it =
			 * extraPoints.iterator(); while (it.hasNext()) { Point2D p =
			 * it.next(); s.lineTo(p.getX(), p.getY()); } } if (n2 != null) {
			 * s.lineTo(n2.getX(), n2.getY()); }
			 */
		return s;
	}

	private Point2D calcRadiusPoint(Point2D center, Point2D B, double radius) {
		double x1 = center.getX();
		double x2 = B.getX();
		double y1 = center.getY();
		double y2 = B.getY();

		double ABLen = Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5);
		double coef = radius / ABLen;

		double x12 = x2 - x1;
		double y12 = y2 - y1;

		double x3 = x1 + coef * x12;
		double y3 = y1 + coef * y12;
		return new Point2D.Double(x3, y3);
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

	@Override
	public String getName() {
		return name;
	}

	public void setLastPoint(double x, double y) {
		lastPoint = new Point2D.Double(x, y);
		setChanged();
		notifyObservers();
	}

}
