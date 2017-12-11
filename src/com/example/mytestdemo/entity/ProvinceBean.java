package com.example.mytestdemo.entity;

import java.util.List;

/** * 
 @author  zengcq
 @date 创建时间：2017年5月18日
 @version 1.0 
 */
public class ProvinceBean {

	private long id;
	private String name;
	private List<CityBean> city;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CityBean> getCityList() {
		return city;
	}
	public void setCityList(List<CityBean> cityList) {
		this.city = cityList;
	}
	
}
