package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import com.oracle.jsp.bean.OrderProductBean;
import com.oracle.jsp.util.DBUtil;

public class OrderProductDao {

	/**
	 * 添加产品订单
	 * @param productOrderBean
	 */
	public void addOrderProduct(OrderProductBean productOrderBean) {
		// TODO Auto-generated method stub
		String sql="insert into user_order_product(order_id,product_id,price,number,create_date)"+
		" values('"+productOrderBean.getOrderBean().getId()+"','"+productOrderBean.getProductBean().getId()+"','"+productOrderBean.getProductBean().getPrice()*productOrderBean.getNumber()+"','"+productOrderBean.getNumber()+"',now())";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		try{
			state=conn.createStatement();
			state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}	
	}
	
}
