package com.jsu.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Address {
	private String id;
	private String uId;
	private int type;//1表示默认地址，0表示非默认地址
	private String name;
	private String tel;
	private String address;
	
	
	public Address() {
		super();
	}
	public Address(String id, String uId, int type, String name, String tel,
			String address) {
		super();
		this.id = id;
		this.uId = uId;
		this.type = type;
		this.name = name;
		this.tel = tel;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
