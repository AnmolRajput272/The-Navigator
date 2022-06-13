package com.Navigator.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Node {
	
	@Id
	private int blockNo;
	private String buildingName;
	private boolean canBeDestintion;
	private String connectedNodes;
	
	public int getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public boolean isCanBeDestintion() {
		return canBeDestintion;
	}
	public void setCanBeDestintion(boolean canBeDestintion) {
		this.canBeDestintion = canBeDestintion;
	}
	public String getConnectedNodes() {
		return connectedNodes;
	}
	public void setConnectedNodes(String connectedNodes) {
		this.connectedNodes = connectedNodes;
	}
	
	@Override
	public String toString() {
		return "Node [blockNo=" + blockNo + ", buildingName=" + buildingName + ", canBeDestintion=" + canBeDestintion
				+ ", connectedNodes=" + connectedNodes + "]";
	}
	
	public Node(int blockNo, String buildingName, boolean canBeDestintion, String connectedNodes) {
		super();
		this.blockNo = blockNo;
		this.buildingName = buildingName;
		this.canBeDestintion = canBeDestintion;
		this.connectedNodes = connectedNodes;
	}
	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
