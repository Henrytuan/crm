package com.henry.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.henry.crm.domain.Customer;
import com.henry.crm.domain.PageBean;
import com.henry.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	// 模型驱动需要用到的对象
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	// 注入 CustomerService
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// 使用set方法的方式接收数据（当前页和每页显示的记录数）：
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
	 * 客户管理：跳转到添加页面的方法： saveUI
	 */
	public String saveUI() {
		return "saveUI";
	}
	
	/*
	 * 保存客户的方法: save
	 */
	public String save() {
		customerService.save(customer);
		return NONE;
	}
	
	/*
	 * 分页查询客户的方法： findAll
	 */
	@SuppressWarnings("unused")
	public String findAll() {
		// 接收分页参数
		// 最好使用DetachedCriteria对象（条件查询--带分页）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
}