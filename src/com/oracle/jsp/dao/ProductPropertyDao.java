package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.jsp.bean.ProductOptionBean;
import com.oracle.jsp.bean.ProductPropertyBean;
import com.oracle.jsp.bean.ProductTypeBean;
import com.oracle.jsp.util.DBUtil;

public class ProductPropertyDao {
	/**
	 * 商品添加分类属性
	 * @author BaoHui
	 *
	 */
	public boolean add(ProductPropertyBean productPropertyBean)
	{
		String sql = "insert into product_type_property(name,product_type_id,sort,create_date) values ('"+productPropertyBean.getName()
				+"','"+productPropertyBean.getProductTypeId()+"','"+productPropertyBean.getSort()+"',now())";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f =false;
		int a = 0;
		try{
			state = conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f= true;
		}
		return f;
	}
	/**
	 * 通过分类获取所有属性
	 * @param typeId
	 * @return
	 * @param args
	 */
	public List<ProductPropertyBean> getListByType(int typeId){
		String sql = "select * from product_type_property where product_type_id='"+typeId+"'";
		List<ProductPropertyBean> list =new ArrayList<>();
		Connection conn=DBUtil.getConn();
		Statement state = null;
		ResultSet rs =null;
		try{
			state =conn.createStatement();
			rs =state.executeQuery(sql);
			ProductPropertyBean productPropertyBean = null;
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sort = rs.getInt("sort");
				String createDate=rs.getString("create_date");
				productPropertyBean = new ProductPropertyBean(id,sort,typeId,name,createDate);
				list.add(productPropertyBean);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	/**
	 * 更新分类数据
	 * @param productTypeBean
	 * @return
	 */
	public boolean update(ProductPropertyBean productPropertyBean)
	{
		String sql= "update product_type_property set name='"+productPropertyBean.getName()+
				"',sort='"+productPropertyBean.getSort()+"',product_type_id='"+productPropertyBean.getProductTypeId()+
				"' where id='"+productPropertyBean.getId()+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f =false;
		int a = 0;
		try{
			state = conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f= true;
		}
		return f;
	}
	
	/**
	 * 通过id得到分类的类型
	 * @param id
	 * @return
	 */
	public ProductPropertyBean getPropertyById(int propertyId){
		String sql="select * from  product_type_property where id='"+propertyId+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		ProductPropertyBean productPropertyBean =null;
		try{
			state =conn.createStatement();
			rs =state.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt("id");
				int productTypeId = rs.getInt("product_type_id");
				String name=rs.getString("name");
				int sort = rs.getInt("sort");
				String createDate=rs.getString("create_date");
				productPropertyBean = new ProductPropertyBean(id,sort,productTypeId,name,createDate);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		return productPropertyBean;
	}
	/**
	 * 删除商品类型
	 * @param id
	 * @return
	 */
	public boolean delete(int id){
		boolean f=true;
		//删除属性下的额选项
		ProductOptionDao productOptionDao=new ProductOptionDao();
		List<ProductOptionBean>optionBeans=productOptionDao.getOptionByProperty(id);
		for(ProductOptionBean optionBean:optionBeans){
			boolean r1 = productOptionDao.delete(optionBean.getId());
			if(!r1){
				f=false;
			}
		}
		String sql="delete from product_type_property where id='"+id+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		
		int a = 0;
		try{
			state = conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f= true;
		}
		return f;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
