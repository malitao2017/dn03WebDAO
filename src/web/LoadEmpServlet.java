/*
 * LoadEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DAOFactory;
import dao.EmployeeDAO;
import entity.Employee;

/**
 * 加载修改页面
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class LoadEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try{
			EmployeeDAO dao = null;
//			dao = new EmployeeDAOJdbcImpl();
			dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
			
			Employee emp = dao.findById(id);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<form action='modify?id="+id+"' method='post'>");
			String name = emp.getName();
			double salary = emp.getSalary();
			int age = emp.getAge();
			out.println("id:"+id+"<br/>");
			out.println("姓名:<input name='name' value='"+name+"'/><br/>");
			out.println("工资:<input name='salary' value='"+salary+"'/><br/>");
			out.println("年龄:<input name='age' value='"+age+"'/>");
			out.println("<input type='submit' value='提交'/>"
					+ "</form>");
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
