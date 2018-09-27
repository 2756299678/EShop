package com.oracle.jsp.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.bean.UserBean;
import com.oracle.jsp.dao.UserDao;
import com.oracle.jsp.util.StringUtil;

public class UserServlet extends HttpServlet{
	
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws 
	ServletException,IOException{
		
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		
	   if("list".equals(method)){
			list(req,resp);
		}else if("update".equals(method))
		{
			update(req,resp);
		}else if("search".equals(method))
		{
			search(req,resp);
		}
	}
	private void search(HttpServletRequest req, HttpServletResponse resp)throws 
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String username =req.getParameter("search");
		UserDao userDao=new UserDao();
		UserBean userBean=new UserBean();
		userBean = userDao.getUserBeanbyName(username);
		if(userBean==null)
		{
			req.getRequestDispatcher("frontUser/blockUser.jsp?status=0").forward(req, resp);
		}
		else
		{
			req.setAttribute("userBean", userBean);
			req.getRequestDispatcher("frontUser/blockUser.jsp").forward(req, resp);
		}
		
		
	}
	/**
	 * 更新您冻结解冻用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int status=StringUtil.StringToInt(req.getParameter("status"));
		int id = StringUtil.StringToInt(req.getParameter("id"));
		//System.out.print(status+"  "+id);
		UserDao userDao = new UserDao();
		boolean f=false;
		f=userDao.update(status,id);
		if(f)
		{
			resp.sendRedirect("userServlet?method=list&panduan=1");
		}
		else
		{
			resp.sendRedirect("userServlet?method=list&panduan=0");
		}
		
	}
	/**
	 * 列表管理
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp)throws 
	ServletException,IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		UserDao userDao=new UserDao();
		List<UserBean> userBeans=userDao.getList();
		req.setAttribute("userBeans", userBeans);
		try {
			req.getRequestDispatcher("frontUser/listUsers.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
