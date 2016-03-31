package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.view.main.PaintingPanel;

public class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5720082392659214007L;
	private PaintingPanel panel;

	private DeleteAction() {
		super();
		putValue(AbstractAction.NAME, "Delete");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('D'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public DeleteAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.delete();
	}
}
