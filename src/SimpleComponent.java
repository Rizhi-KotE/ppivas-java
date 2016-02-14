

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;

/**
 * SimpleComponent
 *
 * @author Eugene Matyushkin aka Skipy
 * @since 21.10.2010
 */
public class SimpleComponent extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        GeneralPath gp = new GeneralPath();
        double dx = getWidth() / 8.0;
        double dy = getHeight() / 8.0;
        gp.moveTo(dx, dy);
        gp.lineTo(dx * 2, dy);
        gp.quadTo(dx, dy * 4, dx * 4, dy * 4);
        gp.quadTo(dx * 7, dy * 4, dx * 6, dy);
        gp.lineTo(dx * 7, dy);
        gp.lineTo(dx * 7, dy * 7);
        gp.lineTo(dx * 6, dy * 7);
        gp.lineTo(dx * 6, dy * 6);
        gp.lineTo(dx * 2, dy * 6);
        gp.lineTo(dx * 2, dy * 7);
        gp.lineTo(dx, dy * 7);
        gp.lineTo(dx, dy);
        g2d.setClip(gp);
        //g2d.setClip(0,0, getWidth(), getHeight() * 2);
        //g2d.setClip(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.blue);
        g2d.fillOval(10, 10, getWidth() - 20, getHeight() * 2 - 20);
        g2d.setColor(Color.red);
        g2d.fillOval(20, 20, getWidth() - 40, getHeight() - 40);
        g2d.setColor(Color.yellow);
        g2d.fillOval(30, 30, getWidth() - 60, getHeight() - 60);
        g2d.setColor(Color.black);
        g2d.fillOval(getWidth()/4 - getWidth()/16, getHeight()/2-getHeight()/8, getWidth()/8, getHeight()/8);
        g2d.fillOval(getWidth()*3/4 - getWidth()/16, getHeight()/2-getHeight()/8, getWidth()/8, getHeight()/8);
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawArc(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, 225, 90);
        
    }
}