package grapheditor.algo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import grapheditor.model.main.Graph;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public class FindMinPathDijkstra implements FindMinPathAlgo {

	public FindMinPathDijkstra() {
	}

	ArrayList<ViewNode> g, p;
	ArrayList<Integer> d;
	ArrayList<Boolean> u;

	private void init(Graph graph) {
		g = new ArrayList<>(graph.getNodes());
		d = new ArrayList<>(graph.getNodes().size());
		for(int i = 0; i < g.size(); i++){
			d.add(Integer.MAX_VALUE);
		}
		p = new ArrayList<>(graph.getNodes().size());
		for(int i = 0; i < g.size(); i++){
			p.add(null);
		}
		u = new ArrayList<>(graph.getNodes().size());
		for(int i = 0; i < g.size(); i++){
			u.add(false);
		}
	}

	@Override
	public GraphPath find(Graph graph, ViewNode start, ViewNode end) {
		init(graph);
		int s = g.indexOf(start);
		d.set(s, 0);
		for (int i = 0; i < g.size(); i++) {
			int v = -1;
			for (int j = 0; j < g.size(); j++)
				if (!u.get(j) && (v == -1 || d.get(j) < d.get(v)))
					v = j;
				if (d.get(v) == Integer.MAX_VALUE)
					break;
				u.set(v, true);

				Iterator<ViewEdge> it = graph.incidentEdgeIterator(g.get(v));
				while (it.hasNext()) {
					ViewEdge edge = it.next();
					int to = g.indexOf(edge.getUnnotherNode(g.get(v)));
					int len = 1;
					try {
						len = Integer.parseInt(edge.getContent());
					} catch (NumberFormatException e) {
						
					}
					if (d.get(v) + len < d.get(to)) {
						d.set(to, d.get(v) + len);
						p.set(to, g.get(v));
					}
					highight(g.get(to));
				}
		}
		return null;
	}

	private void highight(ViewEdge edge) {

	}

	private void highight(ViewNode node) {
		int v = g.indexOf(node);
		Color color = Color.cyan;
		int rgb = color.getRGB() + 255 * d.get(v);
		node.setFixColor(false);
		node.setColor(new Color(rgb));
		node.setFixColor(true);
	}

}
