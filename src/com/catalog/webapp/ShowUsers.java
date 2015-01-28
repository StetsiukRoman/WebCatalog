package com.catalog.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import com.catalog.dao.AddressDao;
import com.catalog.dao.DaoFactory;
import com.catalog.dao.UserDao;
import com.catalog.domain.Address;
import com.catalog.domain.User;
import com.catalog.mysql.MySqlDaoFactory;

/**
 * Servlet implementation class ShowUsers
 */
//@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();    
			DaoFactory msf = new MySqlDaoFactory();
			Connection con;
			try {
				con = msf.getConnection();
				UserDao userDao = msf.getUserDao(con);
				List<User> users = userDao.getAll();
				s.setAttribute("usersData", users);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("http://localhost:8081/CatalogApp/Protected/listUsers.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
