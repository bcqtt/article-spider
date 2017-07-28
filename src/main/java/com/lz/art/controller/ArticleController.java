package com.lz.art.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lz.art.pojo.Article;

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
	
	@RequestMapping(value="/testjson")
	@ResponseBody
	public Map<String,List<Article>> testjson(HttpServletRequest request){
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		
		for(int i=0;i<5;i++){
			Article k = new Article();
			k.setTitle("标题1");
			list1.add(k);
		}
		
		for(int i=0;i<5;i++){
			Article k = new Article();
			k.setTitle("标题2");
			list2.add(k);
		}
		
		list1.addAll(list2);
		
		System.out.println("list1数据：" + JSON.toJSONString(list1));
		System.out.println("list1数据：" + JSON.toJSONString(list2));
		Map<String,List<Article>> map = new HashMap<>();
		map.put("list1", list1);
		map.put("list2", list2);
//		Demo demo = new Demo();
//		demo.setList1(list1);
//		demo.setList2(list2);
		
		return map;
	}
	
	class Demo {
		List<Article> list1;
		List<Article> list2;
		public List<Article> getList1() {
			return list1;
		}
		public void setList1(List<Article> list1) {
			this.list1 = list1;
		}
		public List<Article> getList2() {
			return list2;
		}
		public void setList2(List<Article> list2) {
			this.list2 = list2;
		}
		
	}
	
	
	
	
	
}
