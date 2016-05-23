package com.lz.art.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  
@RequestMapping("content")
public class ContentController {

	@RequestMapping(value="/toToContent")
	public ModelAndView goToContent(){
		ModelAndView model = new ModelAndView();
		model.addObject("object", "地铁每周统计");
		model.setViewName("WEB-INF/jsp/content");
		return model;
	}
	
	@RequestMapping(value="/toToContent2")
	public ModelAndView goToContent2(){
		ModelAndView model = new ModelAndView();
		model.addObject("object", "地铁每周统计");
		model.setViewName("WEB-INF/jsp/content3");
		return model;
	}
	
}
