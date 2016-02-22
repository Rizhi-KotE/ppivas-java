package view.grapheditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;

public class GraphToolPanel extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8654195566424273747L;

	PaintingPanel panel;
	public GraphToolPanel(PaintingPanel anPanel) {
		panel = anPanel;
		
		
		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", panel.getUI());
				panel.changeMouseListener(listener);
			}
		});
		add(but1);
		
		//but1 = null;
		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Arc_tool", panel.getUI());
				panel.changeMouseListener(listener);
			}
		});
		add(but2);
		//add(new JButton(new ImageIcon("image/tool-pair.png")));
		//add(new Separator());
		//JButton button = (new JButton(new ImageIcon("image/tool-zoom-in.png")));
		/*ImageIcon image = new ImageIcon("image/tool-zoom-in.png");
		button.setSize(but.getSize());
		add(button);
		add(new JButton(new ImageIcon("image/tool-zoom-out.png")));*/
	}
}
