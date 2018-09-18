package com.henry.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.henry.crm.dao.BaseDictionaryDao;
import com.henry.crm.domain.BaseDictionary;
/**
 * 字典DAO的实现类
 * @author HenryTuan
 *
 */
public class BaseDictionaryDaoImpl extends HibernateDaoSupport implements BaseDictionaryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDictionary> findByTypeCode(String dict_type_code) {
		return (List<BaseDictionary>) this.getHibernateTemplate().find("from BaseDictionary where dict_type_code = ?", dict_type_code);
	}

}
