package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import grapheditor.view.main.PaintingPanel;

public class IncidenceMatrixAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	PaintingPanel panel;

	private IncidenceMatrixAction() {
		super();
		putValue(AbstractAction.NAME, "Get incident matrix");
		setEnabled(true);
	}

	public IncidenceMatrixAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<ArrayList<Integer>> adjustmentMatrix = panel.getGraph().getGraph().createIncidentMatrix();
		StringBuilder builder = new StringBuilder();
		Iterator<ArrayList<Integer>> rowIt = adjustmentMatrix.iterator();
		while(rowIt.hasNext()){
			Iterator<Integer> nodeIterator = rowIt.next().iterator();
			while(nodeIterator.hasNext()){
				builder.append(nodeIterator.next());
				builder.append(" ");
			}
			builder.append("\n");
		}
		JOptionPane.showMessageDialog(panel, builder.toString());
	}
	
	class Pair {
		int node1;
		int node2;
	}
}
