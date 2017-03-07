/*
 * DelEmpServlet.java
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

/**
 * 按id删除该条记录
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class DelEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try{
			EmployeeDAO dao = null;
//			dao = new EmployeeDAOJdbcImpl();
			dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
			dao.delete(id);
			response.sendRedirect("list");
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
