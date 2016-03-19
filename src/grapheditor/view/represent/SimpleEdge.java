package grapheditor.view.represent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;

public class SimpleEdge implements ViewEdgeRepresent{

	ViewEdge edge;
	
	private double radius = 5;
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
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(edge.getShape());
		drawContent(g2d);
	}

	private void drawContent(Graphics2D g2d){
		g2d.setColor(ViewGraphElement.INIT_COLOR);
		String s = edge.getContent();
		if(s!=null){
			g2d.drawString(s, edge.getContentX(), edge.getContentY());
		}
	}
	@Override
	public boolean contains(double x, double y) {
		return getShape().ptLineDist(x, y)<radius;
	}
	@Override
	public Line2D getShape() {
		return (Line2D)edge.getShape();
	}
	@Override
	public double getWidth() {
		return radius;
	}

}
