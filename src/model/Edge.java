package model;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import Exception.LoopEdgeException;
import frm.Counter;

public class Edge extends GraphElement {
	private final String name = "Edge";
	// ------------Fields------------------
	private Node node1;
	private Node node2;

	private boolean choosed;
	private boolean highLight;
	private int hash;
	private LinkedList<Point2D> extraPoints;
	private Point2D lastPoint;

	private double radius = 50;

	// --------------Constructors--------
	private Edge() {
		hash = Counter.getNextNum(GraphElement.class);
		extraPoints = new LinkedList<Point2D>();
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
			lastPoint = null;
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
		setChanged();
		notifyObservers();

	}

	public boolean isHighlight() {
		return highLight;
	}

	public void setHighlight(boolean is) {
		highLight = is;

	}

	public Shape getShape() {
		Point2D points[] = new Point2D[2];
		if (node1 != null) {
			points[0] = node1.getPoint();
			points[1] = node1.getPoint();
		}
		if (lastPoint != null) {
			points[1] = lastPoint;
		}
		if (node2 != null) {
			points[1] = node2.getPoint();
		}
		return new Line2D.Double(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
	}

	private Point2D normalizeVector(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		double l = Point.distance(0, 0, x, y);
		p.setLocation(x / l, y / l);
		return p;
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

	public void fixLastPoint() {
		if (lastPoint != null) {
			extraPoints.addLast(lastPoint);
		}
	}

	public boolean isComplete() {
		return (node1 != null) && (node2 != null);
	}

}
