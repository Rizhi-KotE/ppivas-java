package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import grapheditor.controler.mouse.AlgoMinPathFindListener;

public class FindMinPathAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	private AlgoMinPathFindListener listener;

	private FindMinPathAction() {
		super();
		putValue(AbstractAction.NAME, "Start finding");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('F'));
		//putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
	}

	public FindMinPathAction(AlgoMinPathFindListener listener) {
		this();
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.startAlgo();
	}
}
