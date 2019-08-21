package com.jsu.action.zhu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import com.jsu.dao.zhu.FindGiftDao;
import com.jsu.entity.Gift;
@Controller
public class FindGiftAction {
	private String type;
	@Resource
	private FindGiftDao findGiftDao;
	@Resource
	private List<Gift> gifts;
	private String jsondata;
	private String partner;
	
	public String findGiftByType(){
		
		//type="创意灯";
		System.out.println("type:"+type);
		System.out.println("查询开始");
		gifts = findGiftDao.findGifts(type);
		for(Gift gift:gifts){
			System.out.println(gift.toString());
		}
		System.out.println("查询结束");
		
		JSONArray json = JSONArray.fromObject(gifts);
		JSONObject obj = new JSONObject();
		if (gifts.size() != 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("results", json);
		}
		jsondata = obj.toString();
		return "success";
	}
	
		public String findGiftByPartner(){
			//partner="女朋友";
			System.out.println("partner:"+partner);
			System.out.println("开始查询");
			gifts = findGiftDao.findGiftsByPartner(partner);
			for(Gift gift:gifts){
				System.out.println(gift.toString());
			}
			
			JSONArray json = JSONArray.fromObject(gifts);
			JSONObject obj = new JSONObject();
			if (gifts.size() != 0) {
				obj.put("code", 200);
				obj.put("status", 1);
				obj.put("results", json);
			} else {
				obj.put("code", 400);
				obj.put("status", 0);
				obj.put("results", json);
			}
			setJsondata(obj.toString());
			return "success";
		}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public FindGiftDao getFindGiftDao() {
		return findGiftDao;
	}
	public void setFindGiftDao(FindGiftDao findGiftDao) {
		this.findGiftDao = findGiftDao;
	}
	public List<Gift> getGifts() {
		return gifts;
	}
	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
	public String getJsondata() {
		return jsondata;
	}
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
	

}
