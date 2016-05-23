package com.lz.art.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public List<Reviews> reviewsList(String searchText,HttpServletRequest request){
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Map<String,String> map= new HashMap<String,String>();
		map.put("searchText", searchText);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Reviews> list = reviewsService.pageOfReviews(map);
		return list;
	}
	

}
