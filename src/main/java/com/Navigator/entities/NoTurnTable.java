package com.Navigator.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NoTurnTable {
	
	@Id
	private int Id;
	private int previousNode;
	private int presentNode;
	private int nextNode;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
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
		return "NoTurnTable [Id=" + Id + ", previousNode=" + previousNode + ", presentNode=" + presentNode
				+ ", nextNode=" + nextNode + "]";
	}
	
	public NoTurnTable(int id, int previousNode, int presentNode, int nextNode) {
		super();
		Id = id;
		this.previousNode = previousNode;
		this.presentNode = presentNode;
		this.nextNode = nextNode;
	}
	public NoTurnTable() {
		super();
		// TODO Auto-generated constructor stub
	}
}
