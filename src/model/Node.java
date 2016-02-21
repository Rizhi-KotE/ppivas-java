package model;

public class Node implements Choosable{
	private float x;
	private float y;
	private String idth;
	
	private boolean highlight;
	private boolean choosed;
	
	private Node() {
	}

	public Node(String anId) {
		this();
		setIdth(anId);
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
}
