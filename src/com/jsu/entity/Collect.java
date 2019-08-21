package com.jsu.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Collect implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uId;
	private String gId;
	private String time;
	
	
	public Collect() {
		super();
	}
	public Collect(String uId, String gId, String time) {
		super();
		this.uId = uId;
		this.gId = gId;
		this.time = time;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
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
	@Override
	public String toString() {
		return "Collect [uId=" + uId + ", gId=" + gId + ", time=" + time + "]";
	}
	

}
