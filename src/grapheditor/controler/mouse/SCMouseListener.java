package grapheditor.controler.mouse;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.action.IdentifierAction;
import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.main.PaintingPanel;

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

	JPopupMenu menu;

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1: {
			PaintingPanel parent = (PaintingPanel) e.getComponent().getParent();
			parent.setCurrentNode(e.getComponent());
			MouseListener m[] = parent.getMouseListeners();
			for (MouseListener l : m) {
				l.mouseClicked(e);
			}
			break;
		}
		case MouseEvent.BUTTON3: {
			ShapedComponent s = (ShapedComponent) e.getComponent();
			menu = new JPopupMenu();
			JButton item = new JButton("I");
			item.addActionListener(
					new IdentifierAction());/*
											 * () { public void
											 * actionPerformed(ActionEvent a) {
											 * JDialog dialog = new JDialog();
											 * dialog.addWindowListener(new
											 * WindowListener() {
											 * 
											 * @Override public void
											 * windowOpened(WindowEvent e) { //
											 * TODO Auto-generated method stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowIconified(WindowEvent e) {
											 * // TODO Auto-generated method
											 * stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowDeiconified(WindowEvent e)
											 * { // TODO Auto-generated method
											 * stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowDeactivated(WindowEvent e)
											 * { // TODO Auto-generated method
											 * stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowClosing(WindowEvent e) { //
											 * TODO Auto-generated method stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowClosed(WindowEvent e) { //
											 * TODO Auto-generated method stub
											 * 
											 * }
											 * 
											 * @Override public void
											 * windowActivated(WindowEvent e) {
											 * // TODO Auto-generated method
											 * stub
											 * 
											 * } }); dialog.setModal(true);
											 * dialog.setVisible(true);
											 * s.setContent("Back"); } });
											 */
			menu.add(item);
			menu.show(e.getComponent(), e.getX(), e.getY());
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
		c.getElement().setColor(Color.YELLOW);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ShapedComponent c = (ShapedComponent) e.getComponent();
		c.getElement().currentColor();
	}
}
