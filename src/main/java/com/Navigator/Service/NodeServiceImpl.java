package com.Navigator.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navigator.Dao.NodeDao;
import com.Navigator.entities.Node;

@Service
public class NodeServiceImpl implements NodeService {
	
	@Autowired
	private NodeDao nodeDao;
	
	HashMap<Integer,ArrayList<Integer>> cache;
	
	public NodeServiceImpl() {
		cache = new HashMap<Integer,ArrayList<Integer>>();
	}
	
	public Node getNode(int BlockNo) {
		Optional<Node> n = nodeDao.findById(BlockNo);
		if(n.isEmpty()) {
			System.out.print("\n \n \n \n \n");
			System.out.print(BlockNo);
			System.out.print("\n \n \n \n \n");
		}
		return n.get();
	}
	
	public void removeNode(int BlockNo) {
		if(nodeDao.existsById(BlockNo)) {
			nodeDao.deleteById(BlockNo);
		}
	}
	
	public Node insertNode(Node node) {
		Node n = nodeDao.save(node);
//		cache.put(node.getBlockNo(),
//				StringToArrayList(node.getBlockNo(),node.getConnectedNodes()));
		return n;
	}
	
    private ArrayList<Integer> StringToArrayList(int node,String s){
        String num = "";
        ArrayList<Integer> a = new ArrayList<Integer>();
        for(int i=0;i<s.length();i++){
            char x = s.charAt(i);
            if(x==','){
                a.add(Integer.parseInt(num));
                num = "";
            }
            else{num += x;}
        } if(num.length()>1) a.add(Integer.parseInt(num));
        cache.put(node,a);
        return a;
    }
    
    private String ArrayListToString(int node, ArrayList<Integer> array, 
    								int numToIgnore) {
    	String nodes = "";
    	ArrayList<Integer> newarray = new ArrayList<Integer>();
    	for(int i=0;i<array.size();i++) {
    		if(array.get(i)==numToIgnore) continue;
    		newarray.add(array.get(i));
			nodes+=Integer.toString(array.get(i));
			if(i!=(array.size()-1)) {
				nodes+=",";
			}
    	}
    	cache.put(node,newarray);
    	return nodes;
    }
    
    
    private String ArrayListToStringAdd(int node, ArrayList<Integer> array, 
									    int numToAdd) {
		String nodes = "";
		ArrayList<Integer> newarray = new ArrayList<Integer>();
		for(int i=0;i<array.size();i++) {
			newarray.add(array.get(i));
			nodes+=Integer.toString(array.get(i))+",";
		}
		newarray.add(numToAdd);
		nodes+=Integer.toString(numToAdd);
		cache.put(node,newarray);
		return nodes;
	}
    
    
    public ArrayList<Integer> getConnectedNodesOf(int BlockNo){
    	if(cache.containsKey(BlockNo)) return cache.get(BlockNo);
    	if(!isNodePresent(BlockNo)) return new ArrayList<Integer>();
    	return StringToArrayList(BlockNo, 
    			getNode(BlockNo).getConnectedNodes());
    }
    
    public boolean canBeDestination(int BlockNo) {
    	return getNode(BlockNo).isCanBeDestintion();
    }
    
    public String buildingName(int BlockNo) {
    	return getNode(BlockNo).getBuildingName();
    }
    
    public List<Node> getAllNodes(){
    	return nodeDao.findAll();
    }
    
    public boolean isNodePresent(int BlockNo) {
    	return nodeDao.existsById(BlockNo);
    }
    
    public void removeall() {
    	nodeDao.deleteAll();
    }
    
    public void updateConnectedNodes(int node, String nodes) {
    	Node n = getNode(node);
    	n.setConnectedNodes(nodes);
    	insertNode(n);
    }
    
    public boolean areNodesConnected(int node1, int node2) {
    	for(int connectedNode:getConnectedNodesOf(node1)) {
    		if(connectedNode==node2) return true;
    	} 
    	return false;
    }
    
    public void removeEdge(int node1, int node2) {
    	String nodes1 = ArrayListToString(node1,
    			getConnectedNodesOf(node1),node2);
    	String nodes2 = ArrayListToString(node2,
    			getConnectedNodesOf(node2),node1);
    	updateConnectedNodes(node1,nodes1);
    	updateConnectedNodes(node2,nodes2);
    	
    }
    
    public void addEdge(int node1, int node2) {
    	String nodes1 = ArrayListToStringAdd(
    			node1,getConnectedNodesOf(node1),node2);
    	String nodes2 = ArrayListToStringAdd(
    			node2,getConnectedNodesOf(node2),node1);
    	updateConnectedNodes(node1,nodes1);
    	updateConnectedNodes(node2,nodes2);
    	
    }
    
}
