package com.oracle.jsp.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.bean.AddressBean;
import com.oracle.jsp.bean.OrderBean;
import com.oracle.jsp.bean.OrderProductBean;
import com.oracle.jsp.bean.ProductTypeBean;
import com.oracle.jsp.bean.UserBean;
import com.oracle.jsp.dao.AddressDao;
import com.oracle.jsp.dao.OrderDao;
import com.oracle.jsp.dao.OrderProductDao;
import com.oracle.jsp.dao.ProductDao;
import com.oracle.jsp.dao.ProductTypeDao;
import com.oracle.jsp.util.DateUtil;
import com.oracle.jsp.util.StringUtil;

public class ShoppingServlet extends HttpServlet{

	protected void service(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		
		if("toCart".equals(method)){
			toCart(req,resp);
		}else if("addItem".equals(method)){
			addItem(req,resp);
		}else if("del".equals(method)){
			del(req,resp);
		}else if("makeOrder".equals(method)){
			makeOrder(req,resp);
		}else if("addOrder".equals(method)){
			addOrder(req,resp);
		}
		
	}
	/**
	 * 提交订单
	 * @param req
	 * @param resp
	 */
	private void addOrder(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
		if(userBean==null){
				userBean = new UserBean();
		}
		String code = DateUtil.getDateStr()+DateUtil.getTimeStr()+userBean.getId();
		OrderBean orderBean = new OrderBean(code,userBean);
		OrderDao orderDao = new OrderDao();
		orderDao.addOrder(orderBean);
		orderBean = orderDao.getOrderByCode(code);
		//存储订单
		ProductDao productDao = new ProductDao();
		OrderProductDao orderProductDao = new OrderProductDao();
		OrderProductBean productOrderBean;
		float sum = 0;
		float oSum = 0;
		String[] items = req.getParameterValues("sub");
		for(String item :items){
			String[] arr = item.split("_");
			if(arr.length==2){
				int productId = StringUtil.StringToInt(arr[0]);
				int number = StringUtil.StringToInt(arr[1]);
				productOrderBean = new
				OrderProductBean(orderBean,productDao.getProduct(productId),number);
				orderProductDao.addOrderProduct(productOrderBean);
				sum += productDao.getProduct(productId).getPrice();
				oSum += productDao.getProduct(productId).getOriginalPrice();
				//删除 cookie
				Cookie cookie = new Cookie("items_"+userBean.getId()+"_"+productId, null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
		}
		orderBean.setPrice(sum);
		orderBean.setOriginal_price(oSum);
		//付款方式
		int payway = StringUtil.StringToInt(req.getParameter("payway"));
		orderBean.setPayment_type(payway);;
		//地址
		int addressId = StringUtil.StringToInt(req.getParameter("addr"));
		orderBean.setAddress_id(addressId);
		orderDao.upOrder(orderBean);
		try {
			req.getRequestDispatcher("../productShow/productShowServlet?method=sort&id=0").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 确认订单
	 * @param req
	 * @param resp
	 */
	private void makeOrder(HttpServletRequest req, HttpServletResponse resp)
	{
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
		if(userBean==null){
		userBean = new UserBean();
		}
		float sum = 0;
		float oSum = 0;
		ProductDao productDao = new ProductDao();
		List<OrderProductBean> productOrderBeans = new ArrayList<>();
		OrderProductBean productOrderBean;
		String[] items = req.getParameterValues("sub");
		for(String item :items){
			String[] arr = item.split("_");
			if(arr.length==2){
				int productId = StringUtil.StringToInt(arr[0]);
				int number = StringUtil.StringToInt(arr[1]);
				productOrderBean = new
				OrderProductBean(productDao.getProduct(productId),number);
				productOrderBeans.add(productOrderBean);
				sum += productDao.getProduct(productId).getPrice()*number;
				oSum += productDao.getProduct(productId).getOriginalPrice()*number;
			}
		}
		req.setAttribute("productOrderBeans", productOrderBeans);
		req.setAttribute("sum", sum);
		req.setAttribute("oSum", oSum);
		//转存地址
		AddressDao addressDao=new AddressDao();
		List<AddressBean> addressBeans = addressDao.getAddressList(userBean.getId());
		req.setAttribute("addressBeans", addressBeans);
		try {
			req.getRequestDispatcher("order.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除购物车商品
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp)throws 
	ServletException ,IOException{
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
		if(userBean==null){
		userBean = new UserBean();
		}
		int productId = StringUtil.StringToInt(req.getParameter("productId"));
		Cookie cookie = new Cookie("items_"+userBean.getId()+"_"+productId, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		resp.addCookie(cookie);
		resp.sendRedirect("shoppingServlet?method=toCart");
	}

	/**
	 * 添加商品到购物车
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void addItem(HttpServletRequest req, HttpServletResponse resp)throws 
	IOException {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
		if(userBean==null){
			userBean = new UserBean();
		}
		int productId = StringUtil.StringToInt(req.getParameter("productId"));
		String number = req.getParameter("num");
		Cookie cookie = new Cookie("items_"+userBean.getId()+"_"+productId, number);
		cookie.setPath("/");
		resp.addCookie(cookie);
		PrintWriter out = resp.getWriter();
		out.print("data");
		out.flush();
		out.close();
	}
	/**
	 * 转到购物车
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toCart(HttpServletRequest req, HttpServletResponse resp)throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		//得到用户详细信息
		UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
		//没有用户登陆
		if(userBean==null){
			//置空
			userBean = new UserBean();
		}
		//产品列表
		ProductTypeDao productTypeDao = new ProductTypeDao();
		List<ProductTypeBean> typeBeans = productTypeDao.getTypeBeans(0);
		req.setAttribute("typeBeans", typeBeans);
		//存储订单
		ProductDao productDao = new ProductDao();
		List<OrderProductBean> productOrderBeans = new ArrayList<>();
		OrderProductBean productOrderBean;
		//得到cookie的内容
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().startsWith("items_"+userBean.getId()))
				{
					String[] arr = cookie.getName().split("_");
					if(arr.length==3){
						int productId = StringUtil.StringToInt(arr[2]);
						int number = StringUtil.StringToInt(cookie.getValue());
						productOrderBean = new OrderProductBean(productDao.getProduct(productId),number);
						productOrderBeans.add(productOrderBean);
						
					}
				}
			}
		}
		req.setAttribute("productOrderBeans", productOrderBeans);
		try{
			req.getRequestDispatcher("cart.jsp").forward(req, resp);
		}catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
