package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.grapheditor.PaintingPanel;

class WinOfAplication {

	private JFrame mainFrame;
	private JMenuBar menuBar;
	private PaintingPanel graphPanel;

	public WinOfAplication() {
		mainFrame = loadFrame();
		menuBar = loadMenuBar();
		mainFrame.pack();
	}

	private JFrame loadFrame() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(640, 360));
		frame.setVisible(true);
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

	public void newGraph() {
		graphPanel = new PaintingPanel();
		mainFrame.add(graphPanel);
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