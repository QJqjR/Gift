package com.jsu.action.tang;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.tang.CollectDao;
import com.jsu.entity.Collect;

@Controller
public class CollectAction {
	
	@Resource(name="collectDao")
	private CollectDao dao;
	private String userid;
	private String giftid;
	private String jsonData;
	//添加收藏
	public String yesCollect() throws IOException{
		List<Collect>  collect;
		System.out.println("收藏userid:"+userid+",收藏giftid:"+giftid);
		collect = dao.findIsCollect(userid, giftid);
		
		System.out.println("开始查找是否有收藏该礼品");
		System.out.println(collect);
		if(collect.size()>0){
			System.out.println("该礼品已经收藏了");
			JSONObject obj = new JSONObject();
			obj.put("code", 400);
			obj.put("status", 1);
			jsonData = obj.toString();
		}else{
			System.out.println("该礼品还没有收藏");
		
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String giftid = request.getParameter("giftid");*/
		System.out.println("添加收藏");
		System.out.println("userid："+userid+",giftid:"+giftid);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		
		/*userid="123";
		giftid="321";*/
		
		dao.addCollectById(new Collect(userid, giftid, date));
		System.out.println("添加一条收藏数据成功");
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		
		
		
		JSONObject obj = new JSONObject();
		JSONArray array = JSONArray.fromObject( dao.findAllCollectByUid(userid));
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "添加成功！！");
		obj.put("collects", array.toString());
		jsonData = obj.toString();
		System.out.println(jsonData);
		}
		return "success";
		
		//response.getWriter().write(1);
		//return null;
	}
	//取消收藏
	public String noCollect() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String giftid = request.getParameter("giftid");*/
		System.out.println("取消收藏");
		System.out.println("userid："+userid+",giftid:"+giftid);
		
		//userid="123";
		//giftid="321";
		
		dao.deleteCollectById(userid, giftid);
		System.out.println("删除一条收藏数据成功");
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		JSONArray array = JSONArray.fromObject( dao.findAllCollectByUid(userid));
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "删除成功！！");
		obj.put("collects", array.toString());
		jsonData = obj.toString();
		System.out.println(jsonData);
		return "success";
	}
	public CollectDao getDao() {
		return dao;
	}
	public void setDao(CollectDao dao) {
		this.dao = dao;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGiftid() {
		return giftid;
	}
	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	
}
