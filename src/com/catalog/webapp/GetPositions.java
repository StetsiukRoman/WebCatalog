package com.catalog.webapp;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catalog.dao.DaoFactory;
import com.catalog.dao.PositionDao;
import com.catalog.domain.Position;
import com.catalog.domain.WebUser;
import com.catalog.mysql.MySqlDaoFactory;

/**
 * Servlet implementation class GetPositions
 */
//@WebServlet("http://localhost:8081/CatalogApp/GetPositions")
public class GetPositions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPositions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baseURL = getServletContext().getInitParameter("baseURL");
		HttpSession s = request.getSession();
		//make sure user is part of the admin schema or get out.
		if (s.getAttribute("authorized_user") == null)
		{
			
		}
		else
		{
			WebUser wu = (WebUser)s.getAttribute("authorized_user");
			if (wu.getAuthLevel() < 2)
			{
				response.sendRedirect(baseURL + "/login.jsp");
				return;
			}
		}
		
		
		DaoFactory msf = new MySqlDaoFactory();
		Connection con;
		try {
			con = msf.getConnection();
			PositionDao positionDao = msf.getPositionDao(con);
			List<Position> positions = positionDao.getAll();
			s.setAttribute("positionsData", positions);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(baseURL + "/Protected/addUser.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
