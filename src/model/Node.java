package model;

import java.awt.Color;

public class Node implements Choosable{
	private float x;
	private float y;
	private String idth;
	private Color color = Color.BLACK;

	private boolean choosed;
	
	private Node() {
	}

	public Node(String anId) {
		this();
		idth = anId;
	}

	public Node(float ax, float ay) {
		x = ax;
		y = ay;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setChoosed(boolean is){
		choosed = is;
	}
	
	public boolean isChoosed() {
		return choosed;
	}
}
