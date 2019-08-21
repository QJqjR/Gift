package com.jsu.action.qu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftParamDao;
import com.jsu.entity.GiftParameter;

@Controller
public class GParamsAction {
	private String id;
	private String jsonData;
	@Resource(name="giftParamDao")
	private GiftParamDao dao;
	private List<GiftParameter> GParams;
	
	public String GParams(){
		System.out.println("我要开始查询了哟");
		System.out.println("前端页面的gId:"+id);
		//GParams=dao.findGParamById("11");
		GParams=dao.findGParamById(id);
		System.out.println("我的身高为："+GParams.size());
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("GParams", GParams);
		obj.put("msg", "礼品参数查询成功！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "success";
	}
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public GiftParamDao getDao() {
		return dao;
	}
	public void setDao(GiftParamDao dao) {
		this.dao = dao;
	}
	public List<GiftParameter> getGParams() {
		return GParams;
	}
	public void setGParams(List<GiftParameter> gParams) {
		GParams = gParams;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
