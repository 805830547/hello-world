package com.sixstar.service;

import com.sixstar.dao.BaseDao;
import com.sixstar.dto.LoginDto;


public class LoginService {
	
	public LoginDto loginSelect(LoginDto loginDto){
		BaseDao baseDao = new BaseDao();
		String sql = "select username, password, name from test_liu_user where username = ? and password = ?";
		LoginDto lDto = baseDao.getObject(sql, LoginDto.class, loginDto.getUsername(),loginDto.getPassword());
		return lDto;
	}
	
	
//	public static void main(String[] args) {
//		
//		LoginDto l = new LoginDto();
//		l.setUsername("123");
//		l.setPassword("123123");
//		LoginDto loginDto = new LoginService().loginSelect(l);
//		System.out.println(loginDto.getName());
//	}
//
}
