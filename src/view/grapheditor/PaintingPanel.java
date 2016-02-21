package view.grapheditor;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Graph;

public class PaintingPanel extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	private Graph graph;
	
	public PaintingPanel() {
		super();
		GraphPanelUI ui = new GraphPanelUI(this);
		setUI(ui);
		
		graph = new Graph();
		graph.addObserver(this);
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
	
	//------MouseListener--------
}
