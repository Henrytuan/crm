package com.henry.crm.domain;
/**
 * 客户管理的实体类
 * @author HenryTuan
 * CREATE TABLE `cst_customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */
public class Customer {

	private Long cust_id;
	private String cust_name;
	/*private String cust_source;
	private String cust_industry;
	private String cust_level;*/
	private String cust_phone;
	private String cust_mobile;
	
	// 客户和字典表是多对一：需要在多的一方放的是一的一方的对象
	private BaseDictionary baseDictionarySource;
	private BaseDictionary baseDictionaryIndustry;
	private BaseDictionary baseDictionaryLevel;
	
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	
	public BaseDictionary getBaseDictionarySource() {
		return baseDictionarySource;
	}
	public void setBaseDictionarySource(BaseDictionary baseDictionarySource) {
		this.baseDictionarySource = baseDictionarySource;
	}
	public BaseDictionary getBaseDictionaryIndustry() {
		return baseDictionaryIndustry;
	}
	public void setBaseDictionaryIndustry(BaseDictionary baseDictionaryIndustry) {
		this.baseDictionaryIndustry = baseDictionaryIndustry;
	}
	public BaseDictionary getBaseDictionaryLevel() {
		return baseDictionaryLevel;
	}
	public void setBaseDictionaryLevel(BaseDictionary baseDictionaryLevel) {
		this.baseDictionaryLevel = baseDictionaryLevel;
	}
	
}
