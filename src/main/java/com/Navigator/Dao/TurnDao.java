package com.Navigator.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navigator.entities.NoTurnTable;

public interface TurnDao extends JpaRepository<NoTurnTable,Integer>{
	
}
