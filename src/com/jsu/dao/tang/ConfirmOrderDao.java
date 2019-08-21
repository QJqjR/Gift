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

import com.jsu.entity.Address;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.ShoppingCar;

@Repository
public class ConfirmOrderDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//根据userid查询地址列表，来自tb_address表
	@SuppressWarnings("unchecked")
	public List<Address> findAddressByUserId(final String userid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Address where u_id=?");
				query.setString(0, userid);
				List<Address> addressList = query.list();
				System.out.println("addressList大小："+addressList.size());
				session.close();
				return addressList;
			}
			
		});		
	}
	//根据scid查询礼品参数表，来自tb_giftparameter
	public GiftParameter findGiftParameterByGid(String scid){
		GiftParameter gp = (GiftParameter) getHibernateTemplate().get(GiftParameter.class, scid);
		return gp;
	}
	//根据gid查询礼品表，来自tb_gift表
	public Gift findGiftByGid(String gid){		
		Gift gift = (Gift)getHibernateTemplate().get(Gift.class, gid);
		return gift;
	}
	//根据scid查询购物车表，来自tb_shoppingcar表
	public ShoppingCar findShoppingCarByGid(String scid){
		ShoppingCar sc = (ShoppingCar)getHibernateTemplate().get(ShoppingCar.class, scid);
		return sc;
	}
	//根据scid和gpid查询购物车

}
