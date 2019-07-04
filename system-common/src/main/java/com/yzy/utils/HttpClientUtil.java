package com.yzy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get访问异常URL："+url,e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}
	
	/**
	 * 说        明：测试URL能否访问
	 * 作        者： 王俊强
	 * 修  改  人：
	 * 参数说明：
	 * @param
	 */
	public static int checkUrl(String url,HttpServletRequest request) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		int statusCode = 404;
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			URI uri = builder.build();
			RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000).build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setConfig(requestConfig);
			//设置header
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()){//以此取出头信息
				  String headerName=(String)headerNames.nextElement();
				  String headerValue=request.getHeader(headerName);//取出头信息内容
				  httpGet.setHeader(headerName,headerValue);
			 }
			// 执行请求
			response = httpclient.execute(httpGet);
			// 获取返回状态
		    statusCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get访问异常URL："+url,e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return statusCode;
	}	
	
	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("post访问异常URL："+url,e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}
	
	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("post访问异常URL："+url,e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}
	
    /**
     * 说        明：发送 http post 请求，参数以form表单键值对的形式提交。 
     * 作        者： 王俊强
     * 修  改  人：
     * 参数说明：
     * @param url 地址
     * @param params 参数
     * @param headers 请求头
     * @param encode 编码 
     */
    public static HttpResponse httpPostForm(String url,Map<String,String> params, Map<String,String> headers,String encode){  
        HttpResponse response = new HttpResponse();
        if(encode == null){  
            encode = "utf-8";  
        }  
        //HttpClients.createDefault()等价于 HttpClientBuilder.create().build();   
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();   
        HttpPost httpost = new HttpPost(url);  
        
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        //组织请求参数  
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();  
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();  
            for(String key : keySet) {  
                paramList.add(new BasicNameValuePair(key, params.get(key)));  
            }  
        }
        try {  
            httpost.setEntity(new UrlEncodedFormEntity(paramList, encode));  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        }  
        String content = null;  
        CloseableHttpResponse  httpResponse = null;  
        try {  
            httpResponse = closeableHttpClient.execute(httpost);  
            HttpEntity entity = httpResponse.getEntity();  
            content = EntityUtils.toString(entity, encode);  
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("post访问异常URL："+url,e);
        }finally{  
            try {  
                httpResponse.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        try {  //关闭连接、释放资源  
            closeableHttpClient.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }    
        return response;  
    }
}
