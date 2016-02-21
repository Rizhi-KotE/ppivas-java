package view.grapheditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import model.Graph;

public class PaintingPanel extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	private Graph graph;
	GraphPanelUI ui;
	
	public PaintingPanel() {
		super();
		ui = new GraphPanelUI(this);
		setUI(ui);
		
		graph = new Graph();
		graph.addObserver(this);
		
		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("MouseListener", ui);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}

	
	//------Observer-----------
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		graph.deleteObserver(this);
		
		this.graph = graph;
	}
	
	public void update(Observable o, Object arg) {
		if(graph == null){
			if(o.getClass().getName().equals("model.Graph"));
			setGraph((Graph)o);
		}
		repaint();
	}
	
	
}
