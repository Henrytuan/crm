package com.henry.crm.dao;

import com.henry.crm.domain.User;

/**
 * 用户管理的DAO的接口
 * @author HenryTuan
 *
 */
public interface UserDao {

	void save(User user);

	User login(User user);

}
