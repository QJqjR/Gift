package com.jsu.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Artical {
	
	private String id;
	private String time;
	private String title;
	private String img;
	private String details;
	
	public Artical() {
		super();
	}
	
	public Artical(String id, String time, String title, String img,
			String details) {
		super();
		this.id = id;
		this.time = time;
		this.title = title;
		this.img = img;
		this.details = details;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
}
