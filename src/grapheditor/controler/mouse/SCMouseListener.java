package grapheditor.controler.mouse;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.main.PaintingPanel;

public class SCMouseListener implements MouseInputListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		PaintingPanel parent = (PaintingPanel) e.getComponent().getParent();
		MouseMotionListener m[] = parent.getMouseMotionListeners();
		for (MouseMotionListener l : m) {
			l.mouseMoved(e);
		}

	}

	JPopupMenu menu;

	@Override
	public void mouseClicked(MouseEvent e) {
		PaintingPanel parent = (PaintingPanel) e.getComponent().getParent();
		parent.setCurrentNode(e.getComponent());
		MouseListener m[] = parent.getMouseListeners();
		for (MouseListener l : m) {
			l.mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// redirectEvent(e.getComponent().getParent(), e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.getElement().setColor(Color.YELLOW);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.getElement().currentColor();
	}
}
