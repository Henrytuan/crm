package com.henry.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.henry.crm.dao.LinkManDao;
import com.henry.crm.domain.LinkMan;
import com.henry.crm.domain.PageBean;
import com.henry.crm.service.LinkManService;
/**
 * 联系人Service层的实现类
 * @author HenryTuan
 *
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {

	// 注入DAO
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	// 业务层分页查询联系人的方法
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页显示记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		// 显示每页数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	// 业务层保存联系人的方法
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	// 业务层根据ID查询联系人的方法
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	// 业务层修改联系人的方法
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	// 业务层中删除联系人的方法
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
