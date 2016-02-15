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

public class testPaint extends JFrame {

	PaintingPanel paintingPanel;

	public testPaint() {
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
			paintingPanel.newShape(p);;
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

	class PaintingPanel extends JPanel {
		LinkedList<Shape> list = new LinkedList<Shape>();

		private LinkedList<Shape> newShapesList;

		public void newShape(Shape s) {
			if (newShapesList == null) {
				newShapesList = new LinkedList<Shape>();
			}
			newShapesList.addFirst(s);
		}

		public LinkedList<Shape> getList() {
			return list;
		}

		public PaintingPanel() {

		}

		protected void paintComponent(Graphics g) {

			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(20));

			if(newShapesList == null){
				newShapesList = new LinkedList<Shape>();
			}
			
			Iterator<Shape> i = newShapesList.iterator();
			while(i.hasNext()){
				Shape a = i.next();
				g2d.draw(a);
				list.add(a);
			}
			newShapesList.clear();
		}
	}

	public static void main(String[] s) {
		testPaint a = new testPaint();
	}
}
