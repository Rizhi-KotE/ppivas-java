package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import grapheditor.view.elements.ViewNode;

public class SimpleNode implements ViewNodeRepresent {

	ViewNode node;

	private int radius = 5;

	public SimpleNode(ViewNode n) {
		node = n;
	}

	@Override
	public boolean contains(double x, double y) {

		return getShape().contains(x, y);
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2));
		Shape s = getShape();
		g2d.setColor(Color.white);
		g2d.fill(s);
		g2d.setColor(node.getColor());
		g2d.draw(s);

	}

	@Override
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(node.getX() - radius, node.getY() - radius, radius * 2, radius * 2);
	}

}
