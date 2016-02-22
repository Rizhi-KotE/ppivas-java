package view.grapheditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import model.Graph;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Graph graph;
	GraphPanelUI ui;

	private JToolBar toolBar;

	public PaintingPanel() {
		super();
		ui = new GraphPanelUI(this);
		setUI(ui);

		graph = new Graph();
		graph.addObserver(this);

		setBackground(new Color(255, 255, 255));

		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", ui);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);

		setLayout(new BorderLayout());
		
		toolBar = new GraphToolPanel(this);
		add(toolBar, BorderLayout.NORTH);

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
	
	protected void changeMouseListener(MouseInputListener listener){
		MouseListener[] l1 = getMouseListeners();
		for(MouseListener i : l1){
			removeMouseListener(i);
		}
		MouseMotionListener[] l2 = getMouseMotionListeners();
		for(MouseMotionListener i : l2){
			removeMouseMotionListener(i);
		}
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

}
