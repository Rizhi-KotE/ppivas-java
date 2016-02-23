package model;

import java.util.Random;

public class Node implements Choosable {
	private double x;
	private double y;
	private String idth;
	private int hash;

	private boolean highlight;
	private boolean choosed;

	private Node() {
		hash = new Random().nextInt();
	}

	public Node(String anId) {
		this();
		setIdth(anId);
	}

	public Node(double ax, double ay) {
		this();

		x = ax;
		y = ay;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setChoosed(boolean is) {
		choosed = is;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public String getIdth() {
		return idth;
	}

	private void setIdth(String idth) {
		this.idth = idth;
	}

	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bl = obj instanceof Node;
		return bl && (hashCode() == obj.hashCode());
	}
}
