package com.henry.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用的DAO的接口
 * @author HenryTuan
 *
 */
public interface BaseDao<T> {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	// 根据ID查询一个对象
	public T findById(Serializable id);
	
	// 查询所有
	public List<T> findAll();
	
	// 统计个数
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	// 分页查询
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
}
