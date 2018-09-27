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
	 * ����Ƿ��д��û�
	 * 
	 * @param name
	 * @return
	 * ������û�����ֵΪfalse
	 */
	public boolean checkReg(String name)
	{
		boolean flag = true;
		//��ѯ�û��Ƿ����
		//��������
		Connection connection = DBUtil.getConn();
		Statement statement =null;
		//���ؽ����
		ResultSet rs = null;
		try{
			statement = connection.createStatement();
			//��admin�в�ѯ���ݽ��
			rs = statement.executeQuery("select username from user");
			while (rs.next())
			{
				//admin�д���username����name���бȽ�
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
	 * ����û�
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
	 * ��½
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 */
	//���е�½�����ݿ���飬��֤�˺ź�����
	public UserBean checkLogin(String  username, String password)
	{
		Connection conn = DBUtil.getConn();
		UserBean userBean = null;
		try{
			//����sql�������
			Statement state=conn.createStatement();
			//���ؽ����
			ResultSet rs=state.executeQuery("select * from user where "
					+ "username= '" + username +"'");
			//�жϽ���Ƿ����
			if(rs.next())
			{
			//����н��������Ϊͨ����֤��
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
	 * �г��û���ϸ��Ϣ
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
	 * �޸��û�״̬
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
	
	//����ͼƬ��ַ
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
