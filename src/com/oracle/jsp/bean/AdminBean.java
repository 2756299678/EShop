package com.oracle.jsp.bean;

//ÀàµÄ·â×°
public class AdminBean {
	private int id;
	private String username;
	private String password;
	private String salt;
	private String createDate;
	
	public int getId(){
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassworld(String password)
	{
		this.password=password;
	}
	public String getSalt()
	{
		return salt;
	}
	public void setSalt(String salt)
	{
		this.salt=salt;
	}
	public String getCreateDate()
	{
		return  createDate;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate =createDate;
	}
}



