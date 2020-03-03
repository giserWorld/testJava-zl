package com.ynenginemap.utils;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Lineal;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class longitudeAndLatitudeUtil {
	/*
	 * 创建点
	 */
	 public static Point getPoint_WKT(double x,double y){
		  GeometryFactory gFactory=new GeometryFactory(null, 4326);
			 Coordinate corrd=new Coordinate(x,y);
		   	Point pt= gFactory.createPoint(corrd);
		return pt;
	  }
	 
	 /*
	  * 创建面
	  */
	 public static Polygon creatPolygon_WKT(String str){
			GeometryFactory gFactory=new GeometryFactory(null, 4326);
			Polygon polygon	= gFactory.createPolygon(creatCoordinats(str));
			return polygon;
	  }
	 
	 /*
	  * 创建多面
	  */
	 public static MultiPolygon creatMultiPolygon_WKT(String str){
			GeometryFactory gFactory=new GeometryFactory(null, 4326);
			MultiPolygon polygon = gFactory.createMultiPolygon(creatPloys(str));
			return polygon;
	  }
	 /*
	  * 创建线
	  */
	 public static Lineal creatLine_WKT(String str){
			GeometryFactory gFactory=new GeometryFactory(null, 4326);
			Lineal line= gFactory.createLineString(creatCoordinats(str));
			return line;
	  }
	 /*
	  * 解析线
	  */
	 public static String getLine_WKT(String s){
			String ss = s.replace("LINESTRING(", "").replace(")", "");
			String[] sss = ss.split(",");
			List<Object> all = new ArrayList<Object>();
			for(String str:sss){
				List<String> list = new ArrayList<String>();
				String[] strs = str.split(" ");
				list.add(strs[0]);
				list.add(strs[1]);
				all.add(list);
			}
			return all.toString();
	  }
	 /*
	  * 解析面
	  */
	 public static String getPolygon(String s){
			String polygon= s;
			if(polygon.contains("MULTIPOLYGON")){
				polygon=polygon.replace(")))", "").replace("MULTIPOLYGON(((", "");	
			}
			else{
				polygon = polygon.replace("))", "").replace("POLYGON((", "");	
			}
			
			StringBuffer sb= new StringBuffer("[");
			int i=0;
			for(String str : polygon.split(",")){
				String[] points= str.split(" ");
				String arry="["+points[0]+","+points[1]+"]";
				if(i==polygon.split(",").length-1){
					sb.append(arry);
				}else{
					sb.append(arry+",");
				}
				i++;
			}
			sb.append("]");
			return sb.toString();
		}
	 
	 /**
	  * 创建面和线的工具
	  * @param str
	  * @return
	  */
	 private static Coordinate[] creatCoordinats(String str){
		 String[] strs = str.split(",");
			Coordinate[] coordinates = {};
			if(strs.length/2 == 1){
				coordinates = new Coordinate[2];
			}else{
				coordinates = new Coordinate[strs.length/2];
			}
			
			for (int i = 0; i < strs.length/2; i++) {
				Coordinate cd=new Coordinate(Double.valueOf(strs[i+i]), Double.valueOf(strs[i+i+1]));
				coordinates[i]=cd;
			}
			return coordinates;
	 }
	 
	 /**
	  * 创建多面和线的工具
	  * @param str
	  * @return
	  */
	 private static Polygon[] creatPloys(String str){
		 GeometryFactory gFactory=new GeometryFactory(null, 4326);
		 Polygon polygo = gFactory.createPolygon(creatCoordinats(str));
		 Polygon[] p = {polygo};
		return p;
		
	 }
	
	public static void main(String[] args) {
		String s= "LINESTRING(99.2225652660112 26.3196929579893,99.225054355977 26.3141536515562,99.2331224406938 26.310075937302,99.2457395518998 26.3102298159171,99.2485719646195 26.3149999516254)";
		System.out.println(getLine_WKT(s));
	}
}
