package com.lz.art.crawler;

import java.util.Date;
import java.util.List;

import com.lz.art.common.ApplicationHelper;
import com.lz.art.common.StringHelper;
import com.lz.art.pojo.Crawler;
import com.lz.art.pojo.Reviews;
import com.lz.art.service.IReviewsService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class AmazonReviewProcessor implements PageProcessor {
	public Crawler cra;
	
	private IReviewsService reviewsService;
	
	public AmazonReviewProcessor(Crawler cra) {
		this.cra = cra;
	}
	
	@Override
	public void process(Page page) {
		reviewsService = (IReviewsService) ApplicationHelper.applicationContext.getBean("reviewsService");
		
		//xpath('//ul[@class='a-pagination']')
		List<String> urls = page.getHtml().xpath(this.cra.getTargetUrlTemplate()).links().all();
		page.addTargetRequests(urls);
		
		//xpath=('//span[@class='a-size-base review-text']/text()')
		List<String> reviews = page.getHtml().xpath(this.cra.getContentGrabExpression()).all();
		
		//xpath=//a[@class='a-size-base a-link-normal author']/text()
		List<String> reviewers = page.getHtml().xpath(this.cra.getAuthorGrabExpression()).all();
		
		//xpath=//span[@class='a-size-base a-color-secondary review-date']/text()
		List<String> dateStr = page.getHtml().xpath(this.cra.getDateGrabExpression()).all();
		
		//xpath=//span[@class='a-icon-alt']/text()
		List<String> starts = page.getHtml().xpath(this.cra.getStartsGrabExpression()).all();
		for(int i=0;i<reviews.size();i++){
			//System.out.println("评论人：" + reviewers.get(i) +"--评论内容: " + reviews.get(i));
			
			String reviewer = reviewers.get(i);
			Date date = StringHelper.strToDate(dateStr.get(i).substring(3));
			
			if(reviewsService.checkExist(reviewer,date)){
				continue;
			}
			
			Reviews review = new Reviews();
			review.setId(StringHelper.getUUID());
			review.setReviewer(reviewer);
			review.setReview(reviews.get(i));
			review.setDate(date);
			String start = starts.get(i);
			Float start_ = (float) 0.0 ;
			if(!start.equals("|") && !start.equals("Your rating")){
				start_= Float.parseFloat(start.substring(0, 2));
			}
			review.setStarts(start_);
			
			reviewsService.addReview(review);
		}
		
	}

	@Override
	public Site getSite() {
		Site site = Site.me()
			.setDomain(this.cra.getScope())    //www.amazon.com
			.setRetryTimes(3).setSleepTime(1000).setTimeOut(60000);
		return site;
	}

}
