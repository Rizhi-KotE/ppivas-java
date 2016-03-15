package grapheditor.controler.mouse;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import grapheditor.view.elements.ShapedComponent;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.main.PaintingPanel;

import grapheditor.view.menu.GraphPopupMenu;

class NodeEditor implements MouseInputListener {
	PaintingPanel panel;

	long lastClick;

	boolean isChoose = false;

	public NodeEditor(PaintingPanel panel) {
		this.panel = panel;
	}

	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1: {
			int count = e.getClickCount();
			if (e.getSource().equals(panel)) {
				if (count == 2) {
					panel.addNode(e.getX(), e.getY());
					return;
				}
				panel.clearChoose();
			} else if (e.getSource() instanceof ShapedComponent) {
				panel.choose((ShapedComponent) e.getComponent());
			}
			break;
		}
		case MouseEvent.BUTTON3: {
			int type = GraphPopupMenu.COPY_PASTE;
			if (e.getSource() instanceof ShapedComponent) {
				panel.choose((ShapedComponent) e.getComponent());
				type |= GraphPopupMenu.NODE_OPERATION;
			}
			panel.getPopupMenu().show(panel, e.getX(), e.getY(), type);
			break;
		}
		default:
			break;
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		oldP = null;
		if (choosePanel != null) {
			JComponent c = (JComponent) e.getComponent();
			c.remove(choosePanel);
			choosePanel = null;
			c.repaint();
		}
		if (chooseRectangle != null) {
			chooseRectangle = null;
		}
		oldP = null;
		rectChoose = false;
	}

	public void mouseEntered(MouseEvent e) {
		// e.getComponent().requestFocus();
	}

	public void mouseExited(MouseEvent e) {
		oldP = null;

	}

	private Point2D oldP;
	private ShapedComponent choosePanel;
	private Rectangle2D chooseRectangle;
	private boolean rectChoose = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		if (rectChoose == false) {
			if (e.getComponent().getClass().equals(ShapedComponent.class)) {
				if (oldP == null) {
					oldP = new Point2D.Double(e.getX(), e.getY());
				}
				panel.drag(e.getX() - oldP.getX(), e.getY() - oldP.getY());
				oldP = new Point2D.Double(e.getX(), e.getY());
				return;
			}
		}
		rectChoose = true;
		int x = e.getX();
		int y = e.getY();
		if (oldP == null) {
			oldP = new Point(x, y);
		}
		double w = Math.abs(oldP.getX() - x);
		double h = Math.abs(oldP.getY() - y);
		if (choosePanel == null) {
			panel.clearChoose();
			choosePanel = new ShapedComponent();
			JComponent a = (JComponent) e.getComponent();
			a.add(choosePanel);
		}
		if (chooseRectangle == null) {
			chooseRectangle = new Rectangle2D.Double(Math.min(oldP.getX(), x), Math.min(oldP.getY(), y), w, h);
		} else {
			chooseRectangle.setRect(Math.min(oldP.getX(), x), Math.min(oldP.getY(), y), w, h);
		}
		choosePanel.setShape(new ViewGraphElement() {

			@Override
			public Shape getShape() {
				return chooseRectangle;
			}

			@Override
			public String getType() {
				return "rand";
			}

			@Override
			public boolean isChoosed() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(int x, int y) {
				return false;
			}

			public void drag(double dx, double dy) {
				// TODO Auto-generated method stub

			}
		});
		int oldX = (int) oldP.getX();
		int oldY = (int) oldP.getY();

		if (isChoose) {
			oldP.setLocation(x, y);
		} else {
			panel.clearChoose();
			panel.choose(new Rectangle(Math.min(oldX, x), Math.min(oldY, y), Math.abs(oldX - x), Math.abs(oldY - y)));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}