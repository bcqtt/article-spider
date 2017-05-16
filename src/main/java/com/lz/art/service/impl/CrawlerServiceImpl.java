package com.lz.art.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.art.common.SpiderMap;
import com.lz.art.common.DataNotyOptions;
import com.lz.art.common.StringHelper;
import com.lz.art.dao.CrawlerMapper;
import com.lz.art.pojo.Crawler;
import com.lz.art.service.ICrawlerService;
import com.lz.art.thread.StartAmazonCrawlerThread;

@Service("crawlerService")
public class CrawlerServiceImpl implements ICrawlerService {

	@Resource
	private CrawlerMapper crawlerMapper ;
	

	public List<Crawler> pageOfCrawler() {
		List<Crawler> list = crawlerMapper.selectByExample(null);
		return list;
	}


	@Override
	public DataNotyOptions saveCrawler(Crawler crawler,String opt) {
		DataNotyOptions dno = null;
		int n = 0;
		if(opt.equals("add")){
			crawler.setId(StringHelper.getUUID());
			n = crawlerMapper.insert(crawler);
		}else if(opt.equals("edit")){
			n = crawlerMapper.updateByPrimaryKeySelective(crawler);
		}
		if(n>0){
			dno = new DataNotyOptions("success");
		}else{
			dno = new DataNotyOptions("error");
		}
		return dno;
	}


	@Override
	public Crawler findById(String id) {
		return crawlerMapper.selectByPrimaryKey(id);
	}


	@Override
	public DataNotyOptions startCrawler(Crawler crawler) {
		try {
			new StartAmazonCrawlerThread(crawler).start();
		} catch (Exception e) {
			e.printStackTrace();
			return new DataNotyOptions("error");
		}
		crawler.setStatus(1);
		crawlerMapper.updateByPrimaryKeySelective(crawler);
		return new DataNotyOptions("success");
	}


	@Override
	public DataNotyOptions stopCrawler(String id) {
		SpiderMap.getInstance().get(id).stop();
		return new DataNotyOptions("success");
	}

}
