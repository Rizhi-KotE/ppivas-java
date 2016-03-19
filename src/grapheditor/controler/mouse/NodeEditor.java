package grapheditor.controler.mouse;

import java.awt.Graphics2D;
import java.awt.Image;
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
import grapheditor.view.represent.RectangleRepresent;
import grapheditor.view.represent.ViewGraphElementRepresent;

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
		isRectChoose = false;
	}

	public void mouseEntered(MouseEvent e) {
		// e.getComponent().requestFocus();
	}

	public void mouseExited(MouseEvent e) {

	}

	private Point2D oldP;
	private ShapedComponent choosePanel;
	private Rectangle2D chooseRectangle;
	private boolean isRectChoose = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isRectChoose == false) {
			if (e.getComponent().getClass().equals(ShapedComponent.class)) {
				if (oldP == null) {
					oldP = new Point2D.Double(e.getX(), e.getY());
				}
				panel.drag(e.getX() - oldP.getX(), e.getY() - oldP.getY());
				oldP = new Point2D.Double(e.getX(), e.getY());

				return;
			} else {
				isRectChoose = true;
			}
		}

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

			ViewGraphElementRepresent represent = new RectangleRepresent(this);
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
				return false;
			}

			public void drag(double dx, double dy) {

			}

			@Override
			public boolean contains(double x, double y) {
				return represent.contains(x, y);
			}

			@Override
			public void paintYourSelf(Graphics2D g2d) {
				represent.paintYourSelf(g2d);
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