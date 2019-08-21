package com.jsu.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCar implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String uId;
	private String gPid;
	private int num;
	private String time;
	private String title;
	
	//自己添加的数据，在该映射文件没有，纯属为购物车设计
	private String img;
	private double price;
	private double total;
	private int reseve;	
	private String gId;
	private double fare;
	private String parameter;
	private String type;
	private String account;
	private int status;
	private String msg;
	
	
	
	public ShoppingCar() {
		super();
	}
	
	public ShoppingCar(String id,String uId,String title,String account,String time,String parameter,String type,double total,int status,String msg,int num){
		this.id = id;
		this.uId = uId;
		this.title = title;
		this.account = account;
		this.time = time;
		this.parameter = parameter;
		this.type = type;
		this.total = total;
		this.status = status;
		this.msg = msg;
		this.num = num;
	}
	
	
	public ShoppingCar(String img, String title,String parameter,String type,double price,int num,double total,double fare){
		this.img = img;
		this.title = title;
		this.parameter = parameter;
		this.type = type;
		this.price = price;
		this.num = num;
		this.total = total;
		this.fare = fare;
		
	}
	public ShoppingCar(String id,String title,String img, String parameter,String type,double fare,
			int num,double price,String gId,String gPid,String acount,String time,int status){
		this.id = id;
		this.title = title;
		this.img = img;
		this.parameter = parameter;
		this.type = type;
		this.fare = fare;
		this.num = num;
		this.price = price;
		this.gId=gId;
		this.gPid=gPid;
		this.account=acount;
		this.time=time;
		this.status = status;
	}
	
	public ShoppingCar(String id, String uId, String gPid, int num,
			String time, String title) {
		super();
		this.id = id;
		this.uId = uId;
		this.gPid = gPid;
		this.num = num;
		this.time = time;
		this.title = title;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getgPid() {
		return gPid;
	}
	public void setgPid(String gPid) {
		this.gPid = gPid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getReseve() {
		return reseve;
	}
	public void setReseve(int reseve) {
		this.reseve = reseve;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ShoppingCar [id=" + id + ", uId=" + uId + ", gPid=" + gPid
				+ ", num=" + num + ", time=" + time + ", title=" + title
				+ ", img=" + img + ", price=" + price + ", total=" + total
				+ ", reseve=" + reseve + ", gId=" + gId + ", fare=" + fare
				+ ", parameter=" + parameter + ", type=" + type + "]";
	}

	
}
