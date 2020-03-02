package com.ynenginemap.utils;

import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;



public class GetPoint {

	public static void main(String[] args) {
//		for(int i = 0;i<100;i++){
//			System.out.println(i);
//			getPoint();		云南省保山市昌宁县更戛乡木瓜树村委会洼子寨村民小组	
//		}
//云南省保山市昌宁县更戛乡大沙坝村委会街子村民小组  街子村民小组
		
		//云南省保山市昌宁县更戛乡立达村委会上立达村民小组  上立达村民小组   下立达村民小组

		
		
//		getPoint("云南省保山市昌宁县更戛乡米河村委会洼子寨村民小组");
		
		//22.82751,102.61687  25.094569,102.747125
		String[] str = getAdress("28.149969444444444,102.74441666666667");
		System.out.println(str[0]+";"+str[1]+";"+str[2]+";"+str[3]+";"+str[4]);
		System.out.println("地址解析结果：" + str[0]+str[1]+str[4]);
		
//		getAdress_BaiDu("25.094569,102.747125");
		
	}
	
	/**
	 * 百度地图地址解析 -- 通过中文地址获取经纬度
	 * @param address
	 * @return
	 */
	  public static Map<String, Double> getPoint(String address){  
		  Map<String, Double> position = new HashMap<>();
	         try {    
	                String sCurrentLine;    
	                String sTotalString;    
	                sCurrentLine = "";    
	                sTotalString = "";    
	                java.io.InputStream l_urlStream;    
	                  
	                java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=quN8B3ZrXb0YEtnAoSrNT3K6nyBrlhS1&callback=showLocation");    
	                java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();    
	                l_connection.connect();    
	                l_urlStream = l_connection.getInputStream();    
	                java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));     
	                String str=l_reader.readLine();  
	                //用经度分割返回的网页代码  
	                String s=","+"\""+"lat"+"\""+":";  
	                String strs[]=str.split(s, 2);  
	                String s1="\""+"lng"+"\""+":";  
	               String a[]=strs[0].split(s1, 2);  
	               System.out.println(a[1]);//经度
//	               shop.setLng(a[1]);  
	               s1="}"+","+"\"";  
	              String a1[]=strs[1].split(s1, 2);  
	              System.out.println(a1[0]);//纬度
	              /*将百度坐标转换为gps坐标*/
	              position =  Coordinate_Converter.ToGPS(Double.valueOf(a[1]),Double.valueOf(a1[0]));
	              System.out.println("lon:"+position.get("lon"));
	              System.out.println("lat:"+position.get("lat"));
//	               shop.setLat(a1[0]);  
	            } catch (Exception e) { 
	            	System.out.println(e.getMessage());
	            	position = null;
	                return position;
	            }    
	          return position;
	    }  
	  
	  
	  /**
	   * 调用腾讯地图地址逆解析--通过经纬度获取中文地址
	   * @param location
	   * @return
	   */
		public static String[] getAdress(String location){
			
			String[] s = null;
			
//			String get_access_toke_url = "http://api.map.baidu.com/geocoder/v2/?"
//					+ "ak=quN8B3ZrXb0YEtnAoSrNT3K6nyBrlhS1&"
//					+ "location="+location+"&"
//					+ "output=json&pois=1";
			
			String get_access_toke_url = "http://apis.map.qq.com/ws/geocoder/v1/?"
					+ "location="+location+"&"
					+ "key=SNLBZ-PSE3P-PK6DY-VDWON-5C65S-XHBUB&"
					+ "get_poi=1";
			
			try {
				
				String json = HttpUtil.getUrlBaidu(get_access_toke_url);//调用请求获取百度解析的到的地理位置返回数据
				
				JSONObject jsonObtect = JSONObject.fromObject(json);//将微信返回数据转换为json对象	
				System.out.println("1:"+jsonObtect);
				System.out.println("2:"+jsonObtect.getString("result"));
				JSONObject jsonObtect1 = JSONObject.fromObject(jsonObtect.getString("result"));
				System.out.println("3:"+jsonObtect1.getString("address_component"));
				JSONObject jsonObtect2 = JSONObject.fromObject(jsonObtect1.getString("address_component"));
				String province = jsonObtect2.getString("province");
				System.out.println("省份："+jsonObtect2.getString("province"));
				String city = jsonObtect2.getString("city");
				System.out.println("州市："+jsonObtect2.getString("city"));
				
				String district = jsonObtect2.getString("district");
				
				System.out.println("县（区）："+jsonObtect2.getString("district"));
				
				String district_adcode = JSONObject.fromObject(jsonObtect1.getString("ad_info")).getString("adcode");
				System.out.println("县区_code："+district_adcode);
				
				System.out.println("4:"+jsonObtect1.getString("formatted_addresses"));
				JSONObject jsonObtect3 = JSONObject.fromObject(jsonObtect1.getString("formatted_addresses"));
				System.out.println("详细地址：" + jsonObtect3.getString("recommend"));
				String formatted_addresses = jsonObtect3.getString("recommend");
//		    		String access_token = jsonObtect.getString("access_token");//获取acc
				
				if(null != province && !"".equals(province) && null != city && !"".equals(city) 
						&& null != district && !"".equals(district) && null != district_adcode && !"".equals(district_adcode)
						&& null != formatted_addresses && !"".equals(formatted_addresses)){
					String[] str = {province,city,district,district_adcode,formatted_addresses};
					s = str;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		    		return s;
		}
		
		 /**
		   * 调用百度地图地址逆解析--通过经纬度获取中文地址  有偏差
		   * @param location
		   * @return
		   */
		public static String[] getAdress_BaiDu(String location){
			
			String[] s = null;
			
			String get_access_toke_url = "http://api.map.baidu.com/geocoder/v2/?"
					+ "ak=quN8B3ZrXb0YEtnAoSrNT3K6nyBrlhS1&"
					+ "location="+location+"&"
					+ "output=json&pois=1";
					String json = HttpUtil.getUrlBaidu(get_access_toke_url);//调用请求获取百度解析的到的地理位置返回数据
					
		    		JSONObject jsonObtect = JSONObject.fromObject(json);//将微信返回数据转换为json对象	
		    		System.out.println("1:"+jsonObtect);
		    		System.out.println("2:"+jsonObtect.getString("result"));
		    		JSONObject jsonObtect1 = JSONObject.fromObject(jsonObtect.getString("result"));
		    		System.out.println("3:"+jsonObtect1.getString("addressComponent"));
		    		JSONObject jsonObtect2 = JSONObject.fromObject(jsonObtect1.getString("addressComponent"));
		    		String province = jsonObtect2.getString("province");
		    		System.out.println("省份："+jsonObtect2.getString("province"));
		    		String city = jsonObtect2.getString("city");
		    		System.out.println("州市："+jsonObtect2.getString("city"));
		    		System.out.println("县区_code："+jsonObtect2.getString("adcode"));
		    		String district = jsonObtect2.getString("district");
		    		String district_adcode = jsonObtect2.getString("adcode");
		    		System.out.println("县（区）："+jsonObtect2.getString("district"));
//		    		String access_token = jsonObtect.getString("access_token");//获取acc
		    		if(null != province && !"".equals(province) && null != city && !"".equals(city) && null != district && !"".equals(district) && null != district_adcode && !"".equals(district_adcode)){
		    			String[] str = {province,city,district,district_adcode};
		    			s = str;
		    		}
		    		return s;
		}
	  
}
