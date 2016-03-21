package grapheditor.view.elements;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

import frm.Counter;
import grapheditor.view.represent.SimpleNode;
import grapheditor.view.represent.ViewNodeRepresent;

public class ViewNode extends ViewGraphElement {
	private final String name = "Node";
	// -----------------Fields-------------
	private double x;
	private double y;
	private String idth;
	private int hash;
	private ViewNodeRepresent represent;
	private double radius = 20;

	private boolean highlight;

	// -------------------Constructors------
	private ViewNode() {
		super();
		hash = Counter.getNextNum(ViewNode.class);
		represent = new SimpleNode(this);
	}

	
	public ViewNode(ViewNode n){
		super(n);
		hash = Counter.getNextNum(ViewNode.class);
		x = n.x;
		y = n.y;
		represent = new SimpleNode(this);
	}

	public ViewNode(String anId) {
		this();
		setIdth(anId);
	}

	@Override
	public void setContent(String s) throws NumberFormatException {
		calcContentPoint();
		super.setContent(s);
	}
	
	@Override
	public void calcContentPoint() {
		setContentPoint((int)(getX() + represent.getRadius()*2), (int) (getY() + represent.getRadius()*2));
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hash;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ViewNode)) {
			return false;
		}
		ViewNode other = (ViewNode) obj;
		if (hash != other.hash) {
			return false;
		}
		return true;
	}

	public String getType() {
		return name;
	}

	@Override
	public void drag(double dx, double dy) {
		x += dx;
		y += dy;
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
	public boolean contains(double x, double y) {
		return represent.contains(x, y);
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		represent.paintYourSelf(g2d);
	}

	@Override
	public Shape getShape() {
		return represent.getShape();
	}
}
