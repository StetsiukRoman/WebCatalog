package com.catalog.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catalog.dao.DaoFactory;
import com.catalog.domain.WebUser;
import com.catalog.mysql.MySqlDaoFactory;

/**
 * Servlet implementation class LoginUser
 */
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
        super();
    }

    
	public static String getWebUserByUsernameAndPassword(String userName, String password)
	{
		return String.format("SELECT * FROM user "
						+ "where UserName = '%s' "
						+ "and Password = '%s' "
						, userName, password);
	}
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		WebUser wu = (WebUser)s.getAttribute("authorized_user");
		String baseURL = request.getServletContext().getInitParameter("baseURL");
		String loginError = baseURL + "/loginError.jsp";
		if (wu == null || wu.getUserId().equals("") || wu.getAuthLevel() < 1)
		{
			//log in the user
			String uid = "";
			String pwd = "";
			
			if (request.getParameter("uid") != null)
			{
				uid = request.getParameter("uid");
			}
			if (request.getParameter("pwd") != null)
			{
				pwd = request.getParameter("pwd");
			}
			
			if ((wu == null
					|| wu.getAuthLevel() < 1
					|| wu.getUserId() == null
					|| wu.getUserId().equals("")) && (uid != "" && pwd != ""))
			{
				//perform login
					DaoFactory msf = new MySqlDaoFactory(); 
					Connection con;
					try {
						con = msf.getConnection();
					
					//see if there is a matching WebUser
					String query = getWebUserByUsernameAndPassword(uid, pwd);
	
						PreparedStatement st = con.prepareStatement(query);
						ResultSet rs = st.executeQuery();
						
						while (rs.next())
						{
							wu = new WebUser();
							wu.setId(rs.getInt("ID"));
							wu.setUserId(rs.getString("UserName"));
							wu.setPassword(rs.getString("Password"));
							wu.setAuthLevel(rs.getInt("AuthLevel"));
							s.setAttribute("authorized_user", wu);
						}
						
						
						//add cookie if user wants to
						if (request.getParameter("rememberMe") != null)
						{
							String rememberMe = request.getParameter("rememberMe");
							if (rememberMe.equalsIgnoreCase("ON"))
							{
								//we also want to store the information in a cookie
								//for reuse later:
								int cookieLife = 3600*24*7; //7 days
								Cookie uidCook = new Cookie("credentials_uid",uid);
								uidCook.setMaxAge(cookieLife);  //7 days
								response.addCookie(uidCook);
								Cookie pwdCook = new Cookie("credentials_pwd",pwd);
								uidCook.setMaxAge(cookieLife);  //7 days
								response.addCookie(pwdCook);
							}
						}
					}
					catch (Exception ex)
					{
						System.out.println("Exception: " + ex.getMessage());
						response.sendRedirect(loginError);
						return;
					}
				
			}
			
			if (wu == null
					|| wu.getAuthLevel() < 1
					|| wu.getUserId() == null
					|| wu.getUserId().equals(""))
			{
				response.sendRedirect(loginError);
				return;
			}
		}
		//direct to destination
		String destination = ((request.getParameter("dest") == null
							|| request.getParameter("dest") == "")
							? baseURL + "/index.jsp"
							: baseURL + "/" + request.getParameter("dest") + ".jsp");
		response.sendRedirect(destination);
	}
}

