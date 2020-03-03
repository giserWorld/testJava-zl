package com.ynenginemap.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileUpUtils {

	public Map<String, Object> fileUpload(CommonsMultipartFile[] file,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String path=  request.getSession().getServletContext().getRealPath("");
		String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
		//获取到存储路径
		path= path.split(projectName)[0]+"ViolationBuildGISServer_FileUp/";
		try {
			if(file.length>0){
				for(int i = 0;i<file.length;i++){  
					if(!file[i].isEmpty()){
						// 附件名称
						String fileName = file[i].getOriginalFilename();
//						String name = file[i].getName();
				    	// 文件保存路径  
				    	File files=new File(path);
				    	if(!files.exists()){//不存在创建
							files.mkdirs();
						}
						// 附件地址
						 long time = System.currentTimeMillis();
//							String filePath = path +time+strs[0];  
				    	String[] strs= fileName.split("\\.");
						String filePathNm = time+strs[0]+"."+strs[1];  
			            System.out.println("fileName 1:"+fileName);
			            // 转存文件  
			            file[i].transferTo(new File(path+filePathNm));
			            Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("path", path+filePathNm);
						map1.put("name", fileName);
						list.add(map1);
					 }
				}
			}
			map.put("files", list);
			map.put("success", true);
			map.put("message", "文件上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "文件上传失败");
		}
		return map;
	}
}
