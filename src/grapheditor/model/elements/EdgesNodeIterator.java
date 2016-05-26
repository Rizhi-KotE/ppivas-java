package grapheditor.model.elements;

import Exception.NoSuchNodeException;
import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewNode;

public interface EdgesNodeIterator{
	boolean hasNext(ViewNode node);
	ViewNode next(ViewNode node) throws NoSuchNodeException;
}
