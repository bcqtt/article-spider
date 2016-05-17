package com.lz.art.crawler;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class YueGuangBlogProcessor implements PageProcessor {
	
	public static final String URL_LIST = "http://www\\.williamlong\\.info/cat/\\?page=\\d+";

    public static final String URL_POST = "http://www\\.williamlong\\.info/archives/\\d+\\.html";

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
    		.setDomain("williamlong.info")
    		.setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
//        page.putField("author", page.getUrl().regex("http://lz881228\\.blog\\.163\\.com/.*").toString());
//        List<String> urls = page.getHtml().xpath("//div[@class='post pagebar']").links().regex(".*/cat/\\?page=.*").all();
//        for (String str : urls) {
//        	System.out.println("博文链接：" + str);
//        	
//        }
//        page.addTargetRequests(urls);
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        //page.putField("readme", page.getHtml().xpath("//div[@class='col1 fc05 pre']/tidyText()"));
//        // 部分三：从页面发现后续的url地址来抓取
//        //page.addTargetRequests(page.getHtml().links().regex("(http://lz881228\\.blog\\.163\\.com/)").all());
    	if(page.getUrl().regex(URL_LIST).match()){
    		page.addTargetRequests(page.getHtml().xpath("//div[@id='divMain']").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
    	}else{
    		System.out.println("标题：" + page.getHtml().xpath("//div[@id='divMain']/div/h1/text()").toString());
    	}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new YueGuangBlogProcessor())
                //从"https://github.com/code4craft"开始抓http://lz881228.blog.163.com/
                .addUrl("http://www.williamlong.info/cat/?page=1")
                //.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}
