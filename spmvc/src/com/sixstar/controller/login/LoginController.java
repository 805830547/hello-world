package com.sixstar.controller.login;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSONObject;
import com.sixstar.dto.DataDto;
import com.sixstar.dto.LoginDto;
import com.sixstar.mvc.core.BaseController;
import com.sixstar.mvc.core.IdentifyView;
import com.sixstar.mvc.core.PageData;
import com.sixstar.mvc.core.RequestMapping;
import com.sixstar.mvc.core.ResponseBody;
import com.sixstar.service.DataService;
import com.sixstar.service.LoginService;
import com.sixstar.util.ValidateCodeUtil;

@RequestMapping("/index")
public class LoginController extends BaseController {
	
	DataDto dataDto = new DataDto();
	LoginDto loginDto = new LoginDto();
	DataService dataService = new DataService();

	
	@RequestMapping("/index")
	public IdentifyView index(){
		return new IdentifyView("index/index");
	}
	
	@RequestMapping("/login")
	public IdentifyView loginIn(){
		return new IdentifyView("index/login");
	}
	
	@RequestMapping("/manage")
	public IdentifyView manage(){
		return new IdentifyView("index/manage");
	}

	@RequestMapping(value="/select")
	@ResponseBody
	public void loginSelect(){
		PageData data = this.getPageData();
		int result = 0;
		loginDto.setUsername(data.getString("username"));
		loginDto.setPassword(data.getString("password"));
		loginDto = new LoginService().loginSelect(loginDto);
		try {
			PrintWriter out = response.getWriter();
			if("".equals(loginDto.getName()) || loginDto.getName() == null){
				result = 0;
			}else{
				result = 1;
			}
			out.print(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getValidateCode")
	@ResponseBody
	public void getValidateCode() {
		 BufferedImage  img = ValidateCodeUtil.getValidateCode(request);
		 OutputStream out = null;
		 try {
			out = response.getOutputStream();
			ImageIO.write(img, "jpg", out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/checkValidateCode")
	@ResponseBody
	public void checkValidateCode(){
		PageData data = this.getPageData();
		String code = data.getString("ValidateCode");
		String validateCode = (String) request.getSession().getAttribute("validateCode");
		
		System.out.println("000   "+code);
		System.out.println("111   "+validateCode);
		try {
			int result = 0;
			PrintWriter out = response.getWriter();
			if (code.equalsIgnoreCase(validateCode)) {
				result = 1;
			} else {
				result = 0;
			}
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping("/createData")
	@ResponseBody
	public void createData(){
		int result = 0;
		PageData data = this.getPageData();
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dataDto.setName(data.getString("Name"));
		dataDto.setPosition(data.getString("Position"));
		dataDto.setOffice(data.getString("Office"));
		dataDto.setAge(data.getString("Age"));
		dataDto.setStartDate(data.getString("StartDate"));
		dataDto.setSalary(data.getString("Salary"));
		dataDto.setCurrentTime(df.format(day));
		result = dataService.dataInsert(dataDto);
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping("/updateData")
	@ResponseBody
	public void updateData(){
		int result = 0;
		PageData data = this.getPageData();
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dataDto.setName(data.getString("Name"));
		dataDto.setPosition(data.getString("Position"));
		dataDto.setOffice(data.getString("Office"));
		dataDto.setAge(data.getString("Age"));
		dataDto.setStartDate(data.getString("StartDate"));
		dataDto.setSalary(data.getString("Salary"));
		dataDto.setCurrentTime(df.format(day));
		System.out.println("1111   "+df.format(day));
		result = dataService.dataUpdate(dataDto);
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/dataDelete")
	@ResponseBody
	public void dataDelete(){
		int result = 0;
		PageData data = this.getPageData();
		dataDto.setName(data.getString("Name"));
		result = dataService.dataDelete(dataDto);
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/datalist")
	@ResponseBody
	public void datalist(){
		PageData data = this.getPageData();
		DataDto datadto = new DataDto();
		datadto.setName(data.getString("Name"));
		datadto.setPosition(data.getString("Position"));
		datadto.setAge(data.getString("Age"));
		datadto.setStartDate(data.getString("StartDate"));
		datadto.setSalary(data.getString("Salary"));
		List<JSONObject> list = dataService.DataSelect(datadto);
		try {
			PrintWriter out = response.getWriter();
			out.print(list);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
