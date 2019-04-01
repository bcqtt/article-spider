package com.lz.study.https;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Test {
	
	public static void main(String[] args) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
//		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
//		converterList.remove(1);
//		restTemplate.setMessageConverters(converterList);
		HttpHeaders headers = new HttpHeaders();
		headers.add("AAAAAAA", "AAAAAAA");
		
		String httpBody = null;
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpBody,headers);
		StringBuffer stringBuf = new StringBuffer("https://www.2345.com/");
		URI uri = URI.create(stringBuf.toString());
		
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
		
		if(response.hasBody()) {
			System.out.println("OK");
		}
		
		System.out.println("================================================");
		CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(clientHttpRequestFactory);
        String result = restTemplate.getForObject("https://www.2345.com/",String.class);
        System.out.println(result);
		
	}

}
