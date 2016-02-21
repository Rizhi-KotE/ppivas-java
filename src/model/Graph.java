package model;

import java.util.LinkedList;
import java.util.Observable;

public class Graph extends Observable {

	private static final float UNIT_DISTANCE = 5;

	private final String name = "Graph";

	LinkedList<Node> nodesLocation;

	public Graph() {
		nodesLocation = new LinkedList<Node>();
	}

	public void addNode(float x, float y) {
		Node node = new Node(x, y);
		nodesLocation.add(node);
		notifyObservers();
	}

	public Node[] getNode() {
		Node n[] = new Node[nodesLocation.size()];
		n = (Node[]) nodesLocation.toArray();
		return n;
	}

	public String getName() {
		return name;
	}

}
