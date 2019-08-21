package com.jsu.dao.chen;

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

@Repository
public class ExcelDao extends HibernateDaoSupport {
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findgiftexcel() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select g.g_id,g.g_title,g.g_oprice,g.g_nprice,g.g_comment,g.g_deal,g.g_brand,gp.gp_id,gp.gp_price,gp.gp_title,gp.gp_reseve from tb_gift g,tb_giftparameter gp where g.g_id=gp.g_id";
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findorderexcel() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select o.o_id,o.u_id,o.gp_id,o.g_id,o.o_num,o.pay_time,o.pay_money,o.pay_account,o.o_type,a.receive_address,o.o_msg from tb_order o,tb_address a where o.address_id=a.address_id";
				Query query = session.createSQLQuery(sql);
				List<Object[]> lists = query.list();
				return lists;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findgiftname(final String s) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String id[] = s.split(",");
				String sql = "select g_title from tb_gift where g_id in ";
				if(id.length==1){
					sql = sql + "(?)";
				}else{
				for (int i = 0; i < id.length; i++) {
					if (i == 0) {
						sql = sql + "(?,";
					} else if (i == (id.length - 1)) {
						sql = sql + "?)";
					} else {
						sql = sql + "?,";
					}
				}
				}
				Query query = session.createSQLQuery(sql);
				for (int j = 0; j < id.length; j++) {
					query.setString(j, id[j]);
				}
				List<Object> lists = query.list();
				return lists;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findgiftparameter(final String s) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String id[] = s.split(",");
				String sql = "select gp_title from tb_giftparameter where gp_id in ";
				if(id.length==1){
					sql = sql + "(?)";
				}else{
				for (int i = 0; i < id.length; i++) {
					if (i == 0) {
						sql = sql + "(?,";
					} else if (i == (id.length - 1)) {
						sql = sql + "?)";
					} else {
						sql = sql + "?,";
					}
				}
				}
				Query query = session.createSQLQuery(sql);
				for (int j = 0; j < id.length; j++) {
					query.setString(j, id[j]);
				}
				List<Object> lists = query.list();
				return lists;
			}
		});
	}
}
