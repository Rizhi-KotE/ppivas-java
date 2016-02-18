package view.grapheditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

public class GraphPanelUI extends PanelUI{
	public void paint(Graphics g, JComponent c) {

		Graphics2D g2d = (Graphics2D)g;
		Shape a = new Ellipse2D.Double(10,10,10,10);
		g2d.draw(a);
		g.setColor(Color.BLACK);
		g.fillOval(10, 10, 10, 10);
		super.paint(g, c);
	}
}
