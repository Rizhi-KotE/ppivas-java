package model;

import java.awt.Shape;
import java.util.Observable;

abstract public class GraphElement extends Observable {
	private String content;
	abstract public Shape getShape();

	abstract public String getName();

	abstract public boolean isChoosed();
	
	abstract public boolean contains(int x, int y);
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String s){
		content = s;
	}
}
