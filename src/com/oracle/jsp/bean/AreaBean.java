package com.oracle.jsp.bean;

public class AreaBean {

	private int id;
	private String name;	//��������
	private String postcode;	//��������
	private String createDate;	//����ʱ��
	private int cityId;	//��id
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
