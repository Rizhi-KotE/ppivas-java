package grapheditor.controler.mouse;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import grapheditor.view.main.PaintingPanel;

public class ArcEditor implements MouseInputListener{

	PaintingPanel panel = null;
	
	public ArcEditor(PaintingPanel panel) {
		this.panel = panel;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.fixEdgePoint();;
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
		panel.setExtraEdgePoint(e.getX(),e.getY());
	}

}
