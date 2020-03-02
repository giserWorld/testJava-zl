package com.ynenginemap.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HCY
 * @version 创建时间：2018年9月10日 上午10:13:50
 * 类说明
 */
public class Demo {

	private static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";  
    
    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");  
   
    
    /**
     * 高德地图地理/逆地理编码日调用超量报警（次/日）：3000000
     * @param address
     * @return
     */
    public static double[] addressToGPS(String address) {  
        try {  

            String url = String .format("http://restapi.amap.com/v3/geocode/geo?&s=rsv3&address=%s&key=%s", address, KEY); 
	    	URL myURL = null; 
	    	URLConnection httpsConn = null; 
	    	try { 
	    		myURL = new URL(url); 
	    	} catch (MalformedURLException e) { 
	    		e.printStackTrace(); 
	    	} 
	    	InputStreamReader insr = null;
	    	BufferedReader br = null;
	    	httpsConn = (URLConnection) myURL.openConnection();// 不使用代理 
	    	if (httpsConn != null) { 
		    	insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8"); 
		    	br = new BufferedReader(insr); 
		    	String data = "";
		    	String line = null; 
		    	while((line= br.readLine())!=null){
		    		data+=line;
		    	}
	            Matcher matcher = pattern.matcher(data); 
	            boolean f = matcher.find();
	            int i  = matcher.groupCount();
	            System.out.println("matcher.find() = "+f);
	            System.out.println("matcher.groupCount() = "+i);
	            if (f && (i == 2)) {  
	                double[] gps = new double[2];  
	                gps[0] = Double.valueOf(matcher.group(1));  
	                gps[1] = Double.valueOf(matcher.group(2));  
	                return gps;  
	            }
	    	}
        }catch (Exception e) {
        	e.printStackTrace(); 
		return null;
		}
        return null;
    }
    
    
    /**
     * 计算当前坐标周围10公里的范围坐标
     * @param longitude
     * @param latitude
     * @return
     */
    public static double[] findNeighPosition(double longitude,double latitude){
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dis = 5;//5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;        
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;

        double[] values = {minlng,maxlng,minlat,maxlat};

        return values;
    }
    
    public static void main(String[] args) {  
    	double [] data = addressToGPS("路桥区蓬街镇徐三村");
//		double [] data = Demo.addressToGPS("徐三村一区56号东侧");
    	double [] data1 = findNeighPosition(data[0],data[1]);
        System.out.println("经度:"+data[0]);
        System.out.println("纬度:"+data[1]);
        System.out.println("10公里的范围经度:"+data1[0]);
        System.out.println("10公里的范围经度:"+data1[1]);
        System.out.println("10公里的范围纬度:"+data1[2]);
        System.out.println("10公里的范围纬度:"+data1[3]);
    }
}