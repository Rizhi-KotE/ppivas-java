package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	float scale = 1;

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

	// -------paint---------------

	private void paintNodes(Graphics2D g2d) {
		Node nodes[] = panel.getGraph().getNodes();

		if (nodes != null)
			for (Node n : nodes) {
				Shape s = new Ellipse2D.Float(n.getX(), n.getY(), 10, 10);

				Color cl = g2d.getColor();

				if (n.isChoosed()) {
					g2d.setColor(Color.GREEN);
				}
				paintNode(g2d, s);
				g2d.setColor(cl);
			}
	}

	private void paintNode(Graphics2D g2d, Shape s) {
		g2d.draw(s);
	}

	// --------------nodes------------------

	public void addNode(float x, float y) {
		Graph gr = panel.getGraph();

		gr.addNode(x * scale, y * scale);
	}

	public void choose(float x, float y) {
		panel.getGraph().choose(x * scale, y * scale);
	}
}
