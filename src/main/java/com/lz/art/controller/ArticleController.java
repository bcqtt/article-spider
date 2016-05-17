package com.lz.art.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  
@RequestMapping("article")
public class ArticleController {

	@RequestMapping(value="/articleList")
	public ModelAndView articleList(HttpServletRequest request){
		String nav = request.getParameter("nav");
		ModelAndView model = new ModelAndView();
		model.addObject("nav",nav);
		model.setViewName("WEB-INF/jsp/article/article-list");
		return model;
	}
	
	
	
}
