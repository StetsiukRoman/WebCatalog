package com.catalog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catalog.domain.WebUser;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter("/Protected/*")
public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession s = req.getSession();
		String loginTarget = req.getServletContext().getInitParameter("baseURL") + "/login.jsp";
		String dest = "";
		String reqURI = req.getRequestURI().toLowerCase();
		
		if(reqURI.contains("users"))
		{
			dest = "?dest=Protected/listUsers";
		}
		else if (reqURI.contains("address"))
		{
			dest = "?dest=Protected/listAddresses";
		}
		else if (reqURI.contains("position")) {
			dest = "?dest=Protected/listPositions";
		}

		loginTarget = loginTarget + dest;
		if (s.getAttribute("authorized_user") == null)
		{
			res.sendRedirect(loginTarget);
			return;
		}	
		else
		{
			WebUser wu = (WebUser)s.getAttribute("authorized_user");
			if (wu.getUserId() == null || wu.getUserId() == ""|| wu.getPassword() == null || wu.getPassword() == "")
			{
				res.sendRedirect(loginTarget);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
