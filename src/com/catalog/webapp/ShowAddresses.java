package com.catalog.webapp;

import com.catalog.mysql.*;
import com.catalog.dao.AddressDao;
import com.catalog.dao.DaoFactory;
import com.catalog.domain.Address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowCatalog
 */
public class ShowAddresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAddresses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(); //получаем сессию из запроса
		DaoFactory msf = new MySqlDaoFactory(); //создаем фабрику для создания коннекшена к БД
		Connection con;
		try {
			con = msf.getConnection();//получаем соединение 
			AddressDao addressDao = msf.getAddressDao(con);
			List<Address> addresses = addressDao.getAll();//Получам список адресов
			s.setAttribute("addressesData", addresses);//устанавливаем значение атрибута сессии addressesData равным списку адресов
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8081/CatalogApp/Protected/listAddresses.jsp");//перенаправляем ответ к listAddresses.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
