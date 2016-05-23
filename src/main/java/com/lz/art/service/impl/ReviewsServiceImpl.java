package com.lz.art.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.art.dao.ReviewsMapper;
import com.lz.art.pojo.Reviews;
import com.lz.art.pojo.ReviewsExample;
import com.lz.art.pojo.ReviewsExample.Criteria;
import com.lz.art.service.IReviewsService;

@Service("reviewsService")
public class ReviewsServiceImpl implements IReviewsService {

	@Resource
	private ReviewsMapper reviewsMapper ;
	

	public List<Reviews> pageOfReviews(Map<String,String> map) {
		//List<Reviews> list = reviewsMapper.selectByExample(null);
		List<Reviews> list = reviewsMapper.selectByFullText(map);
		return list;
	}


	@Override
	public void addReview(Reviews review) {
		reviewsMapper.insert(review);
	}


	@Override
	public boolean checkExist(String reviewer, Date date) {
		ReviewsExample example = new ReviewsExample();
		Criteria criteria = example.createCriteria();
		criteria.andReviewerEqualTo(reviewer);
		criteria.andDateEqualTo(date);
		List<Reviews> list = reviewsMapper.selectByExample(example);
		
		return list.size()>0?true:false;
	}

}
