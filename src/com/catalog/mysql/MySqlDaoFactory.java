package com.catalog.mysql;

import com.catalog.dao.DaoFactory; 
import com.catalog.dao.UserDao; 
import com.catalog.dao.AddressDao;
import com.catalog.dao.PositionDao;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class MySqlDaoFactory implements DaoFactory {
	
	private String user = "admin";
	private String password = "password";
	private String url = "jdbc:mysql://localhost:3306/catalog";
	private String driver = "com.mysql.jdbc.Driver"; 
	
	public Connection getConnection() throws SQLException { 
		return DriverManager.getConnection(url, user, password); 
	} 
	
	@Override public AddressDao getAddressDao(Connection connection) { 
		return new MySqlAddressDao(connection); 
	} 
	@Override public PositionDao getPositionDao(Connection connection) {
		return new MySqlPositionDao(connection); 
	}
	
	@Override public UserDao getUserDao(Connection connection) {
		return new MySqlUserDao(connection); 
	} 
	
	public MySqlDaoFactory() { 
		try { 
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		} 
	}
}
	

