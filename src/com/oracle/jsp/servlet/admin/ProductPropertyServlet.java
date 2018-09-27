package com.oracle.jsp.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.oracle.jsp.bean.ProductPropertyBean;
import com.oracle.jsp.bean.ProductTypeBean;
import com.oracle.jsp.dao.ProductPropertyDao;
import com.oracle.jsp.dao.ProductTypeDao;
import com.oracle.jsp.util.StringUtil;

@SuppressWarnings("serial")
public class ProductPropertyServlet extends HttpServlet{
	
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
		else if("getType".equals(method))
		{
			getType(req,resp);
		}
		else if("getProperty".equals(method))
		{
			getProperty(req,resp);
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
	 * ��ȡ����
	 * 
	 * @param resp
	 * @param peq
	 * @throws IOException
	 */
	private void getProperty(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{
		int typeId = StringUtil.StringToInt(req.getParameter("id"));
		ProductPropertyDao productPropertyDao=new ProductPropertyDao();
		List<ProductPropertyBean> propertyList = productPropertyDao.getListByType(typeId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(propertyList));
		out.flush();
		out.close();
	}
	/**
	 * Я������������ת���ൽ������ӽ���
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 * @param args
	 */
	private void getType(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{
		int parentId = StringUtil.StringToInt("id");
		ProductTypeDao productTypeDao = new ProductTypeDao();
		List<ProductTypeBean> typelist = productTypeDao.getTypeList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(typelist));
		out.flush();
		out.close();
	}
	/**
	 * �����س������༯�ϣ���ת�������
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
	 * �����Ʒ����
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * 
	 */
	private void add(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		int id = StringUtil.StringToInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int sort = StringUtil.StringToInt(req.getParameter("sort"));
		String[] parIds = req.getParameterValues("productTypeId");
		int productTypeId = 0;
		for(String parId : parIds){
			productTypeId = Math.max(productTypeId, StringUtil.StringToInt(parId));
		}
		ProductPropertyDao productPropertyDao = new ProductPropertyDao();
		boolean f;
		if(id==0)
		{
			//����
			ProductPropertyBean productPropertyBean = new ProductPropertyBean(sort,productTypeId,name);
			f=productPropertyDao.add(productPropertyBean);
			if(f){
				resp.sendRedirect("productPropertyServlet?method=toAdd&status=1");
			}else{
				System.out.println("�������д�����ݿ�ʧ��");
			}
			
		}
		else{
			ProductPropertyBean productPropertyBean=new ProductPropertyBean(id,sort,productTypeId,name);
			f=productPropertyDao.update(productPropertyBean);
			if(f)
			{
				resp.sendRedirect("productPropertyServlet?method=list&status=1");
			}else{
				System.out.println("�޸�����д�����ݿ�ʧ��");
			}
		}
	}

	private void list(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		ProductTypeDao productTypeDao = new ProductTypeDao();
		String sta = req.getParameter("status");
		List<ProductTypeBean> typeList =productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		if(sta!=null&&"1".equals(sta)){
			req.getRequestDispatcher("list.jsp?status=1").forward(req, resp);
			
		}else{
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			
		}
	}
	
	private void update(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException
	{
		int id = StringUtil.StringToInt(req.getParameter("id"));
		ProductPropertyDao productPropertyDao = new ProductPropertyDao();
		ProductPropertyBean productPropertyBean =productPropertyDao.getPropertyById(id);
		req.setAttribute("productPropertyBean",productPropertyBean);
		ProductTypeDao productTypeDao =new ProductTypeDao();
		List<ProductTypeBean> productTypeList=productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", productTypeList);
		req.getRequestDispatcher("add.jsp").forward(req, resp);
	}
	
	/**
	 * ɾ������
	 * @param req
	 * @param resp
	 * @throes IOException
	 */
	private void delete(HttpServletRequest req,HttpServletResponse resp)throws IOException{
		
		int id = StringUtil.StringToInt(req.getParameter("id"));
		ProductPropertyDao productPropertyDao=new ProductPropertyDao();
		productPropertyDao.delete(id);
		resp.sendRedirect("productPropertyServlet?method=list&status=2");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
