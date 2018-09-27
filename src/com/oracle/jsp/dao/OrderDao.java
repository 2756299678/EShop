package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.jsp.bean.AddressBean;
import com.oracle.jsp.bean.OrderBean;
import com.oracle.jsp.bean.UserBean;
import com.oracle.jsp.util.DBUtil;

public class OrderDao {

	/**
	 * 查看订单详情
	 * @param id
	 * @return
	 */
	
	
	/**
	* 获取订单列表
	* @return
	*/
	public List<OrderBean> getList(int id) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery("select * from user_order where user_id='"+id+"'");
			OrderBean bean;
			while(rs.next()) {
			bean = new OrderBean();
			bean.setId(rs.getInt("id"));
			bean.setCode(rs.getString("code"));
			bean.setPrice(rs.getFloat("price"));
			bean.setCreate_date(rs.getString("create_date"));
			orderBeans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return orderBeans;
	}
	/**
	* 删除订单
	* @param id
	* @return
	*/
	public boolean deleteById(int id) {
	String sql="delete from user_order where id='"+id+"'";
	Connection conn = DBUtil.getConn();
	Statement state = null;
	int a=0;
	boolean f=false;
	try {
	state = conn.createStatement();
	a= state.executeUpdate(sql);
	} catch (Exception e) {
	e.printStackTrace();
	} finally {
	DBUtil.close( state, conn);
	}
	if (a>0){
	f=true;
	}
	return f;
	}
	
	/**
	* 添加订单
	* @param orderBean
	*/
	public boolean addOrder(OrderBean orderBean) {
		String sql="insert into user_order(code,original_price,price,user_id,create_date) value('"+orderBean.getCode()+"','"+orderBean.getOriginal_price()+"','"+orderBean.getPrice()+"','"+orderBean.getUserBean().getId()+"',now())";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f=false;
		int a=0;
		try {
			state = conn.createStatement();
			a= state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
		if (a>0){
			f=true;
		}
		return f;
	}
	
	/**
	* 通过订单编号获取订单
	* @param code
	* @return
	*/
	public OrderBean getOrderByCode(String code) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		OrderBean orderBean = new OrderBean();
	try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from user_order where code='"+code+"'");
		if(rs.next()) {
			orderBean.setId(rs.getInt("id"));
			orderBean.setCode(rs.getString("code"));
			orderBean.setOriginal_price(rs.getFloat("original_price"));
			orderBean.setPrice(rs.getFloat("price"));
			orderBean.setAddress_id(rs.getInt("address_id"));
			orderBean.setUser_id(rs.getInt("user_id"));
			orderBean.setPayment_type(rs.getInt("payment_type"));
			orderBean.setStatus(rs.getInt("status"));
			orderBean.setCreate_date(rs.getString("create_date"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
	DBUtil.close(rs, state, conn);
	}
		return orderBean;
	}
	
	/**
	 * 查询
	 * @param search
	 * @return
	 */
	public List<OrderBean> getListBysearch(String search){
		
		String sql2="select * from user_order where user_id=("
				+ "select id from user where username='"+search+"')";
		
		List<OrderBean> list = new ArrayList<>();
		//System.out.println(sql2);
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql2);
			OrderBean orderBean=null;
			if(rs.next())
			{
				orderBean= new OrderBean();
				orderBean.setId(rs.getInt("id"));
				orderBean.setCode(rs.getString("code"));
				orderBean.setOriginal_price(rs.getDouble("original_price"));
				orderBean.setPrice(rs.getDouble("price"));
				int addressId=rs.getInt("address_id");
				AddressDao addressDao=new AddressDao();
				AddressBean addressBean=addressDao.getAddressBeanbyId(addressId);
				orderBean.setAddressBean(addressBean);
				int userId=rs.getInt("user_id");
				UserDao userDao=new UserDao();
				UserBean userBean=userDao.getUserBeanbyId(userId);
				orderBean.setUserBean(userBean);
				orderBean.setPayment_type(rs.getInt("payment_type"));
				orderBean.setCreate_date(rs.getString("create_date"));
				
				list.add(orderBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.close(rs,state,conn);
		}
		return list;
	}
	/**
	* 更新订单
	* @param orderBean
	*/
	public boolean upOrder(OrderBean orderBean) {
		String sql="update user_order set original_price='"+orderBean.getOriginal_price()+"',price='"+orderBean.getPrice()+"',address_id='"+orderBean.getAddress_id()+"',payment_type='"+orderBean.getPayment_type()+"' where id='"+orderBean.getId()+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		int a=0;
		boolean f=false;
		try {
			state = conn.createStatement();
			a= state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
		if (a>0){
			f=true;
		}
		return f;
	}
	/**
	 * 
	 * 删除
	 * @param orderId
	 * @return
	 */
	public boolean delete(String orderId) {
		// TODO Auto-generated method stub
		String sql = "delete from user_order where id='"+orderId+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f=false;
		int a=0;
		try{
			state = conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
		if(a>0){
			f=true;
		}
		return f;
	}
	/**
	 * 根据id寻找信息
	 * @param orderId
	 * @return
	 */
	public OrderBean getOrderBeanById(String orderId) {
		// TODO Auto-generated method stub
		String sql = "select * from user_order where id='"+orderId+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		OrderBean orderBean=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				orderBean= new OrderBean();
				orderBean.setId(rs.getInt("id"));
				orderBean.setCode(rs.getString("code"));
				orderBean.setOriginal_price(rs.getDouble("original_price"));
				orderBean.setPrice(rs.getDouble("price"));
				int addressId=rs.getInt("address_id");
				AddressDao addressDao=new AddressDao();
				AddressBean addressBean=addressDao.getAddressBeanbyId(addressId);
				orderBean.setAddressBean(addressBean);
				int userId=rs.getInt("user_id");
				UserDao userDao=new UserDao();
				UserBean userBean=userDao.getUserBeanbyId(userId);
				orderBean.setUserBean(userBean);
				orderBean.setPayment_type(rs.getInt("payment_type"));
				orderBean.setCreate_date(rs.getString("create_date"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return orderBean;
	}

}
