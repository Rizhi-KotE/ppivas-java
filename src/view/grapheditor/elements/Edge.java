package view.grapheditor.elements;

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

public class Edge extends Observable implements GraphElement {
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
		hash = Counter.getNextNum(this);
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

	}

	public boolean isHighlight() {
		return highLight;
	}

	public void setHighlight(boolean is) {
		highLight = is;

	}

	public Shape getShape() {
		int size = 0;
		if (extraPoints != null) {
			size += extraPoints.size();
		}
		if (node1 != null)
			size++;
		if (lastPoint != null) {
			size++;
		}
		if (node2 != null)
			size++;
		if (size <= 1) {
			return new Ellipse2D.Double(1, 1, 1, 1);
		}
		Point2D points[] = new Point2D[size];
		int first = 0;
		if (node1 != null)
			points[first++] = node1.getPoint();
		if (extraPoints != null) {
			Iterator<Point2D> it = extraPoints.iterator();
			while (it.hasNext()) {
				points[first++] = it.next();
			}
		}
		if (lastPoint != null) {
			points[first++] = lastPoint;
		}
		if (node2 != null) {
			points[first++] = node2.getPoint();
		}
		Point2D directVectors[] = new Point2D[points.length - 1];
		first = 0;
		int second = 1;
		for (; first < directVectors.length; second++) {
			directVectors[first] = new Point2D.Double(points[second].getX() - points[first].getX(),
					points[second].getY() - points[first].getY());
			first = second;
		}
		Point2D normalVectors[] = new Point2D[directVectors.length];
		for (int i = 0; i < normalVectors.length; i++) {
			normalVectors[i] = new Point2D.Double(1, -directVectors[i].getX() / directVectors[i].getY());
		}
		for (int i = 0; i < normalVectors.length; i++) {
			normalVectors[i] = normalizeVector(normalVectors[i]);
		}
		directVectors[directVectors.length - 1] = normalizeVector(directVectors[directVectors.length - 1]);
		directVectors[0] = normalizeVector(directVectors[0]);
		Point2D upPoints[] = new Point2D[points.length * 2 - 1];
		for (int i = 0; i < normalVectors.length; i++) {
			upPoints[i * 2] = new Point2D.Double(points[i].getX() + normalVectors[i].getX() * radius,
					points[i].getY() + normalVectors[i].getY() * radius);
			upPoints[i * 2 + 1] = new Point2D.Double(points[i + 1].getX() + normalVectors[i].getX() * radius,
					points[i + 1].getY() + normalVectors[i].getY() * radius);
		}
		upPoints[upPoints.length - 1] = new Point2D.Double(
				points[points.length - 1].getX() + directVectors[directVectors.length - 1].getX() * radius,
				points[points.length - 1].getY() + directVectors[directVectors.length - 1].getY() * radius);
		Point2D downPoints[] = new Point2D[points.length * 2 - 1];
		int dPI = 0;
		for (int i = normalVectors.length - 1; i >= 0; i--) {
			downPoints[dPI++] = new Point2D.Double(points[i+1].getX() - normalVectors[i].getX() * radius,
					points[i+1].getY() - normalVectors[i].getY() * radius);
			downPoints[dPI++] = new Point2D.Double(points[i].getX() - normalVectors[i].getX() * radius,
					points[i].getY() - normalVectors[i].getY() * radius);
		}
		downPoints[dPI] = new Point2D.Double(points[0].getX() - directVectors[0].getX() * radius,
				points[0].getY() - directVectors[0].getY() * radius);
		GeneralPath s = new GeneralPath();
		s.moveTo(upPoints[0].getX(), upPoints[0].getY());
		for (int i = 1; i < upPoints.length - 1; i++) {
			s.lineTo(upPoints[i].getX(), upPoints[i].getY());
		}
		s.lineTo(upPoints[upPoints.length - 1].getX(), upPoints[upPoints.length - 1].getY());
		s.lineTo(downPoints[0].getX(), downPoints[0].getY());
		// s.curveTo(upPoints[upPoints.length - 2].getX(),
		// upPoints[upPoints.length - 2].getY(),
		// upPoints[upPoints.length - 1].getX(), upPoints[upPoints.length -
		// 1].getY(),
		// downPoints[upPoints.length - 1].getX(), downPoints[upPoints.length -
		// 1].getY());
		int i = 0;
		for (i = 1; i < downPoints.length - 1; i++) {
			s.lineTo(downPoints[i].getX(), downPoints[i].getY());
		}
		s.lineTo(downPoints[i].getX(), downPoints[i].getY());
		s.lineTo(upPoints[0].getX(), upPoints[0].getY());
		// s.curveTo(downPoints[1].getX(), downPoints[1].getY(),
		// downPoints[0].getX(), downPoints[0].getY(), upPoints[0].getX(),
		// upPoints[0].getY());
		// s.closePath();
		return s;
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
