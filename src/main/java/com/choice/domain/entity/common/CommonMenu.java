package com.choice.domain.entity.common;

import com.choice.domain.entity.base.BaseEntity;

public class CommonMenu extends BaseEntity{
	
	private Integer menuId;//栏目id
	
	private String menuName;//栏目名称
	
	private String url;//地址
	
	private Integer display;//是否显示
	
	private Integer parentId;//父节点
	
	private Integer isLeaf;//是否叶子节点

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	

}
