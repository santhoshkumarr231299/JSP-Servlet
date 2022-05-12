package passchangerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import demologininner.LoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class passChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String newpass = request.getParameter("newpass");
		String reenterpass = request.getParameter("reenterpass");
		String uname = (String) session.getAttribute("user");
		
		if(newpass.length()<5)
		{
			request.setAttribute("alert","Password should contain 5 letters and more");
			request.getRequestDispatcher("passchanger.jsp").forward(request,response);
			return ;
		}
		
		try
		{
			if(newpass.equals(reenterpass))
			{
				LoginDao login = new LoginDao();
				Connection con = null;
				con = login.getConnection();
				
				String sqlQuery = "UPDATE user SET password = '"+newpass+"' WHERE username = '"+uname+"'";
				PreparedStatement ps = con.prepareStatement(sqlQuery);
				ps.executeUpdate(sqlQuery);
				request.setAttribute("alert","Password Changed Successfully");
				session.invalidate();
				request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			}
			else
			{
				request.setAttribute("alert","Password does not match");
				request.getRequestDispatcher("passchanger.jsp").forward(request,response);
			}
		}
		catch(Exception e)
		{
			PrintWriter pw = response.getWriter();
			pw.println("Password Changer Servlet error");
		}
	}

}
