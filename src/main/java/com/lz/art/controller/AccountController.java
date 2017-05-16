package com.lz.art.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lz.art.service.IAccountService;

import net.minidev.json.JSONObject;

@Controller  
@RequestMapping("acc")
public class AccountController {
	private final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	private IAccountService accountService;
	
	@RequestMapping(value="/login/{username}/{password}",method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void login(HttpServletResponse response,
			@PathVariable String username,@PathVariable String password){
		boolean isOk = accountService.login(username, password);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {
		   out = response.getWriter();  
	       out.print(isOk);
	       log.info("输出-->" + isOk);  
		} catch (Exception e) {
			e.printStackTrace();  
		}finally {  
	        if (out != null) {  
	            out.close();  
	        }  
		}
	}
	
	
	@RequestMapping(value="/login2")
	public void login2(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		boolean isOk = accountService.login(username, password);
		outputResult(isOk, request, response);
	}
	
	@RequestMapping(value="/register")
	public void register(HttpServletRequest request,HttpServletResponse response) throws InterruptedException{
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		boolean isOk = accountService.register(username, password);
		Thread.sleep(2000);
		outputResult(isOk, request, response);
	}
	
	private void outputResult(boolean isOk,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=utf-8");  
		PrintWriter out = null;  
		try {
			out = response.getWriter();  
			out.print(isOk);
			log.info("输出-->" + isOk);  
		} catch (Exception e) {
			e.printStackTrace();  
		}finally {  
			if (out != null) {  
				out.close();  
			}  
		}
	}

}
