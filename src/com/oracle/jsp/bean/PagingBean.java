package com.oracle.jsp.bean;

public class PagingBean {
	//总数量
	private int totalCount;
	//总页数
	private int pageCount;
	//当前页
	private int currentPage;
	//一页多少条数据
	private int countPerPage;
	
	private String prefixUrl;
	//true的时候是&否则是？
	private boolean isAnd;
	
	public PagingBean(int currentPage,int totalCount,int countPerPage)
	{
		//赋值一页多少条数据
		this.countPerPage = countPerPage;
		pageCount = (totalCount-1)/countPerPage + 1 ;
		//当前页大于等于总页数，当前页-1
		if(currentPage >= pageCount)
		{
			currentPage = pageCount-1;
		}
		if(currentPage <0)
		{
			currentPage = 0;
		}
		//赋值总页数
		this.totalCount = totalCount;
		//赋值当前页
		this.currentPage = currentPage;
	}
	
	public PagingBean(){}
	
	public int getCurrentPage(){
		return currentPage;
	}
	public void setCurrentPage(int currentPage){
		this.currentPage=currentPage;
	}
	/**
	 * @return Returns the prefixUrl
	 */
	public String getPrefixUrl(){
		return prefixUrl;
	}
	/**
	 * @param prefixUrl The prefixUrl to set
	 */
	public void setPrefixUrl(String prefixUrl)
	{
		this.prefixUrl=prefixUrl;
	}
	/**
	 * @return Returns the totalCount
	 */
	public int getTotalCount(){
		return totalCount;
	}
	/**
	 * @param totalcount The totalCount to set
	 */
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
	
	public int getPageCount(){
		return pageCount;
	}
	public void getPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	
	public int getCountPerPage(){
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage)
	{
		this.countPerPage = countPerPage;
	}
	
	@Override
	public String toString()
	{
		return "PagingBean [totalCount=" + totalCount + ",totalPageCount="
				+ pageCount +",currentPage="+ currentPage
				+",prefixUrl=" + prefixUrl +",countPerPage=" + countPerPage
				+",isAnd" + isAnd + "]";
	}
	public boolean isAnd()
	{
		return isAnd;
	}
	public void setAnd(boolean isAnd)
	{
		this.isAnd =isAnd;
	}
}
