package com.jsu.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Gift implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String oprice;
	private String nprice;
	private int comment;
	private int deal;
	private String address;
	private double fare;
	private String image;
	private String detailsImage;
	private String time;
	private String brand;
	private String parameter;
	private int sale;
	private String situation;
	private String partner;
	private int status;
	private String type;
	
	
	
	
	
	public Gift() {
		super();
	}
	public Gift(String title, String address, double fare, String image,
			String detailsImage, String time, String brand, String parameter,
			String situation, String partner, String type,String price,int status) {
		super();
		this.title = title;
		this.address = address;
		this.fare = fare;
		this.image = image;
		this.detailsImage = detailsImage;
		this.time = time;
		this.brand = brand;
		this.parameter = parameter;
		this.situation = situation;
		this.partner = partner;
		this.type = type;
		this.nprice = price;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOprice() {
		return oprice;
	}
	public void setOprice(String oprice) {
		this.oprice = oprice;
	}
	public String getNprice() {
		return nprice;
	}
	public void setNprice(String nprice) {
		this.nprice = nprice;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDetailsImage() {
		return detailsImage;
	}
	public void setDetailsImage(String detailsImage) {
		this.detailsImage = detailsImage;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Gift [id=" + id + ", title=" + title + ", oprice=" + oprice
				+ ", nprice=" + nprice + ", comment=" + comment + ", deal="
				+ deal + ", address=" + address + ", fare=" + fare + ", image="
				+ image + ", detailsImage=" + detailsImage + ", time=" + time
				+ ", brand=" + brand + ", parameter=" + parameter + ", sale="
				+ sale + ", situation=" + situation + ", partner=" + partner
				+ ", status=" + status + ", type=" + type + "]";
	}
	

}
