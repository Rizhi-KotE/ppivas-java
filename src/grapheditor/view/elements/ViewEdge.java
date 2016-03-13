package grapheditor.view.elements;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import Exception.LoopEdgeException;
import frm.Counter;

public class ViewEdge extends ViewGraphElement implements Observer{
	private final String name = "Edge";
	// ------------Fields------------------
	private ViewNode node1;
	private ViewNode node2;

	private int hash;
	private LinkedList<Point2D> extraPoints;
	private Point2D lastPoint;

	private double radius = 5;

	// --------------Constructors--------
	private ViewEdge() {
		hash = Counter.getNextNum(ViewGraphElement.class);
		extraPoints = new LinkedList<Point2D>();
	}

	public ViewEdge(ViewNode n1, ViewNode n2) {
		this();
		node1 = n1;
		node2 = n2;
	}
	
	public ViewEdge(ViewEdge e){
		node1 = e.node1;
		node2 = e.node2;
		extraPoints = new LinkedList<Point2D>(e.extraPoints);
	}

	// ----------------Methods------------
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
		notifyObservers();
	}

	public void addPoint(Point2D p) {
		if (extraPoints != null)
			extraPoints.add(p);
		setChanged();
		notifyObservers();
	}
	// ------------Seters & Getters-------

	public ViewNode getNode1() {
		return node1;
	}

	public void setNode1(ViewNode node1) {
		this.node1 = node1;
	}

	public ViewNode getNode2() {
		return node2;
	}

	public void setNode2(ViewNode node2) {
		this.node2 = node2;
	}

	// ---------------Choosed------------
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

	@Override
	public String getType() {
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

		setChanged();
		notifyObservers();
	}

	public boolean isComplete() {
		return (node1 != null) && (node2 != null);
	}
	
	@Override
	public boolean contains(int x, int y) {
		Line2D l = (Line2D)getShape();
		return l.ptLineDist(x,y)<radius;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}

	public void drag(double dx, double dy) {
		
	}

}
