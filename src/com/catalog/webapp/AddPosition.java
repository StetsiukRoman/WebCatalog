package com.catalog.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import com.catalog.mysql.MySqlDaoFactory;

/**
 * Servlet implementation class AddPosition
 */
public class AddPosition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPosition() {
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
		HttpSession s = request.getSession(); //получаем из запроса сессию
		String describePos = request.getParameter("position");//получаем из запроса значение переменной newPosition
		Position position = new Position(); // создаем обьект Должности
		position.setDescribePos(describePos);//устанавливаем значение созданного обьекта должности
		
		//Присоединяемся к базе данных
		DaoFactory msf = new MySqlDaoFactory();
		Connection con;
		try {
			con = msf.getConnection();
			PositionDao positionDao = msf.getPositionDao(con);
			Position addedPosition = positionDao.create(position);//создаем в БД новую запись
			System.out.print("Id from added position is: "+addedPosition.getIdPosition());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		s.removeAttribute("positionsData"); //удаляем созданный до этого аттрибут в сессии с именем positionsData для того, чтобы обновить то, что видит пользователь на экране
		response.sendRedirect("http://localhost:8081/CatalogApp/Protected/listPositions.jsp");// перейти к файлу listPositions, который и производит отображение данных на экране
		
	}

}
