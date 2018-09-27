package com.oracle.jsp.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.bean.OrderBean;
import com.oracle.jsp.dao.OrderDao;

public class OrderServlet extends HttpServlet{
	
	protected void service(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		
		if("list".equals(method))
		{
			list(req,resp);
		}else if("delete".equals(method))
		{
			delete(req,resp);
		}
	}
	private void delete(HttpServletRequest req, HttpServletResponse resp)throws 
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String orderId=req.getParameter("orderId");
		
		OrderDao orderDao =new OrderDao();
		boolean f=false;
		f=orderDao.delete(orderId);
		if(f)
		{
			//ɾ���ɹ�
			req.getRequestDispatcher("frontUser/userOrder.jsp?status=1").forward(req, resp);
		}
		else
		{
			//ɾ��ʧ��
			req.getRequestDispatcher("frontUser/userOrder.jsp?status=2").forward(req, resp);
		}
	}
	/**
	 * ��������
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String search=req.getParameter("search");
		String orderId=req.getParameter("orderId");
		if(orderId==null)
		{
			OrderDao orderDao = new OrderDao();
			List<OrderBean> orderBeans = orderDao.getListBysearch(search);
			
			if(orderBeans==null)
			{
				req.getRequestDispatcher("frontUser/userOrder.jsp?status=0").forward(req, resp);
			}
			else
			{
				req.setAttribute("orderBeans", orderBeans);
				req.getRequestDispatcher("frontUser/userOrder.jsp").forward(req, resp);
			}
			
		}
		else
		{
			OrderDao orderDao=new  OrderDao();
			OrderBean orderBean=orderDao.getOrderBeanById(orderId);
			//�洢orderBean
			req.setAttribute("orderBean",orderBean);
			//����ϸ��ҳ��
			req.getRequestDispatcher("frontUser/orderDetail.jsp").forward(req, resp);
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
