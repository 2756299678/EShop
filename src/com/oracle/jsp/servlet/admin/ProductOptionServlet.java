package com.oracle.jsp.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.oracle.jsp.bean.ProductOptionBean;
import com.oracle.jsp.bean.ProductPropertyBean;
import com.oracle.jsp.bean.ProductTypeBean;
import com.oracle.jsp.dao.ProductOptionDao;
import com.oracle.jsp.dao.ProductPropertyDao;
import com.oracle.jsp.dao.ProductTypeDao;
import com.oracle.jsp.util.StringUtil;

@SuppressWarnings("serial")
public class ProductOptionServlet extends HttpServlet {
	
	protected void service(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		//���ֲ�������
		String method = req.getParameter("method");
		if("toAdd".equals(method))
		{
			toAdd(req,resp);
		}
		else if("add".equals(method))
		{
			add(req,resp);
		}
		else if("list".equals(method))
		{
			list(req,resp);
		}
		else if("showOption".equals(method))
		{
			showOption(req,resp);
		}
		else if("update".equals(method))
		{
			update(req,resp);
		}
		else if("delete".equals(method))
		{
			delete(req,resp);
		}
	}
	/**
	 * 
	 * ajax��ѯ����ĳһ�����µ�ѡ��ļ��Ϸ���
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void showOption(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{
		int propertyId = StringUtil.StringToInt(req.getParameter("id"));
		ProductOptionDao productOptionDao=new ProductOptionDao();
		List<ProductOptionBean> optionList = productOptionDao.getOptionByProperty(propertyId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(optionList));
		out.flush();
		out.close();
	}
	/**
	 * ��ѯ�������Լ�����Ϊ����ֵ��ת��list����
	 * ����ajax��ѯ��ѡ���
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @param args
	 */
	private void list (HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		ProductTypeDao productTypeDao = new ProductTypeDao();
		String sta = req.getParameter("status");
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		if(sta!=null && "1".equals(sta)){
			req.getRequestDispatcher("list.jsp?status=1").forward(req, resp);
		}
		else{
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			
		}
	}
	
	/**
	 * �������ѡ��
	 * 
	 * @param args
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void add(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		int id = StringUtil.StringToInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int sort = StringUtil.StringToInt(req.getParameter("sort"));
		int productPropertyId=StringUtil.StringToInt(req.getParameter("PropertyId"));
		ProductOptionDao productOptionDao = new ProductOptionDao();
		boolean f;
		
		if(id==0)
		{
			//����
			ProductOptionBean productOptionBean = new ProductOptionBean(sort,productPropertyId,name);
			f=productOptionDao.add(productOptionBean);
			if(f){
				resp.sendRedirect("productOptionServlet?method=toAdd&status=1");
			}else{
				System.out.println("�����Ʒ����ѡ��д�����ݿ�ʧ��");
			}
			
		}
		else{
			//�޸�
			ProductOptionBean productOptionBean = new ProductOptionBean(id,sort,productPropertyId,name);
			f = productOptionDao.update(productOptionBean);
			if(f){
				resp.sendRedirect("productOptionServlet?method=list&status=1");
			}else{
				System.out.println("�޸���Ʒ����ѡ��д��ʧ��");
			}
		}
	}
	/**
	 * ��ת��add
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 * @param args
	 */
	private void toAdd(HttpServletRequest req,HttpServletResponse resp)
	throws IOException,ServletException{
		ProductTypeDao productTypeDao = new ProductTypeDao();
		String sta = req.getParameter("status");
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		if(sta !=null&& "1".equals(sta))
		{
			req.getRequestDispatcher("add.jsp?status=1").forward(req, resp);
		}
		else{
			req.getRequestDispatcher("add.jsp").forward(req, resp);
		}
	}
	/**
	 * �޸�����ѡ��
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		int id = StringUtil.StringToInt(req.getParameter("id"));
		ProductOptionDao productOptionDao=new ProductOptionDao();
		ProductOptionBean productOptionBean=productOptionDao.getOptionById(id);
		req.setAttribute("productOptionBean", productOptionBean);
		ProductTypeDao productTypeDao=new ProductTypeDao();
		List<ProductTypeBean>productTypeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", productTypeList);
		req.getRequestDispatcher("add.jsp").forward(req, resp);
	}
	/**
	 * ɾ������ѡ��
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void delete(HttpServletRequest req,HttpServletResponse resp)throws IOException
	{
		int id=StringUtil.StringToInt(req.getParameter("id"));
		ProductOptionDao productOptionDao = new ProductOptionDao();
		productOptionDao.delete(id);
		resp.sendRedirect("productOptionServlet?method=list&status=2");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
