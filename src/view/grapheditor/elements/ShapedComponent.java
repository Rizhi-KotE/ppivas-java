package view.grapheditor.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import controler.graphEditor.GraphControlerFactory;

public class ShapedComponent extends JLabel implements Observer{
	private final String name = "ShapedComponent";
	private GraphElement shape;
	private Color color;

	private ShapedComponent() {
		super();
		addMouseListener(GraphControlerFactory.getInstance().getMouseInputListener("ShapedComponent"));
		addMouseMotionListener(GraphControlerFactory.getInstance().getMouseInputListener("ShapedComponent"));
	}

	public ShapedComponent(GraphElement s) {
		this();
		shape = s;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void currentColor(){
		color = Color.BLACK;
	}

	@Override
	public boolean contains(int x, int y) {
		boolean bl = shape != null;
		if (bl) {
			bl = shape.getShape().contains(x, y);
		}
		if(shape.getName().equals("Edge")){
			Line2D l = (Line2D)shape.getShape();
			bl = l.ptLineDist(x, y) < 10;
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
	
	public GraphElement getElement(){
		return shape;
	}
	public String getName(){
		return name;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
