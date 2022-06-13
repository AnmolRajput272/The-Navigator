package com.Navigator.entities;

import javax.persistence.Entity;

@Entity
public class Distance {
	
	@javax.persistence.Id
	private int Id;
	private double distance;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "Distance [Id=" + Id + ", distance=" + distance + "]";
	}
	public Distance(int id, double distance) {
		super();
		Id = id;
		this.distance = distance;
	}
	public Distance() {
		super();
		// TODO Auto-generated constructor stub
	}
}
