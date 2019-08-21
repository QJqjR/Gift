package com.jsu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FormatUtil {
	
	public static String getId(){
		String u = UUID.randomUUID().toString();
		String uuid = u.substring(u.lastIndexOf("-")+1,u.length());
		return uuid;
	}

	public static String getDate(){
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(d);
	}
	
	public static String[] getData(String d){
		return d.split(",");
	}
	
	public static String[] format(String data){
		return data.split(",");
	}
}
