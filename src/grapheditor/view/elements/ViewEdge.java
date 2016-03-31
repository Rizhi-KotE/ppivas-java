package grapheditor.view.elements;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.AbstractDocument.Content;

import Exception.LoopEdgeException;
import frm.Counter;
import grapheditor.view.represent.SimpleEdge;
import grapheditor.view.represent.ViewEdgeRepresent;

public class ViewEdge extends ViewGraphElement implements Observer, Cloneable {
	private LinkedList<Point2D> extraPoints;
	private int hash;
	private Point2D lastPoint;

	private final String name = "Edge";

	private ViewNode node1;
	private ViewNode node2;

	private double radius = 5;

	ViewEdgeRepresent represent;

	private ViewEdge() {
		hash = Counter.getNextNum(ViewGraphElement.class);
		extraPoints = new LinkedList<Point2D>();
		represent = new SimpleEdge(this);
	}

	public ViewEdge(ViewNode n1, ViewNode n2) {
		this();
		node1 = n1;
		node2 = n2;
	}

	public ViewEdge(ViewEdge e) {
		super(e);
		hash = Counter.getNextNum(ViewGraphElement.class);
		node1 = e.node1;
		node2 = e.node2;
		extraPoints = new LinkedList<Point2D>(e.extraPoints);
		represent = new SimpleEdge(this);
	}

	public ViewEdge(ViewEdge e, ViewNode n1, ViewNode n2) {
		super(e);
		hash = Counter.getNextNum(ViewGraphElement.class);
		node1 = n1;
		node2 = n2;
		extraPoints = new LinkedList<Point2D>(e.extraPoints);
		represent = new SimpleEdge(this);
	}

	public void addNode(ViewNode n) throws LoopEdgeException {
		if (node1 == null) {
			node1 = n;
		} else {
			if (node1.equals(n)) {
				throw new LoopEdgeException();
			}
			node2 = n;
			lastPoint = null;
		}
		setChanged();
Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				notifyObservers();
			}
		});
		a.start();
	}

	public void addPoint(Point2D p) {
		if (extraPoints != null)
			extraPoints.add(p);
		setChanged();
Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				notifyObservers();
			}
		});
		a.start();
	}

	@Override
	public boolean contains(double x, double y) {
		Line2D l = (Line2D) getShape();
		return l.ptLineDist(x, y) < radius;
	}

	public void drag(double dx, double dy) {
		calcContentPoint();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewEdge other = (ViewEdge) obj;
		if (extraPoints == null) {
			if (other.extraPoints != null)
				return false;
		} else if (!extraPoints.equals(other.extraPoints))
			return false;
		if (hash != other.hash)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.equals(other.node1))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.equals(other.node2))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	public void fixLastPoint() {
		if (lastPoint != null) {
			extraPoints.addLast(lastPoint);
		}

		setChanged();
Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				notifyObservers();
			}
		});
		a.start();
	}

	public ViewNode getNode1() {
		return node1;
	}

	public ViewNode getNode2() {
		return node2;
	}

	public ViewNode getUnnotherNode(ViewNode node) {
		return node2.equals(node) ? node1 : node2;
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

	@Override
	public String getType() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((extraPoints == null) ? 0 : extraPoints.hashCode());
		result = prime * result + hash;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean isComplete() {
		return (node1 != null) && (node2 != null);
	}

	public void setLastPoint(double x, double y) {
		lastPoint = new Point2D.Double(x, y);
		setChanged();
Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				notifyObservers();
			}
		});
		a.start();
	}

	public void setNode1(ViewNode node1) {
		this.node1 = node1;
	}

	public void setNode2(ViewNode node2) {
		this.node2 = node2;
	}

	@Override
	public void update(Observable o, Object arg) {
		calcContentPoint();
		setChanged();
Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				notifyObservers();
			}
		});
		a.start();
	}

	@Override
	public ViewEdge clone() throws CloneNotSupportedException {
		ViewEdge clone = (ViewEdge) super.clone();

		// clone.hash = Counter.getNextNum(ViewEdge.class);
		clone.node1 = node1;
		clone.node2 = node2;
		return clone;
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		represent.paintYourSelf(g2d);
	}

	@Override
	public void setContent(String s) throws NumberFormatException {
		Integer.parseInt(s);
		calcContentPoint();
		super.setContent(s);
	}

	@Override
	public void calcContentPoint() {
		Rectangle2D bounds = null;
		if ((node1 != null) && (node2 != null)) {
			double x1 = node1.getX();
			double y1 = node1.getY();
			double x2 = node2.getX();
			double y2 = node2.getY();
			bounds = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
		}
		if (bounds != null) {
			int x = (int) (bounds.getX() + bounds.getWidth() / 2 + represent.getWidth() * 2);
			int y = (int) (bounds.getY() + bounds.getHeight() / 2 + represent.getWidth() * 2);
			setContentPoint(x, y);

		}
	}
}
