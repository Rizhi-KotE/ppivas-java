package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.view.main.PaintingPanel;

public class CopyAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	private PaintingPanel panel;

	private CopyAction() {
		super();
		putValue(AbstractAction.NAME, "Copy");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('C'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public CopyAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.copy();
	}
}
