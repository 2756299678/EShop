package com.oracle.jsp.bean;

import java.util.List;

public class OrderBean {

	private int id;
	private String code;
	private double original_price;
	private double price;
	private int payment_type;
	private int status;
	private int user_id;
	private UserBean userBean;
	private int address_id;
	private AddressBean addressBean;
	private String create_date;
	private List<OrderProductBean> orderProductBeans;
	
	public  OrderBean(){};
	public OrderBean(String code, UserBean userBean) {
		this.setCode(code);
		this.setUserBean(userBean);
	}
	public OrderBean(int id,String code,double orginal_price,double price,int payment_type,
			int status,UserBean userBean,AddressBean addressBean,String create_date)
	{
		this.id=id;
		this.code=code;
		this.original_price=orginal_price;
		this.price=price;
		this.payment_type=payment_type;
		this.status=status;
		this.userBean=userBean;
		this.addressBean=addressBean;
		this.create_date=create_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double orginal_price) {
		this.original_price = orginal_price;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public AddressBean getAddressBean() {
		return addressBean;
	}
	public void setAddressBean(AddressBean addressBean) {
		this.addressBean= addressBean;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public List<OrderProductBean> getOrderProductBeans() {
		return orderProductBeans;
	}
	public void setOrderProductBeans(List<OrderProductBean> orderProductBeans) {
		this.orderProductBeans = orderProductBeans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
