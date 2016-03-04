package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class ShapedButton extends JLabel{
	Shape shape;
	
	private ShapedButton(){
		super();
		setMaximumSize(new Dimension(50,50));
		setMinimumSize(new Dimension(50,50));
	}
	
	public ShapedButton(Shape s){
		this();
		shape = s;
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("succes");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("enter");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	  public boolean contains(final int x, final int y) {
	    if ((this.shape == null) || !this.shape.getBounds().equals(this.getBounds())) {
	      return this.shape.contains(x, y);
	    }
	    return super.contains(x, y);
	  }
	
	@Override
	  public void paintComponent(final Graphics g) {
		g.setColor(Color.green);
	    if ((shape != null) && (shape != null)) {
	     ((Graphics2D) g).draw(shape);
	    } else {
	      super.paintComponent(g);
	    }
	  }
}
