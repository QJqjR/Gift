package com.jsu.action.qu;

import org.springframework.stereotype.Controller;

@Controller
public class TestAction {
	private String uid;
	
	public String test(){
		System.out.println("测试前端传过来的uid:"+uid);
		return "success";
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

}
