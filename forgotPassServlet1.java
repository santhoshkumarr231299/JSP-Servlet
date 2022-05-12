package forgotpasschanger1;

import java.io.IOException;
import java.io.PrintWriter;

import demologininner.LoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class forgotPassServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String email = request.getParameter("email");
		
		
		LoginDao ld = new LoginDao();
		HttpSession session = request.getSession();
		try
		{
			if(ld.validateForgotPass(uname,email))
			{
				session.setAttribute("user",uname);
				request.getRequestDispatcher("passchanger.jsp").forward(request,response);
			}
			else
			{
				request.setAttribute("alert","Invalid Username and Email");
				request.getRequestDispatcher("forgotpass.jsp").forward(request,response);
			}
		}
		catch(Exception e)
		{
			PrintWriter pw = response.getWriter();
			pw.println("forgot pass servlet error");
		}
	}

}
