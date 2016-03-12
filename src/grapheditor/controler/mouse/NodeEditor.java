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

class NodeEditor implements MouseInputListener {
	PaintingPanel panel;

	long lastClick;

	boolean isChoose = false;

	public NodeEditor(PaintingPanel panel) {
		this.panel = panel;
	}

	public void mouseClicked(MouseEvent e) {
		int count = e.getClickCount();
		if (e.getSource().equals(panel)) {
			if (count == 2) {
				panel.addNode(e.getX(), e.getY());
				return;
			}
			panel.clearChoose();
		} else if (e.getSource() instanceof ShapedComponent) {
			if (false) {

			} else {
				panel.clearChoose();
				panel.choose((ShapedComponent) e.getComponent());
			}
		}

		/*
		 * else {
		 * 
		 * isChoose = panel.choose(x, y); } lastClick = click;
		 */
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		oldP = null;
		if(choosePanel!=null){
			JComponent c = (JComponent)e.getComponent();
			c.remove(choosePanel);
			choosePanel=null;
			c.repaint();
		}
		if(chooseRectangle!=null){
			chooseRectangle=null;
		}
	}

	public void mouseEntered(MouseEvent e) {
		// e.getComponent().requestFocus();
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private Point2D oldP;
	private ShapedComponent choosePanel;
	private Rectangle2D chooseRectangle;
	@Override
	public void mouseDragged(MouseEvent e) {
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
		if(chooseRectangle==null){
			chooseRectangle = new Rectangle2D.Double(Math.min(oldP.getX(), x), Math.min(oldP.getY(), y), w, h);
		}
		else{
			chooseRectangle.setRect(Math.min(oldP.getX(), x), Math.min(oldP.getY(), y), w, h);
		}
		choosePanel.setShape(new ViewGraphElement() {

			@Override
			public Shape getShape() {
				return chooseRectangle;
			}

			@Override
			public String getName() {
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
		});
		int oldX = (int) oldP.getX();
		int oldY = (int) oldP.getY();

		if (isChoose) {
			//panel.dragChoosenElementOn(x - oldX, y - oldY);
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