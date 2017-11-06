package com.boot.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@QueryContract(href = "", rel = "search", prompt = "查询")
public class Person extends BaseResRep implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String myname;
	private int age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
}
