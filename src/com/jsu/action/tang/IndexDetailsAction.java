package com.jsu.action.tang;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.tang.IndexDetailsDao;
import com.jsu.entity.Collect;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;

//进入页面详情的action
@Controller
public class IndexDetailsAction {
	@Resource(name="indexDetailsDao")
	private IndexDetailsDao dao;
	
	//存储详情页面的信息，存储于gift,giftparameter
	private Gift gift;
	private List<GiftParameter> giftparameter;
	
	//显示在前台的图片，用数组存储
	private String[] img;
	private String[] img1;
	private String[] parameter;
	private String giftid;
	private String userid;
	private JSONObject obj;
	
	public String isCollect(){
		System.out.println("开始查找是否收藏了");
	    System.out.println("userid:"+userid);
	    System.out.println("giftid:"+giftid);
	    List<Collect> collectList = dao.findCollectByUseridAndGid(userid, giftid);
	    System.out.println("collectList大小："+collectList.size());
		 
	    JSONObject obj= new JSONObject();
		 obj.put("code", 200);
		 obj.put("status", 1);
		 obj.put("collectFlag",collectList.size());
		 System.out.println(obj);
		return "success";
	}
	
	
	
	public String indexDetailsDao(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String giftid = request.getParameter("giftid");
		String userid = request.getParameter("userid");*/
		System.out.println("giftid:"+giftid+",userid:"+userid);
		//giftid="8e7b2c11bb47";
		gift = dao.findByGift(giftid);
		System.out.println("gift:"+gift);
		
		giftparameter = dao.findByGiftId(giftid);
		
		System.out.println("遍历giftparameter");
		for(GiftParameter g:giftparameter)
			System.out.println(g);
		
		JSONArray json1 = JSONArray.fromObject(gift);
		JSONArray json2 = JSONArray.fromObject(giftparameter);
		
		System.out.println("序列化输出gift"+obj.toString());
		
		img1 = gift.getImage().split(",");
		img = gift.getDetailsImage().split(",");
		 System.out.println("遍历img");
		 for(String i:img)
			 System.out.println(i);	
		 
		 System.out.println("遍历img1");
		 for(String i:img1)
			 System.out.println(i);
		 
		 System.out.println("查询收藏表，看当前用户id和礼品id是否已经存在，也就是该商品是否已收藏");
		 List<Collect> collectList = dao.findCollectByUseridAndGid(userid,giftid);
		 System.out.println("--------------------------");
		 parameter = gift.getParameter().split(",");
		 
		/*session.setAttribute("parameter", parameter);
		session.setAttribute("photo", img1[0]);
		session.setAttribute("img1", img1);
		session.setAttribute("gift", gift);
		session.setAttribute("giftparameter", giftparameter);
		session.setAttribute("img", img);
		session.setAttribute("collectFlag", collectList.size());*/
		 
		 obj = new JSONObject();
		 obj.put("code", 200);
		 obj.put("status", 1);
		 obj.put("results", json1.toString());
		 obj.put("result2", json2.toString());
		 obj.put("collectFlag",collectList.size());
		 obj.put("parameter", parameter);
		 obj.put("img1",img1);
		 obj.put("Detailsimg", img);
		
		return "success";
	}

	public IndexDetailsDao getIndexDetailsDao() {
		return dao;
	}

	public void setIndexDetailsDao(IndexDetailsDao indexDetailsDao) {
		this.dao = indexDetailsDao;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public IndexDetailsDao getDao() {
		return dao;
	}

	public void setDao(IndexDetailsDao dao) {
		this.dao = dao;
	}
	
	



	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public List<GiftParameter> getGiftparameter() {
		return giftparameter;
	}

	public void setGiftparameter(List<GiftParameter> giftparameter) {
		this.giftparameter = giftparameter;
	}

	public String[] getImg() {
		return img;
	}

	public void setImg(String[] img) {
		this.img = img;
	}

	public String[] getImg1() {
		return img1;
	}

	public void setImg1(String[] img1) {
		this.img1 = img1;
	}

	public String[] getParameter() {
		return parameter;
	}

	public void setParameter(String[] parameter) {
		this.parameter = parameter;
	}

	public String getGiftid() {
		return giftid;
	}

	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
