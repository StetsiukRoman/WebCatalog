package com.catalog.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catalog.dao.AddressDao;
import com.catalog.dao.DaoFactory;
import com.catalog.dao.PositionDao;
import com.catalog.dao.UserDao;
import com.catalog.domain.Address;
import com.catalog.domain.Position;
import com.catalog.domain.User;
import com.catalog.mysql.MySqlDaoFactory;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(); 
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		String patronymic = request.getParameter("patronymic");
		String idPosition = request.getParameter("idPosition");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String house = request.getParameter("house");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		
		Position position = new Position(); 
		
		Address address = new Address();
		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		address.setHouse(house);
		
		DaoFactory msf = new MySqlDaoFactory();
		Connection con;
		try {
			con = msf.getConnection();
			PositionDao positionDao = msf.getPositionDao(con);
			position = positionDao.read(Integer.parseInt(idPosition));
			System.out.print("Id/value from added position is: "+position.getIdPosition()+"   "+position.getDescribePos());
			
			AddressDao addressDao = msf.getAddressDao(con);
			Address addedAddress = addressDao.create(address);
			System.out.print("Id from added address is: "+addedAddress.getIdAddress());
			
			User user = new User();
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setPatronymic(patronymic);
			user.setPosition(position);
			user.setAddress(addedAddress);
			user.setPhone(phone);
			user.setNote(note);
			
			UserDao userDao = msf.getUserDao(con);
			User addedUser = userDao.create(user);
			System.out.print("Id from added user is: "+addedUser.getIdUser());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		s.removeAttribute("usersData");
		s.removeAttribute("addressesData");
		response.sendRedirect("http://localhost:8081/CatalogApp/Protected/listUsers.jsp");
	}

}
