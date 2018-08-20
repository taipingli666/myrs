/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.sign.web.controller.base;

import com.choice.sign.util.Page;

public class BaseController<T>{
	
	public Integer pageSize = 10;

	public Integer pageNumber = 1;
	
	protected Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
}