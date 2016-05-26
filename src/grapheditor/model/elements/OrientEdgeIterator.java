package grapheditor.model.elements;

import Exception.NoSuchNodeException;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public class OrientEdgeIterator implements EdgesNodeIterator {

	ViewEdge edge;

	public OrientEdgeIterator(ViewEdge edge) {
		this.edge = edge;
	}

	@Override
	public boolean hasNext(ViewNode node) {
		boolean out = false;
		if (node.equals(edge.getNode1())) {
			out = true;
		}
		return out;
	}

	@Override
	public ViewNode next(ViewNode node){
		if(node.equals(edge.getNode1())){
			return edge.getNode2();
		}
		throw new NoSuchNodeException();
	}

}
