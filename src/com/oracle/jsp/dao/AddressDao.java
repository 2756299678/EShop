package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.jsp.bean.AddressBean;
import com.oracle.jsp.bean.AreaBean;
import com.oracle.jsp.bean.CityBean;
import com.oracle.jsp.bean.ProvinceBean;
import com.oracle.jsp.util.DBUtil;

public class AddressDao {

	public String selectCityById(int id)
	{
		String sql="select * from city where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		String city=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				city=rs.getString("name");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return city;
	}
	
	public String selectprovinceById(int id)
	{
		String sql="select * from province where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		String province=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				province=rs.getString("name");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return province;
	}
	
	public String selectAreaById(int id)
	{
		String sql="select * from area where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		String area=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				area=rs.getString("name");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return area;
	}
	
	public AddressBean getAddressBeanbyId(int id)
	{
		String sql="select * from user_address where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		AddressBean addressBean = new AddressBean();
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				addressBean.setId(rs.getInt("id"));
				addressBean.setCellphone(rs.getString("cellphone"));
				
				addressBean.setCity_id(rs.getInt("city"));
				addressBean.setProvince_id(rs.getInt("province"));
				addressBean.setRegion_id(rs.getInt("region"));
				
				addressBean.setCity(selectCityById(rs.getInt("city")));
				addressBean.setProvince(selectprovinceById(rs.getInt("province")));
				addressBean.setRegion(selectAreaById(rs.getInt("region")));
				
				addressBean.setName(rs.getString("name"));
				addressBean.setAddress(rs.getString("address"));
				addressBean.setCreate_date(rs.getString("create_date"));
				addressBean.setUser_id(rs.getInt("user_id"));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return addressBean;
	}
	
	public List<AddressBean> getAddressList(int id) {
		// TODO Auto-generated method stub
		String sql="select * from user_address where user_id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<AddressBean> addressBeans = new ArrayList<>();
		AddressBean addressBean=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				addressBean = new AddressBean();
				addressBean.setId(rs.getInt("id"));
				addressBean.setCellphone(rs.getString("cellphone"));
				
				addressBean.setCity_id(rs.getInt("city"));
				addressBean.setProvince_id(rs.getInt("province"));
				addressBean.setRegion_id(rs.getInt("region"));
				
				addressBean.setCity(selectCityById(rs.getInt("city")));
				addressBean.setProvince(selectprovinceById(rs.getInt("province")));
				addressBean.setRegion(selectAreaById(rs.getInt("region")));
				
				addressBean.setName(rs.getString("name"));
				addressBean.setAddress(rs.getString("address"));
				addressBean.setCreate_date(rs.getString("create_date"));
				addressBean.setUser_id(rs.getInt("user_id"));
				addressBean.setStatus(rs.getInt("status"));
				
				addressBeans.add(addressBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return addressBeans;
	}
	public void save(AddressBean addressBean) {
		// TODO Auto-generated method stub
		String sql="insert into user_address(name,province,city,region,address,cellphone,user_id,create_date)"+
		" values('"+addressBean.getName()+"','"+addressBean.getProvince_id()+"','"+
		addressBean.getCity_id()+"','"+addressBean.getRegion_id()+"','"+addressBean.getAddress()+"','"+
		addressBean.getCellphone()+"','"+addressBean.getUser_id()+"',now())";
		Connection conn=DBUtil.getConn();
		Statement state = null;
		try{
			state = conn.createStatement();
			state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
	}

	public List<AddressBean> getList() {
		// TODO Auto-generated method stub
		String sql="select * from user_address";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<AddressBean> addressBeans = new ArrayList<>();
		AddressBean addressBean=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				addressBean=new AddressBean();
				addressBean.setId(rs.getInt("id"));
				addressBean.setCellphone(rs.getString("cellphone"));
				
				addressBean.setCity_id(rs.getInt("city"));
				addressBean.setProvince_id(rs.getInt("province"));
				addressBean.setRegion_id(rs.getInt("region"));
				
				addressBean.setCity(selectCityById(rs.getInt("city")));
				addressBean.setProvince(selectprovinceById(rs.getInt("province")));
				addressBean.setRegion(selectAreaById(rs.getInt("region")));
				
				addressBean.setName(rs.getString("name"));
				addressBean.setAddress(rs.getString("address"));
				addressBean.setCreate_date(rs.getString("create_date"));
				addressBean.setUser_id(rs.getInt("user_id"));
				addressBeans.add(addressBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return addressBeans;
	}

	/**
	 * 删除地址
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from user_address where id='"+id+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		if (a > 0) {
			f = true;
		}
		return f;
	}

	/**
	 * 更新是否为默认的地址
	 * @param id
	 * @return
	 */
	public boolean update(int id,int userId) {
		// TODO Auto-generated method stub
		String sql1="update user_address set status='"+0+"' where user_id='"+userId+"'";
		String sql="update user_address set status='"+1+"' where id="+id+"";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql1);
			a = state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		if (a > 0) {
			f = true;
		}
		return f;
	}

	public List<ProvinceBean> getProvinceList() {
		// TODO Auto-generated method stub
		String sql="select * from province";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<ProvinceBean> provinces = new ArrayList<>();
		ProvinceBean province=null;
		
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				province=new ProvinceBean();
				province.setId(rs.getInt("id"));
				province.setName(rs.getString("name"));
				province.setPostcode(rs.getString("postcode"));
				province.setCreateDate(rs.getString("create_date"));
				provinces.add(province);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return provinces;	
	}

	//通过city(id)获取cityBean对象
	public CityBean getCityById(int id){
		String sql="select * from city where id="+id;
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		CityBean cityBean=null;
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				cityBean=new CityBean();
				cityBean.setId(resultSet.getInt("id"));
				cityBean.setName(resultSet.getString("name"));
				cityBean.setPostcode(resultSet.getString("postcode"));
				cityBean.setCreatedate(resultSet.getString("create_date"));
				cityBean.setProvince_id(resultSet.getInt("province_id"));
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet,statement,connection);
		}
		return cityBean;
	}
	
	public List<CityBean> getCityList(int parentId) {
		// TODO Auto-generated method stub
		String sql="select * from city where province_id='"+parentId+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<CityBean> citys = new ArrayList<>();
		CityBean city=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				city =new CityBean();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPostcode(rs.getString("postcode"));
				city.setProvince_id(rs.getInt("province_id"));
				city.setCreatedate(rs.getString("create_date"));
				citys.add(city);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return citys;
	}

	
	public AreaBean getAreaById(int id){
		String sql="select * from area where id="+id;
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		AreaBean areaBean=null;
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				areaBean=new AreaBean();
				areaBean.setId(resultSet.getInt("id"));
				areaBean.setName(resultSet.getString("name"));
				areaBean.setPostcode(resultSet.getString("postcode"));
				areaBean.setCreateDate(resultSet.getString("create_date"));
				areaBean.setCityId(resultSet.getInt("city_id"));
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet,statement,connection);
		}
		return areaBean;
	}
	
	
	
	public List<AreaBean> getAreaList(int cityId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<AreaBean> areaBeans = new ArrayList<AreaBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from area where city_id='"+cityId+"'");
		AreaBean bean;
		while(rs.next()) {
		bean = new AreaBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setPostcode(rs.getString("postcode"));
		bean.setCreateDate(rs.getString("create_date"));
		bean.setCityId(rs.getInt("city_id"));
		
		areaBeans.add(bean);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return areaBeans;
	}
	
	

}
