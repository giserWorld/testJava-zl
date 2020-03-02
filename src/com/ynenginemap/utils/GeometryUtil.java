package com.ynenginemap.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;

/**
 * 空间对象的读取 存储操作
 * 使用WKT   WKB 格式
 * @author enginemap
 *
 */
public class GeometryUtil {
      
	/**
	 * 点数据的封装类 
	 * @author enginemap
	 *
	 */
	  public class Point{
		  /**
		   * 经度
		   */
		  private BigDecimal x;
		  /**
		   * 纬度
		   */
		  private BigDecimal y;
		  /**
		   *坐标系SRID
		   */
		  private int srid=4326; //WGS84坐标系
		/**
		 * 经度 
		 * @return
		 */
		public BigDecimal getX() {
			return x;
		}
		public void setX(BigDecimal x) {
			this.x = x;
		}
		/**
		 * 纬度
		 * @return
		 */
		public BigDecimal getY() {
			return y;
		}
		public void setY(BigDecimal y) {
			this.y = y;
		}
		/**
		 * 坐标系 SRID 
		 * @return
		 */
		public int getSrid() {
			return srid;
		}
		public void setSrid(int srid) {
			this.srid = srid;
		}
		  
	  }
	  
	  public static com.vividsolutions.jts.geom.Point getPoint_WKT(double x,double y){
		  GeometryFactory gFactory=new GeometryFactory(null, 4326);
			 Coordinate corrd=new Coordinate(x,y);
		   	com.vividsolutions.jts.geom.Point pt= gFactory.createPoint(corrd);
		return pt;
		  
	  }
	
	/**
	 * 通过经纬度坐标 来转换成postgis的WKB格式
	 *   POINT(x y)
	 * @param x 为经度
	 * @param y 为维度
	 * @return
	 */
	  public static byte[] getPoint(double x,double y)
	  {
		     GeometryFactory gFactory=new GeometryFactory(null, 4326);
		     Coordinate  coor=new Coordinate(x, y);
		     Geometry geo= gFactory.createPoint(coor);
			 WKBWriter wkbwriter =new WKBWriter();
			 byte[] pt= wkbwriter.write(geo);
			 return pt;
			  
	  }
		/**
		 * 通过经纬度坐标 来转换成postgis的WKB格式
		 *   POINT(x y)
		 * @param x 为经度
		 * @param y 为维度
		 * @return
		 */
		  public static byte[] getPoint(BigDecimal x,BigDecimal y)
		  {
			  double x1=x.doubleValue();
			  double y1=y.doubleValue();
			  GeometryFactory gFactory=new GeometryFactory(null, 4326);
			     	Coordinate  coor=new Coordinate(x1, y1);
			     	
			     Geometry geo= gFactory.createPoint(coor);
			     System.out.println(geo.getCoordinate()) ;
				 WKBWriter wkbwriter =new WKBWriter();
				 byte[] pt= wkbwriter.write(geo);
				 return pt;
		  }
		  /**
		   * 将一系列点集 转换成postgis的 WKB格式的线
		   * @param list
		   * @return
		   */
		  public static byte[] getPolyline(List<Point> list)  
		  {
			  GeometryFactory gFactory=new GeometryFactory(null, 4326);
			 
			   List<Coordinate> List_coor=new ArrayList<Coordinate>();
			  
			   for(Point  pt : list)
			   {
				Coordinate cr=new Coordinate(pt.x.doubleValue(),pt.y.doubleValue()) ; 
				List_coor.add(cr);
			   }
			   Coordinate[] coorArray=(Coordinate[])   List_coor.toArray();
		     Geometry  geo=	  gFactory.createLineString(coorArray);
		     WKBWriter wkbwriter =new WKBWriter();
			 byte[] line= wkbwriter.write(geo);
			 return line;
		  }
		  /**
		   * 将一系列点集 转换成postgis的 WKB格式的面 （多边形）
		   * @param list 为自定义Point的一个类的集合
		   * @return
		   */
		  public static byte[] getPolyon(List<Point> list)  
		  {
			  GeometryFactory gFactory=new GeometryFactory(null, 4326);
				 
			   List<Coordinate> List_coor=new ArrayList<Coordinate>();
			  
			   for(Point  pt : list)
			   {
				Coordinate cr=new Coordinate(pt.x.doubleValue(),pt.y.doubleValue()) ; 
				List_coor.add(cr);
				
			   }
			   Coordinate[] coorArray=(Coordinate[])   List_coor.toArray();
			   Geometry  geo=gFactory.createPolygon(coorArray);
			   WKBWriter wkbwriter =new WKBWriter();
				 byte[] polygon= wkbwriter.write(geo);
				 return polygon;
			   
			   
			   
		  }
		  
		  public static String[] get(byte[] pt){
			  GeometryFactory gFactory=new GeometryFactory(null, 4326);
			  WKBReader wktreader = new WKBReader();
			  WKBWriter wkbwriter =new WKBWriter();
			  double x = 0.0;
			  double y = 0.0;
			  Coordinate  coor=new Coordinate(x, y);
			  Geometry geo= gFactory.createPoint(coor);
				 try {
					 String dd = wkbwriter.toHex(pt);
					 geo = wktreader.read(pt);
					 System.out.println(geo.getCoordinate().y) ;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				return null;
		  }
}
