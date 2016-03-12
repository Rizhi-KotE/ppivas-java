package grapheditor.view.elements;

import java.awt.Color;
import java.awt.Shape;
import java.util.Observable;

abstract public class ViewGraphElement extends Observable {
	private String content;

	private Color color;
	protected boolean choose;

	public abstract Shape getShape();

	public abstract String getName();

	public boolean isChoosed() {
		return choose;
	}

	abstract public boolean contains(int x, int y);

	public String getContent() {
		return content;
	}

	public void setContent(String s) {
		content = s;
	}

	public void setChoosed(boolean is) {
		choose = is;
		if (is) {
			setColor(Color.green);
		} else {
			currentColor();
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
		setChanged();
		notifyObservers();
	}

	public void currentColor() {
		if (choose == false) {
			color = Color.BLACK;
			setChanged();
			notifyObservers();
		}
	}

	// -------------------------------drag--------------
	public void drag(double dx, double dy) {
		// TODO
	}
}
