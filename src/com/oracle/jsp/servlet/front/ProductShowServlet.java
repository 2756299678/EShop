package com.oracle.jsp.servlet.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.bean.ProductBean;
import com.oracle.jsp.bean.ProductOptionBean;
import com.oracle.jsp.dao.ProductDao;
import com.oracle.jsp.dao.ProductOptionDao;
import com.oracle.jsp.util.StringUtil;

@SuppressWarnings("serial")
public class ProductShowServlet extends HttpServlet {

	protected void service(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException
	{
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		
		if("sort".equals(method))
		{
			sort(req,resp);
		}
		else if("search".equals(method))
		{
			search(req, resp);
		}
		else if("info".equals(method))
		{
			info(req, resp);
		}
	}
	/**
	 * 显示商品详情
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void info(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		int id = StringUtil.StringToInt(req.getParameter("id"));
		ProductDao productDao = new ProductDao();
		ProductBean productBean = productDao.getProduct(id);
		String option = productBean.getProductProperties();
		String[] options = option.split(",");
		ProductOptionDao productOptionDao = new ProductOptionDao();
		List<ProductOptionBean> productOptionBeans = new ArrayList<>();
		for(String item:options){
			int optionId = StringUtil.StringToInt(item);
			ProductOptionBean productOptionBean =productOptionDao.getOptionById(optionId);
			productOptionBeans.add(productOptionBean);
		}
		productBean.setProductOptionBeans(productOptionBeans);
		req.setAttribute("productBean", productBean);
		// 获取商品评论
		/*
		* ProductCommentDao productCommentDao = new ProductCommentDao();
		* List<CommentBean> commentBeans =
		* productCommentDao.getComsByProduct(id);
		* req.setAttribute("commentBeans", commentBeans);
		*/
		try {
		req.getRequestDispatcher("info.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
		e.printStackTrace();
		}
	}
	/**
	 * 进行搜索商品的操作
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		String chars = req.getParameter("key");
		ProductDao productDao = new ProductDao();
		List<ProductBean> productBeans = new ArrayList<>();
		productBeans = productDao.getLists(chars);
		req.setAttribute("productBeans", productBeans);
		if (productBeans.size() > 0) {
			try{
				req.getRequestDispatcher("list.jsp").forward(req,resp);
			}catch(ServletException | IOException e)
			{
				e.printStackTrace();
			}
		}else
		{
			try {
				req.getRequestDispatcher("list.jsp?status=1").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sort(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		int type_id = StringUtil.StringToInt(req.getParameter("id"));
		List<ProductBean> productBeans =productDao.getListById(type_id);
		req.setAttribute("productBeans", productBeans);
		try {
		req.getRequestDispatcher("list.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
		e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
