package com.jsu.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Order {
	private String oId;
	private String uId;
	private String gPId;
	private String gId;
	private String num;
	private String time;
	private String money;
	private String account;
	private int type;//0表示已完成的订单，1表示待收货订单，2表示申请退款，3表示已经完成退款
	private String addressId;
	private String msg;
	
	
	public Order() {
		super();
	}

	public Order(String oId, String uId, String gPId, String gId, String num,
			String time, String money, String account, int type,
			String addressId, String msg) {
		super();
		this.oId = oId;
		this.uId = uId;
		this.gPId = gPId;
		this.gId = gId;
		this.num = num;
		this.time = time;
		this.money = money;
		this.account = account;
		this.type = type;
		this.addressId = addressId;
		this.msg = msg;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getgPId() {
		return gPId;
	}
	public void setgPId(String gPId) {
		this.gPId = gPId;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Order [oId=" + oId + ", uId=" + uId + ", gPId=" + gPId
				+ ", gId=" + gId + ", time=" + time + ", money=" + money
				+ ", account=" + account + ", type=" + type + ", addressId="
				+ addressId + ", msg=" + msg + "]";
	}

}
