package grapheditor.model.elements;

import Exception.NoSuchNodeException;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public class NoOrientEdgeIterator implements EdgesNodeIterator {

	ViewEdge edge;

	public NoOrientEdgeIterator(ViewEdge edge) {
		this.edge = edge;
	}

	@Override
	public boolean hasNext(ViewNode node) {
		boolean out = false;
		if (node.equals(edge.getNode1())) {
			out = true;
		}
		if (node.equals(edge.getNode2())) {
			out = true;
		}
		return out;
	}

	@Override
	public ViewNode next(ViewNode node){
		if(node.equals(edge.getNode1())){
			return edge.getNode2();
		}
		if(node.equals(edge.getNode2())){
			return edge.getNode1();
		}
		throw new NoSuchNodeException();
	}

}
