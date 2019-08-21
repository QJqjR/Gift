package com.jsu.dao.zhu;

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

@Repository
public class GiftParameterDao extends HibernateDaoSupport {
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void updateGiftPatameter(final List<GiftParameter> list) {
		getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub

				for (GiftParameter giftParameter : list) {
					System.out.println("进入查询");
					String hql = "update GiftParameter set gp_price = ? , gp_reseve = ? where gp_id = ?";
					Query query = session.createQuery(hql);
					query.setString(2, giftParameter.getgId());
					query.setDouble(0, giftParameter.getPrice());
					query.setInteger(1, giftParameter.getReseve());
					query.executeUpdate();
					session.beginTransaction().commit();
				}
				session.flush();
				session.close();
				System.out.println("查询成功");
				return null;
			}
		});

	}

}
