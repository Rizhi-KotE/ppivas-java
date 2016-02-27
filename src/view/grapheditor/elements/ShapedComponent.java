package view.grapheditor.elements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JLabel;

public class ShapedComponent extends JLabel {
	Shape shape;

	private ShapedComponent() {
		// TODO Auto-generated constructor stub
	}

	public ShapedComponent(Shape s) {
		shape = s;
	}

	@Override
	public boolean contains(int x, int y) {
		boolean bl = shape != null;
		if (bl) {
			bl = shape.contains(x, y);
		}
		return bl;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (shape != null) {
			g2d.draw(shape);
		}
	}
}
