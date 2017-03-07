/*
 * ModifyEmpServlet.java
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
 * 修改数据库
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class ModifyEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		double salary = Double.parseDouble(req.getParameter("salary"));
		int age = Integer.parseInt(req.getParameter("age"));
		try{
			EmployeeDAO dao = null;
//			dao = new EmployeeDAOJdbcImpl();
			dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
			
			Employee emp = new Employee();
			emp.setId(id);
			emp.setName(name);
			emp.setSalary(salary);
			emp.setAge(age);
			dao.update(emp);
			resp.sendRedirect("list");
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
