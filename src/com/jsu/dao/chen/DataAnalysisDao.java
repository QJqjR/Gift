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
public class DataAnalysisDao extends HibernateDaoSupport {
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Object> findsexmoneyorder() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select case when o.pay_money>0 and o.pay_money<=10 then '0-10' when o.pay_money>10 and o.pay_money<=20 then '10-20' when o.pay_money>20 and o.pay_money<=50 then '20-50' when o.pay_money>50 and o.pay_money<=100 then '50-100'when o.pay_money>100 and o.pay_money<=300 then '100-300'when o.pay_money>300 and o.pay_money<=1000 then '300-1000'when o.pay_money>1000  then '1000-��' else null end  �۸����,count(case when u.u_sex='男' then '��' else null end) as ��,count(case when u.u_sex='女' then 'Ů' else null end) as Ů,count(*)���� from tb_user u,tb_order o where u.u_id=o.u_id  group by case when o.pay_money>0 and o.pay_money<=10 then '0-10'when o.pay_money>10 and o.pay_money<=20 then '10-20'when o.pay_money>20 and o.pay_money<=50 then '20-50'when o.pay_money>50 and o.pay_money<=100 then '50-100' when o.pay_money>100 and o.pay_money<=300 then '100-300' when o.pay_money>300 and o.pay_money<=1000 then '300-1000' when o.pay_money>1000  then '1000-��'else null end";
				Query query = session.createSQLQuery(sql);
				List<Object> list = query.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findagemoneyorder() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select case when o.pay_money>0 and o.pay_money<=10 then '0-10'when o.pay_money>10 and o.pay_money<=20 then '10-20'when o.pay_money>20 and o.pay_money<=50 then '20-50'when o.pay_money>50 and o.pay_money<=100 then '50-100'when o.pay_money>100 and o.pay_money<=300 then '100-300'when o.pay_money>300 and o.pay_money<=1000 then '300-1000'when o.pay_money>1000  then '1000-��'else null end  �۸����,count(case when u.u_age>0 and u.u_age<=18 then '0-18'else null end)as '0-18',count(case when u.u_age>18 and u.u_age<=30 then '18-30'else null end)as '18-30',count(case when u.u_age>30 and u.u_age<=45 then '30-45'else null end)as '30-65',count(case when u.u_age>45 and u.u_age<=65 then '45-65'else null end)as '45-65',count(case when u.u_age>65  then '65-��'else null end)as '65-��', count(*)���� from tb_user u,tb_order o where u.u_id=o.u_id group by case when o.pay_money>0 and o.pay_money<=10 then '0-10' when o.pay_money>10 and o.pay_money<=20 then '10-20' when o.pay_money>20 and o.pay_money<=50 then '20-50' when o.pay_money>50 and o.pay_money<=100 then '50-100' when o.pay_money>100 and o.pay_money<=300 then '100-300' when o.pay_money>300 and o.pay_money<=1000 then '300-1000' when o.pay_money>1000  then '1000-��' else null end";
				Query query = session.createSQLQuery(sql);
				List<Object> list = query.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findagesexmoneyorder() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select case when o.pay_money>0 and o.pay_money<=10 then '0-10' when o.pay_money>10 and o.pay_money<=20 then '10-20' when o.pay_money>20 and o.pay_money<=50 then '20-50' when o.pay_money>50 and o.pay_money<=100 then '50-100' when o.pay_money>100 and o.pay_money<=300 then '100-300' when o.pay_money>300 and o.pay_money<=1000 then '300-1000' when o.pay_money>1000  then '1000-��' else null end  �۸����,count(case when u.u_age>0 and u.u_age<=18 and u.u_sex ='男' then '0-18(��)'else null end)as '0-18(��)',count(case when u.u_age>0 and u.u_age<=18 and u.u_sex ='女' then '0-18(Ů)'else null end)as '0-18(Ů)',count(case when u.u_age>18 and u.u_age<=30 and u.u_sex ='男' then '18-30(��)'else null end)as '18-30(��)',count(case when u.u_age>18 and u.u_age<=30 and u.u_sex ='女' then '18-30(Ů)'else null end)as '18-30(Ů)',count(case when u.u_age>30 and u.u_age<=45 and u.u_sex ='男' then '30-45(��)'else null end)as '30-45(��)',count(case when u.u_age>30 and u.u_age<=45 and u.u_sex ='女' then '30-45(Ů)'else null end)as '30-45(Ů)',count(case when u.u_age>45 and u.u_age<=65 and u.u_sex ='男' then '45-65(��)'else null end)as '45-65(��)',count(case when u.u_age>45 and u.u_age<=65 and u.u_sex ='女' then '45-65(Ů)'else null end)as '45-65(Ů)',count(case when u.u_age>65 and u.u_sex ='男' then '65-��(��)'else null end)as '65-��(��)',count(case when u.u_age>65 and u.u_sex ='女' then '65-��(Ů)'else null end)as '65-��(Ů)', count(*)���� from tb_user u,tb_order o where u.u_id=o.u_id group by case when o.pay_money>0 and o.pay_money<=10 then '0-10' when o.pay_money>10 and o.pay_money<=20 then '10-20' when o.pay_money>20 and o.pay_money<=50 then '20-50' when o.pay_money>50 and o.pay_money<=100 then '50-100' when o.pay_money>100 and o.pay_money<=300 then '100-300' when o.pay_money>300 and o.pay_money<=1000 then '300-1000' when o.pay_money>1000  then '1000-��'else null end";
				Query query = session.createSQLQuery(sql);
				List<Object> list = query.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findrefund() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select refund_cause,count(*) as number from tb_refund GROUP BY refund_cause";
				Query query = session.createSQLQuery(sql);
				List<Object> list = query.list();
				return list;
			}
		});
	}
}
