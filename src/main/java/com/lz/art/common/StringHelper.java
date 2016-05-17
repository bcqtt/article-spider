package com.lz.art.common;

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

}
