//create table maremp(id number,name varchar2(30),password varchar2(30),email varchar2(30),country varchar2(30))
//create sequence crud increment by 1 start with 1; 

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/EmpDao")
public class EmpDao extends HttpServlet 
{
	public static Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
		}
		catch(Exception ae)
		{	ae.printStackTrace();}
		return con;
	}

public static int save(Emp e)
{
	int status=0;
	try
	{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into MarlabEmployee(id,name,password,email,country,phoneNo,projectName) values(crud.nextval,?,?,?,?,?,?)");
		ps.setString(1, e.getName());
		ps.setString(2, e.getPassword());
		ps.setString(3,e.getEmail());
		ps.setString(4, e.getCountry());
		ps.setString(5,e.getPhoneNo());
		ps.setString(6, e.getProjectName());
		status=ps.executeUpdate();
	}
	catch(Exception ae)
	{
		ae.printStackTrace();
	}
	return status;
}
public static int update(Emp e)
{
	int status=0;
	try
	{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("update MarlabEmployee set name=?,password=?,email=?,country=?,phoneNo=?,projectName=? where id=?");
		ps.setString(1, e.getName());
		ps.setString(2, e.getPassword());
		ps.setString(3,e.getEmail());
		ps.setString(4, e.getCountry());
		ps.setString(5, e.getPhoneNo());
		ps.setString(6, e.getProjectName());
		ps.setInt(7,e.getId());
		status=ps.executeUpdate();
	}
	catch(Exception ae)
	{
		ae.printStackTrace();
	}
	return status;
}

public static int delete(int id)
{
	int status=0;
	try
	{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from MarlabEmployee where id=?");
		ps.setInt(1,id);
		status=ps.executeUpdate();
	}
	catch(Exception ae)
	{
		ae.printStackTrace();
	}
	return status;
}

public static Emp getEmployeeById(int id)
{
	Emp e=new Emp();
	try
	{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from MarlabEmployee where id=?");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setPassword(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setCountry(rs.getString(5));
			e.setPhoneNo(rs.getString(6));
			e.setProjectName(rs.getString(7));
				}
	}
	catch(Exception ae)
	{
		ae.printStackTrace();
	}
	return e;
}

public static List<Emp> getAllEmployees()
{
	List<Emp> list=new ArrayList<Emp>();
	try
	{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from MarlabEmployee");
			ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			Emp e=new Emp();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setPassword(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setCountry(rs.getString(5));
			e.setPhoneNo(rs.getString(6));
			e.setProjectName(rs.getString(7));
			list.add(e);
				}
	}
	catch(Exception ae)
	{
		ae.printStackTrace();
	}
	return list;
}
}

