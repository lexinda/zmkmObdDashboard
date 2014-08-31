package com.obd.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @Description: Html相关工具类
 * @author Junwu Zheng(junwu.zheng@boco.jp)
 * @date 2013-9-4 下午3:19:40
 * @version V1.0
 */
public class HtmlUtil {

	/**
	 * html转义
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}
}
