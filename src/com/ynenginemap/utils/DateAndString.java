package com.ynenginemap.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndString {
	/**
	 * 获取当前的日期 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public String dateToString(){
		//我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
	}
	public String dateToString1(){
		//我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms:us");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
	}
	/**
	 * 获取当前的日期
	 * @return yyyy-MM-dd
	 */
	public String getDate(){
		//我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        return createdate;
	}
	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd
	*/
	public String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	 }
	/**
	 * 日期格式转换String转Date
	 * @return
	 */
	public Date stringToDate(String times){
		Date date = new Date();
		 try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            date = sdf.parse(times);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
         return date;
	}
}
