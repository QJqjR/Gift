package com.jsu.util;

import java.util.UUID;

public class CreateIdUtil {

	public static String getId(){
		 String u =  UUID.randomUUID().toString();
		 String uuid = u.substring(u.lastIndexOf('-')+1, u.length()); 
		 return uuid;
	}
}
