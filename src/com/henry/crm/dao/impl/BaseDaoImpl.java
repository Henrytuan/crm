package com.henry.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.henry.crm.dao.BaseDao;
/**
 * 通用的DAO的实现类
 * @author HenryTuan
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	@SuppressWarnings("rawtypes")
	private Class clazz;
	// 提供构造方法：在构造方法中传入具体类型的class
	/*
	 * 不想子类上有构造方法，必须在父类中提供无参数的构造，在无参构造中获得具体类型的Class.
	 * 具体类型的Class是参数类型中的实际类型参数。
	 */
	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		// 反射第一步：获得实现类的class
		// 正在被调用类的那个class, CustomerDaoImpl.class 或者 LinkManDaoImpl.class
		Class clazz = this.getClass();
		// 第二步：通过实现类的class，获得父类的参数化类型：BaseDaoImpl<Customer>或者BaseDaoImpl<LinkMan>
		Type type = clazz.getGenericSuperclass();
		// 强制转换为Type的子类：参数化类型
		ParameterizedType pType = (ParameterizedType) type;
		// 第三步：通过参数化类型获得实际类型参数：得到一个实际类型参数的数组（有Map<String, Integer）这种情况存在)
		Type[] types = pType.getActualTypeArguments();
		// 只需要获得第一个实际类型参数即可：得到 Customer、LinkMan.
		this.clazz = (Class) types[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	// 根据ID查询一个对象的方法
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	// 查询所有的方法
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	// 统计个数的方法
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// 设置统计个数的条件
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	// 分页查询的方法
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}
}
