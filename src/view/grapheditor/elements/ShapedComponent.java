package view.grapheditor.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JLabel;

public class ShapedComponent extends JLabel {
	Shape shape;

	private ShapedComponent() {
		
	}

	public ShapedComponent(Shape s) {
		super();
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
		g2d.setColor(Color.black);
		if (shape != null) {
			g2d.draw(shape);
		}
		super.paintComponent(g2d);
	}
}
