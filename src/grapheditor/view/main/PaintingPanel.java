package grapheditor.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.action.CopyAction;
import grapheditor.controler.action.CutAction;
import grapheditor.controler.action.DeleteAction;
import grapheditor.controler.action.IdentifierAction;
import grapheditor.controler.action.PasteAction;
import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.menu.GraphPopupMenu;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private ViewGraph viewGraph;

	private Map<String, Action> actionEvents;

	public static final String IDENTIFIER = "IdentifierAction";
	public static final String COPY_ACTION = "CopyAction";
	public static final String PASTE_ACTION = "PasteAction";
	public static final String CUT_ACTION = "CutAction";
	public static final String DELETE_ACTION = "DeleteAction";

	// -------------initialization-------
	public PaintingPanel() {
		super();
		viewGraph = new ViewGraph(this);
		viewGraph.addObserver(this);
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout());
		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", this);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);
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
		initializationEvents();
	}

	private void initializationEvents() {
		actionEvents = new HashMap<String, Action>();
		actionEvents.put(IDENTIFIER, new IdentifierAction(this));
		actionEvents.put(COPY_ACTION, new CopyAction(this));
		actionEvents.put(PASTE_ACTION, new PasteAction(this));
		actionEvents.put(CUT_ACTION, new CutAction(this));
		actionEvents.put(DELETE_ACTION, new DeleteAction(this));
	}

	// ------Observer-----------

	public ViewGraph getGraph() {
		return viewGraph;
	}

	public void setGraph(ViewGraph viewGraph) {
		this.viewGraph.deleteObserver(this);
		this.viewGraph = viewGraph;
	}

	public void update(Observable o, Object arg) {
		if (viewGraph == null) {
			if (o.getClass().getName().equals("model.Graph"))
				;
			setGraph((ViewGraph) o);
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
	// ---------------------node--------------------------

	public void setCurrentNode(Component component) {
		viewGraph.setCurrentNode(component);
	}

	public void addNode(double x, double y) {
		getGraph().addNode(x, y);
	}

	// ----------------------edge-------------------------
	public void addEdge() {
		getGraph().addEdge();
	}

	public void fixEdgePoint() {
		getGraph().fixEdgePoint();
	}

	public void setExtraEdgePoint(int x, int y) {
		getGraph().setExtraEdgePoint(x, y);
	}
	// -------------------Drag------------------
	// -------------------choose----------------

	public void choose(ShapedComponent E) {
		getGraph().choose(E);
	}

	public boolean choose(Rectangle rect) {
		return getGraph().choose(rect);
	}

	public void clearChoose() {
		getGraph().clearChoose();
	}

	public void putActionEvent(String s, Action a) {
		actionEvents.put(s, a);
	}

	public Action getActionEvent(String s) {
		return actionEvents.get(s);
	}

	// -------------------content---------

	public void setContent(String s) {
		viewGraph.addName(s);
	}

	// --------------------popupMenu--------
	private GraphPopupMenu popupMenu;

	public GraphPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			setPopupMenu(new GraphPopupMenu(this));
		}
		return popupMenu;
	}

	public void setPopupMenu(GraphPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}

	// -------------------corrections-------
	public void copy() {
		// TODO
	}

	public void paste() {
		// TODO
	}

	public void cut() {
		// TODO
	}

	public void delete() {
		// TODO
	}

}
