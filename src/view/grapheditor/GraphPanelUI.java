package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

import model.Graph;
import model.Node;

public class GraphPanelUI extends PanelUI {
	private PaintingPanel panel;

	// -----------elements-------------

	private LinkedList<GeneralPath> edges;

	private Shape currentShape;

	float scale = 1;

	int nodeRadius = 20;

	private GraphPanelUI() {
		super();
	}

	public GraphPanelUI(PaintingPanel p) {
		this();
		panel = p;

	}

	// -------paint---------------

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		paintNodes(g2d);
		paintCurrentShape(g2d);
	}

	private void paintCurrentShape(Graphics2D g2d) {
		if (currentShape != null) {
			g2d.setColor(new Color(125, 0, 0));
			g2d.draw(currentShape);
		}
	}

	private void paintNodes(Graphics2D g2d) {
		Node nodes[] = panel.getGraph().getNodes();

		if (nodes != null)
			for (Node n : nodes) {

				Point node = new Point((int) n.getX(), (int) n.getY());
				Color cl = g2d.getColor();

				if (n.isHighlight()) {
					g2d.setColor(Color.YELLOW);
				}
				if (n.isChoosed()) {
					g2d.setColor(Color.GREEN);
				}

				paintNode(g2d, node);
				g2d.setColor(cl);
			}
	}

	private void paintNode(Graphics2D g2d, Point s) {
		g2d.drawOval(s.x - nodeRadius, s.y - nodeRadius, nodeRadius * 2, nodeRadius * 2);
	}

	// --------------nodes------------------

	public void addNode(float x, float y) {
		Graph gr = panel.getGraph();

		gr.addNode(scale * (x), scale * (y));
	}

	// -------------------Drag------------------
	public void dragChoosenElementOn(float x, float y) {
		panel.getGraph().dragChoosenElementOn(x * scale, y * scale);
	}
	// -------------------choose----------------

	public boolean choose(float x, float y) {
		return panel.getGraph().choose(scale * (x), scale * (y));
	}

	public boolean choose(Rectangle rect) {
		setCurrentShape(rect);
		return panel.getGraph().choose(rect);
		
	}

	public void highlight(float x, float y) {
		panel.getGraph().highlight(scale * (x), scale * (y));
	}

	public void clearChoose() {
		panel.getGraph().clearChoose();
	}

	public void clearHighlight() {
		panel.getGraph().clearHighlight();
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
		panel.repaint();
	}

}
