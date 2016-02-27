package controler.graphEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

import view.grapheditor.PaintingPanel;
import view.grapheditor.elements.ShapedComponent;

public class SCMouseListener implements MouseInputListener{
	public SCMouseListener(){
		
	}
	
	private void redirectEvent(Component c, MouseEvent e){
		int id = e.getID();
		long when = e.getWhen();
		int modifiers = e.getModifiers();
		int x = e.getX();
		int y = e.getY();
		int clickCount = e.getClickCount();
		boolean popupTrigger = e.isPopupTrigger();
		MouseEvent f = new MouseEvent(c, id, when, modifiers, x, y, clickCount, popupTrigger);
		c.getToolkit().getSystemEventQueue().postEvent(f);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		PaintingPanel parent = (PaintingPanel) e.getComponent().getParent();
		parent.setCurrentNode(e.getComponent());
		redirectEvent(parent, e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.setColor(Color.GREEN);
		c.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.currentColor();
		c.repaint();
	}
}
