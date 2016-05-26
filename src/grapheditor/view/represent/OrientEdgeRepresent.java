package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class OrientEdgeRepresent implements ViewEdgeRepresent {

	private ViewEdge edge;
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

	public OrientEdgeRepresent(ViewEdge e) {
		edge = e;
	}

	@Override
	public void paintYourSelf(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(edge.getColor());
		g2d.setStroke(stroke);
		g2d.draw(edge.getShape());

		double x1 = edge.getNode1().getX();
		double y1 = edge.getNode1().getY();
		double x2 = edge.getNode2().getX();
		double y2 = edge.getNode2().getY();

		double R = Point2D.distance(x1, y1, x2, y2);
		double dX = x2 - x1;
		double dY = y2 - y1;

		double coefX = dX / R;
		double coefY = dY / R;

		double x3 = x2 - coefX * 5;
		double y3 = y2 - coefY * 5;

		double angle = Math.acos(coefX);

		if (dY < 0) {
			angle = 2 * Math.PI - angle;
		}

		double x4 = x3 - 10 * Math.cos(angle - Math.PI / 6);
		double y4 = y3 - 10 * Math.sin(angle - Math.PI / 6);
		double x5 = x3 - 10 * Math.cos(angle + Math.PI / 6);
		double y5 = y3 - 10 * Math.sin(angle + Math.PI / 6);
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x3, y3);
		path.lineTo(x4, y4);
		path.lineTo(x5, y5);
		path.closePath();
		
		g2d.fill(path);
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
