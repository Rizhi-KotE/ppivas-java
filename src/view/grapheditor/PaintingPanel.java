package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import model.Graph;
import view.grapheditor.elements.Edge;
import view.grapheditor.elements.Node;
import view.grapheditor.elements.ShapedComponent;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Graph graph;

	private JToolBar toolBar;

	public PaintingPanel() {
		super();

		graph = new Graph();
		graph.addObserver(this);

		setBackground(new Color(255, 255, 255));

		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", this);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);

		setLayout(null);

		addNode(60, 60);
		//toolBar = createToolBar();
		//add(toolBar, BorderLayout.NORTH);

		setVisible(true);
	}

	// ------Observer-----------

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		graph.deleteObserver(this);

		this.graph = graph;
	}

	public void update(Observable o, Object arg) {
		if (graph == null) {
			if (o.getClass().getName().equals("model.Graph"))
				;
			setGraph((Graph) o);
		}
		repaint();
	}

	protected void changeMouseListener(MouseInputListener listener) {
		MouseListener[] l1 = getMouseListeners();
		for (MouseListener i : l1) {
			removeMouseListener(i);
		}
		MouseMotionListener[] l2 = getMouseMotionListeners();
		for (MouseMotionListener i : l2) {
			removeMouseMotionListener(i);
		}
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();

		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool",
					PaintingPanel.this);
				changeMouseListener(listener);
			}
		});
		toolBar.add(but1);

		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Arc_tool",
						PaintingPanel.this);
				changeMouseListener(listener);
			}
		});
		toolBar.add(but2);
		return toolBar;
	}
	// ------------------------paint----------------------
	// -----------elements-------------

	private Shape currentShape;

	float scale = 1;

	int nodeRadius = 20;
	// -------paint---------------

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		paintNodes(g2d);
		paintCurrentShape(g2d);
		paintEdges(g2d);
	}

	private void paintCurrentShape(Graphics2D g2d) {
		if (currentShape != null) {
			g2d.setColor(new Color(125, 0, 0));
			g2d.draw(currentShape);
		}
	}

	private void paintNodes(Graphics2D g2d) {
		Node nodes[] = getGraph().getNodes();

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

	private void paintEdges(Graphics2D g2d) {
		Edge edges[] = getGraph().getEdges();
		for (Edge e : edges) {
			Color cl = g2d.getColor();

			if (e.isHighlight()) {
				g2d.setColor(Color.YELLOW);
			}
			if (e.isChoosed()) {
				g2d.setColor(Color.GREEN);
			}

			paintEdge(g2d, e);
			g2d.setColor(cl);
		}
	}

	private void paintEdge(Graphics2D g2d, Edge e) {
		Shape s = e.getShape(nodeRadius + 10, scale);
		g2d.draw(s);
	}
	// --------------nodes------------------

	public void addNode(float x, float y) {
		Node n = new Node(x, y);
		ShapedComponent s = new ShapedComponent(n.getShape());
		add(s);
		repaint();
	}

	// ----------------Edge------------------

	public void createEdge(double x, double y) {
		getGraph().createEdge(x, y);
	}

	// -------------------Drag------------------
	public void dragChoosenElementOn(float x, float y) {
		getGraph().dragChoosenElementOn(x * scale, y * scale);
	}
	// -------------------choose----------------

	public boolean choose(float x, float y) {
		return getGraph().choose(scale * (x), scale * (y));
	}

	public boolean choose(Rectangle rect) {
		setCurrentShape(rect);
		return getGraph().choose(rect);

	}

	public void highlight(float x, float y) {
		getGraph().highlight(scale * (x), scale * (y));
	}

	public void clearChoose() {
		getGraph().clearChoose();
	}

	public void clearHighlight() {
		getGraph().clearHighlight();
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
		repaint();
	}
}
