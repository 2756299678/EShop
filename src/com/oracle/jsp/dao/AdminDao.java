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
 *admin�����ݲ���
 *
 */
public class AdminDao {

	/**
	 * ��½
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 */
	//���е�½�����ݿ���飬��֤�˺ź�����
	public AdminBean checkLogin(String  username, String password)
	{
		Connection conn = DBUtil.getConn();
		AdminBean adminBean = null;
		try{
			//����sql�������
			Statement state=conn.createStatement();
			//���ؽ����
			ResultSet rs=state.executeQuery("select * from admin where "
					+ "username= '" + username +"'");
			//�жϽ���Ƿ����
			if(rs.next())
			{
			//����н��������Ϊͨ����֤��
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
			rs = statement.executeQuery("select username from admin");
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
	 * ��ӹ���Ա
	 * 
	 * @param adminBean
	 */
	public void save(AdminBean adminBean)
	{
		//����insert���
		String sql="insert into admin(username,password,salt,create_date)"
				+ "values('"+adminBean.getUsername()+"','"+adminBean.getPassword()+"','"
				+adminBean.getSalt()+"','"+adminBean.getCreateDate()+"')";
		//��������
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
	 * �鿴����Ա
	 * 
	 * @return
	 */
	public List<AdminBean>list(){
		//ִ�в�ѯ���
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
	 * ͨ��id��ȡadminBean����
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
	 * �޸Ĺ���Ա
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
		//��������
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
	 * ͨ��idɾ��
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
	 * ��ȡ���ݱ��е���������
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
	 * ��ȡÿһ����ҳ������
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
