package removeroleServlet;

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

import demologininner.LoginDao;

public class removerole extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public removerole() {
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
		String role = request.getParameter("role");
		if(role.equals("Select"))
		{
			request.setAttribute("alert","Select a valid option");
			request.getRequestDispatcher("removerole.jsp").forward(request,response);
			return ;
		}
		Connection con = null;
		LoginDao login = new LoginDao();
		try
		{
			con = login.getConnection();
			PreparedStatement ps = null;
			String sqlQuery = "DELETE FROM roles WHERE Nroles = '"+role+"'";							
			ps = con.prepareStatement(sqlQuery);	
			
			ps.executeUpdate();
			request.setAttribute("message",role+" was removed from the Database");
			request.getRequestDispatcher("homepage2.jsp").forward(request,response);
			return ;
		}
		catch(Exception e)
		{
			PrintWriter pw = response.getWriter();
			pw.println("remove role error");
		}
	}

}
