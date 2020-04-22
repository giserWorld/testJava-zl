package com.ynenginemap.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
	@RequestMapping("test/test.action")
	@ResponseBody
	public Object test(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mgs","获取数据成功！");
		map.put("code",0);
		map.put("data","");
		return map;
	}
}

