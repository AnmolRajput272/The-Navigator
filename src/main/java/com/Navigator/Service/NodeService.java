package com.Navigator.Service;

import java.util.ArrayList;
import java.util.List;

import com.Navigator.entities.Node;

public interface NodeService {
	
	public ArrayList<Integer> getConnectedNodesOf(int node);
	public Node getNode(int BlockNo);
	public void removeNode(int BlockNo);
	public Node insertNode(Node node);
	public boolean canBeDestination(int BlockNo);
	public String buildingName(int BlockNo);
	public List<Node> getAllNodes();
	public boolean isNodePresent(int BlockNo);
	public void removeall();
	public boolean areNodesConnected(int node1, int node2);
	public void removeEdge(int node1, int node2);
	public void addEdge(int node1, int node2);
}
