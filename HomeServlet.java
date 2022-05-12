package homePageServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demologininner.LoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
@MultipartConfig
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
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
			if(session.getAttribute("username") != null && session.getAttribute("admin") != null)
			{
				response.sendRedirect("homepage2.jsp");
			}
			response.sendRedirect("homepage.jsp");
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
		PrintWriter pw = response.getWriter();
		String uploadPathImg = "E:/eclipse projects/demo/src/main/webapp/images/";
		String uploadPathDocs = "E:/eclipse projects/demo/src/main/webapp/docs/";
		
		Part file = request.getPart("photo");
		String imgName = file.getSubmittedFileName();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String college = request.getParameter("college");
		String yearOfPassing = request.getParameter("yearOfPassing");
		String rollNumber = request.getParameter("rollNumber");
		String clgCGPA = request.getParameter("clgCGPA");
		String tenthPercentage = request.getParameter("tenthPercentage");
		String twelvethPercentage = request.getParameter("twelvethPercentage");
		String diplomaPercentage = request.getParameter("diplomaPercentage");
		String pg = request.getParameter("pg");
		
		Part resume = request.getPart("resume");
		String resumeName = resume.getSubmittedFileName();
		
		String role = request.getParameter("role");
		if(role.equals("Select"))
		{
			request.setAttribute("message", "Enter a valid Job Role");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		
		String uname = (session.getAttribute("username").toString());
		
		Connection con = null;
		if(Double.parseDouble(clgCGPA) > 10 && Double.parseDouble(clgCGPA) < 0)
		{
			request.setAttribute("message", "Enter a valid College CGPA");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		else if(Double.parseDouble(tenthPercentage) > 100 && Double.parseDouble(tenthPercentage) < 0)
		{
			request.setAttribute("message", "Enter a valid 10th Percentage");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		else if(Double.parseDouble(twelvethPercentage) > 100 && Double.parseDouble(twelvethPercentage) < 0)
		{
			request.setAttribute("message", "Enter a valid 12th Percentage");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		else if(Double.parseDouble(diplomaPercentage) > 100 && Double.parseDouble(diplomaPercentage) < 0 )
		{
			request.setAttribute("message", "Enter a valid Diploma Percentage");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		else if(Double.parseDouble(pg) > 10 && Double.parseDouble(pg) < 0)
		{
			request.setAttribute("message", "Enter a valid PG CGPA");
			request.getRequestDispatcher("homepage.jsp").forward(request,response);
			return;
		}
		else
		{
			LoginDao login = new LoginDao();
			try
			{
			con = login.getConnection();
			PreparedStatement ps = null;
			String sqlQuery = "select * from userdb where username = '"+uname+"' and role = '"+role+"'";							
			ps = con.prepareStatement(sqlQuery);	
							
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				request.setAttribute("message","You have already applied for this "+role+" Role");
				request.getRequestDispatcher("homepage.jsp").forward(request,response);
				return ;
			}		
			}
			catch(Exception e)
			{
				pw.println("hiiii");
			}
		}
		
		//Photo uploading to respected folder
		String uploadPath = uploadPathImg + imgName;
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		//rename
		File photoName = new File(uploadPath);
		String photoRenamePath = uploadPathImg + uname +role+ imgName.substring(imgName.lastIndexOf('.'));
		File photoRenameTo = new File(photoRenamePath);
		photoName.renameTo(photoRenameTo);
		
		//Resume uploading to Respected Folder
		String uploadPath2 = uploadPathDocs + resumeName;
		FileOutputStream fos2 = new FileOutputStream(uploadPath2);
		InputStream is2 = resume.getInputStream();
		byte[] data2 = new byte[is2.available()];
		is2.read(data2);
		fos2.write(data2);
		fos2.close();
		
		//rename
		File resumePathName = new File(uploadPath2);
		String resumeRenamePath = uploadPathDocs + uname +role+ resumeName.substring(resumeName.lastIndexOf('.'));
		File resumeRenameTo = new File(resumeRenamePath);
		resumePathName.renameTo(resumeRenameTo);
		
		String newPhotoName = (uname +role+ imgName.substring(imgName.lastIndexOf('.')));
		String newResumeName = (uname + role + resumeName.substring(resumeName.lastIndexOf('.')));
		
		try
		{		
			if(con == null)
			{ 
				pw.println("error connecting to database");
			}
			else
			{
				PreparedStatement ps = null;
				String sqlQuery;
				try
				{
				sqlQuery = "INSERT INTO userdb (username,photo,firstName,lastName,address,dob,phoneNumber,college,yearOfPassing,rollNumber,clgCGPA,tenthPercentage,twelvethPercentage,diplomaPercentage,pg,resume,role) VALUES ('"+uname+"','"+newPhotoName+"','"+firstName+"','"+lastName+"','"+address+"','"+dob+"','"+phoneNumber+"','"+college+"','"+yearOfPassing+"','"+rollNumber+"','"+clgCGPA+"','"+tenthPercentage+"','"+twelvethPercentage+"','"+diplomaPercentage+"','"+pg+"','"+newResumeName+"','"+role+"')";
								
				ps = con.prepareStatement(sqlQuery);	
								
				ps.executeUpdate();
				}
				catch(Exception e)
				{
					pw.println("lame");
				}
				
				request.setAttribute("message","You have submitted the Application form successfully!!!");
				request.getRequestDispatcher("homepage.jsp").forward(request,response);			
			}
		}
		catch(Exception e)
		{
			pw.println("Home Servlet Error");
		}

		
 	}

}
