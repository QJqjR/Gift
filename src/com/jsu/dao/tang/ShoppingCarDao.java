package com.jsu.dao.tang;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.GiftParameter;
import com.jsu.entity.ShoppingCar;

@Repository
public class ShoppingCarDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//添加一条记录到购物车,来自tb_shoppingcar表
	public void addShoppingCar(ShoppingCar sc){
		getHibernateTemplate().save(sc);
	}
	
	//根据userid查询购物车表,来自tb_shoppingcar表
	@SuppressWarnings("unchecked")
	public List<ShoppingCar> findShoppingCarByUserId(final String userid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from ShoppingCar where u_id=?");
				query.setString(0, userid);
				List<ShoppingCar> sc = query.list();				
				session.close();
				return sc;
			}
			
		});
	}
	//根据gpid查询购物车图片,来自tb_giftparameter表
	@SuppressWarnings("unchecked")
	public List<GiftParameter> findShoppingCarImgByScId(final String gpid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("gpid-----"+gpid);
				Query query = session.createQuery("from GiftParameter where gp_id=?");
				query.setString(0, gpid);
				List<GiftParameter> gp = query.list();
				System.out.println("gp大小："+gp.size());
				session.close();
				return gp;
			}
			
		});
	}
	//根据scid删除购物车表,来自tb_shoppingcar表
	public void deleteByScId(String scid){
		ShoppingCar sc = (ShoppingCar)getHibernateTemplate().get(ShoppingCar.class, scid);
		System.out.println(sc);
		getHibernateTemplate().delete(sc);
	}
	//根据scid更新购物车商品数量，来自tb_shopppingcar表
	public void updateByScId(final String scid, final int num){
		ShoppingCar sc = (ShoppingCar)getHibernateTemplate().get(ShoppingCar.class, scid);
		System.out.println("sc输出没问题的");
		sc.setNum(num);
		getHibernateTemplate().update(sc);
		System.out.println("我就说嘛，该更新了");
	}

}
