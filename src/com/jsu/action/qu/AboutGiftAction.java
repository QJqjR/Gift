package com.jsu.action.qu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jsu.dao.liu.GiftDao;
import com.jsu.entity.Gift;
@Controller
public class AboutGiftAction {
	private String gid;
	private int gstatus;
	private String jsonData;
	@Resource(name="giftDao")
	private GiftDao dao;
	private int limit;
	private int offset;
	
	//gift上架或下架
	public String changeGiftSta(){
		List<Gift> gifts;
		/*gid="1";
		gstatus=1;
		limit=24;
		offset=0;*/
		System.out.println("上架下架的gid:"+gid);
		System.out.println("前端提交过来的gstatus:"+gstatus);
		Gift gift=dao.findGiftById(gid);
		System.out.println("修改前的商品的状态："+gift.getStatus());
		if(gstatus==1){
			gift.setStatus(0);
			dao.updategift(gift);
		}else if(gstatus==0){
			gift.setStatus(1);
			dao.updategift(gift);
		}
		gifts=dao.findGiftBySize(limit,offset);
	    System.out.println("修改后的商品状态："+dao.findGiftById(gid).getStatus());
		System.out.println("gifts的长度："+gifts.size());
		
		
		
		JSONObject obj=new JSONObject();
		JSONArray json = JSONArray.fromObject(gifts);
    	obj.put("code", 200);
   	    obj.put("status", 1);
   	    obj.put("msg", "商品状态修改成功！");
   	    obj.put("gifts", json);
   	    jsonData=obj.toString();
   	    System.out.println(obj.toString());
   	    return "success";
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public GiftDao getDao() {
		return dao;
	}

	public void setDao(GiftDao dao) {
		this.dao = dao;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public int getGstatus() {
		return gstatus;
	}
	public void setGstatus(int gstatus) {
		this.gstatus = gstatus;
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
	
	

}
