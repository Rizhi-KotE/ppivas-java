package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class NoOrientEdge implements ViewEdgeRepresent {

	ViewEdge edge;
	private static Stroke stroke = new BasicStroke(2);
	private double radius = 5;
	private Line2D shape;

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	public NoOrientEdge(ViewEdge e) {
		edge = e;
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(edge.getColor());
		g2d.setStroke(stroke);
		g2d.draw(edge.getShape());
		drawContent(g2d);
	}

	private void drawContent(Graphics2D g2d) {
		g2d.setColor(ViewGraphElement.INIT_COLOR);
		String s = edge.getContent();
		if (s != null) {
			g2d.drawString(s, edge.getContentX(), edge.getContentY());
		}
	}

	@Override
	public boolean contains(double x, double y) {
		Line2D shape = getShape();
		return shape.ptLineDist(x, y) < radius && shape.getBounds().contains(x, y);
	}

	@Override
	public Line2D getShape() {
		Point2D points[] = new Point2D[2];
		if (edge.getNode1() != null) {
			points[0] = edge.getNode1().getPoint();
			points[1] = edge.getNode1().getPoint();
		}
		if (edge.getLastPoint() != null) {
			points[1] = edge.getLastPoint();
		}
		if (edge.getNode2() != null) {
			points[1] = edge.getNode2().getPoint();
		}
		return new Line2D.Double(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
	}

	@Override
	public double getWidth() {
		return radius;
	}

}
