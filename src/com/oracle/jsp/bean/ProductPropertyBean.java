package com.oracle.jsp.bean;

public class ProductPropertyBean {

	private int id;
	private int sort;
	private int productTypeId;
	private String name;
	private String createDate;
	//构造函数1
	public ProductPropertyBean(){
		
	}
	//构造函数2
	public ProductPropertyBean(int sort,int productTypeId,String name){
		this.setSort(sort);
		this.setProductTypeId(productTypeId);
		this.setName(name);
	}
	//构造函数3
	public ProductPropertyBean(int id,int sort,int productTypeId,String name){
		this.setId(id);
		this.setSort(sort);
		this.setProductTypeId(productTypeId);
		this.setName(name);
	}
	//构造函数4
	public ProductPropertyBean(int id,int sort,int productTypeId,String name,String createDate){
		this.setId(id);
		this.setSort(sort);
		this.setProductTypeId(productTypeId);
		this.setName(name);
		this.setCreateDate(createDate);
	}
	//构造函数5
	private ProductPropertyBean(int id,String name){
		this.setId(id);
		this.setName(name);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
