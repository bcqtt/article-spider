package common;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.art.util.DateUtil;
import com.lz.art.util.HttpClientUtil;

public class ChargingTest {
	
	@Test
	public void testGetKwh() {
		
		String body = "{\r\n" + 
				"    \"head\": {\r\n" + 
				"        \"namespace\": \"power\",\r\n" + 
				"        \"method\": \"GET\"\r\n" + 
				"    },\r\n" + 
				"    \"payload\": {}\r\n" + 
				"}";
		
		String url = "http://172.26.83.37/localApi";
		
		try {
			long total = 0;
			for(int i=0;i<=100000;i++) {
				String result = HttpClientUtil.sendPostByJson(url, body);
				JSONObject obj = JSON.parseObject(result);
				JSONObject payload = obj.getJSONObject("payload");
				JSONObject power = payload.getJSONObject("power");
				String now = DateUtil.format(new Date());
				total += Long.parseLong(power.getString("power"));
				System.out.println(now + " : 本次查询：" + power.getString("power") + "   总用电量："  + total);
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
