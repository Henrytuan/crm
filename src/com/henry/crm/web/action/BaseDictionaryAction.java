package com.henry.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.henry.crm.domain.BaseDictionary;
import com.henry.crm.service.BaseDictionaryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@SuppressWarnings("serial")
public class BaseDictionaryAction extends ActionSupport implements ModelDriven<BaseDictionary> {

	// 模型驱动需要用到的对象
	private BaseDictionary baseDictionary = new BaseDictionary();
	@Override
	public BaseDictionary getModel() {
		// TODO Auto-generated method stub
		return baseDictionary;
	}

	
	// 注入Service
	private BaseDictionaryService baseDictionaryService;
	
	public void setBaseDictionaryService(BaseDictionaryService baseDictionaryService) {
		this.baseDictionaryService = baseDictionaryService;
	}
	
	// 根据类型名称查询字典的方法: findByTypeCode
	public String findByTypeCode() throws IOException {
		System.out.println("BaseDictionaryAction中的findByTypeCode方法执行了...");
		// 调用业务层查询
		List<BaseDictionary> list = baseDictionaryService.findByTypeCode(baseDictionary.getDict_type_code());
		// 将list转成JSON	---- jsonlib(引包过于繁杂，当前用这个) / fastjson（引包简单）
		/*
		 * 	JsonConfig	:	转JSON的配置对象
		 * 	JSONArray	:	将数组和list集合转成JSON
		 * 	JSONObject	:	将对象和Map集合转成JSON
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		// 将JSON打印到页面, 需要用到 Response
		ServletActionContext.getResponse().setContentType("text/html; charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
