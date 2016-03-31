package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.view.main.PaintingPanel;

public class PasteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5625383626355208427L;
	private PaintingPanel panel;

	private PasteAction() {
		super();
		putValue(AbstractAction.NAME, "Paste");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('V'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public PasteAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.paste();
	}
}
