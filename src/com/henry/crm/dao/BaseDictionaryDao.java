package com.henry.crm.dao;

import java.util.List;

import com.henry.crm.domain.BaseDictionary;

/**
 * 字典DAO的接口
 * @author HenryTuan
 *
 */
public interface BaseDictionaryDao extends BaseDao<BaseDictionary> {

	List<BaseDictionary> findByTypeCode(String dict_type_code);

}
