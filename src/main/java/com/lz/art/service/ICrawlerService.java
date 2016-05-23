package com.lz.art.service;

import java.util.List;

import com.lz.art.common.DataNotyOptions;
import com.lz.art.pojo.Crawler;

public interface ICrawlerService {
	
	public List<Crawler> pageOfCrawler();

	public DataNotyOptions saveCrawler(Crawler crawler, String opt);

	public Crawler findById(String id);

	public DataNotyOptions startCrawler(Crawler crawler);

	public DataNotyOptions stopCrawler(String id);

}
