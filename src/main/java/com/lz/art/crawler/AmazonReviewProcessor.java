package com.lz.art.crawler;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class AmazonReviewProcessor implements PageProcessor {
	
	public static final String URL_LIST = "http://www\\.amazon\\.com/Ooma-Telo-Free-Phone-Service/product-reviews/B00I4XMEYA/ref=cm_cr_arp_d_paging_btm_\\d+\\?ie=UTF8&showViewpoints=1&sortBy=helpful";

    private Site site = Site.me()
    		.setDomain("www.amazon.com")
    		.setRetryTimes(3).setSleepTime(1000).setTimeOut(60000);
	
	@Override
	public void process(Page page) {
		List<String> urls = page.getHtml().xpath("//ul[@class='a-pagination']").links().all();
		page.addTargetRequests(urls);
		
		List<String> reviews = page.getHtml().xpath("//span[@class='a-size-base review-text']/text()").all();
//		System.out.println("评论内容: " + page.getHtml().xpath("//span[@class='a-size-base review-text']").toString());
		for(String str:reviews){
			System.out.println("评论内容: " + str);
		}
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
    public static void main(String[] args) {

        Spider.create(new AmazonReviewProcessor())
                //从"https://github.com/code4craft"开始抓http://lz881228.blog.163.com/
                .addUrl("http://www.amazon.com/Ooma-Telo-Free-Phone-Service/product-reviews/B00I4XMEYA/ref=cm_cr_getr_d_paging_btm_1?ie=UTF8&showViewpoints=1&sortBy=helpful&pageNumber=1")
                //.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}
