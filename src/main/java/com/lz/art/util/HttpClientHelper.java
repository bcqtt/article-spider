/*
 * 2012-7-21
 */
package com.gionee.mcenter.api.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gionee.mcenter.api.vo.GnAccount;

/**
 * @author Sidney Xu
 * @version 1.0.0
 */
public abstract class HttpClientHelper {

	private final static Log log = LogFactory.getLog(HttpClientHelper.class);

	private static PoolingClientConnectionManager manager;
	private static volatile HttpClient httpClient;

	protected final static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static HttpClient getCurrentHttpClient() {
		if (httpClient == null) {
			synchronized (HttpClientHelper.class) {
				if (httpClient == null) {
					manager = new PoolingClientConnectionManager();
					manager.setMaxTotal(1000);
					manager.setDefaultMaxPerRoute(500);
					httpClient = new DefaultHttpClient(manager);
					httpClient.getParams().setParameter("http.socket.timeout", 60000);
					httpClient.getParams().setParameter("http.connection.timeout", 15000);
					httpClient.getParams().setParameter("http.connection-manager.timeout", 1000 * 60 * 10);
				}
			}
		}
		return httpClient;
	}

	public static String invokeByGet(String url, String charset) {
		String result = null;
		HttpGet httpGet = new HttpGet(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			HttpResponse resp = hc.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, charset);
				EntityUtils.consume(he);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpGet.releaseConnection();
		}
		return result;
	}

	public static String invokeByPost(String url, List<NameValuePair> data, String respDefaultCharset) {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(data, respDefaultCharset));
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, respDefaultCharset);
				EntityUtils.consume(he);
			}
		} catch (java.io.EOFException ex) {
			// do nothing
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	public static String invokeByGet(String url) {
		return invokeByGet(url, "UTF-8");
	}

	public static <X> X invokeByGet(String url, String charset, JavaType type) {
		X result = null;
		HttpGet httpGet = new HttpGet(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			HttpResponse resp = hc.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				if (he.isStreaming()) {
					result = mapper.readValue(new InputStreamReader(he.getContent(), charset), type);
				}
				EntityUtils.consume(he);
			}
		} catch (java.io.EOFException ex) {
			// do nothing
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpGet.releaseConnection();
		}
		return result;
	}

	public static <X> X invokeByGet(String url, JavaType type) {
		return invokeByGet(url, "UTF-8", type);
	}

	public static String invokeByPostOnJson(String url, String json, String respDefaultCharset) {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, respDefaultCharset);
				EntityUtils.consume(he);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	public static String invokeByPostOnJson(String url, String json) {
		return invokeByPostOnJson(url, json, "UTF-8");
	}

	public static <X> X invokeByPostOnJson(String url, String json, String respDefaultCharset, JavaType type) {
		X result = null;
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				if (he.isStreaming()) {
					result = mapper.readValue(new InputStreamReader(he.getContent(), respDefaultCharset), type);
				}
				EntityUtils.consume(he);
			}
		} catch (java.io.EOFException ex) {
			// do nothing
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	public static <X> X invokeByPostOnJson(String url, String json, JavaType type) {
		return invokeByPostOnJson(url, json, "UTF-8", type);
	}

	public static String invokeByPostOnJson(String url, Object data, String respDefaultCharset) {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			ByteArrayEntity entity = new ByteArrayEntity(mapper.writeValueAsBytes(data), ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, respDefaultCharset);
				EntityUtils.consume(he);
			}
		} catch (java.io.EOFException ex) {
			// do nothing
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	public static String invokeByPostOnJson(String url, Object data) {
		return invokeByPostOnJson(url, data, "UTF-8");
	}

	public static <X> X invokeByPostOnJson(String url, Object data, String respDefaultCharset, JavaType type) {
		X result = null;
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			ByteArrayEntity entity = new ByteArrayEntity(mapper.writeValueAsBytes(data), ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				if (he.isStreaming()) {
					result = mapper.readValue(new InputStreamReader(he.getContent(), respDefaultCharset), type);
				}
				EntityUtils.consume(he);
			}
		} catch (java.io.EOFException ex) {
			// do nothing
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	public static <X> X invokeByPostOnJson(String url, Object data, JavaType type) {
		return invokeByPostOnJson(url, data, "UTF-8", type);
	}

	public static String invokeToken(String url, String token, String respDefaultCharset) {
		String result = null;
		String app_timestamp = ((Long) (System.currentTimeMillis() / 1000)).toString();
		String app_nonce = UUID.randomUUID().toString().substring(0, 8);
		String http_method = "POST";
		String sig = CryptoUtility.macSig(ValueConstants.ACCOUNT_HOST, ValueConstants.ACCOUNT_PORT, ValueConstants.TOKEN_APP_KEY, app_timestamp, app_nonce, http_method,
				ValueConstants.ACCOUNT_URL);
		HttpPost httpPost = new HttpPost(url);
		HttpClient hc = getCurrentHttpClient();
		try {
			StringEntity entity = new StringEntity(token, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			String mac = "MAC id=\"" + ValueConstants.TOKEN_APP_ID + "\",ts=\"" + app_timestamp + "\",nonce=\"" + app_nonce + "\",mac=\"" + sig + "\"";
			httpPost.setHeader("Authorization", mac);
			httpPost.setHeader("Content-Type", "application/json");

			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, respDefaultCharset);
				EntityUtils.consume(he);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static GnAccount invokeGnAccount(String gnToken, String rediFromUrl) throws JsonParseException, JsonMappingException, IOException {
		log.info("invokeGnAccount : the gnToken is :" + gnToken + ", and the redirect url is :" + rediFromUrl);
		String content = null;
		String app_timestamp = ((Long) (System.currentTimeMillis() / 1000)).toString();
		String app_nonce = UUID.randomUUID().toString().substring(0, 8);
		String http_method = "POST";
		String sig = CryptoUtility.macSig(ValueConstants.ACCOUNT_HOST, ValueConstants.ACCOUNT_PORT, ValueConstants.TOKEN_APP_KEY, app_timestamp, app_nonce, http_method,
				ValueConstants.TOKEN_URL);
		HttpPost httpPost = new HttpPost(ValueConstants.TOKEN_URL);
		HttpClient hc = getCurrentHttpClient();
		try {
			StringEntity entity = new StringEntity(gnToken, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			String mac = "MAC id=\"" + ValueConstants.TOKEN_APP_ID + "\",ts=\"" + app_timestamp + "\",nonce=\"" + app_nonce + "\",mac=\"" + sig + "\"";
			httpPost.setHeader("Authorization", mac);
			httpPost.setHeader("Content-Type", "application/json");

			HttpResponse resp = hc.execute(httpPost);
			log.info("resp.getStatusLine().getStatusCode():" + resp.getStatusLine().getStatusCode());
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				content = EntityUtils.toString(he, "UTF-8");
				EntityUtils.consume(he);
			}
		} finally {
			httpPost.releaseConnection();
		}
		log.info(ValueConstants.TOKEN_URL + " return:" + content);
		Map maps = mapper.readValue(content, Map.class);
		String userid = (String) maps.get("u");
		String tel = (String) maps.get("tn");
		String nickname = (String) maps.get("na");
		int gnType = maps.get("sty") != null ? (Integer) maps.get("sty") : 0;
		GnAccount account = new GnAccount();
		// account.setRefGnId(userid);
		// account.setNickname(nickname);
		// account.setTelephone(tel);
		// account.setGnType(gnType);
		return account;
	}

	/**
	 * 积分调用
	 */
	public static String scoreServer(StringEntity entity, String url) throws ClientProtocolException, IOException {

		String result = null;
		String app_timestamp = ((Long) (System.currentTimeMillis() / 1000)).toString();
		String app_nonce = UUID.randomUUID().toString().substring(0, 8);
		String http_method = "POST";
		String sig = CryptoUtility.macSig(ValueConstants.SCORE_SIGN_URL, ValueConstants.SCORE_SIGN_PORT, ValueConstants.TOKEN_APP_KEY, app_timestamp, app_nonce, http_method, url);
		String baseUrl = "https://" + ValueConstants.SCORE_SIGN_URL + ":" + ValueConstants.SCORE_SIGN_PORT;
		HttpPost httpPost = new HttpPost(baseUrl + url);
		HttpClient hc = new DefaultHttpClient();
		try {
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", "application/json");
			String mac = "MAC id=\"" + ValueConstants.TOKEN_APP_ID + "\",ts=\"" + app_timestamp + "\",nonce=\"" + app_nonce + "\",mac=\"" + sig + "\"";
			httpPost.setHeader("Authorization", mac);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, "UTF-8");
				EntityUtils.consume(he);
			}
		} finally {
			httpPost.releaseConnection();
		}

		return result;
	}

	/**
	 * 秘钥同步
	 */
	public static String passportSecret() throws ClientProtocolException, IOException {
		String result = null;
		String app_timestamp = ((Long) (System.currentTimeMillis() / 1000)).toString();
		String app_nonce = UUID.randomUUID().toString().substring(0, 8);
		String http_method = "POST";
		String sig = CryptoUtility.macSig(ValueConstants.MAC_SIGN_DOMAIN, ValueConstants.MAC_SIGN_PORT, ValueConstants.TOKEN_APP_KEY, app_timestamp, app_nonce, http_method,
				ValueConstants.MAC_SIGN_METHOD);
		HttpPost httpPost = new HttpPost(ValueConstants.MAC_SIGN_URL + ValueConstants.MAC_SIGN_METHOD);
		HttpClient hc = new DefaultHttpClient();
		try {
			httpPost.setHeader("Content-Type", "application/json");
			String mac = "MAC id=\"" + ValueConstants.TOKEN_APP_ID + "\",ts=\"" + app_timestamp + "\",nonce=\"" + app_nonce + "\",mac=\"" + sig + "\"";
			httpPost.setHeader("Authorization", mac);
			HttpResponse resp = hc.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = resp.getEntity();
				result = EntityUtils.toString(he, "UTF-8");
				EntityUtils.consume(he);
			}
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

}
