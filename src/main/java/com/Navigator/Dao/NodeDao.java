package com.Navigator.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Navigator.entities.Node;

public interface NodeDao extends JpaRepository<Node,Integer>{
	
}
