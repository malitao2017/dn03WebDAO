/*
 * AddEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DAOFactory;
import dao.EmployeeDAO;
import entity.Employee;


/**
 * 添加雇员
 * 1.重定向模式
 * @author LT
 * @version 1.0, 2015年9月9日
 */
public class AddEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			double salary = Double.valueOf(request.getParameter("salary"));
			int age = Integer.valueOf(request.getParameter("age"));
			System.out.println("name:"+name);
			System.out.println("salaty:"+salary);
			System.out.println("age:"+age);
		
			EmployeeDAO dao = null;
//			dao = new EmployeeDAOJdbcImpl();
			dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
			
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSalary(salary);
			emp.setAge(age);
			dao.save(emp);
			//更改为重定向模式
			//重定向之前不能有out.close();
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
