package com.Navigator.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BlockedPath {
	
	@Id
	int id;
	int source;
	int destination;
	
	public BlockedPath(DistanceQuery distanceQuery) {
		this.source = distanceQuery.getSource();
		this.destination = distanceQuery.getDestination();
		this.id = key(this.source,this.destination);
	}
	
	private int key(int source, int destination) {
		if(source<destination) return (source*1000)+destination;
		return (destination*1000)+source;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	@Override
	public String toString() {
		return "BlockedPath [id=" + id + ", source=" + source + ", destination=" + destination + "]";
	}
	public BlockedPath(int id, int source, int destination) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
	}
	public BlockedPath() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
