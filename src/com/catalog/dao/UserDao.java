package com.catalog.dao;

import com.catalog.domain.User;
import java.sql.Connection; 
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
	public User create(User user) throws SQLException; 
	
	public User read(int key); 
	
	public void update(User user); 
	
	public void delete(User user); 
	
	public List<User> getAll() throws SQLException;
}
