package com.henry.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.henry.crm.domain.Customer;
import com.henry.crm.domain.PageBean;
import com.henry.crm.service.CustomerService;
import com.henry.crm.utils.UploadUtils;
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
	 * 文件上传提供的三个属性
	 */
	private String uploadFileName;			// 文件的名称
	private File upload;					// 上传的文件
	private String uploadContentType;		// 文件的类型

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
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
	public String save() throws IOException {
		// 上传图片
		if(upload != null) {
			// 文件上传
			// 设置文件上传的路径
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			// 文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的值
			customer.setCust_image(url+"/"+uuidFileName);
		}
		// 保存客户
		customerService.save(customer);
		return "savaSuccess";
	}
	
	/*
	 * 分页查询客户的方法： findAll
	 */
	@SuppressWarnings("unused")
	public String findAll() {
		// 接收分页参数
		// 最好使用DetachedCriteria对象（条件查询--带分页）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 设置条件：（在web层设置条件）
		if(customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictionarySource()!=null) {
			if(customer.getBaseDictionarySource().getDict_id() != null && !"".equals(customer.getBaseDictionarySource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictionarySource.dict_id", customer.getBaseDictionarySource().getDict_id()));
			}
		}
		if(customer.getBaseDictionaryLevel()!=null) {
			if(customer.getBaseDictionaryLevel().getDict_id() != null && !"".equals(customer.getBaseDictionaryLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictionaryLevel.dict_id", customer.getBaseDictionaryLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictionaryIndustry()!=null) {
			if(customer.getBaseDictionaryIndustry().getDict_id() != null && !"".equals(customer.getBaseDictionaryIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictionaryIndustry.dict_id", customer.getBaseDictionaryIndustry().getDict_id()));
			}
		}
		
		// 调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/*
	 * 删除客户的方法：delete
	 */
	public String delete() {
		// 先查询，再删除，方便删除关联图片，还有方便做级联删除
		customer = customerService.findById(customer.getCust_id());
		// 删除图片
		if(customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if(file.exists()) {
				file.delete();
			}
		}
		// 删除客户
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/*
	 * 编辑客户的方法：edit
	 */
	public String edit() {
		// 根据ID查询,跳转页面，回显数据
		customer = customerService.findById(customer.getCust_id());
		// 要将customer传递到页面：有两种方式，但取值不一样
		// 一：手动压栈；二：模型驱动的对象默认在栈顶
		// 如果使用第一种方式回显数据(传统标签)：<s:property value="cust_name"/>
		// ActionContext.getContext().getValueStack().push(customer);
		// 						strust2的标签：<s:textfiled name="cust_name"/> 不需要写value值，无法异步加载回显
		// 如果使用第二种方式回显数据（传统标签）：<s:property value="model.cust_name"/> 
		// 不需要任何操作，但value值需要加%强制转
		
		// 跳转页面
		return "editSuccess";
	}
	
	public String update() throws IOException {
		// 文件项是否已经选择：如果选择了，删除原有文件，上传新文件。如果没选，使用原有文件即可
		if(upload != null ) {
			// 已经选择了删除原有文件
			String cust_image = customer.getCust_image();
			if(cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			// 文件上传
			// 设置文件上传的路径
			String path = "E:/upload";
			// 一个目录下存放的相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			// 文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// 设置image的属性的新值
			customer.setCust_image(url+"/"+uuidFileName);
			
		}
		customerService.update(customer);
		return  "updateSuccess";
	}
}
