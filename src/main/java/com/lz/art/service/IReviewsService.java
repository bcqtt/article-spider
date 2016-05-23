package com.lz.art.service;

import java.util.Date;
import java.util.List;

import com.lz.art.pojo.Reviews;

public interface IReviewsService {
	
	public List<Reviews> pageOfReviews(String searchText, String date);

	public void addReview(Reviews review);

	public boolean checkExist(String reviewer, Date date);

}
