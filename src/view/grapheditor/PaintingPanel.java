package view.grapheditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import controler.graphEditor.GraphControlerFactory;
import model.Graph;

public class PaintingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Graph graph;
	GraphPanelUI ui;

	private JToolBar toolBar;

	
	public PaintingPanel() {
		super();
		ui = new GraphPanelUI(this);
		setUI(ui);

		graph = new Graph();
		graph.addObserver(this);

		setBackground(new Color(255, 255, 255));

		MouseListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool", ui);
		addMouseListener(listener);
		addMouseMotionListener((MouseMotionListener) listener);

		setLayout(new BorderLayout());

		toolBar = createToolBar();
		add(toolBar, BorderLayout.NORTH);

		setVisible(true);
	}

	// ------Observer-----------

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		graph.deleteObserver(this);

		this.graph = graph;
	}

	public void update(Observable o, Object arg) {
		if (graph == null) {
			if (o.getClass().getName().equals("model.Graph"))
				;
			setGraph((Graph) o);
		}
		repaint();
	}

	protected void changeMouseListener(MouseInputListener listener) {
		MouseListener[] l1 = getMouseListeners();
		for (MouseListener i : l1) {
			removeMouseListener(i);
		}
		MouseMotionListener[] l2 = getMouseMotionListeners();
		for (MouseMotionListener i : l2) {
			removeMouseMotionListener(i);
		}
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();

		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool",
						getUI());
				changeMouseListener(listener);
			}
		});
		toolBar.add(but1);

		// but1 = null;
		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Arc_tool",
						getUI());
				changeMouseListener(listener);
			}
		});
		toolBar.add(but2);
		return toolBar;
	}

}
