package com.henry.crm.service.impl;

import java.util.List;

import com.henry.crm.dao.BaseDictionaryDao;
import com.henry.crm.domain.BaseDictionary;
import com.henry.crm.service.BaseDictionaryService;
/**
 * 数据字典业务层的实现类
 * @author HenryTuan
 *
 */
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

	// 注入DAO
	private BaseDictionaryDao baseDictionaryDao;

	public void setBaseDictionaryDao(BaseDictionaryDao baseDictionaryDao) {
		this.baseDictionaryDao = baseDictionaryDao;
	}

	@Override
	public List<BaseDictionary> findByTypeCode(String dict_type_code) {
		return baseDictionaryDao.findByTypeCode(dict_type_code);
	}
	
}
