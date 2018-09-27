package com.oracle.jsp.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.oracle.jsp.bean.AddressBean;
import com.oracle.jsp.bean.AreaBean;
import com.oracle.jsp.bean.CityBean;
import com.oracle.jsp.bean.ProductTypeBean;
import com.oracle.jsp.bean.ProvinceBean;
import com.oracle.jsp.dao.AddressDao;
import com.oracle.jsp.dao.ProductTypeDao;
import com.oracle.jsp.util.StringUtil;

public class AddressServlet extends HttpServlet{
	
	protected void service (HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String method =req.getParameter("method");
		
		if("show".equals(method))
		{
			show(req,resp);
		}else if("add".equals(method))
		{
			add(req,resp);
		}else if("delete".equals(method))
		{
			delete(req,resp);
		}else if("update".equals(method))
		{
			update(req,resp);
		}else if("getCity".equals(method))
		{
			getCity(req,resp);
		}else if("getArea".equals(method)){
			getArea(req,resp);
		}
		
	}
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void getArea(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		AddressDao addressDao = new AddressDao();
		System.out.println("转到getArea");
		List<AreaBean> areaList = addressDao.getAreaList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(areaList));
		out.flush();
		out.close();

		}
	/**
	 * 获取城市列表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	
	private void getCity(HttpServletRequest req, HttpServletResponse resp)throws
	IOException{
		// TODO Auto-generated method stub
		int parentId=StringUtil.StringToInt(req.getParameter("id"));
		AddressDao addressDao = new AddressDao();
		List<CityBean> cityList = addressDao.getCityList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(cityList));
		out.flush();
		out.close();
	}

	/**
	 * 更新状态，设为默认地址
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int id=StringUtil.StringToInt(req.getParameter("id"));
		int userId=StringUtil.StringToInt(req.getParameter("userId"));
		boolean f=false;
		AddressDao addressDao=new AddressDao();
		f=addressDao.update(id,userId);
		
		resp.sendRedirect(req.getContextPath()+"/front/user/addressServlet?method=show&userId="+userId);
		
	}
	
	/**
	 * 删除收获地址
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int id=StringUtil.StringToInt(req.getParameter("id"));
		int userId=StringUtil.StringToInt(req.getParameter("userId"));
		boolean f=false;
		AddressDao addressDao=new AddressDao();
		f=addressDao.delete(id);
		if(f)
		{
			resp.sendRedirect(req.getContextPath()+"/front/user/addressServlet?method=show&userId="+userId);
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/front/user/addressServlet?method=show&userId="+userId);
		}	
	}
	/**
	 * 收获地址展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int userid=StringUtil.StringToInt(req.getParameter("userId"));
		AddressDao addressDao = new AddressDao();
		List<AddressBean> addressBeans=addressDao.getAddressList(userid);
		req.setAttribute("addressBeans",addressBeans);
		List<ProvinceBean> provinces=addressDao.getProvinceList();
		req.setAttribute("provinces", provinces);
		req.getRequestDispatcher("/front/user/address.jsp").forward(req, resp);
	}

	/**
	 * 添加收获地址
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		//收货人
		String name=req.getParameter("name");
		//详细地址
		String address=req.getParameter("address");
		//手机电话
		String cellphone=req.getParameter("account");
		//用户id
		int userid=StringUtil.StringToInt(req.getParameter("userId"));
		//省的id
		int provinceId=StringUtil.StringToInt(req.getParameter("ProvinceId"));
		//城市的id
		int city=StringUtil.StringToInt(req.getParameter("CityId"));
		//地区的id
		int area=StringUtil.StringToInt(req.getParameter("AreaId"));
		
		AddressBean  addressBean = new AddressBean();
		//存储进addressbean
		
		addressBean.setName(name);
		addressBean.setAddress(address);
		addressBean.setCellphone(cellphone);
		addressBean.setUser_id(userid);
		addressBean.setCity_id(city);
		addressBean.setRegion_id(area);
		addressBean.setProvince_id(provinceId);
		AddressDao addressDao=new AddressDao();
		
		addressDao.save(addressBean);

		resp.sendRedirect(req.getContextPath()+"/front/user/addressServlet?method=show&userId="+userid);

	
		
	}





}
