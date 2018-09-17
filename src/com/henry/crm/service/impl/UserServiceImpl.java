package com.henry.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.henry.crm.dao.UserDao;
import com.henry.crm.domain.User;
import com.henry.crm.service.UserService;
import com.henry.crm.utils.MD5Utils;
/**
 * 用户管理的业务层的实现类
 * @author HenryTuan
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
		
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	// 业务层注册业务的方法
	public void regist(User user) {
		// 对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		// 调用DAO
		userDao.save(user);
	}

}
