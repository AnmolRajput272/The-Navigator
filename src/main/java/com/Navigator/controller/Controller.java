package com.Navigator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Navigator.Service.BlockedPathService;
import com.Navigator.Service.DistanceService;
import com.Navigator.Service.DistanceServiceInterface;
import com.Navigator.Service.InitialDistanceService;
import com.Navigator.Service.NodeService;
import com.Navigator.Service.TurnService;
import com.Navigator.entities.BlockedPath;
import com.Navigator.entities.Distance;
import com.Navigator.entities.DistanceQuery;
import com.Navigator.entities.InitialDistance;
import com.Navigator.entities.NoTurnTable;
import com.Navigator.entities.Node;
import com.Navigator.entities.TurnQuery;

@RestController
//@RequestMapping("/Navigator")
@CrossOrigin(origins="*")
public class Controller {
	
	
	@Autowired
	private DistanceServiceInterface distanceServiceInterface;
	
	@Autowired
	private TurnService turnService;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private InitialDistanceService initialDistanceService;
	
	@Autowired
	private BlockedPathService blockedPathService;
	
	@GetMapping("/Hello")
	public String hello() {
		return "Hello";
	}
	
	@PostMapping("/updateDistance")
	public Distance updateDistance(@RequestBody DistanceQuery distanceQuery) {
		return distanceServiceInterface.updateDistance(distanceQuery);
	}
	
	@GetMapping("/getDistance")
	public double getDistance(@RequestBody DistanceQuery distanceQuery) {
		return distanceServiceInterface.distanceBetween(distanceQuery);
	}
	
	private double getDistance(int source, int destination) {
		return getDistance(new DistanceQuery(source,destination));
	}
	
	@GetMapping("/getDistances")
	public List<Distance> getDistances(){
		return distanceServiceInterface.getDistances();
	}
	
	@DeleteMapping("/deleteDistance")
	public ResponseEntity<HttpStatus> 
			deleteDistance(@RequestBody DistanceQuery distanceQuery){
		try {
			distanceServiceInterface.RemoveDistance(distanceQuery);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/NoTurnTable")
	public List<NoTurnTable> getNoTurnTable(){
		return turnService.getNoTurnTable();
	}
	
	@PostMapping("/NoTurnTable")
	public NoTurnTable insertIntoNoTurnTable(@RequestBody TurnQuery turnQuery) {
		return turnService.insert(turnQuery);
	}
	
	@DeleteMapping("/NoTurnTable")
	public ResponseEntity<HttpStatus> 
			removeFromNoTurnTable(@RequestBody TurnQuery turnQuery){
		turnService.remove(turnQuery);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/NoTurnTable/shouldTakeTurn")
    public boolean shouldTakeTurn(@RequestBody TurnQuery turnQuery) {
		return turnService.shouldTakeTurn(turnQuery);
	}
	
	
	@GetMapping("Node/Connections/{BlockNo}")
	public ArrayList<Integer> getConnectedNodesOf(@PathVariable int BlockNo){
		return nodeService.getConnectedNodesOf(BlockNo);
	}
	
	@GetMapping("/Node/{BlockNo}")
	public Node getNode(@PathVariable int BlockNo) {
		return nodeService.getNode(BlockNo);
	}
	
	@DeleteMapping("/Node/{BlockNo}")
	public ResponseEntity<HttpStatus> removeNode(@PathVariable int BlockNo) {
		nodeService.removeNode(BlockNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/Node")
	public ResponseEntity<HttpStatus> removeAllNodes() {
		nodeService.removeall();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/Node")
	public Node insertNode(@RequestBody Node node) {
		return nodeService.insertNode(node);
	}
	
	@GetMapping("/Node")
	public List<Node> getAllNodes(){
		return nodeService.getAllNodes();
	}
	
	@PostMapping("/Nodes")
	public List<Node> insertMultipleNodes(@RequestBody List<Node> nodes){
		for(Node node:nodes) {
			insertNode(node);
		}
		return nodes;
	}
	
	@GetMapping("/InitialDistance")
	public List<InitialDistance> getInitialDistances(){
		return initialDistanceService.getInitialDistances();
	}
	
	@GetMapping("/InitialDistanceBetween")
	public double getInitialDistance(@RequestBody DistanceQuery distanceQuery) {
		return initialDistanceService.distanceBetween(
			   distanceQuery.getSource(),distanceQuery.getDestination());
	}
	
	@DeleteMapping("/InitialDistance")
	public ResponseEntity<HttpStatus> 
		removeInitialDistance(@RequestBody DistanceQuery distanceQuery){
		initialDistanceService.remove(distanceQuery);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/InitialDistance")
	public InitialDistance 
		insertInitialDistance(@RequestBody DistanceQuery distanceQuery) {
		return initialDistanceService.insert(distanceQuery);
	}
	
	@PostMapping("/InitialDistances")
	public List<InitialDistance> 
		insertInitialDistance(@RequestBody List<DistanceQuery> distanceQuery){
		ArrayList<InitialDistance> a = new ArrayList<InitialDistance>();
		for(DistanceQuery d:distanceQuery) {
			a.add(insertInitialDistance(d));
		}
		return a;
	}
	
	public double getInitialDistance(int source, int destination) {
		return initialDistanceService.distanceBetween(source, destination);
	}
	
	@DeleteMapping("/removeAll/Distances")
	public void removeAllDistances() {
		distanceServiceInterface.removeall();
	}
	
	@DeleteMapping("/removeAll/InitialDistances")
	public void removeAllInitialDistances() {
		initialDistanceService.removeall();
	}
	
	@DeleteMapping("/removeAll/Turns")
	public void removeAllTurns() {
		turnService.removeall();
	}
	
	@GetMapping("/getAllBlocked")
	public List<BlockedPath> getAllBlockedPaths(){
		return blockedPathService.getAll();
	}
	
	@DeleteMapping("/blockPath")
	public void removeEdge(@RequestBody DistanceQuery distanceQuery) {
//		BlockedPath blockedPath = new BlockedPath(distanceQuery);
//		if(blockedPathService.IsBlocked(blockedPath)) return;
//		blockedPathService.insertPath(blockedPath);
		int node1 = distanceQuery.getSource();
		int node2 = distanceQuery.getDestination();
		if(!nodeService.areNodesConnected(node1, node2)) return;
		nodeService.removeEdge(node1, node2);
		Populator(); 
//		removeInitialDistance(new DistanceQuery(node1,node2));
//		deleteDistance(new DistanceQuery(node1,node2));
//		for(int connectedNode:getConnectedNodesOf(node1)) {
//			removeFromNoTurnTable(
//					new TurnQuery(node2,node1,connectedNode));
//			removeFromNoTurnTable(
//					new TurnQuery(connectedNode,node1,node2));
//		}
//		
//		for(int connectedNode:getConnectedNodesOf(node2)) {
//			removeFromNoTurnTable(
//					new TurnQuery(node1,node2,connectedNode));
//			removeFromNoTurnTable(
//					new TurnQuery(connectedNode,node2,node1));
//		}
	}
	
	@PostMapping("/openPath")
	public void addEdge(@RequestBody DistanceQuery distanceQuery) {
//		BlockedPath blockedPath = new BlockedPath(distanceQuery);
//		if(!blockedPathService.IsBlocked(blockedPath)) return;
//		blockedPathService.deletePath(blockedPath);
		if(nodeService.areNodesConnected(distanceQuery.getSource(),
			distanceQuery.getDestination())) {
			return ;
		}
		nodeService.addEdge(distanceQuery.getSource(),
							distanceQuery.getDestination());
		Populator();
	}
	
	double InitialMap[][] = new double[100][100];
	private void BFS(int source) {
		ArrayList<Integer> array = new ArrayList<Integer>(); 
		HashSet<Integer> hashset = new HashSet<Integer>(); 
		updateDistance(new DistanceQuery(source,source,0.0));
		array.add(source);
		int index = 0;
		
		double map[] = new double[100];
		for(int i=42;i<=72;i++) map[i]=100000.0;
		map[source]=0.0;
		
		while(index<array.size()) {
			Integer node =  array.get(index);
			double distanceTillHere = map[node];
			for(int connectedNode:getConnectedNodesOf(node)) {
				double d = InitialMap[node][connectedNode];
				double d1 = map[connectedNode]; 
				if((d+distanceTillHere)<d1) {
					map[connectedNode]=d+distanceTillHere;
					hashset.add(connectedNode);
				}
			} index++;
			
			if(index==array.size()) {
				array.clear();
				Iterator<Integer> i = hashset.iterator();
				while(i.hasNext()) {
					array.add(i.next());
				}
				hashset.clear();
				index = 0;
			}
		}
		
		for(int i=42;i<=72;i++) {
			if(map[i]!=100000.0) {
				updateDistance(new DistanceQuery(source,i,map[i]));
			}
		}
		
	}
	
	private void populateDistance() {
		for(int i=42;i<=72;i++) {
			for(int j=42;j<=72;j++) {
				if(i==j) continue;
				updateDistance(new DistanceQuery(i,j,100000.0));
			}
		}
	}
	
	@PostMapping("/StartPopulator")
	public void Populator() {
//		populateDistance();
		for(InitialDistance initialDistance : getInitialDistances()) {
			InitialMap[initialDistance.getSource()]
				[initialDistance.getDestination()] = initialDistance.getDistance();
			InitialMap[initialDistance.getDestination()]
					[initialDistance.getSource()] = initialDistance.getDistance();
		}
		for(Node node:getAllNodes()) {
			BFS(node.getBlockNo());
		}
	}
	
	@GetMapping("/Path")
	public ArrayList<Integer>  getPath(@RequestBody DistanceQuery distanceQuery){
		int source = distanceQuery.getSource();
		int destination = distanceQuery.getDestination();
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(source);
		HashSet<Integer> set = new HashSet<Integer>();
		while(source!=destination) {
			double minDistance = 10000.0;
			int minDistanceBlockNo = -1;
			if(set.contains(source)) return new ArrayList<Integer>();
			set.add(source);
			for(int connectedNode:getConnectedNodesOf(source)) {
				double d1 = getDistance(source,connectedNode); 
				double d2 = getDistance(connectedNode,destination);
				if((d1+d2)<minDistance) {
					minDistance = d1+d2;
					minDistanceBlockNo = connectedNode;
				}
			}
			source = minDistanceBlockNo;
			path.add(source);
		}
		return path;
	}
	
	@GetMapping("/ShowPath")
	public ArrayList<Integer> pathWithParams(
			@RequestParam(value="source") int source,
			@RequestParam(value="destination") int destination){
		
		
//		System.out.print("\n \n \n \n Hello \n \n \n \n");
		
//		if(getDistance(source,destination)==100000.0)
//			return new ArrayList<Integer>();
//		
//		System.out.print("\n \n \n \n Hello \n \n \n \n");
		
		
		Date date = new Date();
		System.out.println(date);
		
		if((!nodeService.isNodePresent(source)) || 
				(!nodeService.isNodePresent(destination))  ) {
			return new ArrayList<Integer>();
		}
		
		return getPath(new DistanceQuery(source,destination));
	}
}
