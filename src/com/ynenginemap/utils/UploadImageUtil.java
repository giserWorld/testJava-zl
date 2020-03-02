package com.ynenginemap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tongtech.tmqi.util.BASE64Decoder;
import com.tongtech.tmqi.util.BASE64Encoder;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * 处理上传的图片
 * @author ysg
 *
 */
public class UploadImageUtil {
	/*
	 * 图片转换
	 */
	public static byte[] getImage(String base64Decoder){
		BASE64Decoder d = new BASE64Decoder();
		byte[] image = null;
		try {
			image = d.decodeBuffer(base64Decoder.toString());
			for (int i = 0; i < image.length; ++i) {
				if(image[i]<0){
					image[i]+=256;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return image;	
	}
	/*
	 * 获取路径
	 */
	public static String getPath(){
		String path = ServletActionContext.getServletContext().getRealPath("/");
		return path;
	}
	
	public static String getPath(HttpServletRequest request){
		//String path = request.getRequestURI();
		String path = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort() //端口号 
			    + request.getContextPath(); //应用名称，如果应用名称为
			  //  + request.getServletPath() //请求的相对url 
			  //  + "?" + request.getQueryString(); //请求参数
		return path;
	}
	
	public static String getPath_imageserver(HttpServletRequest request){
		//String path = request.getRequestURI();
		String path = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort() //端口号 
			    +"/ImageServer"
			    + request.getContextPath()+"image"; //应用名称，如果应用名称为
			  //  + request.getServletPath() //请求的相对url 
			  //  + "?" + request.getQueryString(); //请求参数
		return path;
	}
	
	
	public static String getPath_value(HttpServletRequest request){
		//String path = request.getRequestURI();
		String path = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort() //端口号 
			    + request.getContextPath() //应用名称，如果应用名称为
			    + request.getServletPath() //请求的相对url 
			    + "?" + request.getQueryString(); //请求参数
		return path;
	}
	
	/**
	 * 将图片转化为BASE64编码的二进制流
	 * @param imgFile
	 * @return
	 */
	public static  String getImageStr(String path, String name) {
		if(name == null || "".equals(name) || "null".equals(name)){
			name = "account_photo.jpg";
		}
		File f = new File(path + name);
		System.out.println(path + name);
		if(!f.exists()){
			System.out.println(path + "account_photo.jpg");
			f = new File(path + "account_photo.jpg");
		}

		InputStream in = null;
		byte[] data = null;
		try {
			System.out.println(f.exists());
			in = new FileInputStream(f);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
}
