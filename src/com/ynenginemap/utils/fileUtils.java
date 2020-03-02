package com.ynenginemap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thoughtworks.xstream.persistence.FileStreamStrategy;



public class fileUtils {
	
	/*
	 * 文件下载
	 */
	public static ResponseEntity<byte[]> dowloadFile(HttpServletRequest request,String fileName,String fileId) throws IOException{
		byte[] body = null;
		String dfileName = URLEncoder.encode(fileName, "UTF-8");
		File file=new File(getfilepath(fileId,request,null));
	    InputStream is = new FileInputStream(file);
	    body = new byte[is.available()];
	    is.read(body);
	    return FileStream(body,fileName);
	}
	
	public static String getfileUploadPath(){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		String path=  request.getSession().getServletContext().getRealPath("");
		String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
		path= path.split(projectName)[0]+"ImageServer\\WebRoot\\YLZRBHQGISServer_image\\";
		return path;
	}
	
	/**
	 * 获取文件路径
	 * @param fileId 文件ID
	 * @param request 
	 * @return
	 */
	public static String getfilepath(String fileId,HttpServletRequest request,String FunctionName){
		String path=getfileUploadPath();
		if(!"".equals(FunctionName)&&FunctionName!=null){
			path=path+"YLZRBHQGISServer_image/"+FunctionName+fileId;
		}else{
			path=path+"YLZRBHQGISServer_image/"+fileId;
		}
		return path;
	}

	/**
	 * 获取图片加载地址
	 * @param request httpservletrequest
	 * @param FunctionName 功能名称（图片不在根目录的可用到）
	 * @return
	 */
	public static String LoadImgPath(String FunctionName){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		               .getRequestAttributes()).getRequest();
		String imgpath = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort()//端口号 
			    + "/ImageServer/WebRoot/YLZRBHQGISServer_image/"; //服务名（在tomcat里面配置的文件服务名）
		if(FunctionName!=null){
			imgpath+=FunctionName;
		}
		
		return imgpath;
	}

	/**
	 * 获取导出地址
	 * @param request httpservletrequest
	 * @param FunctionName 功能名称（图片不在根目录的可用到）
	 * @return
	 */
	public static String LoadEcportExcelPath(String FunctionName){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		               .getRequestAttributes()).getRequest();
		String imgpath = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort()//端口号 
			    + "/ImageServer/WebRoot/EcportExcel/"; //服务名（在tomcat里面配置的文件服务名）
		if(FunctionName!=null){
			imgpath+=FunctionName;
		}
		
		return imgpath;
	}

	public static String getfileUploadPath1(){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		String path=  request.getSession().getServletContext().getRealPath("");
		String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
		path= path.split(projectName)[0]+"ImageServer\\WebRoot\\EcportExcel\\";
		return path;
	}
	public static String getfileUploadPath2(){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		String path=  request.getSession().getServletContext().getRealPath("");
		String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
		path= path.split(projectName)[0]+"ImageServer\\WebRoot\\EcportExcel2\\";
		return path;
	}

	public static String LoadEcportExcelPath2(String FunctionName){
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		               .getRequestAttributes()).getRequest();
		String imgpath = request.getScheme() //当前链接使用的协议
			    +"://" + request.getServerName()//服务器地址 
			    + ":" + request.getServerPort()//端口号 
			    + "/ImageServer/WebRoot/EcportExcel2/"; //服务名（在tomcat里面配置的文件服务名）
		if(FunctionName!=null){
			imgpath+=FunctionName;
		}
		
		return imgpath;
	}
	
	/**
	 *  通过二进制返回文件流
	 * @param body 二进制流
	 * @param fileName 文件名
	 * @return
	 */
	 
	public static ResponseEntity<byte[]> FileStream(byte[] body,String fileName){
		 fileName= !"".equals(fileName)&&fileName!=null?fileName:"tupian";
		 HttpHeaders headers = new HttpHeaders();
		    headers.add("Content-Disposition", "attchement;filename=" + fileName);
		    HttpStatus statusCode = HttpStatus.OK;
		    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		    return entity;
	}
	
	/**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//  // 删除单个文件
  String file = "c:/test/test.txt";
  deleteFile(file);
//  System.out.println();
        // 删除一个目录
        String dir = "D:/home/web/upload/upload/files";
        deleteDirectory(dir);
//  System.out.println();
//  // 删除文件
//  dir = "c:/test/test0";
//  DeleteFileUtil.delete(dir);

    }

}
