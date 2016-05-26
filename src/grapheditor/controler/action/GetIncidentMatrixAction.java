package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import grapheditor.view.main.PaintingPanel;

public class GetIncidentMatrixAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	PaintingPanel panel;

	private GetIncidentMatrixAction() {
		super();
		putValue(AbstractAction.NAME, "Get incident matrix");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('M'));
	}

	public GetIncidentMatrixAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<ArrayList<Integer>> list = panel.getGraph().getGraph().createIncidentMatrix();
		StringBuilder builder = new StringBuilder();
		Iterator<ArrayList<Integer>> rowIt = list.iterator();
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
}
