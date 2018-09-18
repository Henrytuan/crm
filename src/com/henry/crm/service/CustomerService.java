package com.henry.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.henry.crm.domain.Customer;
import com.henry.crm.domain.PageBean;

/**
 * 客户管理的Service的接口
 * @author HenryTuan
 *
 */
public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

}
