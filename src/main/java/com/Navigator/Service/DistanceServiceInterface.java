package com.Navigator.Service;

import java.util.List;

import com.Navigator.entities.Distance;
import com.Navigator.entities.DistanceQuery;

public interface DistanceServiceInterface {
	
	public List<Distance> getDistances();
	public int key(int source, int destination);
	public double distanceBetween(int id);
	public double distanceBetween(DistanceQuery distanceQuery);
	public Distance updateDistance(DistanceQuery distanceQuery);
	public void RemoveDistance(int id);
	public void RemoveDistance(DistanceQuery distanceQuery);
	public void removeall();
	
}
