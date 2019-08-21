package com.jsu.util;

import java.util.ArrayList;
import java.util.List;

import com.jsu.entity.Gift;

public class ArrayListOnly {
	public static List<Gift> arrayListOnly(List<Gift> giftList) {
		for (int i = 0; i <giftList.size(); i++) {
            String id = giftList.get(i).getId();
            for(int j =i+1;j<giftList.size();j++){
            	if(id.equals(giftList.get(j).getId())){
            		giftList.remove(j);
            	}
            }
        }
		
		return giftList;
	}

}
