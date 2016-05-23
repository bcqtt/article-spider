package com.lz.art.common;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class StringHelper {
	
	/**
	 * 获取记录id
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "");
		return str;
	}
	
	public static Date strToDate(String str) {
		@SuppressWarnings("deprecation")
		long time = Date.parse(str);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(strToDate(new String("on May 8, 2016").substring(3)));
	}

}
