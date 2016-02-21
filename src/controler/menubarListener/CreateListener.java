package controler.menubarListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.WinOfAplication;

public class CreateListener implements ActionListener {

	public CreateListener() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		WinOfAplication.getInstance().newGraph();
	}

}
