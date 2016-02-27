package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import view.grapheditor.PaintingPanel;

class WinOfAplication {

	private JFrame mainFrame;
	private JMenuBar menuBar;
	private PaintingPanel graphPanel;
	private JToolBar toolBar;

	public WinOfAplication() {
		mainFrame = loadFrame();
		menuBar = loadMenuBar();
		mainFrame.pack();
	}

	private JFrame loadFrame() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(640, 360));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	JMenuBar loadMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu currentMenu = new JMenu("Файл");

		JMenuItem menuItem = new JMenuItem("Создать");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newGraph();
			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("Открыть");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile();

			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("Закрыть");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFile();

			}
		});
		currentMenu.add(menuItem);

		currentMenu.addSeparator();

		menuItem = new JMenuItem("Выход");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		currentMenu.add(menuItem);

		menuBar.add(currentMenu);
		mainFrame.setJMenuBar(menuBar);
		return menuBar;
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();

		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool",
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but1);

		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Arc_tool",
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but2);
		return toolBar;
	}
	public void newGraph() {
		graphPanel = new PaintingPanel();
		mainFrame.add(graphPanel);
		toolBar = createToolBar();
		mainFrame.add(toolBar, BorderLayout.NORTH);
		mainFrame.pack();
	}

	private void openFile() {
		// TODO
	}

	private void closeFile() {
		// TODO
	}
	
	

	static public void main(String[] args) {
		WinOfAplication f = new WinOfAplication();
	}
}