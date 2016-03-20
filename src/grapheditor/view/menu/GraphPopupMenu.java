package grapheditor.view.menu;

import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import grapheditor.view.main.PaintingPanel;

public class GraphPopupMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2419786175938878256L;
	private PaintingPanel panel;
	
	// -----------------const---------------
	public static final int COPY_PASTE = 1;
	public static final int NODE_OPERATION = 1 << 1;
	public static final int ARC_OPERATION = 1 << 2;
	public static final int START_ALGO = 1 << 3;

	private int currentType;

	public GraphPopupMenu(PaintingPanel p) {
		super();
		panel = p;
		constructMenu(1);
	}

	private void constructMenu(int a) {
		if (currentType != a) {
			removeAll();
			if ((a & START_ALGO) == START_ALGO) {
				List<Action> node = new LinkedList<Action>();
				node.add(panel.getActionEvent(PaintingPanel.FIND_MIN_PATH));

				Iterator<Action> it = node.iterator();
				while (it.hasNext()) {
					add(new JMenuItem(it.next()));
				}
				add(new Separator());
			}
			if ((a & NODE_OPERATION) == NODE_OPERATION) {
				List<Action> node = new LinkedList<Action>();
				node.add(panel.getActionEvent(PaintingPanel.IDENTIFIER));

				Iterator<Action> it = node.iterator();
				while (it.hasNext()) {
					add(new JMenuItem(it.next()));
				}
				add(new Separator());
			}
			if ((a & COPY_PASTE) == COPY_PASTE) {
				List<Action> l = new LinkedList<Action>();
				l.add(panel.getActionEvent(PaintingPanel.COPY_ACTION));
				l.add(panel.getActionEvent(PaintingPanel.CUT_ACTION));
				l.add(panel.getActionEvent(PaintingPanel.PASTE_ACTION));
				l.add(panel.getActionEvent(PaintingPanel.DELETE_ACTION));

				Iterator<Action> it = l.iterator();
				while (it.hasNext()) {
					add(new JMenuItem(it.next()));
				}
				currentType = a;
			}
		}
	}

	public void show(Component invoker, int x, int y, int type) {
		constructMenu(type);
		super.show(invoker, x, y);
	}

}
