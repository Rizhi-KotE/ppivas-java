package grapheditor.view.elements;

import java.awt.Color;
import java.awt.Shape;
import java.util.Observable;

abstract public class ViewGraphElement extends Observable {
	private String content;

	private Color color = Color.BLACK;
	protected boolean choose;
	protected boolean isDeleted;

	public ViewGraphElement() {
		super();
	}

	public ViewGraphElement(ViewGraphElement s) {
		content = s.content;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
		if (isDeleted) {
			setChanged();
			notifyObservers();
		}
	}

	public abstract Shape getShape();

	public abstract String getType();

	public boolean isChoosed() {
		return choose;
	}

	abstract public boolean contains(int x, int y);

	public String getContent() {
		return content;
	}

	public void setContent(String s) {
		content = s;
		setChanged();
		notifyObservers();
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		if (choose == false) {
			color = c;
			setChanged();
			notifyObservers();
		}
	}

	public void currentColor() {
		if (choose == false) {
			color = Color.BLACK;
			setChanged();
			notifyObservers();
		}
	}

	// -------------------------------drag--------------
	public abstract void drag(double dx, double dy);
}
