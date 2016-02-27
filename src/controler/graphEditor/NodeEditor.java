package controler.graphEditor;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.event.MouseInputListener;

import view.grapheditor.PaintingPanel;

class NodeEditor implements MouseInputListener {
	PaintingPanel ui;

	long lastClick;

	boolean isChoose = false;

	public NodeEditor(PaintingPanel ui) {
		this.ui = ui;
	}

	private Point2D oldP = null;

	public void mouseClicked(MouseEvent e) {
		/*long click = System.currentTimeMillis();
		int x = e.getX();
		int y = e.getY();
		ui.clearChoose();
		if ((click - lastClick) < 400)*/
			ui.addNode(e.getX(), e.getY());
		/*else {
			
			isChoose = ui.choose(x, y);
		}
		lastClick = click;*/
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		oldP = null;
		ui.setCurrentShape(null);
		isChoose = false;
	}

	public void mouseEntered(MouseEvent e) {
		// e.getComponent().requestFocus();
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (oldP == null) {
			oldP = new Point(x, y);
			isChoose=ui.choose(x, y);
		}
		int oldX = (int) oldP.getX();
		int oldY = (int) oldP.getY();
		
		if (isChoose) {
			ui.dragChoosenElementOn(x - oldX, y - oldY);
			oldP.setLocation(x, y);
		} else {
			ui.clearChoose();
			ui.choose(new Rectangle(Math.min(oldX, x), Math.min(oldY, y), Math.abs(oldX - x), Math.abs(oldY - y)));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		ui.clearHighlight();
		ui.highlight(x, y);
	}

}