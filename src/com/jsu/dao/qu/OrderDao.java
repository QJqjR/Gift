package com.jsu.dao.qu;

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
import com.jsu.entity.Order;
import com.jsu.entity.User;

@Repository
public class OrderDao extends HibernateDaoSupport {
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 根据用户Id查找所有剁手记录
	public List<Order> findAllOrder(String id) {
		String hql = "from Order where uId = ? order by pay_time DESC";
		List<Order> orders = getHibernateTemplate().find(hql, id);
		return orders;
	}

	// 根据用户id和订单类型查找订单
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByType(final String id, final int type) {
		System.out.println("开始按类型查询订单");
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Order where uId = ? and type= ? order by pay_time DESC";
				Query query = session.createQuery(hql);
				query.setString(0, id);
				query.setInteger(1, type);
				List<Order> orders = query.list();
				for (Order order : orders) {
					System.out.println(order.getMoney() + "   "
							+ order.getTime());
				}
				return orders;
			}
		});
	}

	// 根据oid查询数据，来自tb_order表
	public Order findOrderById(String oid) {
		return (Order) getHibernateTemplate().get(Order.class, oid);
	}

	// 根据giftid查询数据，来自tb_gift表
	public Gift findGiftById(String giftid) {
		return (Gift) getHibernateTemplate().get(Gift.class, giftid);
	}
	// 根据giftid查询数据，来自tb_gift表
		public GiftParameter findGiftParameterById(String gpid) {
			return (GiftParameter) getHibernateTemplate().get(GiftParameter.class, gpid);
		}

	// 根据gpid查询数据，来自tb_giftparameter表
	@SuppressWarnings("unchecked")
	public List<GiftParameter> findGiftParameter2ById(final String gid, final String gpid) {
		return (List<GiftParameter>) getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from GiftParameter where g_id=? and gp_id=?");
				query.setString(0, gid);
				query.setString(1, gpid);
				List<GiftParameter> gpList = query.list();
				System.out.println("gplist大小来了："+gpList.size());
				session.close();
				return gpList;
			}
			
		});
	}

	// 根据addressid查询数据，来自tb_address表
	public Address findAddressById(String addressid) {
		return (Address) getHibernateTemplate().get(Address.class, addressid);
	}

	// 根据addressid查询数据，来自tb_address表
	public User findUserById(String userid) {
		return (User) getHibernateTemplate().get(User.class, userid);
	}
	
	//修改订单状态
	public void  updateType(final String Oid,final String account,final int type){
		System.out.println("oid:" + Oid + "   account:" + account + "   type:" + type);
		System.out.println("开始修改");
		getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hql = "update Order set o_type = ? where o_id = ? and pay_account = ?";
					Query query = session.createQuery(hql);
					query.setInteger(0,type);
					query.setString(1,Oid);
					query.setString(2,account);
					query.executeUpdate();
					session.beginTransaction().commit();
					session.flush();
					session.close();
					System.out.println("修改成功");
				return null;
			}
		});
	}

	public void deleteOrder(String id) {
		Order order=(Order) getHibernateTemplate().get(Order.class, id);
		getHibernateTemplate().delete(order);
	}
}
