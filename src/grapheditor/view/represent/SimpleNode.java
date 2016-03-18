package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import grapheditor.view.elements.ViewNode;

public class SimpleNode implements ViewGraphElementRepresent {

	ViewNode node;

	private int radius = 5;

	public SimpleNode(ViewNode n) {
		node = n;
	}

	@Override
	public boolean contains(double x, double y) {
		Ellipse2D elipse = new Ellipse2D.Double(node.getX() - radius, node.getY() - radius, radius * 2, radius * 2);
		return elipse.contains(x, y);
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2));
		int x =(int) node.getX();
		int y = (int) node.getY();
		g2d.setColor(Color.white);
		g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		g2d.setColor(node.getColor());
		g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
		
	}

}
