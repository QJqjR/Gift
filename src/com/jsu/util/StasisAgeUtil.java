package com.jsu.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.jsu.dao.tang.SaleDao;
import com.jsu.entity.Gift;
import com.jsu.entity.Order;
import com.jsu.entity.User;

public class StasisAgeUtil {
	List<Order> orderList = new ArrayList<Order>();
	boolean flag;
	Gift gift ;
	Map<String, Integer> mapList;
	Map<String, Integer> giftList;

	public List<TreeMap<String, Integer>> util(List<User> userList, SaleDao dao) {
		List<User> list1 = new ArrayList<User>();
		List<User> list2 = new ArrayList<User>();
		List<User> list3 = new ArrayList<User>();
		List<User> list4 = new ArrayList<User>();
		List<User> list5 = new ArrayList<User>();
		List<User> list6 = new ArrayList<User>();
		List<TreeMap<String, Integer>> listAll = new ArrayList<TreeMap<String, Integer>>();

		for (User user : userList) {
			if (user.getAge() >= 0 && user.getAge() <= 20) {
				System.out.println("年龄在0~20之间");
				list1.add(user);
			} else if (user.getAge() >= 21 && user.getAge() <= 30) {
				System.out.println("年龄在21~30之间");
				list2.add(user);
			} else if (user.getAge() >= 31 && user.getAge() <= 40) {
				System.out.println("年龄在31~40之间");
				list3.add(user);
			} else if (user.getAge() >= 41 && user.getAge() <= 50) {
				System.out.println("年龄在41~50之间");
				list4.add(user);
			} else if (user.getAge() >= 51 && user.getAge() <= 60) {
				System.out.println("年龄在51~60之间");
				list5.add(user);
			} else {
				System.out.println("年龄在61之间");
				list6.add(user);
			}
		}

		System.out.println("每个年龄的用户统计完了，该统计，每个的每个用户购买商品数了，来自tb_order表");
			listAll.add((TreeMap<String, Integer>) treeMapList(list1, dao));
			listAll.add((TreeMap<String, Integer>) treeMapList(list2, dao));
			listAll.add((TreeMap<String, Integer>) treeMapList(list3, dao));
			listAll.add((TreeMap<String, Integer>) treeMapList(list4, dao));
			listAll.add((TreeMap<String, Integer>) treeMapList(list5, dao));
			listAll.add((TreeMap<String, Integer>) treeMapList(list6, dao));
		
		
		return listAll;
		
	}
    //统计并返回前十
	public TreeMap<String, Integer> treeMapList(List<User> list, SaleDao dao) {
		mapList = new TreeMap<String, Integer>();
		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		for (User u : list) {
			orderList = dao.findOrderByUserid(u.getId());
			System.out.println("有多少订单：" + orderList.size());
			for (Order order : orderList) {
				System.out.println(order);
				String[] giftid = order.getgId().split(",");
				String[] nums = order.getNum().split(",");
				System.out.println("--------------------");
				for (int i = 0; i < giftid.length; i++) {
					flag = mapList.containsKey(giftid[i]);
					if (flag) {
						mapList.put(
								giftid[i],
								mapList.get(giftid[i])
										+ Integer.parseInt(nums[i]));
					} else {
						mapList.put(giftid[i], Integer.parseInt(nums[i]));
					}
				}

			}
		}

		System.out.println("遍历12222222222222treemap表");
		for (Entry<String, Integer> mapping : mapList.entrySet()) {
			System.out.println("key:" + mapping.getKey() + ",value:"
					+ mapping.getValue());
			gift = dao.findGiftByGiftid(mapping.getKey());
			treeMap.put(gift.getType(), mapping.getValue());
		}
		System.out.println("------------------------");
		List<Map.Entry<String, Integer>> listArray = new ArrayList<Map.Entry<String, Integer>>(
				treeMap.entrySet());
		Collections.sort(listArray,
				new Comparator<Map.Entry<String, Integer>>() {
					// 降序排序
					public int compare(Entry<String, Integer> o1,
							Entry<String, Integer> o2) {
						return o2.getValue().compareTo(o1.getValue());
					}
				});

		System.out.println("------------------------");
		mapList.clear();
		int i = 0;
		for (Map.Entry<String, Integer> mapping : listArray) {
			if (i == 5)
				break;
			System.out.println(mapping.getKey() + ":" + mapping.getValue());
			mapList.put(mapping.getKey(), mapping.getValue());
			i++;
		}
		System.out.println("-------------mapList大小-----------："+mapList.size());
		return (TreeMap<String, Integer>) mapList;
	}

}
