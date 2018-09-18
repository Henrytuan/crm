package com.henry.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.henry.crm.domain.Customer;

/**
 * 客户管理的DAO的接口
 * @author HenryTuan
 *
 */
public interface CustomerDao {

	void save(Customer customer);

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

}
