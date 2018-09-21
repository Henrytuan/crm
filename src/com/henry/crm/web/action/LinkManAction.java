package com.henry.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.henry.crm.domain.Customer;
import com.henry.crm.domain.LinkMan;
import com.henry.crm.domain.PageBean;
import com.henry.crm.service.CustomerService;
import com.henry.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	// 模型驱动需要用到的对象
	private LinkMan  linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	// 注入Service
	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	// 注入客户管理的Service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 分页参数
	private Integer currPage = 1;
	private Integer pageSize = 3;
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/*
	 * 查询联系人列表的Action: findAll
	 */
	public String findAll() {
		// 创建离线条件查询：
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 设置条件：名字
		if(linkMan.getLkm_name() != null) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		// 设置性别条件
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		// 调用业务层
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria, currPage, pageSize);
		// 把pageBean压入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/*
	 * 跳转到添加页面的联系人的方法： saveUI
	 */
	public String saveUI() {
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 将list集合保存到值栈中: 对象用push比较方便，list用set比较方便
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/*
	 * 保存客户的方法：save
	 */
	public String save() {
		// 调用业务层保存联系人
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/*
	 * 跳转到编辑页面的方法：edit
	 */
	public String edit() {
		// 查询某个联系人，查询所有客户
		List<Customer> list = customerService.findAll();
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 将list集合和linkMan对象的数据带到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	
	/*
	 * 更新联系人的方法
	 */
	public String update() {
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/*
	 * 删除联系人的方法
	 */
	public String delete() {
		// 先查询，再删除
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
