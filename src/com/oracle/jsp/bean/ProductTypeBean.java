package com.oracle.jsp.bean;

import java.util.List;
/**
 * 商品分类bean
 * @author BaoHui
 *
 */

public class ProductTypeBean {
	private int id;
	private String name;
	private int parentId;
	private int sort;
	private String intro;
	private String createDate;
	private List<ProductTypeBean> childBeans;
	private ProductTypeBean parentBean;
	//构造函数1
	public ProductTypeBean(){
		
	}
	//构造函数2 id name
	public ProductTypeBean(int id,String name)
	{
		this.setId(id);
		this.setName(name);
	}
	//构造函数3 sort parentId name intro
	public ProductTypeBean(int sort,int parentId,String name,String intro)
	{
		this.setSort(sort);
		this.setParentId(parentId);
		this.setName(name);
		this.setIntro(intro);
	}
	//构造函数4 id sort parentId name intro
	public ProductTypeBean(int id,int sort,int parentId,String name,String intro)
	{
		this.setId(id);
		this.setSort(sort);
		this.setParentId(parentId);
		this.setName(name);
		this.setIntro(intro);
	}
	//构造函数5 id name sort intro createDate
	public ProductTypeBean(int id,String name,int sort,String intro,String createDate)
	{
		this.setId(id);
		this.setName(name);
		this.setSort(sort);
		this.setIntro(intro);
		this.setCreateDate(createDate);
	}
	//构造函数6 id sort parentabean name intro createDate
	public ProductTypeBean(int id,int sort,ProductTypeBean parentBean,String name,String intro,String createDate)
	{
		this.setId(id);
		this.setSort(sort);
		this.setParentBean(parentBean);
		this.setName(name);
		this.setIntro(intro);
		this.setCreateDate(createDate);
	}
	
	//get set
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	
	public  String getName(){
		return name;
	}
	public void  setName(String name){
		this.name=name;
	}
	
	public  int getParentId(){
		return parentId;
	}
	public void  setParentId(int parentId){
		this.parentId=parentId;
	}
	
	public int getSort(){
		return sort;
	}
	public void setSort(int sort){
		this.sort=sort;
	}
	
	public String getIntro(){
		return intro;
	}
	public void setIntro(String intro){
		this.intro = intro;
	}
	
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	
	public List<ProductTypeBean> getChildBeans(){
		return childBeans;
	}
	public void setChildBeans(List<ProductTypeBean> childBeans)
	{
		this.childBeans=childBeans;
	}
	
	public ProductTypeBean getParentBean(){
		return parentBean;
	}
	public void setParentBean(ProductTypeBean parentBean){
		this.parentBean=parentBean;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
