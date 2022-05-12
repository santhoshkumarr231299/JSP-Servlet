package updatedb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demologininner.LoginDao;

public class DeleteDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("frontpage.jsp");
			return;
		}
		if(session.getAttribute("username")!=null && session.getAttribute("admin") == null)
		{
			response.sendRedirect("homepage.jsp");
			return;
		}
		else if(session.getAttribute("username")!=null && session.getAttribute("admin") == null)
		{
			response.sendRedirect("homepage.jsp");
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("frontpage.jsp");
			return;
		}
		if(session.getAttribute("username")!=null && session.getAttribute("admin") == null)
		{
			response.sendRedirect("homepage.jsp");
			return;
		}
		else if(session.getAttribute("username")!=null && session.getAttribute("admin") == null)
		{
			response.sendRedirect("homepage.jsp");
			return;
		}
		
		String[] deleteValues = request.getParameterValues("users");
		if(deleteValues == null)
		{
			response.sendRedirect("homepage2.jsp");
			return ;
		}
		LoginDao login = new LoginDao();
		Connection con = null;
		try
		{
			con = login.getConnection();
			for(String delete: deleteValues)
			{
				String sqlQuery = "delete from userdb where resume = '"+delete+"'";
				PreparedStatement ps = con.prepareStatement(sqlQuery);
				ps.executeUpdate(sqlQuery);
				File resume = new File("E:/eclipse projects/demo/src/main/webapp/docs/"+delete);
				resume.delete();
				File photo = new File("E:/eclipse projects/demo/src/main/webapp/images/"+delete.substring(0,delete.length()-4)+".jpg");
				photo.delete();
			}
		}
	catch(Exception e)
	{
		
	}
		response.sendRedirect("homepage2.jsp");
}
}
