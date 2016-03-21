package grapheditor.controler.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import grapheditor.controler.mouse.AlgoMinPathFindListener;
import prop.KeyStrokeProperty;

public class StepAlgoAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885620313037850096L;
	private AlgoMinPathFindListener listener;

	private StepAlgoAction() {
		super();
		putValue(AbstractAction.NAME, "Step finding");
		putValue(AbstractAction.MNEMONIC_KEY, new Integer('S'));
		putValue(ACCELERATOR_KEY, KeyStrokeProperty.STEP_ALGO_ACTION);
	}

	public StepAlgoAction(AlgoMinPathFindListener listener) {
		this();
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.stepAlgo();
	}
}
