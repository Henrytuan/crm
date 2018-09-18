package com.henry.crm.service;

import com.henry.crm.domain.User;

/**
 * 用户管理的业务层的接口
 * @author HenryTuan
 *
 */
public interface UserService {

	void regist(User user);

	User login(User user);

}
