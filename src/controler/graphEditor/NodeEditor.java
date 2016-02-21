package controler.graphEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import view.grapheditor.GraphPanelUI;

class NodeEditor implements MouseInputListener {
	GraphPanelUI ui;

	public NodeEditor(GraphPanelUI ui) {
		this.ui = ui;
	}

	private int oldX, oldY;

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		ui.addNode(x, y);
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		ui.choose(x, y);
	}

}