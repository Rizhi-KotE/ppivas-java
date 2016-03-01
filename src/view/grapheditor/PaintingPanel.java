package view.grapheditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Exception.LoopEdgeException;
import controler.graphEditor.GraphControlerFactory;
import model.Edge;
import model.Graph;
import model.GraphElement;
import model.Node;
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
		this.graph.deleteObserver(this);
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
				graph.addEdge(newEdge);
				ShapedComponent s = new ShapedComponent(newEdge);
				newEdge.addObserver(s);
				add(s);
			}

		}
		if (newEdge != null) {
			try {
				newEdge.addNode(currentNode);
			} catch (LoopEdgeException e) {
				graph.deleteEdge(newEdge);
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

	public void addNode(float x, float y) {
		Node n = new Node(x, y);
		graph.addNode(n);
		ShapedComponent s = new ShapedComponent(n);
		add(s);
	}

	// ----------------Edge------------------

	public void createEdge(double x, double y) {
		getGraph().createEdge(x, y);
	}

	// -------------------Drag------------------
	// -------------------choose----------------

	private Set<ShapedComponent> choose;

	public void choose(ShapedComponent E) {
		if (choose == null) {
			choose = new HashSet<ShapedComponent>();
		}
		E.setColor(Color.green);
		E.setChoose(true);
		choose.add(E);
	}

	public boolean choose(Rectangle rect) {
		return getGraph().choose(rect);
	}

	public void clearChoose() {
		if (choose == null) {
			choose = new HashSet<ShapedComponent>();
		}
		Iterator<ShapedComponent> it = choose.iterator();
		while (it.hasNext()) {
			ShapedComponent s = it.next();
			s.setChoose(false);
			s.currentColor();
		}
		choose.clear();
	}

	public void clearHighlight() {
		getGraph().clearHighlight();
	}
}
