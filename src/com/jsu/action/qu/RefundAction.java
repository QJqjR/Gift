package com.jsu.action.qu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.OrderDao;
import com.jsu.dao.qu.RefundDao;
import com.jsu.entity.Refund;
import com.jsu.util.CreateIdUtil;

@Controller
public class RefundAction {
	@Resource(name="refundDao")
	private RefundDao dao;
	@Resource(name="orderDao")
	private OrderDao odao;
	private String  id;
	private String oid;
	private String uid;
	private String gid;
	private String gpid;
	private String time;
	private String money;
	private int type;
	private String cause;
	private String account;
	private String image;
	private String desc;
	private int offset;
	private int limitset;
	private String jsonData;
	
	public String addRefund(){
		/*oid="08d11513aea8";
		account="201908131009608d11513aea8";
		gid="99819b79783d";
		gpid="11c46bcfb32b";
		uid="10096";*/
		//num=200;
		type=odao.findOrderById(oid).getType();
		System.out.println(oid);
		System.out.println("type:"+type);
		if(type==1||type==0){
			System.out.println("将order表中的type改为2");
			odao.updateType(oid, account,2);
		
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			time=df.format(new Date());
			Refund refund=new Refund(CreateIdUtil.getId(),oid,uid,gid,gpid,time,money,cause,account,image,desc);
			//Refund refund=new Refund(CreateIdUtil.getId(),"1","1","2","3","4");
			System.out.println(dao);
			dao.AddRefund(refund);
		}
		JSONObject obj=new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "退款申请提交成功");
		jsonData=obj.toString();
		return "success";
		
	}
	
	public String findAllRefund(){
		List<Refund> list=dao.findAllRefund(offset,limitset);
		JSONArray json=JSONArray.fromObject(list); 
		JSONObject obj=new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("list", json);
		obj.put("msg", "显示所有退款记录");
		jsonData=obj.toString();
		return "success";
	}

	public RefundDao getDao() {
		return dao;
	}

	public void setDao(RefundDao dao) {
		this.dao = dao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimitset() {
		return limitset;
	}

	public void setLimitset(int limitset) {
		this.limitset = limitset;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public OrderDao getOdao() {
		return odao;
	}

	public void setOdao(OrderDao odao) {
		this.odao = odao;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGpid() {
		return gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
