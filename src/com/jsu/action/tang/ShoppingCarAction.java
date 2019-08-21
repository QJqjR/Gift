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

import com.jsu.dao.tang.ShoppingCarDao;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.ShoppingCar;
import com.jsu.util.CreateIdUtil;

@Controller
public class ShoppingCarAction {
	@Resource(name="shoppingCarDao")
	private ShoppingCarDao dao;
	//通过ajax异步传输购物车信息，弹出购物车
	private JSONObject obj;
	//进入购物车页面
	private JSONObject obj2;
	private String gpid;
	private String userid;
	private String num;
	private String title;
	private String scid;
	private String checkList;
	
	//添加到购物车
	public String addCar() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();*/
		
		System.out.println("----------------------------");
		System.out.println("添加到购物车");
		System.out.println("添加页面的用户名："+userid);
		/*String gpid = request.getParameter("gpid");
		String userid = request.getParameter("userid");
		String num = request.getParameter("num");
		String title = request.getParameter("title");*/
		System.out.println("gpid:"+gpid+",userid:"+userid+",num:"+num+",title:"+title);
		System.out.println("----------------------------");
		String scid = CreateIdUtil.getId();
		
		System.out.println("scid:"+scid);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		System.out.println(date);// new Date()为获取当前系统时间
		ShoppingCar sc = new ShoppingCar(scid,userid,gpid,Integer.parseInt(num),date,title);
		//ShoppingCar sc = new ShoppingCar(scid,userid,gpid,1000,date,"测试用的");
		System.out.println("开始保存");
		dao.addShoppingCar(sc);
		
		System.out.println("保存完了");
		List<ShoppingCar> scar = dao.findShoppingCarByUserId(userid);
		 obj = new JSONObject();
		 obj.put("code", 200);
		 obj.put("status", 1);
		 obj.put("scar", scar);
		 obj.put("msg", "成功添加至购物车");
		 System.out.println(obj.toString());
		//response.getWriter().write(1);
		return "success";
	}
	
	
	
	//弹出购物车列表
	public String shoppingCar1() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();*/
		System.out.println("弹出购物车列表");
		
		//String userid = request.getParameter("userid");
		System.out.println("userid:"+userid);
		
		//userid="10095";
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		List<GiftParameter> gp;
		for(ShoppingCar s:sc){
			System.out.println("s值："+s.getgPid());
			gp = dao.findShoppingCarImgByScId(s.getgPid());
			System.out.println("返回gp值："+gp.get(0).getImage());
			s.setImg(gp.get(0).getImage());
			s.setPrice(gp.get(0).getPrice());
		}
		System.out.println("遍历sc");
		for(ShoppingCar s:sc)
			System.out.println(s.getImg());
		
		if(sc.size()>10){
			System.out.println("当前登录用户购物车商品大于10，应该减少");
			for(int i=10;i<sc.size();i++)
				sc.remove(i);			
		}
		
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		
		//response.getWriter().write(1);
		return "success";
	}
	//进入购物车页面
	public String shoppingCar2(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();*/
		System.out.println("进入购物车页面");
		//String userid = request.getParameter("userid");
		System.out.println("userid-------------->>"+userid);
		
		//userid="10095";
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		System.out.println("sc的长度："+sc.size());
		System.out.println(sc.get(0));
		List<GiftParameter> gp;
		for(ShoppingCar s:sc){
			System.out.println(s);
			System.out.println("s值："+s.getgPid());
			gp = dao.findShoppingCarImgByScId(s.getgPid());
			System.out.println("返回gp值："+gp.get(0).getImage());
			s.setImg(gp.get(0).getImage());
			s.setPrice(gp.get(0).getPrice());
			s.setTotal(gp.get(0).getPrice()*s.getNum());
			s.setReseve(gp.get(0).getReseve());
			s.setgId(gp.get(0).getgId());
			s.setParameter(gp.get(0).getParameter());
			s.setType(gp.get(0).getType());
		}
		System.out.println("遍历sc---------跳转购物车页面，不是弹出购物车啊----------------------");
		double totalCount=0;
		for(ShoppingCar s:sc){
			totalCount = totalCount + s.getTotal();
			System.out.println(s);
			System.out.println("gId:"+s.getgId());
		}
			

		
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		
		/*session.setAttribute("sc", sc);
		session.setAttribute("scCount", sc.size());
		session.setAttribute("totalCount", totalCount);*/
		
		return "success";
	}
	
	//弹出购物车，点击删除
	public String deleteCarByScId() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();*/
		//String scid = request.getParameter("scid");
		System.out.println("删除购物车id");
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		System.out.println("删除后的购物车列表");
		//response.getWriter().write(1);
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "删除成功");
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		return "success";
	}
	//购物车页面，删除单个商品
	public String delByScId() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String scid = request.getParameter("scid");*/
		
		/*scid="e2a15a2fafc6";
		userid="10095";*/
		dao.deleteByScId(scid);
		
		System.out.println("删除购物车id:"+scid);
		
		//dao.deleteByScId(scid);
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		System.out.println("删除后的购物车列表");
		//response.getWriter().write(1);
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "删除成功");
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		//response.getWriter().write(1);
		return "success";
	}
	
	//批量删除
	public String delAll() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String checkList = request.getParameter("checkList");
		System.out.println("checkList:"+checkList);*/
		
		/*checkList="d3d99eb65822,5bc717e6b3dd";
		userid="10095";*/
		
		String[] delList = checkList.split(",");
		System.out.println("遍历delList");
		for(String delid:delList){
			System.out.println(delid);
			dao.deleteByScId(delid);
		}
				
		//response.getWriter().write(1);
		
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		System.out.println("删除后的购物车列表");
		//response.getWriter().write(1);
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "删除成功");
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		
		return "success";
	}
	
	public String changeNumByScId() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String scid = request.getParameter("scid");
		String num = request.getParameter("num");*/
		System.out.println("scid:"+scid+",num:"+num);
		dao.updateByScId(scid, Integer.parseInt(num));
		
		List<ShoppingCar> sc = dao.findShoppingCarByUserId(userid);
		System.out.println("商品数量更新后的购物车列表");
		//response.getWriter().write(1);
		JSONArray json = JSONArray.fromObject(sc);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "数量更新成功");
		obj.put("results", json.toString());
		System.out.println(obj.toString());
		return "success";
	}
	
	public ShoppingCarDao getDao() {
		return dao;
	}
	public void setDao(ShoppingCarDao dao) {
		this.dao = dao;
	}
	public JSONObject getObj() {
		return obj;
	}
	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	public JSONObject getObj2() {
		return obj2;
	}
	public void setObj2(JSONObject obj2) {
		this.obj2 = obj2;
	}
	public String getGpid() {
		return gpid;
	}
	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public String getCheckList() {
		return checkList;
	}
	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}
	

}
