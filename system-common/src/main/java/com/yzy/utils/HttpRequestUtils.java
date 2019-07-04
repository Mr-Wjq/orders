package com.yzy.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpRequestUtils {
	 private static final Logger log = LoggerFactory.getLogger(HttpRequestUtils.class);
	public static Boolean httpGet(String url) {
    	try{
    		// 根据地址获取请求
    		HttpGet httpGet  = new HttpGet(url);//这里发送GET请求
    		RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000).build();
    		httpGet.setConfig(requestConfig);
    		//存放参数
    		/*List <BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	           params.add(new BasicNameValuePair("mes",mes)); 
	           params.add(new BasicNameValuePair("situationAwarenessUrl",taiShiURL)); 
	           httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));*/
    		// 获取当前客户端对象
    		CloseableHttpClient httpClient = HttpClients.createDefault();
    		// 通过请求对象获取响应对象
    		HttpResponse httpResponse = httpClient.execute(httpGet);
    		int statusCode = httpResponse.getStatusLine().getStatusCode();
    		// 判断网络连接状态码是否正常(0--200都数正常)
    		if (statusCode == HttpStatus.SC_OK) {
    			return true;
    		}else {
    			return false;
    		}
    	}catch (Exception e){
    		log.error("HttpRequestUtils类异常",e);
    		return false;
    	}
	}
	
}
