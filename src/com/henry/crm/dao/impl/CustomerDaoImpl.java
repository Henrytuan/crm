package com.henry.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.henry.crm.dao.CustomerDao;
import com.henry.crm.domain.Customer;
/**
 * 客户管理的DAO的实现类
 * @author HenryTuan
 *
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	// DAO中保存客户的方法
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	// DAO中带条件统计个数的方法
	public Integer findCount(DetachedCriteria detachedCriteria) {
		
		// 设置条件 select count(*) from xxx where 条件，返回Long类型
		detachedCriteria.setProjection(Projections.rowCount());
		
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	// DAO中分页查询客户的方法
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Customer>)this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

}
