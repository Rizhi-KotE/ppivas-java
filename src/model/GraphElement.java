package model;

import java.awt.Shape;
import java.util.Observable;

abstract public class GraphElement extends Observable {
	abstract public Shape getShape();

	abstract public String getName();

	abstract public boolean isChoosed();
}
