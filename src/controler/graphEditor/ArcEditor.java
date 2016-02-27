package controler.graphEditor;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import view.grapheditor.PaintingPanel;

public class ArcEditor implements MouseInputListener{

	PaintingPanel panel = null;
	
	public ArcEditor(PaintingPanel panel) {
		this.panel = panel;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		panel.addEdge();
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
		panel.setEdgePoint(e.getX(),e.getY());
	}

}
