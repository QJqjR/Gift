package com.jsu.entity;

import org.springframework.stereotype.Component;

@Component
public class PushGift {
	private String id;
	private String articalId;
	private String title;
	private String details;
	private String image;
	private String price;
	private String link;
	
	public PushGift() {
		super();
	}

	public PushGift(String id, String articalId, String title, String details,
			String image, String price, String link) {
		super();
		this.id = id;
		this.articalId = articalId;
		this.title = title;
		this.details = details;
		this.image = image;
		this.price = price;
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticalId() {
		return articalId;
	}

	public void setArticalId(String articalId) {
		this.articalId = articalId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	

}
