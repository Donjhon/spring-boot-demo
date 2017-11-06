package com.boot.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * StringUtil 
 * @author pcitc
 * @version 1.0
 */
public class StringTool {
	
	public static final String rexpString = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	//(?!_)(?!.*?_$)[a-zA-Z0-9]+$
	public static final String rexpStringExceptCHS = "^[a-zA-Z0-9]+$";
//	public static final String rexpIP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	
	public static boolean validateFormat(String resource){
		return resource.matches(rexpString);
	}
	
	public static boolean validateFormatExceptCHS(String resource){
		return resource.matches(rexpStringExceptCHS);
	}
	
//	public static boolean validateFormatIP(String resource){
//		return  resource.matches(rexpIP);
//	}
	/**
	 * 获取基础的HREF
	 * @param resource
	 * @return
	 */
	public static String getBaseHref(String href) {
		String hrefBase = "";
		String baseHref = "";
		if (href != null && !href.equals("") && !href.isEmpty()) {
			// 顶层HREF
			int endIndex = href.lastIndexOf("/");
			hrefBase = href.substring(0, endIndex);
		}
		try {
			hrefBase=URLDecoder.decode(hrefBase,"UTF-8");
			baseHref = hrefBase.replaceAll(" ", "").replaceAll("　", "");
		} catch (UnsupportedEncodingException e) {}
		return baseHref;
	}
}

