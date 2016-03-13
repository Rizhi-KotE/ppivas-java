package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Nfaf extends JPanel {
	public Nfaf() {
		super();

	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		if (image == null) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			// } else {
			image.createGraphics().setColor(Color.black);
			image.createGraphics().fillOval(50, 50, 50, 50);
		}
		g.drawImage(image, 0, 0, null);
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
