package com.jsu.dao.qu;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.Address;
import com.jsu.entity.Order;

@Repository
public class AddressDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void AddAddress(Address address){
		System.out.println("开始插入地址---------------");
		getHibernateTemplate().save(address);
		System.out.println("插入完成------------------");
	}
	
	public Address findAddressById(String id){
		Address address=(Address) getHibernateTemplate().get(Address.class, id);
		return address;
	}

	public List<Address> findAllAddress(String userid) {
		String hql="from Address where uId = ? ";
		List<Address> address=getHibernateTemplate().find(hql,userid);
		return address;
	}
	

}
