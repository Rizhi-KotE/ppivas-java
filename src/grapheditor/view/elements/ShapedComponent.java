package grapheditor.view.elements;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.main.PaintingPanel;

public class ShapedComponent extends JLabel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6388626636136514844L;

	private final String name = "ShapedComponent";
	private ViewGraphElement shape;

	public ShapedComponent() {
		super();
		addMouseListener(GraphControlerFactory.getInstance().getMouseInputListener("ShapedComponent"));
		addMouseMotionListener(GraphControlerFactory.getInstance().getMouseInputListener("ShapedComponent"));
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("hidden");
			}
		});
	}

	public ShapedComponent(ViewGraphElement s) {
		this();
		shape = s;
	}

	public void setShape(ViewGraphElement viewGraphElement) {
		if (shape != null) {
			shape.deleteObserver(this);
		}
		viewGraphElement.addObserver(this);
		shape = viewGraphElement;
		repaint();
	}

	@Override
	public boolean contains(int x, int y) {
		return shape.contains(x, y);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(shape.getColor());
		g2d.setStroke(new BasicStroke(5));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (shape != null) {
			shape.paintYourSelf(g2d);
		}
	}

	private void paintContent(Graphics2D g2d, float x, float y) {
		String s = shape.getContent();
		if (s != null)
			g2d.drawString(s, x, y);
	}

	public ViewGraphElement getElement() {
		return shape;
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (getElement().isChoosed() == true) {
			PaintingPanel p = (PaintingPanel) getParent();
			if (p == null) {
				return;
			}
			//p.choose(this);
		}
		if (getElement().isDeleted() == true) {
			PaintingPanel p = (PaintingPanel) getParent();
			if (p == null) {
				return;
			}
			p.remove(this);
			p.revalidate();
			return;
		}
		repaint();
	}

	@Override
	public int hashCode() {
		return getElement().hashCode();
	}

	//////////////////////////////////
	public void setContent(String s) {
		if (shape != null) {
			shape.setContent(s);
		}
	}
}
