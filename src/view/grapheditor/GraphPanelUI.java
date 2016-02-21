package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

import model.Node;

public class GraphPanelUI extends PanelUI {
	PaintingPanel panel;

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		paintNodes(g2d);
	}

	private GraphPanelUI() {
		super();
	}

	public GraphPanelUI(PaintingPanel p) {
		this();
		panel = p;

	}

	void paintNodes(Graphics2D g2d) {
		Node nodes[] = panel.getGraph().getNode();

		for (Node n : nodes) {
			Shape s = new Ellipse2D.Float(n.getX(), n.getY(), 10, 10);
			
			paintNode(g2d, s);
		}
	}

	void paintNode(Graphics2D g2d, Shape s) {
		g2d.draw(s);
	}

}
