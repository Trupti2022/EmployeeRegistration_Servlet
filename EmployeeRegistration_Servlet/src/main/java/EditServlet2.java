import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String sid=req.getParameter("id");
		int id=Integer.parseInt(sid);
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		String phoneNo=req.getParameter("phoneNo");
		String projectName=req.getParameter("projectName");
		
		Emp e=new Emp();
		e.setId(id);
		e.setPassword(password);
		e.setName(name);
		e.setEmail(email);
		e.setCountry(country);
		e.setPhoneNo(phoneNo);
		e.setProjectName(projectName);
		
		int status=EmpDao.update(e);
		if(status>0)
		{
			res.sendRedirect("ViewServlet");
		}
		else
		{
			pw.println("Sorry not updated");
		}
		
	}
}
