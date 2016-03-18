package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import grapheditor.view.elements.ViewGraphElement;

public class RectangleRepresent implements ViewGraphElementRepresent {
	ViewGraphElement element;

	public RectangleRepresent(ViewGraphElement e) {
		element = e;
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		Shape rect = element.getShape();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(255, 255, 0, 50));
		g2d.fill(rect);
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(rect);
	}

	@Override
	public boolean contains(double x, double y) {
		return element.getShape().contains(x, y);
	}

}
