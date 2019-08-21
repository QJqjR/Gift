package com.jsu.action.zhu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.zhu.PushGiftDao;
import com.jsu.entity.PushGift;
import com.jsu.util.CreateIdUtil;
 
@Controller
public class FindPushGiftAction {
	@Resource
	private PushGiftDao pushGiftDao;
	private List<PushGift> pushGifts;
	private String articalId;
	private String jsondata;
	private String title;
	private String details;
	private String image;
	private String price;
	private String link;
	private String id;
	
	//根据articalid查找pushGift
	public String findPushGift(){
		//articalId="5daaf5af86b6";
		pushGifts = pushGiftDao.findByArticaId(articalId);
		System.out.println(pushGifts);
		System.out.println("进入查询");
		JSONArray json = JSONArray.fromObject(pushGifts);
		JSONObject obj = new JSONObject();
		if (pushGifts.size() != 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("result", json);
			obj.put("msg", "推送礼品查询成功！");
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("result", json);
			obj.put("msg", "推送礼品查询失败！");
		}
		jsondata = obj.toString();	
		return "success";
	}
	
	//根据articalId增加pushgift
	public String addPushGift(){
		String[] titles=title.split(",");
		String[] detas=details.split(",");
		String[] images=image.split(",");
		String[] prices=price.split(",");
		String[] links=link.split(",");
		
		for(int i=0;i<titles.length;i++){
			PushGift pusGift=new PushGift(CreateIdUtil.getId(),articalId,titles[i],detas[i],images[i],prices[i],links[i]);
			pushGiftDao.addPushGift(pusGift);
		}
		JSONArray json = JSONArray.fromObject(pushGifts);
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
	    obj.put("result", json);
	    obj.put("msg", "推送礼品添加成功！");
		jsondata = obj.toString();	
		return "success";
	}
	
	//删除pushGift
		public String deletePushGift(){
			//id="2";
			PushGift pushGift=pushGiftDao.findById(id);
			pushGiftDao.deletePushGiftById(pushGift);
			JSONArray json = JSONArray.fromObject(pushGifts);
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
		    obj.put("result", json);
		    obj.put("msg", "推送礼品删除成功！");
			jsondata = obj.toString();	
			return "success";
		}
		
		//更新pushGift
		public String updatePushGift(){
			id="1";
			PushGift pushGift=pushGiftDao.findById(id);
			pushGift.setArticalId(articalId);
			pushGift.setDetails("考研顺利");
			pushGift.setImage(image);
			pushGift.setPrice(price);
			pushGift.setTitle(title);
			pushGift.setLink(link);
			
			pushGiftDao.updatePushGiftById(pushGift);
			JSONArray json = JSONArray.fromObject(pushGifts);
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
		    obj.put("result", json);
		    obj.put("msg", "推送礼品更新成功！");
			jsondata = obj.toString();	
			return "success";
		}
	
	public PushGiftDao getPushGiftDao() {
		return pushGiftDao;
	}
	public void setPushGiftDao(PushGiftDao pushGiftDao) {
		this.pushGiftDao = pushGiftDao;
	}
	public List<PushGift> getPushGifts() {
		return pushGifts;
	}
	public void setPushGifts(List<PushGift> pushGifts) {
		this.pushGifts = pushGifts;
	}
	public String getArticalId() {
		return articalId;
	}
	public void setArticalId(String artivalId) {
		this.articalId = artivalId;
	}
	public String getJsondata() {
		return jsondata;
	}
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
