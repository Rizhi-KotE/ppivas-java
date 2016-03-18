package grapheditor.view.represent;

import java.awt.Graphics2D;
import java.awt.Image;

public interface ViewGraphElementRepresent {
	public void paintYourSelf(Graphics2D g2d);
	boolean contains(double x, double y);
}
