package com.choice.domain.entity.external;

import java.util.List;

public class InspectVO {
	private InspectInfo inspectInfo;
	private List<InspectCheckItem> itemList;
	public InspectInfo getInspectInfo() {
		return inspectInfo;
	}
	public void setInspectInfo(InspectInfo inspectInfo) {
		this.inspectInfo = inspectInfo;
	}
	public List<InspectCheckItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<InspectCheckItem> itemList) {
		this.itemList = itemList;
	}
	
	
}
