package com.jsu.entity;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;


@Repository
public class Comment {
     private String id;
     private String uId;
     private String gId;
     private String detail;
     private String redetail;
     private String time;
     private String image;
     private int rate;//评价等级，1,2,3,4,5，等级越高，评价越好
     private int review;
     
     //纯显示用
     private String email;
     private String uimage;
     private String gtitle;
     private String uname;
     
	public Comment() {
		super();
	}
	
	
	
	
	
	public Comment(String uId, String detail, String redetail, String time,
			String image, int rate, String email, String uimage, String gtitle,
			String uname) {
		super();
		this.uId = uId;
		this.detail = detail;
		this.redetail = redetail;
		this.time = time;
		this.image = image;
		this.rate = rate;
		this.email = email;
		this.uimage = uimage;
		this.gtitle = gtitle;
		this.uname = uname;
	}





	public Comment(String id, String uId, String gId, String detail,
			String redetail, String time, String image, int rate, int review,
			String email, String uimage, String gtitle) {
		super();
		this.id = id;
		this.uId = uId;
		this.gId = gId;
		this.detail = detail;
		this.redetail = redetail;
		this.time = time;
		this.image = image;
		this.rate = rate;
		this.review = review;
		this.email = email;
		this.uimage = uimage;
		this.gtitle = gtitle;
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
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRedetail() {
		return redetail;
	}
	public void setRedetail(String redetail) {
		this.redetail = redetail;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
     
}
