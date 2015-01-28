package com.catalog.dao;

import com.catalog.domain.Position;
import java.sql.Connection; 
import java.sql.SQLException;
import java.util.List;

public interface PositionDao {
	public Position create(Position position) throws SQLException; 
	
	public Position read(int key) throws SQLException; 
	
	public void update(Position position); 
	
	public void delete(Position position); 
	
	public List<Position> getAll() throws SQLException;
}