package grapheditor.algo;

import grapheditor.model.main.Graph;
import grapheditor.view.elements.ViewNode;

public interface FindMinPathAlgo {
	GraphPath find(Graph graph, ViewNode start, ViewNode end);
}
