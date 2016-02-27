package controler.graphEditor;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import view.grapheditor.PaintingPanel;

public class ArcEditor implements MouseInputListener{

	PaintingPanel ui = null;
	
	public ArcEditor(PaintingPanel ui) {
		this.ui = ui;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ui.clearChoose();
		int x = e.getX();
		int y = e.getY();
		ui.createEdge(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
