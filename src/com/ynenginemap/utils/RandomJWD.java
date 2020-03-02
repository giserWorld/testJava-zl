package com.ynenginemap.utils;

import java.math.BigDecimal;
/**
 * 在矩形内随机生成经纬度
 * @author hcy
 *
 */
public class RandomJWD {

	/**
	    * @Title: randomLonLat
	    * @Description: 在矩形内随机生成经纬度
	    * @param MinLon：最新经度  MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
	    * @return
	    * @throws
	    */
	  public static String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat, String type) {
//	    Random random = new Random();
	    BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
	    String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
	    System.out.println(lon);
	    db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
	    String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
	    System.out.println(lat);
	    if (type.equals("Lon")) {
	      return lon;
	    } else if(type.equals("Lat")){
	      return lat;
	    }else{
	    	return lon+","+lat;	
	    }
	  }
	  
	  public static void main(String[] args) {
		  String jwd = randomLonLat(121.510543,121.534576,28.57509,28.61699,"");
		  System.out.println(jwd);
	  }
		     
}
