package com.lz.art.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.art.dao.ReviewsMapper;
import com.lz.art.pojo.Reviews;
import com.lz.art.service.IReviewsService;

@Service("reviewsService")
public class ReviewsServiceImpl implements IReviewsService {

	@Resource
	private ReviewsMapper reviewsMapper ;
	

	public List<Reviews> pageOfReviews() {
		List<Reviews> list = reviewsMapper.selectByExample(null);
		//String jsonStr =  JSONArray.toJSONString(list);  
		return list;
	}

}
