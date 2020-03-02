package com.ynenginemap.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOrLong {
	//输入String类型的2001-03-15 15:37日期转换为long类型的毫秒数
	public static long stringToLong(String date){
		 /**  
	     * 先用SimpleDateFormat.parse() 方法将日期字符串转化为Date格式  
	     * 通过Date.getTime()方法，将其转化为毫秒数  
	     */  
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//24小时制  
	//  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");//12小时制  
	    long time=0;
		try {
			time = simpleDateFormat.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    return time;
	}
	 
    //输入long类型的毫秒数，转换为String类型的时间2001-03-15 15:37
	public static String longTOString(long time){
		/**  
	     * 直接用SimpleDateFormat格式化 Date对象，即可得到相应格式的日期 字符串。  
	     */ 
	    Date date = new Date();  
	    date.setTime(time);  
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//24小时制  
	    return simpleDateFormat.format(date);  
	}
}
