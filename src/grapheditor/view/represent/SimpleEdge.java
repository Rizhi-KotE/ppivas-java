package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import grapheditor.view.elements.ViewEdge;

public class SimpleEdge implements ViewEdgeRepresent{

	ViewEdge edge;
	public SimpleEdge(ViewEdge e) {
		edge = e;
	}
	@Override
	public void paintYourSelf(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(edge.getColor());
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(edge.getShape());
	}

	@Override
	public boolean contains(double x, double y) {
		return getShape().ptLineDist(x, y)<5;
	}
	@Override
	public Line2D getShape() {
		return (Line2D)edge.getShape();
	}

}
