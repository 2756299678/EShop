package com.oracle.jsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.jsp.bean.AdminBean;
import com.oracle.jsp.bean.ProductBean;
import com.oracle.jsp.bean.UserBean;
import com.oracle.jsp.util.DBUtil;
import com.oracle.jsp.util.MD5;

public class UserDao {

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
			rs = statement.executeQuery("select username from user");
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
	 * 添加用户
	 * @param userBean
	 */
	public void add(UserBean userBean)
	{
		String sql="insert into user(username,password,salt,nickname,truename,sex,pic,status,create_date)"
				+ "values('"+userBean.getUsername()+"','"+userBean.getPassword()+"','"+userBean.getSalt()+"','"
				+userBean.getNickname()+"','"+userBean.getTruename()+"','"+userBean.getSex()+"','"
				+userBean.getPic()+"','"+userBean.getStatus()+"','"+userBean.getCreateDate()+"')";
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
	
	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 */
	//进行登陆的数据库检验，验证账号和密码
	public UserBean checkLogin(String  username, String password)
	{
		Connection conn = DBUtil.getConn();
		UserBean userBean = null;
		try{
			//创建sql加载语句
			Statement state=conn.createStatement();
			//返回结果集
			ResultSet rs=state.executeQuery("select * from user where "
					+ "username= '" + username +"'");
			//判断结果是否存在
			if(rs.next())
			{
			//如果有结果，是认为通过验证的
				if(rs.getString("password").equals(MD5.GetMD5Code
						(password+rs.getString("salt"))))
				{
					userBean = new UserBean();
					userBean.setId(rs.getInt("id"));
					userBean.setUsername(rs.getString("username"));
					userBean.setPassword(rs.getString("password"));
					userBean.setNickname(rs.getString("nickname"));
					userBean.setTruename(rs.getString("truename"));
					userBean.setSex(rs.getString("sex"));
					userBean.setSalt(rs.getString("salt"));
					userBean.setStatus(rs.getInt("status"));
					userBean.setCreateDate(rs.getString("create_date"));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return userBean;
	}
	/**
	 * 列出用户详细信息
	 * @return
	 */
	public List<UserBean> getList(){
		String sql = "select * from user";
		List<UserBean> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			UserBean userBean=null;
			while (rs.next()) {
				int id=rs.getInt("id");
				String img=rs.getString("pic");
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				String truename= rs.getString("truename");
				String sex = rs.getString("sex");
				int status=rs.getInt("status");
				
				userBean = new UserBean(id,username,nickname,truename,sex,status,img);
				list.add(userBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	/**
	 * 修改用户状态
	 * @param i
	 * @param id
	 * @return
	 */
	public boolean update(int i,int id)
	{
		String sql = "update user set status='"+i+"' where id='"+id+"'";
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
	
	public UserBean getUserBeanbyId(int id)
	{
		String sql="select * from user where id='"+id+"'";
		UserBean userBean=null;
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while (rs.next()) {
				String img=rs.getString("pic");
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				String truename= rs.getString("truename");
				String sex = rs.getString("sex");
				int status=rs.getInt("status");
				String createDate = rs.getString("create_date");
				userBean = new UserBean(id,username,nickname,truename,sex,status,img,createDate);
				userBean.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return userBean;
	}
	
	public UserBean getUserBeanbyName(String name)
	{
		String sql="select * from user where username='"+name+"'";
		UserBean userBean=null;
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while (rs.next()) {
				int id=rs.getInt("id");
				String img=rs.getString("pic");
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				String truename= rs.getString("truename");
				String sex = rs.getString("sex");
				int status=rs.getInt("status");
				String createDate = rs.getString("create_date");
				userBean = new UserBean(id,username,nickname,truename,sex,status,img,createDate);
				userBean.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return userBean;
	}
	
	public boolean updatemessage(UserBean userBean) {
		// TODO Auto-generated method stub
		String sql="update user set username='"+userBean.getUsername()+"',password='"+userBean.getPassword()
		+"',nickname='"+userBean.getNickname()+"',salt='"+userBean.getSalt()+"'  where id='"+userBean.getId()+"'";
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
	
	//更改图片地址
	public boolean updatepic(int updateId, UserBean userBean) {
		// TODO Auto-generated method stub
		String sql="update user set pic='"+userBean.getPic()+"' where id='"+updateId+"'";
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

}
