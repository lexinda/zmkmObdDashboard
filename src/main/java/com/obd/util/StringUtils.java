package com.obd.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类:提供针对字符串的通用操作，为了避免与apache中StringUtils的冲突，方便使用，继承了后者
 * 
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2011/07/13
 * @version 1.1 update by Wenlong Meng at 2011/07/28 for public static final String toSSLUrl(String url)
 * @version 1.2 update by Wenlong Meng at 2011/08/01 for subStrByByte
 */
public class StringUtils {

	/**
	 * 将str转化为int类型值，若出错或为空，则返回defaultVal
	 * 
	 * @param str
	 *            待转化字符串
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	public static InputStream toInputStream(String str) {
		try {
			return new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将str转化为int类型值，若出错或为空，则返回defaultVal
	 * 
	 * @param str
	 *            待转化字符串
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	public static int toInt(String str, int defaultVal) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return defaultVal;
		}

	}

	/**
	 * 指定url转化为ssl安全的url： 1、若url为空(" "或null)或以https开头，则返回其本身 2、若url以http开头，则替换为https开头 3、若url不以http开头，则添加https://
	 * 
	 * @param url
	 *            url字符串
	 * @return
	 */
	public static final String toSSLUrl(String url) {

		// 为空，则返回原url
		if (url == null) {
			return url;
		}

		if (url.startsWith("http:")) {
			url = url.replaceFirst("http:", "https:");
		} else if (url.startsWith("https:")) {

		} else {
			url = "https://" + url;
		}

		return url;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode digits. A decimal point is not a unicode digit and returns false.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = false
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains digits, and is non-null
	 */
	public static boolean isNum(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

}
