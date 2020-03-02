package com.ynenginemap.utils;


import io.netty.handler.codec.http.HttpMethod;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 根据地址获取请求 https get请求
 * @author ysg
 *
 */
public class HttpUtil {
	public static String getUrl(String url){
		System.out.println("url:="+url);
		String result = null;
		try {
			//根据地址获取请求
			HttpGet resquest = new HttpGet(url);
			//获取当前客户端对象
//			HttpClient httpClient = new DefaultHttpClient();
			CloseableHttpClient httpclient = HttpClients.createDefault();  
			//通过请求对象获取响应对象
//			HttpResponse response =  httpClient.execute(resquest);
			
			 CloseableHttpResponse response = httpclient.execute(resquest);  
			
			//判断网络连接状态码是否正常（0--200都是正常）
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result = EntityUtils.toString(response.getEntity());
				result = new String(result.getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	
	public static void main(String[] args) {
		String jsonObject = getUrl("http://geo.tvupack.com//geoservice//Out//getGPSBypeeridAndTimestampServlet?peerid=bf86118e38f90a72");
		System.out.println(jsonObject);
	}
	
	public static String getUrlBaidu(String url){
		System.out.println("url:="+url);
		String result = null;
		try {
			//根据地址获取请求
			HttpGet resquest = new HttpGet(url);
			//获取当前客户端对象
//			HttpClient httpClient = new DefaultHttpClient();
			CloseableHttpClient httpclient = HttpClients.createDefault();  
			//通过请求对象获取响应对象
//			HttpResponse response =  httpClient.execute(resquest);
			
			 CloseableHttpResponse response = httpclient.execute(resquest);  
		
		
			
			
			//判断网络连接状态码是否正常（0--200都是正常）
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result = EntityUtils.toString(response.getEntity());
//				result = new String(result.getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
		
	}
}
