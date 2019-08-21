package com.jsu.action.liu;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftDao;
import com.jsu.entity.Gift;
import com.jsu.util.FormatUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class GiftAction {
	
	private String jsonData;
	private String title;
	private String address;
	private double fare;
	private String time;
	private String brand;
	private String parameter;
	private String situation;
	private String partner;
	private String type;
	private String image;
	private String price;
	private String detailsImage;
	private int limit;
	private int offset;
	
	@Resource(name="giftDao")
	private GiftDao gdao;
	
	
	public String addGift(){
		
		
		Gift gift = new Gift(title,address,fare,image,detailsImage,time,brand,parameter,situation,partner,type,price,1);
		gift.setId(FormatUtil.getId());
		System.out.println(gift);
		gdao.insertGift(gift);
		JSONObject obj = new JSONObject();
		
		obj.put("code",200);
		obj.put("status", 1);
		jsonData = obj.toString();
		return "success";
	}
	
	
	
	public String getGiftList(){
		
		System.out.println(limit+"\t"+offset);
		JSONArray ja = JSONArray.fromObject(gdao.findGiftBySize(limit, offset));
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", ja.toString());
		System.out.println(obj.toString());
		jsonData = obj.toString();
		return "success";
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public int getLimit() {
		return limit;
	}



	public void setLimit(int limit) {
		this.limit = limit;
	}



	public int getOffset() {
		return offset;
	}



	public void setOffset(int offset) {
		this.offset = offset;
	}



	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
	
	

}
