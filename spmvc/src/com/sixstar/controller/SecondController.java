package com.sixstar.controller;

import com.sixstar.mvc.core.BaseController;
import com.sixstar.mvc.core.RequestMapping;
import com.sixstar.mvc.core.ResponseBody;

@RequestMapping("/asd")
public class SecondController extends BaseController{
	
	@RequestMapping("/show")
	@ResponseBody
	public void show(){
		System.out.println("asdqweasd");
	}
	
	
	
}
