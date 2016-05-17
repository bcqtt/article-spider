package com.lz.art.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.art.dao.UrlMapper;
import com.lz.art.pojo.Url;
import com.lz.art.service.IUrlservice;

@Service("urlService")
public class UrlServiceImpl implements IUrlservice {
	
	@Resource
	private UrlMapper urlMapper;

	@Override
	public void saveUrl(Url url) {
		urlMapper.insert(url);
	}

}
