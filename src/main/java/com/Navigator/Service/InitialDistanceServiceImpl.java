package com.Navigator.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navigator.Dao.InitialDistanceDao;
import com.Navigator.entities.DistanceQuery;
import com.Navigator.entities.InitialDistance;

@Service
public class InitialDistanceServiceImpl implements InitialDistanceService {
	
	@Autowired
	private InitialDistanceDao initialDistanceDao;
	
	private int key(int source, int destination) {
		if(source<destination) return (source*1000)+destination;
		return (destination*1000)+source;
	}
	
	private InitialDistance getEntity(DistanceQuery distanceQuery) {
		return new InitialDistance(key(distanceQuery.getSource(),
				distanceQuery.getDestination()),
				distanceQuery.getSource(),
				distanceQuery.getDestination(),
				distanceQuery.getDistance());
	}
	
	@Override
	public double distanceBetween(int source, int destination) {
		Optional<InitialDistance> initialDistance = initialDistanceDao.
				findById(key(source,destination));
//				.get().getDistance();
				
		if(initialDistance.isEmpty()) {
			System.out.print("\n \n \n \n \n" + source + " "
					+destination+" \n \n \n \n \n");
		}
		return initialDistance.get().getDistance();
//		if(initialDistance.isPresent()) {
//			return initialDistance.get().getDistance();
//		}
//		return 100000.0;
	}

	@Override
	public InitialDistance insert(DistanceQuery distanceQuery) {
		return initialDistanceDao.save(getEntity(distanceQuery));
	}

	@Override
	public void remove(DistanceQuery distanceQuery) {
		InitialDistance initialDistance = getEntity(distanceQuery);
		if(initialDistanceDao.existsById(initialDistance.getId())) {
			initialDistanceDao.deleteById(initialDistance.getId());
		}
	}

	@Override
	public List<InitialDistance> getInitialDistances() {
		return initialDistanceDao.findAll();
	}
	
	@Override
	public void removeall() {
		initialDistanceDao.deleteAll();
	}

}
