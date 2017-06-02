package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.lz.art.util.HttpUtil;

/**
 * 测试查询类的接口
 * @author gionee
 *
 */
public class GetSearchResultApiTest {
	
	//@Test
	public void testInsetNav(){
		String api = "http://localhost:8080/search-api/inset/insetNav";
		//api = "http://t-baserom.gionee.com/search-api/inset/insetNav";   //测试环境
		api = "http://globalsearchonline.gionee.com/search-api/inset/insetNav"; //正式环境
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("site_url", "http://m.appgionee.com/cp_info.php?fr=gn_search_h5&softid=152277");
		map.put("site_name", "头条视频");
		map.put("sort_id", "2");
		map.put("icon_url", "http://baseromcdn.gionee.com/image/search/icon_thirdparty/fD0GlAKB3BbDdmvu1493889053.png");
		map.put("source", "人工测试");
		String result = HttpUtil.sendPost(api, map, "utf-8");
		System.out.println(result);
	}
	
	//@Test
	public void testRecommendOfAppShop(){
		String api = "http://localhost:8080/search-api/search/appshop/recommend";
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("TAG", "GN_GET_APP_LIST");
		map.put("TYPE", "5");
		map.put("IMEI", "00000000000000");
		map.put("MODEL", "GIONEE GN9011");
		map.put("SYSVS", "amigo3.7.02");
		map.put("ANDROID", "23");
		map.put("NETTYPE", "wifi");
		map.put("CHL", "global_search");
		map.put("INDEX_START", "0");
		map.put("INDEX_SIZE", "10");
		String result = HttpUtil.sendPost(api, map, "utf-8");
		System.out.println(result);
	}
	
	//@Test
	public void testAppshopRecommend() throws IOException{
		String api = "http://localhost:8080/search-api/search/appshop/recommend";
		String body="";
		
		/*
		CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(api);
        
        //拼接参数
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("TAG", "GN_GET_APP_LIST"));
        nvps.add(new BasicNameValuePair("CHL", "AMIGO_SEARCH"));
        nvps.add(new BasicNameValuePair("IMEI", "00000000000000"));
        nvps.add(new BasicNameValuePair("MODEL", "GIONEE GN9011"));
        nvps.add(new BasicNameValuePair("SYSVS", "amigo3.7.02"));
        nvps.add(new BasicNameValuePair("ANDROID", "23"));
        nvps.add(new BasicNameValuePair("NETTYPE", "wifi"));
        nvps.add(new BasicNameValuePair("TYPE", "5"));
        nvps.add(new BasicNameValuePair("INDEX_START", "0"));
        nvps.add(new BasicNameValuePair("INDEX_SIZE", "10"));
        //设置参数到请求对象中  
        method.setEntity(new UrlEncodedFormEntity(nvps, "utf-8")); 
        
        //设置header信息  
        //指定报文头【Content-type】、【User-Agent】  
        method.setHeader("Content-type", "application/x-www-form-urlencoded");  
        method.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        
        CloseableHttpResponse response2 = httpclient.execute(method);
        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            if(entity2!=null){
            	//按指定编码转换结果实体为String类型  
                body = EntityUtils.toString(entity2, "UTF-8");
            }
            
	        System.out.println(body);
            
            //消耗掉response
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
        */
        
		DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost(api);
        HttpEntity httpEntity;
        StringEntity entity;
        HttpResponse response = null;
        httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        JSONObject jsonParam = new JSONObject();
        
        try {
            jsonParam.put("TAG", "GN_GET_APP_LIST");// TAG
            jsonParam.put("CHL", "AMIGO_SEARCH");// CHL
            jsonParam.put("IMEI", "00000000000000");// IMEI
            jsonParam.put("MODEL", "GIONEE GN9011");// 机型
            jsonParam.put("SYSVS", "amigo3.7.02");// ROM版本
            jsonParam.put("ANDROID", "23");// Android 版本
            jsonParam.put("NETTYPE", "wifi");// 网络类型
            jsonParam.put("TYPE", "5");// 网络类型
            jsonParam.put("INDEX_START", "0");// 网络类型
            jsonParam.put("INDEX_SIZE", "10");// 网络类型

            //TODO DAIZM
            entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            method.setEntity(entity);
            response = httpClient.execute(method);
            StatusLine stateLine = response.getStatusLine();
            if (stateLine.getStatusCode() == HttpStatus.SC_OK) {
                httpEntity = response.getEntity();
                body = EntityUtils.toString(entity, "UTF-8");
                System.out.println(body);
            } else {
                String message = "connection exception, stateLine: " + stateLine + "  url: " + api;
            }
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("TAG", "GN_GET_APP_LIST");
//		map.put("TYPE", "5");
//		map.put("IMEI", "00000000000000");
//		map.put("MODEL", "GIONEE GN9011");
//		map.put("SYSVS", "amigo3.7.02");
//		map.put("ANDROID", "23");
//		map.put("NETTYPE", "wifi");
//		map.put("CHL", "global_search");
//		map.put("INDEX_START", "0");
//		map.put("INDEX_SIZE", "10");
//		String result = HttpUtil.sendPost(api, map, "utf-8");
//		System.out.println(result);
	}
	
	@Test
	public void testAppshopHotkeylist(){
		String api = "http://localhost:8080/search-api/search/appshop/hotkeylist";
		//api = "http://t-baserom.gionee.com/search-api/search/appshop/hotkeylist";
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("TAG", "GN_SERACH_HOT_KEY_LIST");
		map.put("TYPE", "5");
		map.put("IMEI", "00000000000000");
		map.put("MODEL", "GIONEE GN9011");
		map.put("SYSVS", "amigo3.7.02");
		map.put("ANDROID", "23");
		map.put("NETTYPE", "wifi");
		map.put("CHL", "global_search");
		map.put("INDEX_START", "0");
		map.put("INDEX_SIZE", "10");
		String result = HttpUtil.sendPost(api, map, "utf-8");
		System.out.println(result);
	}

}
