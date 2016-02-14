package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TwoButtonsFlip extends JFrame {

	private static final long serialVersionUID = 4367936803677964688L;
	JPanel buttonsPanel;
	JTextField textPanel;

	public TwoButtonsFlip() {
		super();
		textPanel = new JTextField();
		add(textPanel, BorderLayout.NORTH);

		buttonsPanel = new JPanel();
		buttonsPanel.add(new JButton());
		buttonsPanel.add(new JButton());

		add(buttonsPanel, BorderLayout.SOUTH);

		setVisible(true);
		
		
	}
	
}
