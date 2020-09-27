package com.lz.art.test;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class UrlGenerate {
  
  private static String appid = "100002";
  
  private static String appkey = "!CDappkey#";  //rhappkey124!
  
  private static String sign(Map<String, Object> param) {
    param.remove("appkey");
    TreeMap<String, Object> sortedParam = new TreeMap<>(param);
    StringBuilder paramBuilder = new StringBuilder();
    if (sortedParam.size() > 0) {
      sortedParam.forEach((k, v) -> {
        if (v != null) {
          paramBuilder.append(k).append("=").append(v).append("&");
        }
        });
      paramBuilder.append("appkey=").append(appkey);
    } else {
      paramBuilder.append("appkey=").append(appkey);
    }
    //生成md5，再转换成大写
    return DigestUtils.md5Hex(paramBuilder.toString()).toUpperCase();
  }
  
  public static String generateSigUrl(String unionId, String accessToken, String body) {
    Map<String, Object> urlParams = new HashMap<>();
    urlParams.put("appid", appid);
    String timestamp = System.currentTimeMillis() + "";
    urlParams.put("timestamp", timestamp);
    String reqId = System.currentTimeMillis() + "";
    urlParams.put("req_id", System.currentTimeMillis() + "");
    if (unionId != null) {
      urlParams.put("unionId", unionId);
    }
    if (accessToken != null) {
      urlParams.put("accessToken", accessToken);
    }
    if (body != null) {
      urlParams.put("body", body);
    }
    
    
    String sig = sign(urlParams);
    String sigUrl = String.format("appid=%s&timestamp=%s&req_id=%s&sig=%s", appid, timestamp, reqId, sig);
    return sigUrl;
  }

  @Test
  public void testGetAuthCode() {  //1 获取验证码
	String unionId = null;
    String accessToken = null;
    String body = "{\"phone\":\"18589074983\",\"type\":1,\"country_code\":\"+086\",\"code_length\":6}";  //注册用
    body = "{\"phone\":\"18589074983\",\"type\":3,\"country_code\":\"+086\",\"code_length\":6}";         //登录用
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl);//appid=100002&timestamp=1552444782580&req_id=1552444782580&sig=D17D7B535145119CD4887E4C8FEC7193

  }
  
  @Test
  public void testCheckAuthCode() { //2 校验验证码
	String unionId = null;
    String accessToken = null;
//    {"phone":"18589074983","type":1,"code":"111111","country_code":"+086"}
    String body = "{\"phone\":\"18589074983\",\"type\":3,\"code\":\"393387\",\"country_code\":\"+086\"}";
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl);//appid=100002&timestamp=1552445086566&req_id=1552445086566&sig=1EBB6D0FD3478B18B349D0791FC60ECD

  }
  
  @Test
  public void testRegister() { //3 注册
	String unionId = null;
    String accessToken = null;
    //{"pwd":"c924218e42775f01dfb928a7d2083bb8","secret":"572473f99c34494c021d4a76564266e4","phone":"18589074983","country_code":"+086","name":"laizhiwen","gender":1,"avatar":"http://ip:port/pic/xxx.jpg","birthday":"1991-09-01"}
    String body = "{\"pwd\":\"c924218e42775f01dfb928a7d2083bb8\",\"secret\":\"9f075d79f8f7d8cc841c16a9f455c731\",\"phone\":\"18588402560\",\"country_code\":\"+086\",\"name\":\"shima\",\"gender\":1,\"avatar\":\"http://ip:port/pic/xxx.jpg\",\"birthday\":\"1991-09-01\"}";
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl);//appid=100002&timestamp=1552445086566&req_id=1552445086566&sig=1EBB6D0FD3478B18B349D0791FC60ECD

  }
  
  @Test
  public void testLoginUsePhoneNumberAndPassword() { //4 手机号+密码登录
	String unionId = null;
    String accessToken = null;
    //{"phone":"18589074983","country_code":"+086","pwd":"c924218e42775f01dfb928a7d2083bb8","os_type":"Android","app_version":"v0.5","os_version":"android4.3","hardware_version":"Huawei","app_uuid":"12345678909876543210123456789000"}
    String body = "{\"phone\":\"18589074983\",\"country_code\":\"+086\",\"pwd\":\"c924218e42775f01dfb928a7d2083bb8\",\"os_type\":\"Android\",\"app_version\":\"v0.5\",\"os_version\":\"android4.3\",\"hardware_version\":\"Huawei\",\"app_uuid\":\"12345678909876543210123456789000\"}";
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl); //appid=100002&timestamp=1552445929754&req_id=1552445929754&sig=0873B7248F982CE4C7532837172B6980

  }
  
  @Test
  public void testLoginUsePhoneNumberAndAuthCode() { //4 手机号+验证按登录
	String unionId = null;
    String accessToken = null;
    //{"phone":"18589074983","country_code":"+086","code":"111111","os_type":"Android","app_version":"v0.5","os_version":"android4.3","hardware_version":"Huawei","app_uuid":"1234567899876543210012345678900"}
    String body = "{\"phone\":\"17110000007\",\"country_code\":\"+086\",\"code\":\"111111\",\"os_type\":\"Android\",\"app_version\":\"v0.5\",\"os_version\":\"android4.3\",\"hardware_version\":\"Huawei\",\"app_uuid\":\"1234567899876543210012345678900\"}";
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl); //appid=100002&timestamp=1552446756309&req_id=1552446756309&sig=451255C36778D881C12D457BC3B4BECD

  }
  
  @Test
  public void testResetPassword() { //5 重置密码
	String unionId = null;
    String accessToken = null;
    //{\"pwd\": \"123456\",\"secret\": \"xxxx\"}
    String body = "{\"pwd\": \"123456\",\"secret\": \"B3D9E645AB076F1AE9EE218DD379E5C5\"}";
    String sigUrl = generateSigUrl(unionId, accessToken, body);
    System.out.println(sigUrl);

  }
  
//  public static void main(String[] args) {
//    
//    //2、获取手机验证码url生成
//    body = "{\"phone\":\"18589074983\",\"type\":1,\"country_code\":\"+086\",\"code_length\":6}";
//    sigUrl = generateSigUrl(unionId, accessToken, body);
//    System.out.println(sigUrl);
//    //3、注册url生成
//    body = "{\"pwd\":\"c924218e42775f01dfb928a7d2083bb8\",\"secret\":\"5cb12bd30d7f944efb6f35b3938119ca\",\"phone\":\"18218089328\",\"country_code\":\"+086\",\"name\":\"laizhiwen\",\"gender\":1,\"avatar\":\"http://ip:port/pic/xxx.jpg\",\"birthday\":\"1991-09-01\"}";
//    sigUrl = generateSigUrl(unionId, accessToken, body);
//    System.out.println(sigUrl);
//    String body = "{\"union_id\": [\"C4CB657F3169D0BA6F0577EDAC9F23A4\",\"49DEB536C24932D00280F795FD59AF4E\"]}";
//    String sigUrl = generateSigUrl(body);
//    System.out.println(sigUrl);
//    String filename = String.format("%s.jpg", "fdsfdhsifdsf");
//    System.out.println(filename);
//    String filename = String.format("%s_%s.jpg", "test", System.currentTimeMillis());
//    System.out.println(filename);
//      System.out.println(Integer.parseInt("00000"));
//    String[] strs = filename.split("[.]");
//    System.out.println(strs[1]);
//
//  }

}