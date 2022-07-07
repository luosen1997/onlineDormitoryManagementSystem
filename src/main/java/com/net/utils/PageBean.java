package com.net.utils;

import org.springframework.stereotype.Repository;

@Repository
public class PageBean {
	//总页数
	private int totalPageCount=1;
	//页面大小，即每页显示记录数
	private int pageSize=10;
	//记录总数
	private int recordCount=0;
	//当前页号
	private int currPageNo=1;

	private String key;
	private String value;
	private String key1;
	private String value1;
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public PageBean(){}
	public PageBean(int pageSize){
		this.pageSize=pageSize;
	}
	
	public int getCurrPageNo() {
		if(totalPageCount==0)
			return 0;
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		if(this.currPageNo>0)
			this.currPageNo = currPageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize>0)
			this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		if(recordCount>0){
			this.recordCount = recordCount;
			this.setTotalPageCountByRs();
		}
	}
	
	//设置总页数
	private void setTotalPageCountByRs(){
		if(this.recordCount%this.pageSize==0)
			this.totalPageCount=this.recordCount/this.pageSize; 
		else if(this.recordCount%this.pageSize>0)
			this.totalPageCount=this.recordCount/this.pageSize+1;
		else 
			this.totalPageCount=0;
	}
	/**
	 * 得到开始记录数
	 *
	public int getStartRow(){
		return (currPageNo - 1) * pageSize + 1;
	}*/

//	得到开始记录数

	public int getStartRow(){
		return (currPageNo-1)*pageSize;
	}
	/**
	 * 得到结束记录数
	 * 
	public int getEndRow(){
		return currPageNo * pageSize;
	}*/

//	得到结束记录数

	public int getEndRow(){
		return pageSize;
	}

//	servlet中通用的分页代码

	public PageBean getPageBean(String page,int count){
		if (page == null) {
			page = "1";
		}
		int p=Integer.parseInt(page==null || "".equals(page)?"0":page);
		if(p<=0) page="1";
		int pageIndex = Integer.parseInt(page);		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setRecordCount(count);
		pageBean.setCurrPageNo(pageIndex);
		pageBean.setTotalPageCount(pageBean.getTotalPageCount());
		return pageBean;
	}
}