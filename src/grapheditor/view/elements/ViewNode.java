package grapheditor.view.elements;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import frm.Counter;

public class ViewNode extends ViewGraphElement {
	private final String name = "Node";
	// -----------------Fields-------------
	private double x;
	private double y;
	private double radius;
	private String idth;
	private int hash;

	private boolean highlight;

	// -------------------Constructors------
	private ViewNode() {
		hash = Counter.getNextNum(ViewGraphElement.class);
		radius = 20;
	}

	public ViewNode(String anId) {
		this();
		setIdth(anId);
	}

	public ViewNode(double ax, double ay) {
		this();

		x = ax;
		y = ay;
	}

	// -----------------Methods----------------

	public Point2D getPoint() {
		return new Point2D.Double(x, y);
	}

	public Shape getShape() {
		return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}

	// ----------------Getters & Setters-------
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public String getIdth() {
		return idth;
	}

	private void setIdth(String idth) {
		this.idth = idth;
	}

	// ---------------------Choosed-----------

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	// -----------------For sets------------------------
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bl = obj instanceof ViewNode;
		return bl && (hashCode() == obj.hashCode());
	}
	public String getType(){
		return name;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}
}
