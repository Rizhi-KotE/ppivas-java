package view.grapheditor.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import controler.graphEditor.GraphControlerFactory;
import model.GraphElement;
import view.grapheditor.PaintingPanel;

public class ShapedComponent extends JLabel implements Observer {
	private final String name = "ShapedComponent";
	private GraphElement shape;
	private Color color;
	private boolean choose;

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

	public ShapedComponent(GraphElement s) {
		this();
		s.addObserver(this);
		shape = s;
	}

	public void setShape(GraphElement s) {
		if (shape != null) {
			shape.deleteObserver(this);
		}
		s.addObserver(this);
		shape = s;
		repaint();
	}

	public void setChoose(boolean is) {
		choose = is;
	}

	public void setColor(Color c) {
		color = c;
		repaint();
	}

	public void currentColor() {
		if (choose == false) {
			color = Color.BLACK;
			repaint();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		boolean bl = shape != null;
		if (bl) {
			bl = shape.getShape().contains(x, y);
			if (shape.getName().equals("Edge")) {
				Line2D l = (Line2D) shape.getShape();
				bl = l.ptLineDist(x, y) < 5;
			}
		}

		return bl;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		if (shape != null) {
			g2d.draw(shape.getShape());
		}
	}

	public GraphElement getElement() {
		return shape;
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (getElement().isChoosed() == true) {
			PaintingPanel p = (PaintingPanel) getParent();
			p.choose(this);
			return;
		}
		repaint();
	}

	@Override
	public int hashCode() {
		return getElement().hashCode();
	}
}
