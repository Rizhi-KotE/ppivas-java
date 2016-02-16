package test;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.grapheditor.*;
public class TestPaint extends JFrame {

	PaintingPanel paintingPanel;

	public TestPaint() {
		super();

		setPreferredSize(new Dimension(640, 360));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setLayout(new GridBagLayout());
		pack();

		paintingPanel = new PaintingPanel();
		paintingPanel.addMouseListener(new MousePainting());
		add(paintingPanel);
		setVisible(true);
	}

	class MousePainting implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Shape p = new Ellipse2D.Double(e.getX(), e.getY(), 20, 20);
			paintingPanel.newShape(p);
			paintingPanel.repaint();
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

	}

		public static void main(String[] s) {
		TestPaint a = new TestPaint();
	}
}
