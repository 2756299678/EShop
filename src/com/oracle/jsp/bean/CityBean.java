package com.oracle.jsp.bean;

public class CityBean {

	private int id;
	private String name;
	private String postcode;
	private String createdate;
	private int province_id;
	private ProvinceBean  province;
	
	public CityBean(){};
	
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public ProvinceBean getProvince() {
		return province;
	}
	public void setProvince(ProvinceBean province) {
		this.province = province;
	}
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
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	

}
