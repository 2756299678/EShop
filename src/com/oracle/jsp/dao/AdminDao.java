package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.jsp.util.DBUtil;
import com.oracle.jsp.util.MD5;
import com.oracle.jsp.bean.AdminBean;

/**
 * 
 *admin表数据操作
 *
 */
public class AdminDao {

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 */
	//进行登陆的数据库检验，验证账号和密码
	public AdminBean checkLogin(String  username, String password)
	{
		Connection conn = DBUtil.getConn();
		AdminBean adminBean = null;
		try{
			//创建sql加载语句
			Statement state=conn.createStatement();
			//返回结果集
			ResultSet rs=state.executeQuery("select * from admin where "
					+ "username= '" + username +"'");
			//判断结果是否存在
			if(rs.next())
			{
			//如果有结果，是认为通过验证的
				if(rs.getString("password").equals(MD5.GetMD5Code
						(password+rs.getString("salt"))))
				{
					adminBean = new AdminBean();
					adminBean.setId(rs.getInt("id"));
					adminBean.setUsername(rs.getString("username"));
					adminBean.setPassworld(rs.getString("password"));
					adminBean.setSalt(rs.getString("salt"));
					adminBean.setCreateDate(rs.getString("create_date"));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return adminBean;
	}
	/**
	 * 检查是否有此用户
	 * 
	 * @param name
	 * @return
	 * 有这个用户返回值为false
	 */
	public boolean checkReg(String name)
	{
		boolean flag = true;
		//查询用户是否存在
		//创建连接
		Connection connection = DBUtil.getConn();
		Statement statement =null;
		//返回结果集
		ResultSet rs = null;
		try{
			statement = connection.createStatement();
			//在admin中查询数据结果
			rs = statement.executeQuery("select username from admin");
			while (rs.next())
			{
				//admin中存在username，与name进行比较
				if(name.equals(rs.getString("username")))
				{
					flag = false;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs,statement,connection);
			
		}
		return flag;
	}
	/**
	 * 添加管理员
	 * 
	 * @param adminBean
	 */
	public void save(AdminBean adminBean)
	{
		//创建insert语句
		String sql="insert into admin(username,password,salt,create_date)"
				+ "values('"+adminBean.getUsername()+"','"+adminBean.getPassword()+"','"
				+adminBean.getSalt()+"','"+adminBean.getCreateDate()+"')";
		//创建链接
		Connection conn = DBUtil.getConn();
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
	/**
	 * 查看管理员
	 * 
	 * @return
	 */
	public List<AdminBean>list(){
		//执行查询语句
		String sql = "select * from admin";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<AdminBean>adminBeans = new ArrayList<AdminBean>();
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			AdminBean adminBean;
			while(resultSet.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));
				adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassworld(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
				adminBeans.add(adminBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement, connection);
		}
		return adminBeans;
	}
	/**
	 * 通过id获取adminBean对象
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public AdminBean getById(int id){
		
		String sql = "select * from admin where id ='"+id+"'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		AdminBean adminBean = null;
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));
				adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassworld(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement, connection);
		}
		return adminBean;
	}
	/**
	 * 修改管理员
	 * @param adminBean
	 */
	public boolean update(AdminBean adminBean){
		/*String sql="insert into admin(username,password,salt,create_date)"
				+ "values('"+adminBean.getUsername()+"','"+adminBean.getPassword()+"','"
				+adminBean.getSalt()+"','"+adminBean.getCreateDate()+"')";*/
		String sql="update admin set password='"+adminBean.getPassword()+"',salt='"+adminBean.getSalt()+"' where id='"+adminBean.getId()+"'";
		System.out.println(adminBean.getId());
		System.out.println(adminBean.getPassword());
		System.out.println(adminBean.getSalt());
		//创建链接
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
	 * 通过id删除
	 */
	public void delete(int id){
		String sql = "delete from admin where id="+id;
		Connection conn=DBUtil.getConn();
		Statement state=null;
		try{
			state = conn.createStatement();
			state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
	}
	/**
	 * 获取数据表中的数据总量
	 * @return
	 */
	public int getCount(){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		int size = 0;
		try{
			String sql="select count(*) count from admin";
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs=state.executeQuery(sql);
			
			if(rs.next())
			{
				size = rs.getInt("count");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,state,conn);
		}
		return size;
	}
	
	/**
	 * 获取每一个分页的数据
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AdminBean>getListByPage(int start,int size)
	{
		String sql="select * from admin limit "+ start +","+ size;
		Connection connection=DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		
		try{
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			AdminBean adminBean;
			while(resultSet.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));
				adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassworld(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
				adminBeans.add(adminBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement, connection);
		}
		return adminBeans;
	}	
	static void main(String []str){
		
	}
}
