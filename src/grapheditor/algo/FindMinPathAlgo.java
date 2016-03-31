package grapheditor.algo;

import Exception.NoPathException;
import grapheditor.model.main.Graph;
import grapheditor.view.elements.ViewNode;

public interface FindMinPathAlgo {
	GraphPath find(Graph graph, ViewNode start, ViewNode end) throws NoPathException;
	void nextStep(Graph graph, ViewNode start, ViewNode end) throws NoPathException;
}
