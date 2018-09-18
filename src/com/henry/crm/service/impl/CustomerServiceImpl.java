package com.henry.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.henry.crm.dao.CustomerDao;
import com.henry.crm.domain.Customer;
import com.henry.crm.domain.PageBean;
import com.henry.crm.service.CustomerService;
/**
 * 客户管理的Service的实现类
 * @author HenryTuan
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// 注入客户的DAO
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	// 业务层保存客户的方法
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	// 业务层分页查询客户的方法：
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		// 封装当前的页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		// 封装总的记录数
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		Double totalCountDouble = totalCount.doubleValue();
		Double num = Math.ceil(totalCountDouble/pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
}
