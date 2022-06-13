package com.Navigator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Navigator.Dao.DistanceDao;
import com.Navigator.entities.Distance;
import com.Navigator.entities.DistanceQuery;

@Service
public class DistanceService implements DistanceServiceInterface{
	
	@Autowired
	private DistanceDao distanceDao;
	
	DistanceService(){}
	
	public List<Distance> getDistances(){
		return distanceDao.findAll();
	}
	
	public int key(int source, int destination) {
		return (source*1000)+destination;
	}
	
	public double distanceBetween(int id) {
		Distance distance = distanceDao.getById(id);
		return distance.getDistance();
	}

	public double distanceBetween(DistanceQuery distanceQuery) {
		
		try {
			Distance distance = 
					distanceDao.getById(
							key(distanceQuery.getSource(),
									distanceQuery.getDestination()));
			return distance.getDistance();
		}
		catch(Exception e){
			Distance distance = 
					distanceDao.getById(
							key(distanceQuery.getDestination(),
									distanceQuery.getSource()));
			return distance.getDistance();
		}
		
//		return 1000.0;
	}
	
	public Distance updateDistance(DistanceQuery distanceQuery) {
		Distance distance = 
				new Distance(
						key(distanceQuery.getSource(),
								distanceQuery.getDestination()),
									distanceQuery.getDistance());
		return distanceDao.save(distance);
	}
	
	public void RemoveDistance(int id){
		if(distanceDao.existsById(id)) {
			distanceDao.deleteById(id);
		}
	}
	
	public void RemoveDistance(DistanceQuery distanceQuery){
		distanceDao.deleteById(
				key(distanceQuery.getSource(),
						distanceQuery.getDestination()));
	}
	
	public void removeall() {
		distanceDao.deleteAll();
	}
}
