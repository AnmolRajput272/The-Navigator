package com.Navigator.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navigator.entities.InitialDistance;

public interface InitialDistanceDao extends 
					JpaRepository<InitialDistance,Integer>{
	
	
}
