package com.ynenginemap.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GestDateUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("当前时间："+ new Date().toLocaleString());
		//System.out.println("当天0点时间："+ getTimesmorning().toLocaleString());
		//System.out.println("当天24点时间："+ getTimesnight().toLocaleString());
		//System.out.println("本周周一0点时间："+ getTimesWeekmorning().toLocaleString());
		//System.out.println("本周周日24点时间："+ getTimesWeeknight().toLocaleString());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("本月初0点时间："+ formatter.format(getTimesMonthmorning()));
		System.out.println("本月未24点时间："+ getTimesMonthnight().getTime());
		}


		// 获得当天0点时间
		public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		}


		// 获得当天24点时间
		public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return  cal.getTime();
		}


		// 获得本周一0点时间
		public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return  cal.getTime();
		}


		// 获得本周日24点时间
		public  static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
		}


		// 获得本月第一天0点时间
		public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return  cal.getTime();
		}


		// 获得本月最后一天24点时间
		public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
		}
	
}
