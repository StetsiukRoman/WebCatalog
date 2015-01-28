package com.catalog.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.catalog.dao.UserDao;
import com.catalog.domain.Address;
import com.catalog.domain.Position;
import com.catalog.domain.User;

public class MySqlUserDao implements UserDao {

	private final Connection connection;
	
	public MySqlUserDao(Connection connection) { 
		this.connection = connection; 
	} 
	
	@Override
	public User create(User user) throws SQLException {
		Statement stmt = connection.createStatement();
	    String sql = "INSERT INTO catalog.user_info  (`first_name`,`second_name`,`patronymic`,`id_position`,`id_address`,`phone`,`note`) " +
	                   "VALUES ('"+user.getFirstName()+
	                   			"','" + user.getSecondName()+
	                   			"','" + user.getPatronymic()+
	                   			"','" + user.getPosition().getIdPosition()+
	                   			"','" + user.getAddress().getIdAddress()+
	                   			"','" + user.getPhone()+
	                   			"','" + user.getNote()+"');";

		stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
	    if(rs.next())
	    {
	      int idUser = rs.getInt(1);
	      user.setIdUser(idUser);
	    }
	    return user;
	}

	@Override
	public User read(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() throws SQLException {
		String sql = "SELECT * FROM catalog.user_info;";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery(); 
		List<User> list = new ArrayList<User>();
		
		while (rs.next()) { 
			User g = new User();
			g.setIdUser(rs.getInt("id_User_info"));
			g.setFirstName(rs.getString("first_name"));
			g.setSecondName(rs.getString("second_name"));
			g.setPatronymic(rs.getString("patronymic"));
			g.setPhone(rs.getString("phone"));
			g.setNote(rs.getString("note"));
			
			//Position
			String sqlPos = "SELECT * FROM catalog.position WHERE id_position=?;";
			PreparedStatement stmPos = connection.prepareStatement(sqlPos);
			stmPos.setInt(1,rs.getInt("id_position"));
			ResultSet rsPos = stmPos.executeQuery(); 
			rsPos.next();
			Position position = new Position();
			position.setIdPosition(rsPos.getInt("id_position"));
			position.setDescribePos(rsPos.getString("position"));
			g.setPosition(position);
			
			
			//Address
			String sqlAdd = "SELECT * FROM catalog.address WHERE id_address=?;";
			PreparedStatement stmAdd = connection.prepareStatement(sqlAdd);
			stmAdd.setInt(1, rs.getInt("id_address"));
			ResultSet rsAdd = stmAdd.executeQuery(); 
			rsAdd.next();
			Address address = new Address();
			address.setIdAddress(rsAdd.getInt("id_address"));
			address.setCountry(rsAdd.getString("country"));
			address.setCity(rsAdd.getString("city"));
			address.setStreet(rsAdd.getString("street"));
			address.setHouse(rsAdd.getString("house"));
			g.setAddress(address);
	
			list.add(g);
			} 
		return list; 
	}

}
