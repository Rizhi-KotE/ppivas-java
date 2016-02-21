package model;

public class Edge implements Choosable{
	private Node node1, node2;
	
	private boolean choosed;

	public Node getNode1() {
		return node1;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	public boolean isChoosed() {
		return choosed;
	}

	@Override
	public void setChoosed(boolean is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isHighlight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHighlight(boolean is) {
		// TODO Auto-generated method stub
		
	}
	
}
