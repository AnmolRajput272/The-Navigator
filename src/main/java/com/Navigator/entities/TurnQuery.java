package com.Navigator.entities;

public class TurnQuery {
	
	private int previousNode;
	private int presentNode;
	private int nextNode;
	
	public int getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(int previousNode) {
		this.previousNode = previousNode;
	}
	public int getPresentNode() {
		return presentNode;
	}
	public void setPresentNode(int presentNode) {
		this.presentNode = presentNode;
	}
	public int getNextNode() {
		return nextNode;
	}
	public void setNextNode(int nextNode) {
		this.nextNode = nextNode;
	}
	
	@Override
	public String toString() {
		return "TurnQuery [previousNode=" + previousNode + ", presentNode=" + presentNode + ", nextNode=" + nextNode
				+ "]";
	}
	
	public TurnQuery(int previousNode, int presentNode, int nextNode) {
		super();
		this.previousNode = previousNode;
		this.presentNode = presentNode;
		this.nextNode = nextNode;
	}
	public TurnQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int key() {
		return (((previousNode*1000)+presentNode)*1000)+nextNode;
	}
}
