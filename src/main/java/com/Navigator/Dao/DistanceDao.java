package com.Navigator.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navigator.entities.Distance;

public interface DistanceDao extends JpaRepository<Distance,Integer>{
	
}
