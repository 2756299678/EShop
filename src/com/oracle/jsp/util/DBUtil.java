package com.oracle.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 数据库的工具类，用来连接数据库和断开数据库
 * 周宝辉 2017 5 6
 * */

public class DBUtil {
	//eshop为数据库名称，db_user为数据库用户名db_password为数据库密码
	//数据库链接地址为jdbc:mysql://127.0.0.1/eshop?
	//允许用户自己设定数据库编码，而且设置成UTF-8
	public static String db_url="jdbc:mysql://127.0.0.1/eshop?useUnicode=true&characterEcoding=utf-8";
	//用户名
	public static String db_user="root";
	public static String db_password="baohui";
	
	//创建链接
	public static Connection getConn(){
		//链接初始化
		Connection conn=null;
		try{
			//加载jdbc驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(db_url,db_user,db_password);
			//System.out.print("成功！");
		}
		catch(Exception e){
			//抛出异常(深层次)
			e.printStackTrace();
			//System.out.println("失败！");
		}
		return conn;
	}
	
	//不返回数据集的链接关闭
	public static void close(Statement state,Connection conn)
	{
		//判断
		if(state!=null)
		{
			try{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try{
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	//使用结果集的链接关闭
	public static void close(ResultSet rs,Statement state,Connection conn){
		if(rs!=null)
		{
			try{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(state!=null)
		{
			try{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getConn();
	}
}
