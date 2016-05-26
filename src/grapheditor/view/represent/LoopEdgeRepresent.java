package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class LoopEdgeRepresent implements ViewEdgeRepresent {

	private ViewEdge edge;
	private static Stroke stroke = new BasicStroke(2);
	private double radius = 20;
	private Double elipseCenter;

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	public LoopEdgeRepresent(ViewEdge e) {
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
		double dX = elipseCenter.getX() - x;
		double dY = elipseCenter.getY() - y;
		boolean isCorrectConner = false;
		if (dX > 0 && dY < 0) {
			double tg = dX / dY;
			if (Math.abs(tg - 1) < 0.2) {
				isCorrectConner = false;
			} else {
				isCorrectConner = true;
			}
		} else {
			isCorrectConner = true;
		}
		return Math.abs(Point2D.distance(0, 0, dX, dY) - radius) < 2 && isCorrectConner;
	}

	@Override
	public GeneralPath getShape() {
		Point2D nodeCenter = edge.getNode1().getPoint();
		elipseCenter = new Point2D.Double(nodeCenter.getX() - radius / Math.sqrt(2),
				nodeCenter.getY() - radius / Math.sqrt(2));
		double angle3 = Math.PI / 6;

		Point2D point3 = new Point2D.Double(elipseCenter.getX() + radius * Math.cos(angle3),
				elipseCenter.getY() + radius * Math.sin(angle3));

		Shape elipse = new Ellipse2D.Double(elipseCenter.getX() - radius, elipseCenter.getY() - radius, radius * 2,
				radius * 2);
		GeneralPath path = new GeneralPath();
		path.append(elipse, true);
		path.moveTo(point3.getX(), point3.getY());

		path.lineTo(point3.getX() - 5, point3.getY()-10);
		path.lineTo(point3.getX() + 7, point3.getY()-5);
		path.closePath();
		return path;
	}

	@Override
	public double getWidth() {
		return radius;
	}

}
