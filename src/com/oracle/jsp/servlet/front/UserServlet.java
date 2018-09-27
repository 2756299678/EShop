package com.oracle.jsp.servlet.front;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oracle.jsp.bean.AdminBean;
import com.oracle.jsp.bean.ProductBean;
import com.oracle.jsp.bean.UserBean;
import com.oracle.jsp.dao.AdminDao;
import com.oracle.jsp.dao.UserDao;
import com.oracle.jsp.util.Constants;
import com.oracle.jsp.util.DateUtil;
import com.oracle.jsp.util.MD5;
import com.oracle.jsp.util.StringUtil;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet{

	protected void service(HttpServletRequest req,HttpServletResponse resp)throws 
	ServletException,IOException{
		
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		
		if("reg".equals(method)){
			reg(req,resp);
		}else if("login".equals(method))
		{
			login(req,resp);
		}else if("end".equals(method))
		{
			end(req,resp);
		}else if("updatemessage".equals(method))
		{
			updatemessage(req,resp);
		}else if("updatepic".equals(method))
		{
			updatepic(req,resp);
		}
	}
	
	
	/**
	 * ����ͷ��
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatepic(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		int updateId=StringUtil.StringToInt(req.getParameter("updateId"));
		
		DiskFileItemFactory diskFileItemFactory= new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		
		try
		{
			UserBean userBean =new UserBean();
			List<FileItem>fileItems=upload.parseRequest(req);
			
			for(FileItem item:fileItems){
				item.getString("utf-8");
				
				if(item.isFormField()){
					//���������
					processFormField(item,userBean);
				}else{
					//�����ϴ��ļ�
					//System.out.println("tupian");
					processUploadFile(item,userBean);
				}
			}
			
			if(userBean.getPic().equals(""))
			{
				userBean.setPic("http://localhost:8080/EShop/upload/user/20170704/191604.jpg");
			}
			//����ͼƬ
			UserDao userDao = new UserDao();
			boolean f=false;
			f=userDao.updatepic(updateId,userBean);
			if(f)
			{
				//���ĳɹ�
				userBean=userDao.getUserBeanbyId(updateId);
				req.getSession().setAttribute("userBean",userBean);
				req.getRequestDispatcher("/front/user/userInfoDetail.jsp").forward(req, resp);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * ������ϸ��Ϣ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatemessage(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int updateId=StringUtil.StringToInt(req.getParameter("updateId"));
		
		String account = req.getParameter("account");
		String nickname=req.getParameter("nickname");
		String password=req.getParameter("inputPassword");
		
		
		UserBean userBean = new UserBean();
		String salt=StringUtil.getRandomString(10);
		userBean.setSalt(salt);
		String md5Pwd = MD5.GetMD5Code(MD5.GetMD5Code(password)+salt);
		userBean.setPassword(md5Pwd);
		
		userBean.setId(updateId);
		userBean.setUsername(account);
		userBean.setNickname(nickname);
		
		UserDao userDao = new UserDao();
		boolean flag=false;
		if(account.equals(userDao.getUserBeanbyId(updateId).getUsername()))
		{
			flag=true;
		}
		else
		{
			flag=userDao.checkReg(account);
		}
		if(flag)
		{
			boolean f=false;
			f=userDao.updatemessage(userBean);
			if(f)
			{
				//���ĳɹ�
				userBean=userDao.getUserBeanbyId(updateId);
				req.getSession().setAttribute("userBean",userBean);
				req.getRequestDispatcher("/front/user/userInfoDetail.jsp?status=1").forward(req, resp);
				//resp.sendRedirect(req.getContextPath()+"/front/user/userInfoDetail.jsp?status=1");
			}
			else
			{
				//����ʧ��
				resp.sendRedirect(req.getContextPath()+"/front/user/userInfoDetail.jsp?status=2");
			}
			
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/front/user/userInfoDetail.jsp?status=3");
		}
			
	}
	/**
	 * �Ƴ���½
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void end(HttpServletRequest req, HttpServletResponse resp)throws 
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String status = req.getParameter("status");
		if (status != null && "1".equals(status)) {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/front/user/login.jsp");
		}
	}
	
	/**
	 * ��½����
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException ,IOException{
		// TODO Auto-generated method stub
		//����
		req.setCharacterEncoding("utf-8");
		//ȡֵ
		String username = req.getParameter("account");
		//ȡֵ֮�󾭹�����
		String password = MD5.GetMD5Code(req.getParameter("password"));
		//����AdminDao��ʵ������ʵ����
		UserDao userDao = new UserDao();
		//����AdminBean
		UserBean userBean = userDao.checkLogin(username,password);
		//����ֵ�ǿ��˺�����ƥ��
		if(userBean != null)
		{
			if(userBean.getStatus()==1)
			{
				//��½�ɹ�
				userBean=userDao.getUserBeanbyName(username);
				req.getSession().setAttribute("userBean",userBean);
				//·����ʾ
				//req.getRequestDispatcher("main.jsp").forward(req,resp);
				//�ض������
				resp.sendRedirect(req.getContextPath()+"/front/user/userInfo.jsp");
			}
			else
			{
				//�ض�����ʽ���
				resp.sendRedirect(req.getContextPath()+"/front/user/login.jsp?status=3");
			}
		}else{
			//�ض�����ʽ���
			resp.sendRedirect(req.getContextPath()+"/front/user/login.jsp?status=1");
			
		}
	}
	
	/**
	 * ע�ᴦ��
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void reg(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		//�ļ��Ĵ���
		DiskFileItemFactory diskFileItemFactory= new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		
		
		try
		{
			UserBean userBean =new UserBean();
			List<FileItem>fileItems=upload.parseRequest(req);
			
			//1Ϊ�ⶳ��0Ϊ����
			int status=1;
			userBean.setStatus(status);
			
			//����ʱ��
			SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//����ʱ����Ϣ
			userBean.setCreateDate(createDate1.format(new Date()));
			//�洢ͼƬ
			for(FileItem item:fileItems){
				item.getString("utf-8");
				
				if(item.isFormField()){
					//���������
					processFormField(item,userBean);
				}else{
					//�����ϴ��ļ�
					System.out.println("tupian");
					processUploadFile(item,userBean);
				}
			}
			
			UserDao userDao= new UserDao();
			boolean flag= userDao.checkReg(userBean.getUsername());
			if(flag)
			{
				userDao.add(userBean);
				//ע��ɹ�
				resp.sendRedirect("add.jsp?status=1");
			}
			else{
				System.out.println("�û����ظ�");
				//ע��ʧ��
				resp.sendRedirect("add.jsp?status=2");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * �����ϴ����ļ�
	 * @param req
	 * @param resp
	 * @param productBean
	 */
	private void processUploadFile(FileItem item,UserBean userBean)
	{
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		filename = filename.substring(index+1,filename.length());
		String picPath=Constants.PIC_SHOW_PATH +"user/"+ DateUtil.getDateStr()+"/"+DateUtil.getTimeStr()+"."+filename;
		long fileSize = item.getSize();
		if("".equals(filename)&&fileSize==0){
			return;
		}
		//�½��ļ��У�����Ϊ�ļ�������ʱ��Ϊ�ļ���
		File file = new File(Constants.PIC_UPLOAD_PATH+"/user/"+DateUtil.getDateStr());
		file.mkdirs();
		File uploadFile = new File(Constants.PIC_UPLOAD_PATH+"/user/"+DateUtil.getDateStr()+"/"+DateUtil.getTimeStr()+"."+filename);
		try{
			item.write(uploadFile);
			userBean.setPic(picPath);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * ���������
	 * @param item
	 * @param pw
	 * @param productBean
	 * @param UnsupportedEncodingException
	 */
	private void processFormField(FileItem item , UserBean userBean)throws UnsupportedEncodingException{
		String name = item.getFieldName();
		String value = new String(item.getString("utf-8"));
		switch(name){
		case "account":
			userBean.setUsername(value);
			break;
		case "inputPassword":
			String salt=StringUtil.getRandomString(10);
			userBean.setSalt(salt);
			String md5Pwd = MD5.GetMD5Code(MD5.GetMD5Code(value)+salt);
			userBean.setPassword(md5Pwd);
			break;
		case "nickname":
			userBean.setNickname(value);
			break;
		case "truename":
			userBean.setTruename(value);
			break;
		case "sex":
			userBean.setSex(value);
			break;
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
