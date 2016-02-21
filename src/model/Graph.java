package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

public class Graph extends Observable {

	private static final float UNIT_DISTANCE = 5;

	private final String name = "Graph";

	LinkedList<Node> nodesLocation;

	LinkedList<Choosable> choose;

	public Graph() {
		nodesLocation = new LinkedList<Node>();
		choose = new LinkedList<Choosable>();
	}

	// ------------nodes-----------------
	public void addNode(float x, float y) {
		Node node = new Node(x, y);
		nodesLocation.add(node);
		setChanged();
		notifyObservers();
	}

	public Node[] getNodes() {
		Node n[] = null;
		int size = nodesLocation.size();
		if (size != 0) {
			n = new Node[size];
			Iterator<Node> it = nodesLocation.iterator();
			int i = 0;
			while (it.hasNext()) {
				n[i++] = it.next();
			}
		}
		return n;
	}

	// ---------------choose---------------
	public void choose(float x, float y){
		Iterator<Node> it = nodesLocation.iterator();
		while(it.hasNext()){
			Node n = it.next();
			if((Math.abs(n.getX()-x)<5) &&(Math.abs(n.getY()-y)<5)){
				n.setChoosed(true);
				choose.add(n);
				setChanged();
				notifyObservers();
			}
				
		}
	}

	public String getName() {
		return name;
	}

}
