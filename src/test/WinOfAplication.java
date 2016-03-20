package test;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.main.PaintingPanel;

class WinOfAplication {

	class TabPanel extends JPanel{
		PaintingPanel paintingPanel;
		JToolBar toolBar;
		
		public TabPanel(PaintingPanel panel) {
			super(new BorderLayout());
			paintingPanel = panel;
			toolBar = createToolBar(paintingPanel);
			JScrollPane scrolPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			add(scrolPane);
			add(toolBar,BorderLayout.WEST);
		}

		/**
		 * @return the paintingPanel
		 */
		public PaintingPanel getPaintingPanel() {
			return paintingPanel;
		}
	}
	static public void main(String[] args) {
		WinOfAplication f = new WinOfAplication();
	}
	private JFrame mainFrame;

	private JMenuBar menuBar;

	private JTabbedPane tabbedPane;

	public WinOfAplication() {
		mainFrame = loadFrame();
		menuBar = loadMenuBar();
		mainFrame.pack();
	}

	private PaintingPanel createGraph() {
		PaintingPanel mainPaitingPanel = newGraph();
		if (tabbedPane == null) {
			tabbedPane = newTabbedPane();
			mainFrame.add(tabbedPane);
		}
		TabPanel panel = new TabPanel(mainPaitingPanel);
		tabbedPane.addTab(mainPaitingPanel.getGraph().getIDName(), panel);
		tabbedPane.setSelectedComponent(panel);
		return mainPaitingPanel;
	}

	private JToolBar createToolBar(PaintingPanel graphPanel) {
		JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener(GraphControlerFactory.NODE_TOOL,
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but1);

		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener(GraphControlerFactory.ARC_TOOL,
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but2);

		JButton but3 = new JButton("Алгоритм");
		but3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getAlgoInputListener(GraphControlerFactory.MIN_PATH_LISTENER,
				GraphControlerFactory.DIJKSTRA_S_ALGORITHM,		graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but3);
		return toolBar;
	}

	private JFrame loadFrame() {
		JFrame frame = new JFrame();
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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
				createGraph();
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

		menuItem = new JMenuItem("Сохранить как");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveFile();

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
		JMenu menu = new JMenu("Правка");
		menuBar.add(menu);
		mainFrame.setJMenuBar(menuBar);
		return menuBar;
	}

	public PaintingPanel newGraph() {
		PaintingPanel graphPanel = new PaintingPanel();
		JMenu menu = menuBar.getMenu(1);
		menu.add(new JMenuItem(graphPanel.getActionEvent("CopyAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("PasteAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("CutAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("DeleteAction")));
		menuBar.add(menu);
		menuBar.revalidate();
		return graphPanel;
	}

	private JTabbedPane newTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
		return tabbedPane;
	}
	
	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(mainFrame);
		String file = chooser.getSelectedFile().getPath();
			PaintingPanel graphPanel = createGraph();
		if (file != null) {
			graphPanel.open(file);
			graphPanel.revalidate();
		}
	}
	private void saveFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(mainFrame);
		String file = chooser.getSelectedFile().getPath();
		TabPanel panel = (TabPanel)tabbedPane.getSelectedComponent();
		panel.getPaintingPanel().save(file);
	}
}