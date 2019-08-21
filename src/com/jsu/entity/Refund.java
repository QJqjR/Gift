package com.jsu.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Refund {
	private String  id;
	private String oid;
	private String uid;
	private String gid;
	private String gpid;
	private String time;
	private String money;
	private String cause;
	private String account;
	private String image;
	private String desc;
	
	//不存放数据库，为显示加的
	private String title;
	private String parameter;
	private String type;
	
	public Refund() {
		super();
	}
	
	public Refund(String id,String oid,String gid,String gpid,String account,String uid,String title,String parameter,String type,String time,String money,String cause,String desc,String image){
		this.id = id;
		this.oid = oid;
		this.gid = gid;
		this.gpid = gpid;
		this.account = account;
		this.uid = uid;
		this.title = title;
		this.parameter = parameter;
		this.type = type;
		this.time = time;
		this.money = money;
		this.cause = cause;
		this.desc = desc;
		this.image = image;
	}
	
	public Refund(String id, String oid, String uid, String gid, String gpid,
			String time, String money, String cause, String account,
			String image, String desc) {
		super();
		this.id = id;
		this.oid = oid;
		this.uid = uid;
		this.gid = gid;
		this.gpid = gpid;
		this.time = time;
		this.money = money;
		this.cause = cause;
		this.account = account;
		this.image = image;
		this.desc = desc;
	}


	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGpid() {
		return gpid;
	}
	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
