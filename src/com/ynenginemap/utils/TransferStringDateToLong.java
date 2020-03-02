package com.ynenginemap.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferStringDateToLong {
	 /**     
	  * * String日期转换为Long     
	  * * @param formatDate("MM/dd/yyyy HH:mm:ss")     
	  * * @param date("12/31/2013 21:08:00")     
	  * * @return     
	  * * @throws ParseException     
	  * */    
	public static Long StringDateToLong(String date) {        
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");        
		Date dt=null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dt);       
		return dt.getTime();    
	}
}
