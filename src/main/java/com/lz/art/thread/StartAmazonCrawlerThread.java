package com.lz.art.thread;

import org.apache.log4j.Logger;

import com.lz.art.common.SpiderMap;
import com.lz.art.crawler.AmazonReviewProcessor;
import com.lz.art.pojo.Crawler;

import us.codecraft.webmagic.Spider;

public class StartAmazonCrawlerThread extends Thread {
	final Logger log = Logger.getLogger(StartAmazonCrawlerThread.class);
	
	private Crawler crawler;
	
	public StartAmazonCrawlerThread(Crawler crawler){
		this.crawler = crawler;
	}
	
	@Override
    public void run() {
		long t1 = System.currentTimeMillis();
		Spider spider = Spider.create(new AmazonReviewProcessor(crawler));
		SpiderMap.getInstance().put(crawler.getId(), spider);
		
		spider.addUrl(crawler.getStartUrl())
			//开启5个线程抓取
			.thread(5)
			//启动爬虫
			.run();
		long t2 = System.currentTimeMillis();
		log.info("耗时：  "+ (t2-t1) );
    }
	
}
