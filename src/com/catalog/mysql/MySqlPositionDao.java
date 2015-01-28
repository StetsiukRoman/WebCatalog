package com.catalog.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.catalog.dao.PositionDao;
import com.catalog.domain.Address;
import com.catalog.domain.Position;


public class MySqlPositionDao implements PositionDao {
	
	private final Connection connection;
	
	public MySqlPositionDao(Connection connection)  { 
		this.connection = connection; 
	} 
	
	@Override
	public Position create(Position position) throws SQLException {
		Statement stmt = connection.createStatement();
	    String sql = "INSERT INTO catalog.position  (`position`) " +
	                   "VALUES ('"+position.getDescribePos()+"');";

		stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
	    if(rs.next())
	    {
	      int newId = rs.getInt(1);
	      position.setIdPosition(newId);
	    }
	    return position;
	}

	@Override
	public Position read(int key) throws SQLException {
		String sql = "SELECT * FROM catalog.position WHERE id_position = ?;"; 
		PreparedStatement stm = connection.prepareStatement(sql); 
		stm.setInt(1, key);  
		ResultSet rs = stm.executeQuery();
		rs.next();
        Position position = new Position(); 
        position.setIdPosition(rs.getInt("id_position")); 
        position.setDescribePos(rs.getString("position"));
		return position;
	}

	@Override
	public void update(Position position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Position position) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Position> getAll() throws SQLException {
		String sql = "SELECT * FROM catalog.position;";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery(); 
		List<Position> list = new ArrayList<Position>();
		
		while (rs.next()) { 
			Position g = new Position();
			g.setIdPosition(rs.getInt("id_position"));
			g.setDescribePos(rs.getString("position"));
			list.add(g); 
			} 
		return list;
	}

}
