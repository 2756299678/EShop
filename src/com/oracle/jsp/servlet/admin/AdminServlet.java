package com.oracle.jsp.servlet.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.util.Constants;
import com.oracle.jsp.util.MD5;
import com.oracle.jsp.util.StringUtil;
import com.oracle.jsp.bean.AdminBean;
import com.oracle.jsp.bean.PagingBean;
import com.oracle.jsp.dao.AdminDao;




/**
 * 
 * damin����
 * @author BaoHui
 *
 */


public class AdminServlet extends HttpServlet  {

	@Override//��鷽������ȷ��
	//���е�½�Ĵ���һ����login����һ���ǽ�����½
	protected void service(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		
		//���ñ���
		req.setCharacterEncoding("utf-8");
		//ȡ��method��ֵ
		String method=req.getParameter("method");
		//�ж�method��ֵ
		if("login".equals(method))
		{
			//��½����
			login(req,resp);
		}else if("addUser".equals(method))
		{
			addUser(req,resp);
		}
		else if("list".equals(method))
		{
			listUsers(req,resp);
		}
		else if("toUpdate".equals(method))
		{
			toUpdate(req,resp);
		}
		else if("delete".equals(method))
		{
			delete(req,resp);
		}
		else if("end".equals(method)){
			//���ߴ���
			end(req,resp);
		}
	}
	
	/**
	 * �˳���¼
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void end(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String status = req.getParameter("status");
		if (status != null && "1".equals(status)) {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		}
	}
	/**
	 * �鿴����Ա
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listUsers(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		//���ñ���
		req.setCharacterEncoding("utf-8");
		//���ñ���status����ȡ��ֵ
		String status = req.getParameter("status");
		//ʵ������adminDao
		AdminDao adminDao = new AdminDao();
		//�õ����ڵ�ҳ��
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		//���ݱ��е���������
		int countSize = adminDao.getCount();
		//����pagingBeanʵ�����й���
		PagingBean pagingBean = new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_1);
		List<AdminBean>adminBeans = adminDao.getListByPage(currentPage * Constants.PAGE_SIZE_1, Constants.PAGE_SIZE_1);
		pagingBean.setPrefixUrl(req.getContextPath()+"/admin/adminServlet?method=list");
		pagingBean.setAnd(true);
		//requset������ֵ
		req.setAttribute(Constants.SESSION_ADMIN_BEANS,adminBeans);
		req.setAttribute("pagingBean",pagingBean);
		if(status != null){
			req.getRequestDispatcher("listUsers.jsp?status="+status).forward(req, resp);
		}else{
			req.getRequestDispatcher("listUsers.jsp").forward(req, resp);
		}
	}
	/**
	 * �޸Ĺ���Ա
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toUpdate(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		int id = StringUtil.StringToInt(req.getParameter("id"));
		if(id>1)
		{
			AdminDao adminDao = new AdminDao();
			AdminBean adminBean = adminDao.getById(id);
			req.setAttribute(Constants.SESSION_UPDATE_BEAN,adminBean);
			req.getRequestDispatcher("addUser.jsp").forward(req, resp);
		}else if(id==1){
			resp.sendRedirect(req.getContextPath()+"/admin/adminServlet?method=list&status=1");
		}else{
			resp.sendRedirect(req.getContextPath()+"/admin/adminServlet?method=list&status=2");
		}
	}
	
	
	/**
	 * ��¼
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void login(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException ,IOException{
		//����
		req.setCharacterEncoding("utf-8");
		//ȡֵ
		String username = req.getParameter("username");
		//ȡֵ֮�󾭹�����
		String password = MD5.GetMD5Code(req.getParameter("password"));
		//����AdminDao��ʵ������ʵ����
		AdminDao adminDao = new AdminDao();
		//����AdminBean
		AdminBean adminBean = adminDao.checkLogin(username,password);
		//����ֵ�ǿ��˺�����ƥ��
		if(adminBean != null)
		{
			//��½�ɹ�
			//��adminBean,��ֵΪadminBean��ֵ�洢��session��
			req.getSession().setAttribute("adminBean",adminBean);
			//·����ʾ
			//req.getRequestDispatcher("main.jsp").forward(req,resp);
			//�ض������
			resp.sendRedirect(req.getContextPath()+"/admin/main.jsp");
		}else{
			//�ض�����ʽ���
			resp.sendRedirect(req.getContextPath()+"/admin/login.jsp?status=1");
			
		}
	}
	/**
	 * ��ӹ���Ա
	 * 
	 * @param rep
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	private void addUser(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		int id = StringUtil.StringToInt(req.getParameter("updateId"));
		String updateId = req.getParameter("updateId");
		System.out.println(updateId);
		
		AdminDao adminDao = new AdminDao();
		String username=req.getParameter("username");
		String password = req.getParameter("password");
		AdminBean adminBean = new AdminBean(); 
		
		adminBean.setUsername(username);
		String salt=StringUtil.getRandomString(10);
		String md5Pwd = MD5.GetMD5Code(MD5.GetMD5Code(password)+salt);
		//������ֵ
		adminBean.setSalt(salt);
		//���������
		adminBean.setPassworld(md5Pwd);
		//����ʱ��
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//����ʱ����Ϣ
		adminBean.setCreateDate(createDate1.format(new Date()));
		
		/*updateΪ��ʱ��ӣ�����ʱΪ�޸�*/
		if(!updateId.equals(""))
		{
			adminBean.setId(id);
			//������ֵ
			adminBean.setSalt(salt);
			//���������
			adminBean.setPassworld(md5Pwd);
			
			System.out.println(adminBean.getPassword());
			System.out.println(adminBean.getSalt());
			boolean f=false;
			f=adminDao.update(adminBean);
			if(f)
			{
				resp.sendRedirect("adminServlet?method=list&status=2");
			}
			else
			{
				System.out.println("δִ��");
			}
		}
		else
		{
			boolean flag= adminDao.checkReg(username);
			if(flag)
			{
				//�û��������ڣ�ע��ɹ���д�����ݿ�
				adminDao.save(adminBean);
				resp.sendRedirect("addUser.jsp?status=1");
			}
			else{
				//�û������ڣ�ע��ʧ�ܣ�����
				resp.sendRedirect("addUser.jsp?status=2");
			}
		}
	}
	
	/**
	 * ɾ������Ա
	 * 
	 * @param req
	 * @param resp
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		
		req.setCharacterEncoding("utf-8");
		
		int id = StringUtil.StringToInt(req.getParameter("id"));
		if(id>1)
		{
			AdminDao adminDao = new AdminDao();
			adminDao.delete(id);
			resp.sendRedirect(req.getContextPath()+"/admin/adminServlet?method=list&status=3");
		}else if(id == 1)
		{
			resp.sendRedirect(req.getContextPath()+"/admin/adminServlet?method=list&status=1");
		}else
		{
			resp.sendRedirect(req.getContextPath()+"/admin/adminServlet?method=list&status=2");
		}
	}
}

