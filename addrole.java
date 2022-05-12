package addroleServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demologininner.LoginDao;

public class addrole extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public addrole() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		}
		if(session.getAttribute("username") == null)
		{
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		}
		response.sendRedirect("homepage2.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		}
		if(session.getAttribute("username") == null)
		{
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		}
		String role = request.getParameter("adminaddrole");
		PrintWriter pw = response.getWriter();
		
		Connection con = null;
		LoginDao login = new LoginDao();
		try
		{
			con = login.getConnection();
			PreparedStatement ps = null;
			
			String sqlQuery = "SELECT * FROM roles WHERE Nroles = '"+role+"'";
			ps= con.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				request.setAttribute("message",role+" role already exists");	
				request.getRequestDispatcher("homepage2.jsp").forward(request,response);
				return ;
			}
			
			sqlQuery = "INSERT INTO roles(Nroles) VALUES('"+role+"')";							
			ps = con.prepareStatement(sqlQuery);	
			
			ps.executeUpdate(); 
			
			request.setAttribute("message","New Role ("+role+") added to Database");
			request.getRequestDispatcher("homepage2.jsp").forward(request,response);
			return ;
		}
		catch(Exception e)
		{
			//PrintWriter pw = response.getWriter();
			pw.println("add role error");
		}
	}

}
