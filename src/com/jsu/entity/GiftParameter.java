package com.jsu.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class GiftParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String gId;
	private int reseve;
	private String type;
	private double price;
	private String title;
	private String parameter;
	private String image;
	
	
	
	
	
	
	public GiftParameter() {
		super();
	}
	public GiftParameter(String id, String gId, int reseve, String type,
			double price, String title, String parameter, String image) {
		super();
		this.id = id;
		this.gId = gId;
		this.reseve = reseve;
		this.type = type;
		this.price = price;
		this.title = title;
		this.parameter = parameter;
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public int getReseve() {
		return reseve;
	}
	public void setReseve(int reseve) {
		this.reseve = reseve;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "GiftParameter [id=" + id + ", gId=" + gId + ", reseve="
				+ reseve + ", type=" + type + ", price=" + price + ", title="
				+ title + ", parameter=" + parameter + ", image=" + image + "]";
	}
	

}
