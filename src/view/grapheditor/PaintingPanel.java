package view.grapheditor;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

public class PaintingPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	LinkedList<Shape> list = new LinkedList<Shape>();

	private LinkedList<Shape> newShapesList;

	public void newShape(Shape s) {
		if (newShapesList == null) {
			newShapesList = new LinkedList<Shape>();
		}
		newShapesList.addFirst(s);
		rebuildBuffer();
	}

	public LinkedList<Shape> getList() {
		return list;
	}

	public PaintingPanel() {

	}

	 private BufferedImage buffer;
	protected void paintComponent(Graphics g) {
		if(buffer != null){
			rebuildBuffer();
		}
		g.drawImage(buffer, 0, 0, this);
		
	}
	
	private void rebuildBuffer(){
		int w = getWidth();
		int h = getHeight();
		
		buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d =buffer.createGraphics();
		g2d.setStroke(new BasicStroke(20));

		if (newShapesList == null) {
			newShapesList = new LinkedList<Shape>();
		}

		Iterator<Shape> i = newShapesList.iterator();
		while (i.hasNext()) {
			Shape a = i.next();
			g2d.draw(a);
			list.add(a);
		}
		newShapesList.clear();

	}
	
	class ComponentListenerPP extends ComponentAdapter{

		@Override
		public void componentResized(ComponentEvent e) {
			
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
}
