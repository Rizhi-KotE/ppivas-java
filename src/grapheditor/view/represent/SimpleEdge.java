package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class SimpleEdge implements ViewEdgeRepresent {

	ViewEdge edge;
	private static Stroke stroke = new BasicStroke(2);
	private double radius = 5;
	private Point2D point1;
	private Point2D point2;
	private Line2D shape;

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	public SimpleEdge(ViewEdge e) {
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
		return getShape().ptLineDist(x, y) < radius;
	}

	@Override
	public Line2D getShape() {
		if ((point1 == null) && (point2 == null)) {
			shape = (Line2D) edge.getShape();
			point1 = shape.getP1();
			point2 = shape.getP2();
			return shape;
		}
		if ((point1.equals(edge.getNode1().getPoint())) && ((point2.equals(edge.getNode2().getPoint())))) {
			shape = (Line2D) edge.getShape();
			point1 = shape.getP1();
			point2 = shape.getP2();
		}
		return shape;
	}

	@Override
	public double getWidth() {
		return radius;
	}

}
