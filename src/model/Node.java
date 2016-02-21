package model;

import java.awt.Color;

import javax.swing.text.Position;

public class Node {
	float x;
	float y;
	String idth;
	Color color = Color.BLACK;

	private Node() {
	}

	public Node(String anId) {
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
}
