package com.oracle.jsp.bean;

public class UserBean {

	
	private int id;
	private String username;
	private String password;
	private String salt;
	private int status;
	private String createDate;
	private String sex;
	private String nickname;
	private String truename;
	private String pic;
	public UserBean(){
		
	}
	public UserBean(int id,String username,String password,String salt,String createDate,
			String sex,String nickname,String truename,String pic)
	{
		this.id=id;
		this.username=username;
		this.password=password;
		this.salt=salt;
		this.createDate=createDate;
		this.sex=sex;
		this.nickname=nickname;
		this.truename=truename;
		this.pic=pic;
	}
	public UserBean(String username,String password,String salt,String createDate,
			String sex,String nickname,String truename,String pic)
	{
		
		this.username=username;
		this.password=password;
		this.salt=salt;
		this.createDate=createDate;
		this.sex=sex;
		this.nickname=nickname;
		this.truename=truename;
		this.pic=pic;
	}
	public UserBean(int id,String username,String nickname,String truename,String sex,int status,String pic)
	{
		this.id=id;
		this.username=username;
		this.sex=sex;
		this.nickname=nickname;
		this.truename=truename;
		this.status=status;
		this.pic=pic;
	}
	public UserBean(int id,String username,String nickname,String truename,String sex,int status,String pic,String createDate)
	{
		this.id=id;
		this.username=username;
		this.sex=sex;
		this.nickname=nickname;
		this.truename=truename;
		this.status=status;
		this.pic=pic;
		this.createDate=createDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
