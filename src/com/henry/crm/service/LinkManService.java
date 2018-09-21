package com.henry.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.henry.crm.domain.LinkMan;
import com.henry.crm.domain.PageBean;

/**
 * 联系人Service层的接口
 * @author HenryTuan
 *
 */
public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
