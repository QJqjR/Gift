package com.jsu.dao.tang;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.Gift;
import com.jsu.entity.Order;
import com.jsu.entity.ShoppingCar;


//立即购买
@Repository
public class BuyDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//创建一条订单数据，来自tb_order表
	public void addOrder(Order order){
		System.out.println("开始插入");
		getHibernateTemplate().save(order);
		System.out.println("插入完成");
	}
	//根据giftid查询数据，来自tb_gift表
	public Gift findGiftById(String giftid){
		Gift gift = (Gift)getHibernateTemplate().get(Gift.class, giftid);
		return gift;
	}
	//根据scid删除购物车已经购买的东西
	public void deleteShoppingCarByScid(String scid){
		ShoppingCar sc = (ShoppingCar) getHibernateTemplate().get(ShoppingCar.class, scid);
		getHibernateTemplate().delete(sc);
	}

}
