package grapheditor.controler.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class IdentifierAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource().getClass().getName());
	}

}
