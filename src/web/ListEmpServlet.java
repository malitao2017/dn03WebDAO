/*
 * ListEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DAOFactory;
import dao.EmployeeDAO;
import entity.Employee;

/**
 * 展示全部雇员
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try{
			EmployeeDAO dao = null;
//			dao = new EmployeeDAOJdbcImpl();
			dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
			
			List<Employee> list = dao.findAll();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<table border='1' width='60%' cellpadding='0' cellspacing='0'>"
					+ "<tr>"
						+ "<td>序号</td>"
						+ "<td>姓名</td>"
						+ "<td>工资</td>"
						+ "<td>年龄</td>"
						+ "<td>操作</td>"
					+ "</tr>");
			for(Employee emp :list){
				out.println("<tr>"
						+ "<td>"+emp.getId()+"</td>"
						+ "<td>"+emp.getName()+"</td>"
						+ "<td>"+emp.getSalary()+"</td>"
						+ "<td>"+emp.getAge()+"</td>"
						+ "<td><a href='del?id="+emp.getId()+"'>删除</a>&nbsp;"
						+ "<a href='load?id="+emp.getId()+"'>修改</a></td>"
						+ "</tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html' >添加雇员</a>");
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}
