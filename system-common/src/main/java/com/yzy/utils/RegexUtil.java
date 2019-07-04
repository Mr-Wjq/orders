package com.yzy.utils;

import org.springframework.util.StringUtils;

public class RegexUtil {
	public static boolean required(String string) {
		return StringUtils.hasText(string);
	}
	public static boolean zhName(String string,int firstNum,int endNum) {
		boolean required = required(string); //若果为空返回true  只校验不为空的时候的格式
		if(!required) {
			return !required;
		}
		String regex = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{"+firstNum+","+endNum+"}$";
		return string.matches(regex);
	}
	public static boolean loginName(String string,int firstNum,int endNum) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^[a-zA-Z][a-zA-Z0-9_-]{"+firstNum+","+endNum+"}$";
		return string.matches(regex);
	}
	public static boolean password(String string,int firstNum,int endNum) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^.*(?=.{"+firstNum+","+endNum+"})(?=.*\\d)(?=.*[A-Za-z]{1,})(?!.*[\\s])(?=.*[!@#$%^&*?\\(\\)]).*$";
		return string.matches(regex);
	}
	public static boolean email(String string) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
		return string.matches(regex);
	}
	public static boolean phone(String string) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^[1][3456789]\\d{9}$";
		return string.matches(regex);
	}
	
	/**
	 * 说        明：折扣验证
	 * 作        者： 王俊强
	 * 修  改  人：
	 * 参数说明：
	 * @param
	 */
	public static boolean discount(String string) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^[0-9]|[0-9]\\.[0-9]$";
		return string.matches(regex);
	}
	/**
	 * 说        明：正整数验证
	 * 作        者： 王俊强
	 * 修  改  人：
	 * 参数说明：
	 * @param
	 */
	public static boolean integ(String string) {
		boolean required = required(string);
		if(!required) {
			return !required;
		}
		String regex = "^\\d+$";
		return string.matches(regex);
	}
	
}
