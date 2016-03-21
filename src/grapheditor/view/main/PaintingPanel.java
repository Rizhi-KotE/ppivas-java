package grapheditor.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.action.CopyAction;
import grapheditor.controler.action.CutAction;
import grapheditor.controler.action.DeleteAction;
import grapheditor.controler.action.FindMinPathAction;
import grapheditor.controler.action.IdentifierAction;
import grapheditor.controler.action.PasteAction;
import grapheditor.controler.action.StepAlgoAction;
import grapheditor.controler.mouse.AlgoMinPathFindListener;
import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.elements.ViewNode;
import grapheditor.view.menu.GraphPopupMenu;
import prop.KeyStrokeProperty;

public class PaintingPanel extends JPanel implements Scrollable {

	public static final String CUT_ACTION = "CutAction";

	public static final String COPY_ACTION = "CopyAction";
	public static final String DELETE_ACTION = "DeleteAction";
	public static final String FIND_BY_STEP = "find by step";
	public static final String FIND_MIN_PATH = "find min path";
	public static final String IDENTIFIER = "IdentifierAction";
	public static final String PASTE_ACTION = "PasteAction";
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actionEvents;
	private GraphPopupMenu popupMenu;

	private ViewGraph viewGraph;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (viewGraph.getDragElements() != null)
			g.drawImage(viewGraph.getDragElements(), 0, 0, null);
		
	}

	public PaintingPanel() {
		super();
		initializationEvents();
		initializationInputMap();
		viewGraph = new ViewGraph(this);
		setPreferredSize(new Dimension(5000, 5000));
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout());
		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", this);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);
		addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent e) {
				revalidate();
			}

			@Override
			public void componentRemoved(ContainerEvent e) {
				repaint();
			}
		});

	}

	private void initializationInputMap() {
		InputMap map = getInputMap();
	}

	public void addEdge() {
		getGraph().addEdge();
	}

	public void addNode(double x, double y) {
		getGraph().addNode(x, y);
	}

	public void changeMouseListener(MouseInputListener listener) {
		MouseListener[] l1 = getMouseListeners();
		boolean isNewListener = false;
		for (MouseListener i : l1) {
			if (!i.equals(listener)) {
				removeMouseListener(i);
				isNewListener |= true;
			}
		}
		MouseMotionListener[] l2 = getMouseMotionListeners();
		for (MouseMotionListener i : l2) {
			if (!i.equals(listener)) {
				removeMouseMotionListener(i);
				isNewListener |= true;
			}
		}
		if (isNewListener) {
			addMouseListener(listener);
			addMouseMotionListener(listener);
			viewGraph.changeListener();
		}
		if (listener instanceof AlgoMinPathFindListener) {
			actionEvents.put(FIND_MIN_PATH, new FindMinPathAction((AlgoMinPathFindListener) listener));
			actionEvents.put(FIND_BY_STEP, new StepAlgoAction((AlgoMinPathFindListener) listener));
			getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStrokeProperty.STEP_ALGO_ACTION, FIND_BY_STEP);
			getActionMap().put(FIND_BY_STEP, getActionEvent(FIND_BY_STEP));
		}
	}

	public boolean choose(Rectangle2D chooseRectangle) {
		return getGraph().choose(chooseRectangle);
	}

	public void choose(ShapedComponent E) {
		getGraph().choose(E);
	}

	public void clearChoose() {
		getGraph().clearChoose();
	}

	public void connectGraph(ViewGraph newGraph) {
		newGraph.setPanel(this);
		viewGraph = newGraph;
		viewGraph.connectViewToPanel();
	}

	public void copy() {
		viewGraph.copy();

	}

	public void cut() {
		// TODO
	}

	public void delete() {
		viewGraph.delete();
	}

	public void disconnectGraph() {
		removeAll();
		viewGraph.setPanel(null);
		viewGraph = null;
	}

	public void drag(double dx, double dy) {
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				viewGraph.dragChoosenElementOn(dx, dy);
			}
		});
		a.start();

	}

	public void fixEdgePoint() {
		getGraph().fixEdgePoint();
	}

	public Action getActionEvent(String s) {
		return actionEvents.get(s);
	}

	public ViewNode getCurrentNode() {
		return viewGraph.getCurrentNode();
	}

	public ViewGraph getGraph() {
		return viewGraph;
	}

	public GraphPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			setPopupMenu(new GraphPopupMenu(this));
		}
		return popupMenu;
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	private void initializationEvents() {
		actionEvents = new HashMap<String, Action>();
		actionEvents.put(IDENTIFIER, new IdentifierAction(this));
		actionEvents.put(COPY_ACTION, new CopyAction(this));
		actionEvents.put(PASTE_ACTION, new PasteAction(this));
		actionEvents.put(CUT_ACTION, new CutAction(this));
		actionEvents.put(DELETE_ACTION, new DeleteAction(this));
		// actionEvents.put(FIND_MIN_PATH, new FindMinPathAction(this));
	}

	public void open(String s) {
		viewGraph.open(s);
	}

	public void paste() {
		viewGraph.paste();
	}

	public void putActionEvent(String s, Action a) {
		actionEvents.put(s, a);
	}

	public void save(String s) {
		viewGraph.save(s);
	}

	public void setContent(String s) {
		viewGraph.addName(s);
	}

	public void setCurrentNode(Component component) {
		viewGraph.setCurrentNode(component);
	}

	public void setExtraEdgePoint(int x, int y) {
		getGraph().setExtraEdgePoint(x, y);
	}

	public void setGraph(ViewGraph graph) {

		viewGraph = graph;
	}

	public void setPopupMenu(GraphPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}

	public void dragCatch() {
		viewGraph.dragCatch();
	}
}
