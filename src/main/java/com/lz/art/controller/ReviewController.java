package com.lz.art.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lz.art.pojo.Reviews;
import com.lz.art.service.IReviewsService;

@Controller  
@RequestMapping("reviews")
public class ReviewController {
	
	@Resource
	private IReviewsService reviewsService;
	
	@RequestMapping(value="/reviewsView")
	public ModelAndView reviewsView(){
		ModelAndView model = new ModelAndView();
		model.setViewName("WEB-INF/jsp/article/reviews");
		return model;
	}
	
	@RequestMapping(value="/reviewsList")
	@ResponseBody
	public List<Reviews> reviewsList(String searchText,String date){
		List<Reviews> list = reviewsService.pageOfReviews(searchText,date);
		return list;
	}
	

}
