package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import grapheditor.view.main.PaintingPanel;

public class GetUdjustmentListAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	PaintingPanel panel;

	private GetUdjustmentListAction() {
		super();
		putValue(AbstractAction.NAME, "Get adjust list");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('M'));
	}

	public GetUdjustmentListAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<ArrayList<Integer>> list = panel.getGraph().getGraph().createAdjustmenList();
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
