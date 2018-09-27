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
 * damin管理
 * @author BaoHui
 *
 */


public class AdminServlet extends HttpServlet  {

	@Override//检查方法的正确性
	//进行登陆的处理，一个是login，另一个是结束登陆
	protected void service(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		//取回method的值
		String method=req.getParameter("method");
		//判断method的值
		if("login".equals(method))
		{
			//登陆处理
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
			//下线处理
			end(req,resp);
		}
	}
	
	/**
	 * 退出登录
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
	 * 查看管理员
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listUsers(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		//设置编码
		req.setCharacterEncoding("utf-8");
		//设置变量status并提取赋值
		String status = req.getParameter("status");
		//实例化类adminDao
		AdminDao adminDao = new AdminDao();
		//得到现在的页码
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		//数据表中的数据总量
		int countSize = adminDao.getCount();
		//创建pagingBean实例进行构造
		PagingBean pagingBean = new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_1);
		List<AdminBean>adminBeans = adminDao.getListByPage(currentPage * Constants.PAGE_SIZE_1, Constants.PAGE_SIZE_1);
		pagingBean.setPrefixUrl(req.getContextPath()+"/admin/adminServlet?method=list");
		pagingBean.setAnd(true);
		//requset函数赋值
		req.setAttribute(Constants.SESSION_ADMIN_BEANS,adminBeans);
		req.setAttribute("pagingBean",pagingBean);
		if(status != null){
			req.getRequestDispatcher("listUsers.jsp?status="+status).forward(req, resp);
		}else{
			req.getRequestDispatcher("listUsers.jsp").forward(req, resp);
		}
	}
	/**
	 * 修改管理员
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
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void login(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException ,IOException{
		//编码
		req.setCharacterEncoding("utf-8");
		//取值
		String username = req.getParameter("username");
		//取值之后经过加密
		String password = MD5.GetMD5Code(req.getParameter("password"));
		//创建AdminDao的实例进行实例化
		AdminDao adminDao = new AdminDao();
		//创建AdminBean
		AdminBean adminBean = adminDao.checkLogin(username,password);
		//返回值非空账号密码匹配
		if(adminBean != null)
		{
			//登陆成功
			//将adminBean,赋值为adminBean的值存储到session中
			req.getSession().setAttribute("adminBean",adminBean);
			//路径表示
			//req.getRequestDispatcher("main.jsp").forward(req,resp);
			//重定向访问
			resp.sendRedirect(req.getContextPath()+"/admin/main.jsp");
		}else{
			//重定向访问界面
			resp.sendRedirect(req.getContextPath()+"/admin/login.jsp?status=1");
			
		}
	}
	/**
	 * 添加管理员
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
		//储存盐值
		adminBean.setSalt(salt);
		//储存加密码
		adminBean.setPassworld(md5Pwd);
		//现在时间
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//储存时间信息
		adminBean.setCreateDate(createDate1.format(new Date()));
		
		/*update为空时添加，不空时为修改*/
		if(!updateId.equals(""))
		{
			adminBean.setId(id);
			//储存盐值
			adminBean.setSalt(salt);
			//储存加密码
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
				System.out.println("未执行");
			}
		}
		else
		{
			boolean flag= adminDao.checkReg(username);
			if(flag)
			{
				//用户名不存在，注册成功，写入数据库
				adminDao.save(adminBean);
				resp.sendRedirect("addUser.jsp?status=1");
			}
			else{
				//用户名存在，注册失败，跳回
				resp.sendRedirect("addUser.jsp?status=2");
			}
		}
	}
	
	/**
	 * 删除管理员
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

