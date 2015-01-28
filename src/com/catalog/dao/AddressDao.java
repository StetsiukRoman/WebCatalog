package com.catalog.dao;

import com.catalog.domain.Address;
import java.sql.Connection; 
import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
	public Address create(Address address) throws SQLException ; 
	
	public Address read(int key) throws SQLException; 
	
	public void update(Address address); 
	
	public void delete(Address address); 
	
	public List<Address> getAll() throws SQLException;
}