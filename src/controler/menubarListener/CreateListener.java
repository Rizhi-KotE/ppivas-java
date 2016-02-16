package controler.menubarListener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import view.WinOfAplication;
import view.grapheditor.GraphEditorDoc;

public class CreateListener implements ActionListener {

	public CreateListener() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Container frame = WinOfAplication.getContainer("mainframe");
		GraphEditorDoc panel = new GraphEditorDoc();
        frame.add(panel);
	}

}
