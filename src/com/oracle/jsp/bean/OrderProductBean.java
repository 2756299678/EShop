package com.oracle.jsp.bean;

public class OrderProductBean {

	

	//唯一标识
	private int id;
	//订单的id号
	private int orderId;
	//订单信息
	private OrderBean orderBean;
	//产品的id号
	private int  productId;
	//产品信息
	private ProductBean productBean;
	//产品现价
	private float price;
	//产品数量
	private int number;
	//创建时间
	private String datetime;
	public OrderProductBean(){
		
	}
	public OrderProductBean(ProductBean productBean,int number)
	{
		this.productBean=productBean;
		this.number=number;
	}
	public OrderProductBean(OrderBean orderBean,ProductBean productBean,int number)
	{
		this.setOrderBean(orderBean);
		this.setProductBean(productBean);
		this.setNumber(number);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
