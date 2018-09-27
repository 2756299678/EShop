package com.oracle.jsp.bean;

public class ProductOptionBean {

	private int id;
	private String name;
	private int sort;
	private String createDate;
	private int propertyId;
	private ProductPropertyBean productPropertyBean;
	//构造函数
	public ProductOptionBean(){
		
	}
	//构造函数
	public ProductOptionBean(int sort,int productPropertyId,String name){
		this.setSort(sort);
		this.setPropertyId(productPropertyId);
		this.setName(name);
	}
	//构造函数
	public ProductOptionBean(int id,int sort,int productPropertyId,String name,String createDate){
		this.setId(id);
		this.setSort(sort);
		this.setPropertyId(productPropertyId);
		this.setName(name);
		this.setCreateDate(createDate);
	}
	//构造函数
	public ProductOptionBean(int id,int sort,int productPropertyId,String name){
		this.setId(id);
		this.setSort(sort);
		this.setPropertyId(productPropertyId);
		this.setName(name);
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public ProductPropertyBean getProductPropertyBean() {
		return productPropertyBean;
	}

	public void setProductPropertyBean(ProductPropertyBean productPropertyBean) {
		this.productPropertyBean = productPropertyBean;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
