package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import grapheditor.view.main.PaintingPanel;

public class NodeVertexesCount extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	PaintingPanel panel;

	private NodeVertexesCount() {
		super();
		putValue(AbstractAction.NAME, "Get nodes, vertexes count");
		setEnabled(true);
	}

	public NodeVertexesCount(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder builder = new StringBuilder();
		int edgesCount = panel.getGraph().getGraph().getEdges().size();
		int nodesCount = panel.getGraph().getGraph().getNodes().size();
		builder.append("Nodes count --- ");
		builder.append(nodesCount);
		builder.append("\n");
		builder.append("Edges count --- ");
		builder.append(edgesCount);
		JOptionPane.showMessageDialog(panel, builder.toString());
	}
}
