package com.Navigator.entities;

import javax.persistence.Entity;

@Entity
public class InitialDistance {
	
	@javax.persistence.Id
	private int Id;
	private int source;
	private int destination;
	private double distance;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "InitialDistance [Id=" + Id + ", source=" + source + ", destination=" + destination + ", distance="
				+ distance + "]";
	}
	public InitialDistance(int id, int source, int destination, double distance) {
		super();
		Id = id;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
	public InitialDistance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
