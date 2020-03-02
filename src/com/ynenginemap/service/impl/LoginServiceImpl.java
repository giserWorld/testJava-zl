package com.ynenginemap.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynenginemap.dao.BaseDAO;
import com.ynenginemap.entity.UserBean;
import com.ynenginemap.service.LoginService;


@Service("LoginService")//暴露出自定义名字的service bean
@Transactional
public class LoginServiceImpl implements LoginService {
	
	//自动注入
	@Resource
	private BaseDAO<UserBean> uDAO;
	@Resource
	private BaseDAO<Map<String, Object>> mDAO;
	
	
	@Override
	public Map<String, Object> getByUsernameAndpassword(String username, String password,String path) {
		String sql = "select * from tb_user where id='1'";
		Map<String, Object> user = mDAO.getBySQL1(sql);
		return user;
	}
}
