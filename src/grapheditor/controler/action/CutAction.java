package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.view.main.PaintingPanel;

public class CutAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8983001788551795842L;
	private PaintingPanel panel;

	private CutAction() {
		super();
		putValue(AbstractAction.NAME, "Cut");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('X'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public CutAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.cut();
	}
}
