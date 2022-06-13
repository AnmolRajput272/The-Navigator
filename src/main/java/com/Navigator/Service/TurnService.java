package com.Navigator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Navigator.Dao.TurnDao;
import com.Navigator.entities.NoTurnTable;
import com.Navigator.entities.TurnQuery;

@Service
public class TurnService {
	
	@Autowired
	private TurnDao turnDao;
	
	public List<NoTurnTable> getNoTurnTable(){
		return turnDao.findAll();
	}
	
	public boolean shouldTakeTurn(TurnQuery turnQuery) {
		return turnDao.existsById(turnQuery.key());
	}
	
	private NoTurnTable entityFromQuery(TurnQuery turnQuery) {
		NoTurnTable noTurnTable = 
				new NoTurnTable(turnQuery.key(),turnQuery.getPreviousNode(),
					turnQuery.getPresentNode(),turnQuery.getNextNode());
		return noTurnTable;
	}
	
	public NoTurnTable insert(TurnQuery turnQuery) {
		return turnDao.save(entityFromQuery(turnQuery));
	}
	
	public void remove(TurnQuery turnQuery) {
		if(turnDao.existsById(turnQuery.key())) {
			turnDao.deleteById(turnQuery.key());
		}
	}
	
	public void removeall() {
		turnDao.deleteAll();
	}
}
