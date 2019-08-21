package com.jsu.action.tang;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import com.jsu.dao.liu.GiftDao;
import com.jsu.dao.liu.GiftParamDao;
import com.jsu.dao.qu.OrderDao;
import com.jsu.dao.qu.UserDao;
import com.jsu.dao.tang.BuyDao;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.User;
import com.jsu.util.CreateIdUtil;
@Controller
public class BuyAction {
	@Resource(name="buyDao")
	private BuyDao dao;
	@Resource(name="giftDao")
	private GiftDao gdao;
	@Resource(name="giftParamDao")
	private GiftParamDao gpdao;
	@Resource(name="userDao")
	private UserDao udao;
	@Resource(name="orderDao")
	private OrderDao odao;
	private String userid;
	private String gpid;
	private String money;
	private String addressid;
	private String msg;
	private String giftid;
	private String num;
	private String scid;
	private String jsonData;
	//单个商品购物
	public String buySingloten(){
		System.out.println("让开让开，要到单购买页面了");
	
		/*gpid="11c46bcfb32b";
		giftid="99819b79783d";
		money="9000";
		userid="10096";
		num="1";*/
		
		System.out.println("msg:"+msg);
		System.out.println("userid:"+userid+",gpid:"+gpid+",giftid:"+giftid+",num:"+num+",money:"+money+"addressid:"+addressid);
		
		//首先判断用户余额是否可以支付该商品
		 User user=udao.findUserById(userid);
		 double purse=Double.parseDouble(user.getPurse());
		 double Money=Double.parseDouble(money);
		System.out.println("还未付款时，用户的余额为："+purse);
		 if(purse>=Money){
			 
			 GiftParameter GP=gpdao.findGParameterById(gpid);
			 
			 System.out.println(GP.toString());
			 
			   int t=GP.getReseve();
			   if(t<Integer.parseInt(num)){
				   JSONObject obj = new JSONObject();
					obj.put("code", 400);
					obj.put("status", 0);
					obj.put("msg", "库存不足！！");
					jsonData = obj.toString();
					System.out.println(obj.toString());
					return "reseveError";
			 
			   }
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		String oid = CreateIdUtil.getId();
		
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date2 = df.format(new Date());
		String account=date2+userid+oid;
		account=account.replace("-", "");
		System.out.println("支付宝账号："+account);
		
		if(msg==null||"".equals(msg)){
			msg="  ";
		}
		
		Order order = new Order(oid,userid,gpid,giftid,num,date,money,account,1,addressid,msg);
		System.out.println("order开始添加");
	    dao.addOrder(order);	
	    System.out.println("order添加完毕");
	    
	   Gift gift = dao.findGiftById(giftid);
	  
	   //更新gift表
	  gdao.updateGiftTable(gift.getId(),gift.getDeal(),gift.getSale(),Integer.parseInt(num));
	   gift = dao.findGiftById(giftid);
	   
	   //更新giftParamter表
	// GiftParameter GP=gpdao.findGParameterById(gpid);
	  // int t=GP.getReseve();
	   System.out.println("该礼品参数的库存为："+t);
	   if(t>=Integer.parseInt(num)){
	   GP.setReseve(t-Integer.parseInt(num));
	   gpdao.updateGParam(GP);
	   GP=gpdao.findGParameterById(gpid);
	   }
	   
	   //从用户钱包里扣款
	   purse=purse-Double.parseDouble(money);
	   System.out.println("用户账户剩余余额："+purse);
	   user.setPurse(purse+"");
	   udao.updateUser(user);
	   
	   //把用户扣掉的金额转给商家
	   User superUser = udao.findUserById("0d2acf280422");
	   System.out.println("超级用户的账户余额为："+superUser.getPurse());
	   superUser.setPurse(Double.parseDouble(superUser.getPurse())+Double.parseDouble(money)+"");
	   System.out.println("收钱后，超级用户的余额为："+superUser.getPurse());
	   udao.updateUser(superUser);
	   
	   System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("WIDout_trade_no", oid);
		obj.put("WIDtotal_amount", money);
		obj.put("WIDsubject", gift.getTitle());
		obj.put("deal", gift.getDeal());
		obj.put("sale", gift.getSale());
		obj.put("reseve", GP.getReseve());
		obj.put("msg", "单个商品提交成功！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "success";
	}else{
		JSONObject obj = new JSONObject();
		obj.put("code", 400);
		obj.put("status", 0);
		obj.put("msg", "余额不足！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "error";
	}
}

	//多个商品提交
	public String buyProperty(){
  	//gpid="11c46bcfb32b,1d4707281ce1";
		/*giftid="99819b79783d,8e7b2c11bb47";
		money="253,552.42";
		num="1,1";
		userid="10096";
		scid = "fa9062e3f4a8,fa10e7f13c96";*/
				
		String[] gpids=gpid.split(",");
		System.out.println("礼品参数id:"+gpid);
		String[] giftids=giftid.split(",");
		System.out.println("礼品id:"+giftid);
		String[] moneys=money.split(",");
		System.out.println("礼品价格："+money);
		String[] nums=num.split(",");
		System.out.println("礼品数量列表："+num);
		
		String[] scids = scid.split(",");
		System.out.println("购物车id:"+scid);
		
		double m = 0;
		for(String mo:moneys)
			m = m + Double.parseDouble(mo);
		System.out.println("总价：" + m);
		
		//首先判断用户余额是否可以支付该商品
		 User user=udao.findUserById(userid);
		 double purse=Double.parseDouble(user.getPurse());
		 System.out.println("用户钱包余额："+purse);
		 System.out.println("用户余额够吗："+(purse>=m));
		
		if(purse>=m){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		String oid = CreateIdUtil.getId();
		System.out.println("多选订单的id:"+oid);
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date2 = df.format(new Date());
		String account=date2+userid+oid;
		account=account.replace("-", "");
		System.out.println("支付宝账号："+account);
		
		if(msg==null||"".equals(msg)){
			msg="  ";
		}
	
		Order order = new Order(oid,userid,gpid,giftid,num,date,String.valueOf(m),account,1,addressid,msg);
		dao.addOrder(order);
		
		 //从用户钱包里扣款
		   purse=purse-m;
		   System.out.println("用户账户剩余余额："+purse);
		   user.setPurse(purse+"");
		   udao.updateUser(user);
		   
		 //把用户扣掉的金额转给商家
		   User superUser = udao.findUserById("0d2acf280422");
		   superUser.setPurse(Double.parseDouble(superUser.getPurse())+m+"");
		   udao.updateUser(superUser);
		   
		for(int i=0;i<gpids.length;i++){
			//更新gift表
			Gift gift = dao.findGiftById(giftids[i]);
		    gdao.updateGiftTable(gift.getId(),gift.getDeal(),gift.getSale(),Integer.parseInt(nums[i]));
		    
		    //更新giftParamter表
			GiftParameter GP=gpdao.findGParameterById(gpids[i]);
			int t=GP.getReseve();
			if(t<Integer.parseInt(nums[i])){
				odao.deleteOrder(oid);
				JSONObject obj = new JSONObject();
				obj.put("code", 400);
				obj.put("status", 0);
				obj.put("msg", "库存不足！！");
				jsonData = obj.toString();
				System.out.println(obj.toString());
				return "reseveError";
			}
			System.out.println("该礼品参数的库存为："+t);
			GP.setReseve(t-Integer.parseInt(nums[i]));
			gpdao.updateGParam(GP);
			//GP=gpdao.findGParameterById(gpid);
		}
		
	   
		Gift gift = dao.findGiftById(giftids[0]);
		 
		System.out.println("userid:"+userid+",addressid:"+addressid);
		System.out.println("遍历gpid");
		for(String g:gpids)
			System.out.println(g);
		
		System.out.println("遍历giftid");
		for(String g:giftids)
			System.out.println(g);
		
		System.out.println("遍历money");
		for(String g:moneys)
			System.out.println(g);
		
		//删除购物车里已经购买的东西
		for(String s:scids)
			dao.deleteShoppingCarByScid(s);
		
		
		 System.out.println("开始发送JSON数据");
		 JSONObject obj = new JSONObject();
		 obj.put("code", 200);
		 obj.put("status", 1);
		 obj.put("WIDout_trade_no", oid);
		 obj.put("WIDtotal_amount", String.valueOf(m));
		 obj.put("WIDsubject", gift.getTitle());
		 obj.put("msg", msg);
		 obj.put("msg", "多个商品提交成功！！");
		 jsonData = obj.toString();
		 System.out.println(obj.toString());
		 return "success";
	}else{
		JSONObject obj = new JSONObject();
		obj.put("code", 400);
		obj.put("status", 0);
		obj.put("msg", "余额不足！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "error";
	}	
}
	


	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public BuyDao getDao() {
		return dao;
	}

	public void setDao(BuyDao dao) {
		this.dao = dao;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGpid() {
		return gpid;
	}
	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getGiftid() {
		return giftid;
	}
	public void setGiftid(String giftid) {
		this.giftid = giftid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public GiftDao getGdao() {
		return gdao;
	}
	public void setGdao(GiftDao gdao) {
		this.gdao = gdao;
	}
	public GiftParamDao getGpdao() {
		return gpdao;
	}
	public void setGpdao(GiftParamDao gpdao) {
		this.gpdao = gpdao;
	}
	public UserDao getUdao() {
		return udao;
	}
	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

}
