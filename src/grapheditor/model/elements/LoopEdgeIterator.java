package grapheditor.model.elements;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public class LoopEdgeIterator implements EdgesNodeIterator {

	ViewEdge edge;

	public LoopEdgeIterator(ViewEdge edge) {
		this.edge = edge;
	}

	@Override
	public boolean hasNext(ViewNode node) {
		return true;
	}

	@Override
	public ViewNode next(ViewNode node){
		return node;
	}

}
