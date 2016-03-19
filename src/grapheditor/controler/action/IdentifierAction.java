package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import grapheditor.view.main.PaintingPanel;

public class IdentifierAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4488916415187971948L;
	private PaintingPanel panel;

	private IdentifierAction() {
		super();
		putValue(AbstractAction.NAME, "Identifier");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('I'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public IdentifierAction(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = new String();
		s = JOptionPane.showInputDialog(panel.getParent(), "Identifier");
		try {
			panel.setContent(s);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(panel.getParent(), e1.getMessage());
		}
	}
}
