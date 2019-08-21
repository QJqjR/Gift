package com.jsu.dao.liu;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.GiftParameter;

@Repository
public class GiftParamDao extends HibernateDaoSupport{
	
	
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void insertGiftParam(GiftParameter p){
		getHibernateTemplate().save(p);
	}
	
	public List<GiftParameter> findGParamById(String id){
		String hql="from GiftParameter where gId = ?";
		List<GiftParameter> GParams=getHibernateTemplate().find(hql, id);
		return GParams;
		
	}
	
	public GiftParameter findGParameterById(String id){
		System.out.println("这边的id为："+id);
		String hql="from GiftParamter where id = ?";
		GiftParameter GP=(GiftParameter) getHibernateTemplate().get(GiftParameter.class, id);
		System.out.println("-------------------------");
		System.out.println(GP.toString());
		return GP;
		
	}
	
	public void updateGParam(GiftParameter gparam){
		getHibernateTemplate().update(gparam);
	}
}
