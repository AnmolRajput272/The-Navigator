package com.Navigator.entities;

public class DistanceQuery {
	
	private int source;
	private int destination;
	private double distance;
	public DistanceQuery(int source, int destination, double distance) {
		super();
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
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
		return "DistanceQuery [source=" + source + ", destination=" + destination + ", distance=" + distance + "]";
	}
	public DistanceQuery(int source, int destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	public DistanceQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

}
