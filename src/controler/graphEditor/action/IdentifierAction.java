package controler.graphEditor.action;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import grapheditor.view.main.PaintingPanel;

public class IdentifierAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8173193772933710132L;
	
	private PaintingPanel panel;
	
	private IdentifierAction() {
		addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public IdentifierAction(PaintingPanel p){
		this();
		panel = p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog d = new JDialog((Frame) panel.getParent(), "Идентификатор", true);
	}

}
