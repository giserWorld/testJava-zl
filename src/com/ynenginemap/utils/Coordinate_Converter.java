package com.ynenginemap.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Here on 2016/5/16.
 * 地球坐标系 (WGS-84) 转换为百度地图坐标
 * 地球坐标系 (WGS-84) 相互转火星坐标系 (GCJ-02) 的转换算法
 */
public class Coordinate_Converter {
	private static double pi = 3.14159265358979324D;// 圆周率
    private static double a = 6378245.0D;// WGS 长轴半径
    private static double ee = 0.00669342162296594323D;// WGS 偏心率的平方
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * gps坐标转换为百度坐标
     *
     * @param longitude 经度
     * @param latitude  维度
     * @return
     */
//    public static LatLng GPSToBaidu(double longitude, double latitude) {
//        LatLng latLng = new LatLng(latitude, longitude);
//        CoordinateConverter converter = new CoordinateConverter();
//        converter.from(CoordinateConverter.CoordType.GPS);
//        converter.coord(latLng);
//        LatLng s = converter.convert();
//        return s;
//    }

    /**
     * 百度坐标转为gps坐标
     *
     * @param longitude 经度
     * @param latitude  维度
     * @return
     */
    public static Map<String, Double> ToGPS(double longitude, double latitude) {
        Map<String, Double> position = new HashMap<String, Double>();
        if (!outofChina(latitude, longitude)) {
            //转换为火星坐标
            double x = longitude - 0.0065, y = latitude - 0.006;
            double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
            double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
            double gg_lon = z * Math.cos(theta);
            double gg_lat = z * Math.sin(theta);
            //转换为地球坐标
            position = gcj2wgs(gg_lon, gg_lat);
        }

        return position;

    }

    /**
     * 中国坐标内
     *
     * @param lat
     * @param lon
     * @return
     */
    public static boolean outofChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    
    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }
    // 84->gcj02
    public static Map<String, Double> transform(double lon, double lat) {
        HashMap<String, Double> localHashMap = new HashMap<String, Double>();
        if (outofChina(lat, lon)) {
            localHashMap.put("lon", Double.valueOf(lon));
            localHashMap.put("lat", Double.valueOf(lat));
            return localHashMap;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        localHashMap.put("lon", mgLon);
        localHashMap.put("lat", mgLat);
        return localHashMap;
    }

    // gcj02-84
    public static Map<String, Double> gcj2wgs(double lon, double lat) {
        Map<String, Double> localHashMap = new HashMap<String, Double>();
        double lontitude = lon
                - (((Double) transform(lon, lat).get("lon")).doubleValue() - lon);
        double latitude = (lat - (((Double) (transform(lon, lat))
                .get("lat")).doubleValue() - lat));
        localHashMap.put("lon", lontitude);
        localHashMap.put("lat", latitude);
        return localHashMap;
    }
}
