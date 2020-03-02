package com.ynenginemap.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeTools {

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回long格式 yyyy-MM-dd HH:mm:ss
	 */
	public static long getLongDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long dateString = Long.valueOf(formatter.format(currentTime));
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy
	 */
	public static String getStringNf() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM
	 */
	public static String getStringDateShortYEARMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String strDate) {
		try {
			if (isDateStr(strDate)) {
				String[] strNew = strDate.trim().split("\\.");
				if (strNew.length == 2 || strNew.length == 3) {
					if (strNew[1].length() == 1) {
						strNew[1] = "0" + strNew[1];
					}
					if (strNew.length == 2) {
						strDate = strNew[0] + "-" + strNew[1] + "-01";
					} else {
						if (strNew[2].length() == 1) {
							strNew[2] = "0" + strNew[2];
						} else if (strNew[2].length() == 3) {
							strNew[2] = strNew[2].substring(1);
						}
						strDate = strNew[0] + "-" + strNew[1] + "-" + strNew[2];
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date strtodate = df.parse(strDate);
					return strtodate;
				} else {
					return null;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// return null;
		return null;
	}

	// 判断是不是日期格式
	public static boolean isDateStr(String str) {
		if (str.length() > 10) {
			return false;
		}
		// Pattern pattern =
		// Pattern.compile("^\\d{4}.([0-9]{2}|[1-9]{1}).?([0-9]{2}|[1-9]{1})$");
		Pattern pattern = Pattern
				.compile("^\\d{4}.?([0-9]{2}|[1-9]{1})?.?([0-9]{2}|[1-9]{1})$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	// 计算相差的月份
	// public static Integer calculateMonthIn(Date date1, Date date2) {
	// Calendar cal1 = new GregorianCalendar();
	// cal1.setTime(date1);
	// Calendar cal2 = new GregorianCalendar();
	// cal2.setTime(date2);
	// int c =
	// (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 +
	// cal1.get(Calendar.MONTH)
	// - cal2.get(Calendar.MONTH);
	// return Math.abs(c);
	// }
	public static Integer getDiffMonth(String start, String end) {
		return MathDiffMonth(start, end);

	}

	public static Integer getABSMonth(String start, String end) {
		return Math.abs(MathDiffMonth(start, end));
		/* return MathDiffMonth(start, end); */
	}

	public static Integer MathDiffMonth(String start, String end) {
		String[] startStr = null;
		if (start.contains("-")) {
			startStr = start.split("-");
		} else {
			startStr = start.split("\\.");
		}
		String[] endStr = null;
		if (end.contains("-")) {
			endStr = end.split("-");
		} else {
			endStr = end.split("\\.");
		}
		if (startStr != null && endStr != null && startStr.length >= 2
				&& endStr.length >= 2) {
			int monthStart = Integer.parseInt(startStr[0]) * 12
					+ Integer.parseInt(startStr[1]);
			int monthEnd = Integer.parseInt(endStr[0]) * 12
					+ Integer.parseInt(endStr[1]);
			return monthStart - monthEnd;
		}
		return -1;

	}

	public static Integer strToInt(String str) {
		return Integer.parseInt(str.split("\\.")[0]);
	}

	/**
	 * 进行职务的格式化处理
	 * 
	 * @param level
	 * @return
	 */
	public static String transLevel(String level) {
		if (!level.equals("保留公司正职") && !level.equals("保留正厅级待遇")
				&& !level.equals("")) {
			if (level.contains("副")) {

				if (level.contains("厅"))
					level = "副厅";
				else
					level = "副处";

			} else {
				if (level.contains("厅"))
					level = "正厅";
				else
					level = "正处";
			}
		} else {
			level = "正厅";
		}
		return level;
	}
	
	
	/**
	 * 
	 * @param year
	 * @return
	 */
	public static List<String> getYear_l(String[] year){
		List<String> l = new ArrayList<String>();
		String s1 = year[0];
		String s2 = year[1];
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy"); 
		java.util.Date begin;
		try {
			begin = sdf1.parse(s1);
			java.util.Date   end=sdf1.parse(s2);      
			double   between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒      
			double  day1=between/(24*3600);
			
			for(int i = 0;i<=Integer.valueOf(s2)-Integer.valueOf(s1);i++){
//				
				Calendar cd = Calendar.getInstance();   
				cd.setTime(sdf1.parse(s1));   
				cd.add(Calendar.YEAR, i);//增加一天   
				//cd.add(Calendar.MONTH, n);//增加一个月
				System.out.println(sdf1.format(cd.getTime()));
				l.add(sdf1.format(cd.getTime()));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		return l;
	}
	
	/**
	 * 获取指定年份之间的所有年份
	 */
	public static List<String> getYear_m(String s1,String s2){
		List<String> l = new ArrayList<String>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy"); 
		java.util.Date begin;
		try {
			begin = sdf1.parse(s1);
			java.util.Date   end=sdf1.parse(s2);      
			double   between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒      
			double  day1=between/(24*3600);
			
			for(int i = 0;i<=Integer.valueOf(s2)-Integer.valueOf(s1);i++){
//				
				Calendar cd = Calendar.getInstance();   
				cd.setTime(sdf1.parse(s1));   
				cd.add(Calendar.YEAR, i);//增加一天   
				//cd.add(Calendar.MONTH, n);//增加一个月
				System.out.println(sdf1.format(cd.getTime()));
				l.add(sdf1.format(cd.getTime()));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
		return l;
		
	}
	
	/**得到传入参数的日期的星期
	 * 若是没有传入参数，则获得当前日期的星期
	 * */
	public static int getWeek(Date date){
		Date currentTime = new Date();
        Calendar cal = Calendar.getInstance();
        if(date != null && !"".equals(date)){
        	currentTime=date;
        }
        cal.setTime(currentTime);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
        week_index = 0;
        } 
		return week_index;
	}
	/**获得传入参数的一周开始日期和结束日期，以及星期
	 * 返回值：Map 
	 * week:参数的星期（如：1，2，3，4，5，6，7）
	 * startDate:这个周开始的日期，如“2016-2-22”
	 * endDate:这个周结束的日期，如“2016-2-27”
	 * */
	public static Map<String,String> getWeekStartAndEnd(Date date1){
		Map<String,String> list=new HashMap<String,String>();
		Date currentTime=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
		//String date=currentTime.toString();
        String date=null;
       if(date1 != null && !"".equals(date1)){
    	    date=simpleDateFormat.format(currentTime);
       }else{
    	    date=simpleDateFormat.format(date1);
       }
        //年月
        String ny=date.substring(0,7);
        //日
        String day=date.substring(8,10);
        //获得星期一的 日
        System.out.println("今日是星期："+getWeek(currentTime));
        //int dayInt=Integer.parseInt(day)-getWeek(currentTime)-1;
    	//String Monday=ny+"-"+String.valueOf(dayInt);  	
		list.put("week", String.valueOf(getWeek(currentTime)));
		list.put("startDate",simpleDateFormat.format(getTimesWeekmorning()));
		list.put("endDate", simpleDateFormat.format(getTimesWeeknight()));
		
		return list;
	}
   /**获得本季度的 季度 和开始 季度 和结束季度的月份*/
	public static Map<String,String> getQuarter(Date date){
			Map<String,String> list=new HashMap<String,String>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM");
		String time=simpleDateFormat.format(date);
		String year=time.substring(0,4);
		int month=Integer.valueOf(time.substring(5,7));
		if(month >=1 && month <=3){
			list.put("quarter", "第一季度");
			list.put("startMonth", year+"-1");
			list.put("endMonth", year+"-3");
		}else if(month >=4 && month <=6){
			list.put("quarter", "第二季度");
			list.put("startMonth", year+"-4");
			list.put("endMonth", year+"-6");
		}else if(month >=7 && month <=9){
			list.put("quarter", "第三季度");
			list.put("startMonth", year+"-7");
			list.put("endMonth", year+"-9");
		} else{//month >=10 && month <=12
			list.put("quarter", "第四季度");
			list.put("startMonth",year+ "-10");
			list.put("endMonth", year+"-12");
		}
		
		return list;
	}

	// 获得本周一0点时间
	public static Date getTimesWeekmorning() {
	Calendar cal = Calendar.getInstance();
	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	return cal.getTime();
	}
	
	// 获得本周一0点时间
		public static String getTimesWeekmorning2() {
			SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return fromat.format(cal.getTime());
		}

	
		// 获得本周日0点时间
				public static String getTimesWeekmorning3() {
					SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH));
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				return fromat.format(cal.getTime());
				}
		
	// 获得本周日24点时间
	public static Date getTimesWeeknight() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getTimesWeekmorning());
	cal.add(Calendar.DAY_OF_WEEK, 7);
	return cal.getTime();
	}
	
	//获取当前日期日  dd日
	public static String dateToStr2(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	//过去多少天的时间
	public static String getN_day(int n){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -n);
        Date d = c.getTime();
        String day = format.format(d);
        //System.out.println("过去七天："+day);
		return day;
	}
	//过去多少月的时间
	public static String n_month(int n){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -n);
        Date m = c.getTime();
        String mon = format.format(m);
		return mon;
        
	}
	//过去多少年的时间
	public static String n_year(int n){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -n);
        Date y = c.getTime();
        String year = format.format(y);
		return year;
	}
	
	public static void main(String[] args) {
		String s = "2016-09-05";
		String ss = s.substring(0, 8);
		System.out.println(ss+"01");
		System.out.println(dateToStr2(new Date()));
	}
}