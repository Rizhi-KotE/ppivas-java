package grapheditor.controler.mouse;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import Exception.NoPathException;
import grapheditor.algo.FindMinPathAlgo;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;
import grapheditor.view.main.PaintingPanel;
import grapheditor.view.menu.GraphPopupMenu;

public class AlgoMinPathFindListener implements MouseInputListener {

	private PaintingPanel panel;
	private ViewNode start;
	private ViewNode end;
	private FindMinPathAlgo algo;

	public AlgoMinPathFindListener(PaintingPanel panel, FindMinPathAlgo a) {
		this.panel = panel;
		algo = a;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftClicked(e);
			break;
		case MouseEvent.BUTTON3:
			rightClicked(e);
			break;
		}
	}

	private void leftClicked(MouseEvent e) {
		ViewNode node = panel.getCurrentNode();
		if (node != null) {
			if (start == null) {
				start = node;
				start.setColor(Color.cyan);
				start.setFixColor(true);
				return;
			} else if (!start.equals(node)) {
				if (end == null) {
					end = node;
					end.setColor(Color.red);
					end.setFixColor(true);
				}
				return;
			}
		}
	}

	private void rightClicked(MouseEvent e) {
		panel.getPopupMenu().show(panel, e.getX(), e.getY(), GraphPopupMenu.START_ALGO);
	}

	public void startAlgo() {
		if ((start == null) || (end == null)) {
			JOptionPane.showMessageDialog(panel.getParent(), "Алгоритм не проинициализирован");
			return;
		}
		try {
			algo.find(panel.getGraph().getGraph(), start, end);
		} catch (NoPathException e) {
			JOptionPane.showMessageDialog(panel.getParent(), "Пути не существует. Задайте другие данные");
		}
	}
	
	public void stepAlgo() {
		if ((start == null) || (end == null)) {
			JOptionPane.showMessageDialog(panel.getParent(), "Алгоритм не проинициализирован");
			return;
		}
		try {
			algo.nextStep(panel.getGraph().getGraph(), start, end);
		} catch (NoPathException e) {
			JOptionPane.showMessageDialog(panel.getParent(), "Пути не существует. Задайте другие данные");
		}
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
		// TODO Auto-generated method stub

	}

}
