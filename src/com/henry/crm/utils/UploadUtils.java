package com.henry.crm.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @author HenryTuan
 *
 */
public class UploadUtils {

	/*
	 * 解决目录下名字重复的问题
	 */
	public static String getUuidFileName(String fileName) {
		// 找到“.”所在的位置
		int idx = fileName.lastIndexOf(".");
		// 获取文件扩展名
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	/*
	 * 目录分离的方法
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		// 一级目录
		int d1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		// 二级目录
		int d2 = code2 & 0xf;
		return "/"+d1+"/"+d2;
	}
}
