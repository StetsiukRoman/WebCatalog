package com.catalog.dao;

import java.sql.Connection; 
import java.sql.SQLException;

public interface DaoFactory {
		public Connection getConnection() throws SQLException; 
		 
		public UserDao getUserDao(Connection connection); 
		
		public PositionDao getPositionDao(Connection connection);
		
		public AddressDao getAddressDao(Connection connection);
}
