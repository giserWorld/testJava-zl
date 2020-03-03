package com.ynenginemap.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	
	public Map<String, Object> getUserIdAndDllx(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies(); //获取cookie数组
		if(cookies != null){
			for(Cookie cookie:cookies){//遍历cookie数组
				map.put(cookie.getName(), cookie.getValue());
			}
		}
		return map;
	}
	
}
