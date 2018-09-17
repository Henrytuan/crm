package com.henry.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.henry.crm.dao.UserDao;
import com.henry.crm.domain.User;
/**
 * 用户管理的DAO的实现类
 * @author HenryTuan
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	// DAO中保存用户的方法
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

}
