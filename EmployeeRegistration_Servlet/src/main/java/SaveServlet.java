import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		String phoneNo=req.getParameter("phoneNo");
		String projectName=req.getParameter("projectName");
		
		Emp e=new Emp();
		e.setPassword(password);
		e.setName(name);
		e.setEmail(email);
		e.setCountry(country);
		e.setPhoneNo(phoneNo);
		e.setProjectName(projectName);
		
		int status=EmpDao.save(e);
		if(status>0)
		{
			pw.println("Record inserted successfully");
			req.getRequestDispatcher("EmployeeReg.html").include(req, res);
		}
		else
		{
			pw.println("Sorry not inserted");
		}
		
	}
	}
