package com.lz.art.common;

import java.util.HashMap;
import java.util.Map;

import us.codecraft.webmagic.Spider;

public class SpiderMap extends HashMap<String,Spider>{
	private static final long serialVersionUID = 1L;
	private static SpiderMap instance;
	
	private SpiderMap (){}
	
	public static synchronized SpiderMap getInstance() {
		if (instance == null) {
			instance = new SpiderMap();
		}
		return instance;
	}

}
