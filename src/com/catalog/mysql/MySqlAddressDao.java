package com.catalog.mysql;

import com.catalog.domain.Address;
import com.catalog.dao.AddressDao;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

//Реализация интерфейса AddressDao
public class MySqlAddressDao implements AddressDao {
	
	private final Connection connection;
	
	public MySqlAddressDao(Connection connection) { 
		this.connection = connection; 
		} 
	
	@Override public Address create(Address address) throws SQLException {
		Statement stmt = connection.createStatement();
	    String sql = "INSERT INTO catalog.address  (`country`, `city`,`street`,`house`) " +
	                   "VALUES ('"+address.getCountry()+
	                   			"','" + address.getCity()+
	                   			"','" + address.getStreet()+
	                   			"','" + address.getHouse()+"');";

		stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
	    if(rs.next())
	    {
	      int idAddress = rs.getInt(1);
	      address.setIdAddress(idAddress);
	    }
	    return address;
	}
	
        
	@Override public Address read(int key) throws SQLException { 
		String sql = "SELECT * FROM catalog.address WHERE id_address = ?;"; 
		PreparedStatement stm = connection.prepareStatement(sql); 
		stm.setInt(1, key);  
		ResultSet rs = stm.executeQuery();
		rs.next(); 
                Address g = new Address(); 
		g.setIdAddress(rs.getInt("id_address")); 
		g.setCountry(rs.getString("country"));
		g.setCity(rs.getString("city"));
		g.setStreet(rs.getString("street"));
		g.setHouse(rs.getString("house"));
		return g; 
	} 
	
	@Override public void update(Address address) { } 
	@Override public void delete(Address address) { } 
	
	@Override public List<Address> getAll() throws SQLException { 
		String sql = "SELECT * FROM catalog.address;"; 
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();  
		List<Address> list = new ArrayList<Address>();
		
		while (rs.next()) { 
			Address g = new Address();
			g.setIdAddress(rs.getInt("id_address"));
			g.setCountry(rs.getString("country"));
			g.setCity(rs.getString("city"));
			g.setStreet(rs.getString("street"));
			g.setHouse(rs.getString("house"));
			list.add(g); 
			} 
		return list;
	} 
	
} 