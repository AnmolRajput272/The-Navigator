package com.Navigator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navigator.Dao.BlockedPathDao;
import com.Navigator.entities.BlockedPath;

@Service
public class BlockedPathService {
	
	@Autowired
	private BlockedPathDao blockedPathDao;
	
	private int key(int source, int destination) {
		if(source<destination) return (source*1000)+destination;
		return (destination*1000)+source;
	}
	
	public BlockedPath insertPath(BlockedPath blockedPath) {
		return blockedPathDao.save(blockedPath);
	}
	
	public void deletePath(BlockedPath blockedPath) {
		blockedPathDao.deleteById(blockedPath.getId());
	}
	
	public List<BlockedPath> getAll(){
		return blockedPathDao.findAll();
	}
	
	public boolean IsBlocked(BlockedPath blockedPath) {
		return blockedPathDao.existsById(blockedPath.getId());
	}
	
	public void deleteAll() {
		blockedPathDao.deleteAll();
	}
}
