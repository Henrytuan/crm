package com.henry.crm.service;

import java.util.List;

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

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

}
