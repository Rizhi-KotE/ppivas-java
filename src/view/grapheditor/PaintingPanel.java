package view.grapheditor;

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
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import view.grapheditor.elements.ShapedComponent;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private ViewGraph viewGraph;

	JPopupMenu loadMenuBar() {
		JPopupMenu currentMenu = new JPopupMenu("Файл");

		JMenuItem menuItem = new JMenuItem("Создать");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("Открыть");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("Закрыть");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		currentMenu.add(menuItem);

		currentMenu.addSeparator();

		menuItem = new JMenuItem("Выход");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		currentMenu.add(menuItem);
		return currentMenu;
	}

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
		System.out.println(getLayout().getClass().getName());
		setComponentPopupMenu(loadMenuBar());
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

	public void setEdgePoint(int x, int y) {
		getGraph().setEdgePoint(x, y);
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

}
