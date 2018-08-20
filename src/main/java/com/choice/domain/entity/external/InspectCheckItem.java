package com.choice.domain.entity.external;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author litp
* 2018年1月25日 上午9:20:01
*
*/
public class InspectCheckItem {
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	/**
	 * id
	 */
	private int id;
	/**
	 * 预约单id
	 */
	private String inspectInfoId;
	/**
	 * 项目编码
	 */
	private String itemCode;
	/**
	 * 项目名称
	 */
	private String itemName;
	/**
	 * 预约项目开始时间
	 */
	private String startTime;
	/**
	 * 预约项目结束时间
	 */
	private String endTime;
	/**
	 * 项目价格
	 */
	private double price;
	/**
	 * 创建时间
	 */
	private String addTime;
	
	public InspectCheckItem() {
		super();
	}
	public InspectCheckItem(int id, String inspectInfoId, String itemCode, String itemName, String startTime,
			String endTime, double price, String addTime) {
		super();
		this.id = id;
		this.inspectInfoId = inspectInfoId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.addTime = addTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInspectInfoId() {
		return inspectInfoId;
	}
	public void setInspectInfoId(String inspectInfoId) {
		this.inspectInfoId = inspectInfoId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStartTime() throws ParseException {
		Date date = fmt.parse(startTime);
		startTime = fmt.format(date);
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() throws ParseException {
		Date date = fmt.parse(endTime);
		endTime = fmt.format(date);
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	} 
	
	
}
