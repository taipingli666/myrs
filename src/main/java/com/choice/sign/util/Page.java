package com.choice.sign.util;

import java.util.ArrayList;
import java.util.List;

/** 
 * @Title: Page.java 
 * @Description: 分页类 
 * @author ChenYa 
 * @date 2014-4-23 下午9:16:23 
 * @version V1.0 
 */
public class Page<T> {

	private int currentPage = 1;

	private int pageSize = 10;

	private int pageIndex = 0;

	private int totalPage = 0;//总页数

	private int totalRecord = 0;//总记录数
	
	private List<T> content = new ArrayList<T>();
	
	private boolean hasPrePage = false;
	
	private boolean hasNextPage = false;

	public Page() {
		
	}
	
	public Page(int totalRecord, List<T> content) {
		super();
		this.totalRecord = totalRecord;
		this.content = content;
	}

	public Page(int pageSize, int currentPage, int totalRecord, List<T> content) {
		this.pageSize = pageSize;
		this.currentPage = (currentPage == 0 ? 1 : currentPage);
		this.totalRecord = totalRecord;
		this.content = content;
		this.pageIndex = Page.calPageIndex(currentPage, pageSize);
		this.totalPage = Page.calTotalPage(totalRecord, pageSize);
		this.hasPrePage = Page.calHasPrePage(currentPage);
		this.hasNextPage = Page.calHasNextPage(currentPage, totalPage);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	public static Integer calPageIndex(Integer currentPage, Integer pageSize) {
		return (currentPage - 1) * pageSize;
	}

	public static Integer calTotalPage(Integer totalRecords, Integer pageSize) {
		if (totalRecords % pageSize == 0) {
			return totalRecords / pageSize;
		} else {
			return totalRecords / pageSize + 1;
		}
	}

	public static boolean calHasPrePage(Integer currentPage) {
		return currentPage == 1 ? false : true;
	}

	public static boolean calHasNextPage(Integer currentPage, Integer totalPage) {
		if(currentPage == totalPage || totalPage == 0)
			return false;
		else
			return true;
	}
	
}
