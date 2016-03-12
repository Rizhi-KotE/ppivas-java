package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Nfaf extends JPanel {
	public Nfaf() {
		super();

	}

	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		if (image == null) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		} else {
			image.createGraphics().setColor(Color.black);
			image.createGraphics().fillOval(50, 50, 50, 50);
		}
		g.drawImage(image, 0, 0, null);
		g.drawLine(50, 50, 68, 68);
	}

	private BufferedImage image;

	public static void main(String[] a) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setPreferredSize(new Dimension(640, 640));
		f.add(new Nfaf());
		f.pack();
	}
}
