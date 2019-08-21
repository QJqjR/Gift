package com.jsu.action.qu;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftDao;
import com.jsu.entity.Gift;

@Controller
public class UpdateGiftAction {
	@Resource(name="giftDao")
	private GiftDao dao;
	private String id;
	
	private String title;
	private String address;
	private double fare;
	private String oprice;
	private String nprice;
	private String time;
	private String brand;
	private String parameter;
	private String situation;
	private String partner;
	private String type;
	private String image;
	private String detailsImage;
	
	private String jsonData;
	
	public String updateGift(){
		
		Gift gift=new Gift();
		//id="1";
		System.out.println("商品修改页面的礼品id:"+id);
		gift=dao.findGiftById(id);
		System.out.println("查询完成");

		gift.setTitle(title);
		//gift.setTitle("生日快乐");
		gift.setAddress(address);
		gift.setFare(fare);
		gift.setOprice(oprice);
		gift.setNprice(nprice);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		gift.setTime(df.format(new Date()));//时间指的是发布时间，修改后是第一次发布的时间还是此时修改后发布的时间？
		gift.setBrand(brand);
		gift.setParameter(parameter);
		gift.setSituation(situation);
		gift.setPartner(partner);
		gift.setType(type);
		gift.setImage(image);
		gift.setDetailsImage(detailsImage);
		
		dao.updategift(gift);
		
		JSONObject obj = new JSONObject();
		obj.put("code",200);
		obj.put("status", 1);
		obj.put("msg", "商品修改成功！");
		jsonData = obj.toString();		
		return "success";
	}

	public GiftDao getDao() {
		return dao;
	}

	public void setDao(GiftDao dao) {
		this.dao = dao;
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	

}
