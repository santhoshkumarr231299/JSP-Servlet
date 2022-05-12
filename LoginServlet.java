package demologinweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import demologininner.LoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("frontpage.jsp");
		}
		else if(session.getAttribute("username") != null && session.getAttribute("admin") != null)
		{
			response.sendRedirect("homepage2.jsp");
		}
		else if(session.getAttribute("username") != null)
		{
			response.sendRedirect("homepage.jsp");
		}
		else
		{
			response.sendRedirect("frontpage.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			
		}
		else if(session.getAttribute("username") != null && session.getAttribute("admin") != null)
		{
			response.sendRedirect("homepage2.jsp");
			return;
		}
		else if(session.getAttribute("username") != null)
		{
			response.sendRedirect("homepage.jsp");
			return;
		}
		
		PrintWriter pw = response.getWriter();
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		
		if(uname == "" || pword == "")
		{
			request.setAttribute("alert", "Username and Password should not be Empty");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return;
		}
		
		LoginDao ld = new LoginDao();
		try {
			if(ld.validateLogin(uname,pword))
			{
				session = request.getSession(true);
				LoginDao login = new LoginDao();
				if(ld.validateAdmin(uname))
				{
					Connection con = null;
					con = login.getConnection();
					
					String sqlQuery = "select * from userdb";
					PreparedStatement ps = con.prepareStatement(sqlQuery);
					ResultSet rs = ps.executeQuery(sqlQuery);
					
					session.setAttribute("admin", "1");
					session.setAttribute("data",rs);
					session.setAttribute("username", uname);
					request.getRequestDispatcher("homepage2.jsp").forward(request,response);
				}
				else
				{
					if(!ld.validateAllJobs(uname))
					{
						session.setAttribute("username", uname);
						request.getRequestDispatcher("homepage.jsp").forward(request,response);
					}
					else
					{
						request.setAttribute("message", "You have applied for all the Jobs!!!");
						session.setAttribute("username", uname);
						request.getRequestDispatcher("homepage.jsp").forward(request,response);
					}
				}
			}
			else
			{
				request.setAttribute("alert","Invalid Username and Password");
				request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			pw.println("Login Servlet CNFE/ SQLE");
		} catch (InstantiationException e) {
			
			pw.println("Login Servlet IE");
		} catch (IllegalAccessException e) {
			
			pw.println("Login Servlet IAE");
		}
		
	}

}
