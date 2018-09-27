package com.oracle.jsp.bean;

public class OrderProductBean {

	

	//Ψһ��ʶ
	private int id;
	//������id��
	private int orderId;
	//������Ϣ
	private OrderBean orderBean;
	//��Ʒ��id��
	private int  productId;
	//��Ʒ��Ϣ
	private ProductBean productBean;
	//��Ʒ�ּ�
	private float price;
	//��Ʒ����
	private int number;
	//����ʱ��
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
