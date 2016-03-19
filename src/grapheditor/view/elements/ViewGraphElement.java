package grapheditor.view.elements;

import java.awt.Color;
import java.awt.Shape;
import java.util.Observable;

abstract public class ViewGraphElement extends Observable implements SelfPainted, SelfContained, Choousable, Cloneable {
	public static final Color INIT_COLOR = Color.BLACK;
	protected boolean choose;
	private Color color = INIT_COLOR;
	private String content;
	private int contentX;

	/**
	 * @return the contentX
	 */
	public int getContentX() {
		return contentX;
	}

	/**
	 * @return the contentY
	 */
	public int getContentY() {
		return contentY;
	}

	public abstract void calcContentPoint();

	protected void setContentPoint(int x, int y) {
		contentX = x;
		contentY = y;
	}

	private int contentY;
	protected boolean isDeleted;

	@Override
	protected ViewGraphElement clone() throws CloneNotSupportedException {
		ViewGraphElement clone = (ViewGraphElement) super.clone();
		clone.content = content;
		clone.choose = false;
		clone.color = INIT_COLOR;
		clone.isDeleted = false;
		return clone;
	}

	public void currentColor() {
		if (choose == false) {
			color = INIT_COLOR;
			setChanged();
			notifyObservers();
		}
	}

	public ViewGraphElement() {
		// TODO Auto-generated constructor stub
	}

	public ViewGraphElement(ViewGraphElement el) {

		content = el.content;
		contentX = el.contentX;
		contentY = el.contentY;
	}

	public abstract void drag(double dx, double dy);

	public Color getColor() {
		return color;
	}

	public String getContent() {
		return content;
	}

	public abstract Shape getShape();

	public abstract String getType();

	@Override
	public boolean isChoosed() {
		return choose;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setChoosed(boolean is) {
		if (choose != is) {
			choose = is;
			if (is) {
				color = Color.green;
				setChanged();
				notifyObservers();
			} else {
				currentColor();
			}
		}
	}

	public void setColor(Color c) {
		if (choose == false) {
			color = c;
			setChanged();
			notifyObservers();
		}
	}

	public void setContent(String s) throws NumberFormatException {
		content = s;
		setChanged();
		notifyObservers();
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
		if (isDeleted) {
			setChanged();
			notifyObservers();
		}
	}
}
