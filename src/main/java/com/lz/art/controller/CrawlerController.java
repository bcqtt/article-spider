package com.lz.art.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lz.art.common.DataNotyOptions;
import com.lz.art.pojo.Crawler;
import com.lz.art.service.ICrawlerService;

@Controller  
@RequestMapping("crawler")
public class CrawlerController {
	
	@Resource
	private ICrawlerService crawlerService;
	
	@RequestMapping(value="/crawlerView")
	public ModelAndView crawlerView(){
		ModelAndView model = new ModelAndView();
		model.setViewName("WEB-INF/jsp/crawler/crawler-list");
		return model;
	}
	
	@RequestMapping(value="/crawlerList")
	@ResponseBody
	public List<Crawler> crawlerList(){
		List<Crawler> list = crawlerService.pageOfCrawler();
		return list;
	}
	
	@RequestMapping(value="/saveCrawler")
	@ResponseBody
	public DataNotyOptions saveCrawler(Crawler crawler,String opt){
		DataNotyOptions dno = crawlerService.saveCrawler(crawler,opt);
		return dno;
	}
	
	@RequestMapping(value="/editCrawler")
	@ResponseBody
	public Crawler editCrawler(int id){
		Crawler crawler = crawlerService.findById(id);
		return crawler;
	}
	
	
	

}
