package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.view.main.PaintingPanel;

public class ChangeEdgeOrientationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	private PaintingPanel panel;

	private ChangeEdgeOrientationAction() {
		super();
		putValue(AbstractAction.NAME, "Change edge orientation");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('C'));
		setEnabled(false);
	}

	public ChangeEdgeOrientationAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.getGraph().changeEdgeOrientation();
	}
}
