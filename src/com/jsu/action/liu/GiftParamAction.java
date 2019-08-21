package com.jsu.action.liu;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftDao;
import com.jsu.dao.liu.GiftParamDao;
import com.jsu.entity.GiftParameter;
import com.jsu.util.FormatUtil;

@Controller
public class GiftParamAction {
	
	@Resource(name="giftParamDao")
	private GiftParamDao gpdao;
	private String jsonData;
	private String image;
	private String name;
	private String stock;
	private String price;
	private String params;
	private String title;
	private String giftId;

	
	public String addGiftParam(){
		System.out.println(image);
		System.out.println(name);
		System.out.println(stock);
		System.out.println(price);
		System.out.println(params);
		System.out.println(title);
		System.out.println(giftId);
		String[] n = FormatUtil.getData(name);
		String[] i = FormatUtil.getData(image);
		String[] s = FormatUtil.getData(stock);
		String[] p = FormatUtil.getData(price);
		String[] t = FormatUtil.getData(title);
		String[] pa = FormatUtil.getData(params);
		GiftParameter gp = null;
		System.out.println(n.length);
		for(int j=0;j<n.length;j++){
			String id = FormatUtil.getId();
			double pri = Double.parseDouble(p[j]);
			int sto = Integer.parseInt(s[j]);
			gp = new GiftParameter(id,giftId,sto,n[j],pri,t[j],pa[j],i[j]);
			System.out.println(gp);
			gpdao.insertGiftParam(gp);
		}
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		jsonData = obj.toString();
		return "success";
	}


	public GiftParamDao getGpdao() {
		return gpdao;
	}


	public void setGpdao(GiftParamDao gpdao) {
		this.gpdao = gpdao;
	}


	public String getJsonData() {
		return jsonData;
	}


	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStock() {
		return stock;
	}


	public void setStock(String stock) {
		this.stock = stock;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getParams() {
		return params;
	}


	public void setParams(String params) {
		this.params = params;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGiftId() {
		return giftId;
	}


	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	
	
	
}
