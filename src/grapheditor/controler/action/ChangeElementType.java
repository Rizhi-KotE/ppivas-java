package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.main.PaintingPanel;

public class ChangeElementType extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	private PaintingPanel panel;

	private ChangeElementType() {
		super();
		putValue(AbstractAction.NAME, "Change type");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('T'));
		//putValue(ACCELERATOR_KEY, KeyStroke.get);
		setEnabled(false);
	}

	public ChangeElementType(PaintingPanel p) {
		this();
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<ViewGraphElement> list = panel.getChoosedElements();
		if(list.size()!=1){
			return;
		}
		List<String> variants = list.get(0).getTypesList();
		String s = (String)JOptionPane.showInputDialog(
		                    panel,
		                    "choose type",
		                    "Choise Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    new ImageIcon(),
		                    variants.toArray(new String[variants.size()]),
		                    "");
		list.get(0).setType(s);
	}
}
