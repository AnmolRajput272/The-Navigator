package com.Navigator.Service;

import java.util.List;

import com.Navigator.entities.DistanceQuery;
import com.Navigator.entities.InitialDistance;

public interface InitialDistanceService {
	
	public double distanceBetween(int source, int destination);
	public InitialDistance insert(DistanceQuery distanceQuery);
	public void remove(DistanceQuery distanceQuery);
	public List<InitialDistance> getInitialDistances();
	public void removeall();
}
