package com.jsu.action.tang;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.tang.ConfirmOrderDao;
import com.jsu.entity.Address;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.ShoppingCar;
//确认订单
@Controller
public class ConfirmOrderAction {
	
	@Resource(name="confirmOrderDao")
	private ConfirmOrderDao dao;
	
	private String userid;
	private String gpid;
	private String gid;
	private String num;
	private String scid;
	
	
	
	//存储当前确认订单用户的所有地址
	private List<Address> addressList;
	//存储商品信息
	private GiftParameter gp;
	private Gift gift;


	//存储商品信息
	private JSONObject obj;
	
	//多选商品
	private List<Gift> giftList = new ArrayList<Gift>();
	private List<ShoppingCar> gpList = new ArrayList<ShoppingCar>();
	private ShoppingCar sc;
	
	//总付款
	private double totalSum=0;
	
	//确认订单(单个商品)
	public String confirmOrderSingten(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse reponse = ServletActionContext.getResponse();
		HttpSession session = request.getSession();*/
		System.out.println("确认订单");
		/*String userid = request.getParameter("userid");
		String gid = request.getParameter("gid");
		String scid = request.getParameter("scid");
		String num = request.getParameter("num");*/
		//System.out.println("userid:"+userid+",gid:"+gid+",scid:"+scid+",num:"+num);
		
		/*userid="10095";
		gpid="5fcd3bf52609";
		gid="1";*/
		
		addressList = dao.findAddressByUserId(userid);
		gp = dao.findGiftParameterByGid(gpid);
		gift =dao.findGiftByGid(gid);
		
		System.out.println("遍历addressList");
		for(Address a:addressList)
			System.out.println(a);
		System.out.println("gp:"+gp);
		System.out.println("gift:"+gift);
		

		
		JSONArray json = JSONArray.fromObject(addressList);
		JSONArray json2 = JSONArray.fromObject(gp);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		obj.put("result2", json2.toString());
		obj.put("gift", gift);
		obj.put("num", num);
		obj.put("total", Integer.parseInt(num)*gp.getPrice());
		System.out.println(obj.toString());
		
		/*session.setAttribute("addressList", addressList);
		session.setAttribute("gp", gp);
		session.setAttribute("gift", gift);
		session.setAttribute("num", num);
		session.setAttribute("total", Integer.parseInt(num) * gp.getPrice());*/
		
		return "success";
	}
	
	public String confirmOrderProperty(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse reponse = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		System.out.println("多选");
		
		String userid = request.getParameter("userid");//用户id
		String[] giftid = request.getParameterValues("giftid");//礼品id
		String[] scid = request.getParameterValues("scid");//购物车id
		String[] spid = request.getParameterValues("spid");//礼品参数id
		String[] countnum = request.getParameterValues("countnum");//数量
		System.out.println("username:"+userid);
		*/
		
		/*scid="5bc717e6b3dd,e2a15a2fafc6";
		gid="11,11";
		gpid="1095,1095";
		num="1,1";*/
		
		String[] scids=scid.split(",");
		String[] giftid=gid.split(",");
		String[] spid=gpid.split(",");
		String[] countnum=num.split(",");
		
	
		System.out.println("遍历box,这是礼品参数，购物车中，现在要准备显示的数据了");
		giftList.clear();
		gpList.clear();
		
		for(int i=0;i<scids.length;i++){
			System.out.println(scids[i]);
			gift = dao.findGiftByGid(giftid[i]);
			sc = dao.findShoppingCarByGid(scids[i]);
			
			gp = dao.findGiftParameterByGid(spid[i]);
			System.out.println("------------------");
			System.out.println(gift);
			System.out.println(sc);
			System.out.println("giftid:"+giftid[i]+",scid:"+scids[i]);
			System.out.println(gp);
			System.out.println("---------------------");

			sc.setImg(gp.getImage());
			sc.setPrice(gp.getPrice());
			System.out.println("countnum[i]:"+countnum[i]+",gp.getNprice():"+gp.getPrice());
			System.out.println(Integer.parseInt(countnum[i]) * gp.getPrice());
			sc.setTotal(Integer.parseInt(countnum[i]) * gp.getPrice());
			sc.setgId(gift.getId());
			sc.setFare(gift.getFare());
			gpList.add(sc);
			giftList.add(gift);
			
			
		}
		System.out.println("gpList大小："+gpList.size());
		System.out.println("giftList大小："+giftList.size());
		System.out.println("---------------------------------");
		
		System.out.println("遍历gpList");
		for(ShoppingCar sc:gpList){
			System.out.println("fare:"+sc.getFare());
			totalSum = totalSum + sc.getTotal();
		}
		//根据username也应是userid读取地址
		addressList = dao.findAddressByUserId(userid);
		/*request.setAttribute("gpList", gpList);
		request.setAttribute("totalSum", totalSum);
		request.setAttribute("num", gpList.size());
		request.setAttribute("giftList", giftList);
		request.setAttribute("addressList", addressList);*/
		
		
		JSONArray json = JSONArray.fromObject(addressList);
		JSONArray json2 = JSONArray.fromObject(gpList);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("totalSum", totalSum);
		obj.put("giftList", gpList.size());
		obj.put("results", json.toString());
		obj.put("result2", json2.toString());
		System.out.println(obj.toString());
		return "success";
	}

	public ConfirmOrderDao getDao() {
		return dao;
	}


	public void setDao(ConfirmOrderDao dao) {
		this.dao = dao;
	}


	public List<Address> getAddressList() {
		return addressList;
	}


	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}


	public GiftParameter getGp() {
		return gp;
	}


	public void setGp(GiftParameter gp) {
		this.gp = gp;
	}


	public Gift getGift() {
		return gift;
	}


	public void setGift(Gift gift) {
		this.gift = gift;
	}


	public JSONObject getObj() {
		return obj;
	}


	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
