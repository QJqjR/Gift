package com.jsu.dao.tang;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.Refund;
import com.jsu.entity.User;

@Repository
public class SaleDao  extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//读取前十的销售量，来自tb_order表
	@SuppressWarnings("unchecked")
	public List<Order> findTenTop(){
		
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("来啊，快活啊");
				SQLQuery query = session.createSQLQuery("select * from tb_order where pay_account!='' and pay_account is not null");
				query.addEntity(Order.class);
				List<Order> orderList = query.list();	
				System.out.println("orderList大小："+orderList.size());
				System.out.println("--------------------------");
				session.close();
				return orderList;
			}
			
		});		
	}
	//获取月销售，来自tb_order表
	@SuppressWarnings("unchecked")
	public List<Order> findMonSale(final String toDay, final String monDay){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery("select * from tb_order where pay_time>='"+monDay+"' and pay_time<='"+toDay+"' and pay_account!='' and pay_account is not null order by pay_time asc");
				query.addEntity(Order.class);
				List<Order> orderList = query.list();
				System.out.println("月销售期间："+orderList.size());
				session.clear();
				return orderList;
			}			
		});
	}
	//根据giftid获取Gift,来自tb_gift表
	public Gift findGiftByGiftid(String giftid){
		return (Gift)getHibernateTemplate().get(Gift.class, giftid);
	}
	//获取所有用户信息
	@SuppressWarnings("unchecked")
	public List<User> findAllUser(){
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from User");
				List<User> userList = query.list();
				session.close();
				return userList;
			}			
		});
	}
	//根据u_id获取订单信息
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByUserid(final String userid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("userid:"+userid);
				SQLQuery query = session.createSQLQuery("select * from tb_order where u_id='"+userid+"' and pay_account!='' and pay_account is not null");
				query.addEntity(Order.class);
				List<Order> orderList = query.list();
				session.close();
				System.out.println("----------orderList大小--------"+orderList.size());
				return orderList;
			}
			
		});
	}
	//查找所有订单
	@SuppressWarnings("unchecked")
	public List<Order> findAllOrder(final int offset,final int limit){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Order where pay_account!='' and pay_account is not null");
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Order> orderList = query.list();
				System.out.println("000000000000"+orderList.size());
				return orderList;
			}
			
		});
	}
	//通过gpid查询参数,来自gift_parameter表
	public GiftParameter findGiftParameterByGpid(String gpid){
		return (GiftParameter)getHibernateTemplate().get(GiftParameter.class, gpid);
	}
	//查询订单状态为2的数据
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByStatus(final int type){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Order where type=?");
				query.setInteger(0, type);
				List<Order> orderList = query.list();
				session.close();
				System.out.println("####orderList大小####"+orderList.size());
				return orderList;
			}
			
		});
	}
	//查询订单，根据oid，来自o_order表
	public Refund findRefundByRid(final String rid){	
		return (Refund) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("rid:"+rid);
				Query query = session.createQuery("from Refund where o_id = ?");
				query.setString(0, rid);
				List<Refund> refundList = query.list();
				System.out.println();
				System.out.println("%%%%%%%%%%%refundList大小："+refundList.size());
				if(refundList.size()==0)
					return null;
				else{
					System.out.println(refundList);
					return refundList.get(0);

				}
			}
			
		});
	}

}
