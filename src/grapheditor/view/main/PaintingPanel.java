package grapheditor.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.action.CopyAction;
import grapheditor.controler.action.CutAction;
import grapheditor.controler.action.DeleteAction;
import grapheditor.controler.action.IdentifierAction;
import grapheditor.controler.action.PasteAction;
import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.menu.GraphPopupMenu;

public class PaintingPanel extends JPanel implements Scrollable {

	private static final long serialVersionUID = 1L;

	public static final String IDENTIFIER = "IdentifierAction";
	public static final String COPY_ACTION = "CopyAction";
	public static final String PASTE_ACTION = "PasteAction";
	public static final String CUT_ACTION = "CutAction";
	public static final String DELETE_ACTION = "DeleteAction";
	private ViewGraph viewGraph;
	private Map<String, Action> actionEvents;

	private GraphPopupMenu popupMenu;

	public PaintingPanel() {
		super();
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
		initializationEvents();
	}

	public void addEdge() {
		getGraph().addEdge();
	}

	public void addNode(double x, double y) {
		getGraph().addNode(x, y);
	}

	public void changeMouseListener(MouseInputListener listener) {
		MouseListener[] l1 = getMouseListeners();
		boolean b = false;
		for (MouseListener i : l1) {
			if (!i.equals(listener)) {
				removeMouseListener(i);
				b |= true;
			}
		}
		MouseMotionListener[] l2 = getMouseMotionListeners();
		for (MouseMotionListener i : l2) {
			if (!i.equals(listener)) {
				removeMouseMotionListener(i);
				b |= true;
			}
		}
		if (b) {
			addMouseListener(listener);
			addMouseMotionListener(listener);
			viewGraph.changeListener();
		}
	}

	public boolean choose(Rectangle rect) {
		return getGraph().choose(rect);
	}

	public void choose(ShapedComponent E) {
		getGraph().choose(E);
	}

	public void clearChoose() {
		getGraph().clearChoose();
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

	public void drag(double dx, double dy) {
		viewGraph.dragChoosenElementOn(dx, dy);
	}

	public void fixEdgePoint() {
		getGraph().fixEdgePoint();
	}

	public Action getActionEvent(String s) {
		return actionEvents.get(s);
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

	
	public void disconnectGraph(){
		removeAll();
		viewGraph.setPanel(null);
		viewGraph = null;
	}
	
	public void connectGraph(ViewGraph newGraph){
		newGraph.setPanel(this);
		viewGraph = newGraph;
		viewGraph.connectViewToPanel();
	}
	public void setGraph(ViewGraph graph) {
		
		viewGraph = graph;
	}

	public void setPopupMenu(GraphPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}
}
