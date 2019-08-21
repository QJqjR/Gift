package com.jsu.dao.tang;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.Order;

@Repository
public class PayDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//根据oid更新payid，来自tb_order表
	public void updatePayIdByOid(String oid, String accountid){
		Order order = (Order)getHibernateTemplate().get(Order.class, oid);
		System.out.println(order.toString());
		order.setAccount(accountid);
		getHibernateTemplate().update(order);

		
		
	}
	

}
