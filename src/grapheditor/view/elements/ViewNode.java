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
	private double radius = 20;
	private String idth;
	private int hash;

	private boolean highlight;

	// -------------------Constructors------
	private ViewNode() {
		super();
		hash = Counter.getNextNum(ViewNode.class);
	}

	public ViewNode(ViewNode n){
		super(n);
		hash = Counter.getNextNum(ViewNode.class);
		x = n.x;
		y = n.y;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hash;
		result = prime * result + (highlight ? 1231 : 1237);
		result = prime * result + ((idth == null) ? 0 : idth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (highlight != other.highlight) {
			return false;
		}
		if (idth == null) {
			if (other.idth != null) {
				return false;
			}
		} else if (!idth.equals(other.idth)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius)) {
			return false;
		}
		return true;
	}
	public String getType(){
		return name;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}
	@Override
	public void drag(double dx, double dy) {
		x+=dx;
		y+=dy;
		setChanged();
		notifyObservers();
	}
}
