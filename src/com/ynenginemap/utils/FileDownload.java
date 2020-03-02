package com.ynenginemap.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 文件下载
 * @author here
 *
 */
public class FileDownload {
	
	/**
	 * 
	 * @param path
	 * @param req
	 * @param response
	 * @return
	 */
	public HttpServletResponse download(String path,HttpServletRequest req, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8"); 
		 OutputStream toClient = null;
		try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
//			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
        	response.reset();
            // 设置response的Header
//          String name = URLEncoder.encode(filename, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" +  new String(filename.getBytes("UTF-8"), "ISO8859-1" ));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
			toClient.close();
//    		//获得当前服务器连接地址
//    		String path1 = req.getServletContext().getRealPath("/");
//            delAllFile(path1 + "docDown");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return response;
	}
	
	//批量文件下载(将多个文件打包成zip包下载)
	public void batchDownLoadFile(HttpServletRequest request,HttpServletResponse response,String filename,String[] filepath,String[] documentname){
		byte[] buffer = new byte[1024];
		Date date=new Date();
//		//获得当前服务器连接地址
		String path1 = request.getServletContext().getRealPath("/ZipDown");
		//生成zip文件存放位置
		String strZipPath = path1 +filename+date.getTime()+".zip";
		File file=new File(path1);
		if(!file.isDirectory() && !file.exists()){
			//创建单层目录
			// f.mkdir();
			// 创建多层目录
			file.mkdirs();
		}
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
			// 需要同时下载的多个文件
			for (int i = 0; i < filepath.length; i++) {
				File f=new File(filepath[i]);
				FileInputStream fis = new FileInputStream(f);
				System.out.println(documentname[i]);
				out.putNextEntry(new ZipEntry(documentname[i]));
				//设置压缩文件内的字符编码，不然会变成乱码
				out.setEncoding("GBK");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
			download(strZipPath, request,response);
			File temp=new File(strZipPath);
			if(temp.exists()){
				temp.delete();
			}
		} catch (Exception e) {
			System.out.println("文件下载错误");
		}
      delAllFile(path1 + "ZipDown");
	}
	
	
	public  boolean delAllFile(String path) {  
	       boolean flag = false;  
	       File file = new File(path);  
	       if(!file.exists()){
	         return flag;  
	       }  
	       if(!file.isDirectory()){
	         return flag;  
	       }  
	       String[] tempList = file.list();  
	       File temp = null;  
	       for(int i=0;i<tempList.length;i++){
	          if(path.endsWith(File.separator)){
	             temp = new File(path + tempList[i]);
	          }else{ 
	              temp = new File(path + File.separator + tempList[i]);  
	          }  
	          if(temp.isFile()){
	             temp.delete();  
	          }  
	          if(temp.isDirectory()){
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
	             flag = true;  
	          }  
	       }  
	       return flag;  
	     }  
	
	}  

