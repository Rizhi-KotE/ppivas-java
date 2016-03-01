package controler.graphEditor;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MouseInputListener;

import view.grapheditor.PaintingPanel;
import view.grapheditor.elements.ShapedComponent;

public class SCMouseListener implements MouseInputListener {
	public SCMouseListener() {

	}

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

	JMenu menu;
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()){
		case MouseEvent.BUTTON1:{
			PaintingPanel parent = (PaintingPanel) e.getComponent().getParent();
			parent.setCurrentNode(e.getComponent());
			MouseListener m[] = parent.getMouseListeners();
			for (MouseListener l : m) {
				l.mouseClicked(e);
			}
			break;
		}
		case MouseEvent.BUTTON3:{
			ShapedComponent s = (ShapedComponent)e.getComponent();
			//menu = new JMenu();
			JButton item = new JButton("I");
			item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent a) {
					
					s.setContent("Back");			
				}
			});
			//menu.add(item);
			s.getParent().add(item);
			s.getParent().revalidate();
		}
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
		c.setColor(Color.YELLOW);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.currentColor();
	}
}
