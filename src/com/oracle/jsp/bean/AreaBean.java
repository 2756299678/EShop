package com.oracle.jsp.bean;

public class AreaBean {

	private int id;
	private String name;	//县区名称
	private String postcode;	//邮政编码
	private String createDate;	//创建时间
	private int cityId;	//市id
	private CityBean cityBean;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public CityBean getCityBean() {
		return cityBean;
	}
	public void setCityBean(CityBean cityBean) {
		this.cityBean = cityBean;
	}

}
