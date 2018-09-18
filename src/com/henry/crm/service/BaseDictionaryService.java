package com.henry.crm.service;

import java.util.List;

import com.henry.crm.domain.BaseDictionary;

/**
 * 数据字典业务层的接口
 * @author HenryTuan
 *
 */
public interface BaseDictionaryService {

	List<BaseDictionary> findByTypeCode(String dict_type_code);

}
