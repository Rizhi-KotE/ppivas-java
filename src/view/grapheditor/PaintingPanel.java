package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import Exception.LoopEdgeException;
import controler.graphEditor.GraphControlerFactory;
import model.Graph;
import view.grapheditor.elements.Edge;
import view.grapheditor.elements.GraphElement;
import view.grapheditor.elements.Node;
import view.grapheditor.elements.ShapedComponent;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Graph graph;

	public PaintingPanel() {
		super();
		graph = new Graph();
		graph.addObserver(this);
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout());
		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", this);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);
		System.out.println(getLayout().getClass().getName());
		addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent e) {
				revalidate();
			}

			@Override
			public void componentAdded(ContainerEvent e) {
				revalidate();
			}
		});
	}

	@Override
	public Component add(Component comp) {
		if (!(comp instanceof JLabel))
			return null;
		return super.add(comp);
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

	public void changeMouseListener(MouseInputListener listener) {
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

	// ------------------------paint----------------------
	// -----------elements-------------

	private Shape currentShape;
	private Node currentNode;
	private Edge newEdge;
	// ---------------------node--------------------------

	public void setCurrentNode(Component c) {
		if (c.getName().equals("ShapedComponent")) {
			GraphElement el = ((ShapedComponent) c).getElement();
			if (el.getName().equals("Node")) {
				currentNode = (Node) el;
			}
		}
	}

	// ----------------------edge-------------------------
	public void addEdge() {
		if (currentNode != null) {
			if (newEdge == null) {
				newEdge = new Edge(null, null);
				ShapedComponent s = new ShapedComponent(newEdge);
				newEdge.addObserver(s);
				add(s);
			}

		}
		if (newEdge != null) {
			try {
				newEdge.addNode(currentNode);
			} catch (LoopEdgeException e) {
				newEdge = null;
				currentNode = null;
				return;
			}
		}
		if (newEdge != null) {
			if (newEdge.isComplete())
				newEdge = null;
		}
		currentNode = null;
	}

	public void setEdgePoint(int x, int y) {
		if (currentNode != null) {
			addEdge();
		} else if (newEdge != null) {
			newEdge.fixLastPoint();
		}
		revalidate();
	}

	public void setExtraEdgePoint(int x, int y) {
		if (newEdge != null) {
			newEdge.setLastPoint(x, y);
			revalidate();
		}
	}

	float scale = 1;

	int nodeRadius = 20;
	// -------paint---------------

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
		
	}

	// --------------nodes------------------

	public void addNode(float x, float y) {
		Node n = new Node(x, y);
		ShapedComponent s = new ShapedComponent(n);
		add(s);
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

	public void clearHighlight() {
		getGraph().clearHighlight();
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
		repaint();
	}
}
