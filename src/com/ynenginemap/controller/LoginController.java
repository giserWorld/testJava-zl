package com.ynenginemap.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynenginemap.entity.UserBean;
import com.ynenginemap.service.LoginService;



@Controller
public class LoginController {

	@Resource//自动注入bean
	private LoginService loginService;
	
	@RequestMapping("login.action")
	@ResponseBody 
	public Object login(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,String username,String password){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String path=  request.getSession().getServletContext().getRealPath("");
		String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
		//获取到存储路径
		path= path.split(projectName)[0]+"ImageServer\\";
		
		Map<String, Object> user = loginService.getByUsernameAndpassword(username,password,"");
		
		map.put("mgs","获取数据成功！");
		map.put("code","0");
		map.put("data",user);
		return map;
	}
}
