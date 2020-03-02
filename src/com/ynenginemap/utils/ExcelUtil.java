package com.ynenginemap.utils;



import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class ExcelUtil {
	/**
	 * 创建excel
	 * @param mapdata 表头和数据
	 * @param userid 用户ID
	 * @param type 名称
	 * @return
	 */
	public static String creataExcel(Map<String, Object> mapdata,String userid,String type){
		 //创建一个webbook，对应一个Excel文件  
        @SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();  
        //在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("Sheet1");  
         
        //创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        //在sheet中添加表头第0行,老版本poi对Excel的行数列数有限制short 
        String[] listo= (String[]) mapdata.get("top");
        HSSFRow row = sheet.createRow(0); 
        for(int i=0;i<listo.length;i++){
        	HSSFCell cell = row.createCell(i);  
        	cell.setCellValue(listo[i]);
        	cell.setCellStyle(style);
        }
  
        //写入数据
        @SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>) mapdata.get("data");
        for (int i = 0; i < list.size(); i++)  
        {  
        	HSSFRow row1 = sheet.createRow(i+1);
            for(int j=0;j<list.get(i).length;j++){
            	HSSFCell cell2 = row1.createCell(j);
            	cell2.setCellValue(String.valueOf(list.get(i)[j]));
            }
        }  
        String a = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        //将文件存到指定位置  
        String path="";
        try{  
        	path= fileUtils.getfileUploadPath1();
        	
        	File file=new File(path+"ecport/"+userid+"/");
        	System.out.println("保存路径："+path+"ecport/"+userid+"/");
        	if(!file.exists()){
        		file.mkdirs();
        	}
        	type=a+type+".xls";
        	System.out.println("type:"+type);
            FileOutputStream fout = new FileOutputStream(path+"ecport/"+userid+"/"+type);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
        	e.printStackTrace();  
        	return "文件创建失败！";
        }
        //返回路径
        String urlpath=fileUtils.LoadEcportExcelPath(null);
       System.out.println("返回的路径："+urlpath+"ecport/"+userid+"/"+type);
        return urlpath+"ecport/"+userid+"/"+type;
	}
//	
//	
//	public static String creataExcel2(Map<String, Object> mapdata,String userid,String type){
//		 //创建一个webbook，对应一个Excel文件  
//       HSSFWorkbook wb = new HSSFWorkbook();  
//       //在webbook中添加一个sheet,对应Excel文件中的sheet  
//       HSSFSheet sheet = wb.createSheet("Sheet1");  
//        
//       //创建单元格，并设置值表头 设置表头居中  
//       HSSFCellStyle style = wb.createCellStyle();  
//       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
//       
//       //在sheet中添加表头第0行,老版本poi对Excel的行数列数有限制short 
//       String[] listo= (String[]) mapdata.get("top");
//       HSSFRow row = sheet.createRow(0); 
//       for(int i=0;i<listo.length;i++){
//       	HSSFCell cell = row.createCell(i);  
//       	cell.setCellValue(listo[i]);
//       	cell.setCellStyle(style);
//       }
// 
//       //写入数据
//       @SuppressWarnings("unchecked")
//		List<Object[]> list= (List<Object[]>) mapdata.get("data");
//       for (int i = 0; i < list.size(); i++)  
//       {  
//       	HSSFRow row1 = sheet.createRow(i+1);
//           for(int j=0;j<list.get(i).length;j++){
//           	HSSFCell cell2 = row1.createCell(j);
//           	cell2.setCellValue(String.valueOf(list.get(i)[j]));
//           }
//       }  
//       String a = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
//       //将文件存到指定位置  
//       String path="";
//       try{  
//       	path= fileUtils.getfileUploadPath();
//       	
//       	File file=new File(path+"ecport/用户数据/");
//       	System.out.println("保存路径："+path+"ecport/用户数据/");
//       	if(!file.exists()){
//       		file.mkdirs();
//       	}
//       	type=a+type+".xls";
//       	System.out.println("type:"+type);
//           FileOutputStream fout = new FileOutputStream(path+"ecport/用户数据/"+type);  
//           wb.write(fout);  
//           fout.close();  
//       }  
//       catch (Exception e)  
//       {  
//       	e.printStackTrace();  
//       	return "文件创建失败！";
//       }
//       //返回路径
//       String urlpath=fileUtils.LoadImgPath(null);
//      System.out.println("返回的路径："+urlpath+"ecport/用户数据/"+type);
//       return urlpath+"ecport/用户数据/"+type;
//	}
//	
//	//==================================Excel数据导入====================================================
//	
//	/**
//	 * 保存文件
//	 * @param files
//	 * @param request
//	 * @return
//	 */
//	public static List<String> getpath(CommonsMultipartFile[] 
//			files,HttpServletRequest request){
//		List<String> list = new ArrayList<>();
//		try{
//			//存本地获取文件路径
//			String path=  request.getSession().getServletContext().getRealPath("");
//			String projectName= request.getSession().getServletContext().getContextPath().replace("/", "");
//			//获取到存储路径
//			path= path.split(projectName)[0]+"YLZRBHQGISServer\\WebRoot\\";
//			if(files.length>0){
//				for(int i = 0;i<files.length;i++){  
////					String uuid= UUID.randomUUID().toString().replace("-", "").toUpperCase();
//					// 文件保存路径  
//					File file=new File(path + "Excel\\");
//					if(!file.exists()){//不存在创建
//						file.mkdirs();
//					}
//					String filePath = path + "Excel\\"+files[i].getOriginalFilename();  
//					// 转存文件  
//					files[i].transferTo(new File(filePath));
//					list.add(filePath);
//				}
//			}
//			return list;
//		}catch(Exception e){
//			return null;
//		}
//	}
//	
//	
//	
//	/**
//	 * 处理excel
//	 * @param list
//	 * @return
//	 */
//	public static List<XhjlbBean> getsj(List<String> list,String type){
//		List<XhjlbBean> xhjlsj = new ArrayList<>();
//		for(String s:list){
//			try {
////				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(s));
//				XSSFWorkbook  workbook = new XSSFWorkbook (new FileInputStream(s));
//				XSSFSheet sheet = workbook.getSheetAt(0);
//				XhjlbBean xhjlb = new XhjlbBean();
//				Field[] declaredField = xhjlb.getClass().getDeclaredFields();
//				System.out.println("实体变量："+declaredField);
//				XSSFRow row1 = sheet.getRow(0);
//				for (int k = 1; k <= sheet.getLastRowNum(); k++) {
//					System.out.println("最后你一行："+sheet.getLastRowNum());
//					XSSFRow row = sheet.getRow(k);
//					System.out.println("row"+row);
//					xhjlb = new XhjlbBean();
//					System.out.println("row.getLastCellNum():"+row.getLastCellNum());
//					for (int j = 0; j < row.getLastCellNum(); j++) {
//						System.out.println("declaredField.length"+declaredField.length);
//						XSSFCell cell = row.getCell(j);
//						if(cell == null ){
////							cell.setCellValue("");
//						}else{
//							
//							System.out.println("cell:"+cell);
//							cell.setCellType(cell.CELL_TYPE_STRING);
//							switch (type) {
//							case "动物资源数据":
//								invokeSetdw(xhjlb, row1.getCell(j).getStringCellValue(), cell.getStringCellValue());
//								break;
//							case "植物资源数据":
//								invokeSetzw(xhjlb, row1.getCell(j).getStringCellValue(), cell.getStringCellValue());
//								break;
//							case "矿物资源数据":
//								invokeSetkw(xhjlb, row1.getCell(j).getStringCellValue(), cell.getStringCellValue());
//								break;
//							default:
//								break;
//							}
//						}
//
//					}
//					xhjlb.setXhjluuid(UUID.randomUUID().toString());
//					
////					qxsjb.setQy(dm);
//					xhjlsj.add(xhjlb);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return xhjlsj;
//	}
//
//	public static void invokeSetdw(Object o, String fieldName, Object value) {
//		System.out.println("fieldName:"+fieldName);
//		switch (fieldName) {
//		case "片区名称":
//			fieldName="Xhjlpqmc";
//			break;
//		case "填表人":
//			fieldName="Tbr";
//			break;
//		
//		case "备注":
//			fieldName="Qw";
//			break;
//		case "天气状况":
//			fieldName="Tqzk";
//			break;
//		case "干扰类型名称":
//			fieldName="Jcjlbz";
//			break;
//		case "干扰强度":
//			fieldName="Xhjlgxsj";
//			break;
//		case "记录时间":
//			fieldName="Jlsj";
//			break;
//		case "种类":
//			fieldName="Zl";
//			break;
//		case "记录地点":
//			fieldName="Jlddd";
//			break;
//		case "海拔":
//			fieldName="Jldhb";
//			break;
//		case "干扰时间":
//			fieldName="Mbdfx";
//			break;
//		case "是否在保护区内":
//			fieldName="Bhqn";
//			break;
//		case "亚成体数量--雌性数量（猴）":
//			fieldName="Mbdhb";
//			break;
//		case "不清数量--幼猴数量（猴）":
//			fieldName="Mbdsj";
//			break;
//		case "总数":
//			fieldName="Jcjlzs";
//			break;
//		case "成体数量--雄性数量（猴）":
//			fieldName="Jcjlctsl";
//			break;
//		case "幼体数量--半大猴数量（猴）":
//			fieldName="Jcjlytsl";
//			break;
//		case "群落类型":
//			fieldName="Jcjlctsj";
//			break;
//		case "火灾隐患":
//			fieldName="Jcjlytsj";
//			break;
//		case "年份":
//			fieldName="Nf";
//			break;
//		case "行为":
//			fieldName="Areatype";
//			break;
//		case "痕迹类型":
//			fieldName="Zwgd";
//			break;
//		
//		case "痕迹数量":
//			fieldName="Soil";
//			break;
//		case "位置名称":
//			fieldName="Wzmc";
//			break;
//		case "痕迹新鲜度":
//			fieldName="Alias";
//			break;
//		case "食物":
//			fieldName="Sw";
//			break;
//		case "伴生动物":
//			fieldName="Bsdwmc";
//			break;
//		case "二级干扰类型名称":
//			fieldName="Grlxejmc";
//			break;
//		case "伴生数量":
//			fieldName="Bsdwsl";
//			break;
//		case "生境类型":
//			fieldName="Sjlx";
//			break;
//		case "经度":
//			fieldName="Jd";
//			break;
//		case "纬度":
//			fieldName="Wd";
//			break;
//		
//		default:
//			break;
//		}
//		
//		 Method setM = null;
//         try {
//        	 switch (fieldName) {
//				case "Bhqn":
//					if(value.equals("是")){
//						value = false;
//					}else{
//						value = true;
//					}
//					System.out.println("value = "+value);
//					break;
//
//				default:
//					break;
//				}
//             setM = o.getClass().getMethod("set"+fieldName, value.getClass());
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("===========setM============");
//         }
//         if(setM != null){
//	         try {
//	        	
//	             setM.invoke(o, value);
//	         } catch (Exception e) {
//	             e.printStackTrace();
//	             System.out.println("============ setM.invoke()========");
//	         }
//         }
//		
//	}
//	public static void invokeSetzw(Object o, String fieldName, Object value) {
//		System.out.println("fieldName:"+fieldName);
//		switch (fieldName) {
//		case "片区名称":
//			fieldName="Xhjlpqmc";
//			break;
//		case "填表人":
//			fieldName="Tbr";
//			break;
//			
//		case "备注":
//			fieldName="Qw";
//			break;
//		case "天气状况":
//			fieldName="Tqzk";
//			break;
//		case "干扰类型名称":
//			fieldName="Jcjlbz";
//			break;
//		case "干扰强度":
//			fieldName="Xhjlgxsj";
//			break;
//		case "记录时间":
//			fieldName="Jlsj";
//			break;
//		case "种类":
//			fieldName="Zl";
//			break;
//		case "记录地点":
//			fieldName="Jlddd";
//			break;
//		case "海拔":
//			fieldName="Jldhb";
//			break;
//		case "干扰时间":
//			fieldName="Mbdfx";
//			break;
//		case "是否在保护区内":
//			fieldName="Bhqn";
//			break;
//		
//		case "总数":
//			fieldName="Jcjlzs";
//			break;
//		
//		case "群落类型":
//			fieldName="Jcjlctsj";
//			break;
//		case "火险隐患":
//			fieldName="Jcjlytsj";
//			break;
//		case "年份":
//			fieldName="Nf";
//			break;
//		
//		case "位置名称":
//			fieldName="Wzmc";
//			break;
//		case "物候":
//			fieldName="Zwwhmc";
//			break;
//		
//		case "二级干扰类型名称":
//			fieldName="Grlxejmc";
//			break;
//		
//		case "经度":
//			fieldName="Jd";
//			break;
//		case "纬度":
//			fieldName="Wd";
//			break;
//			
//		default:
//			break;
//		}
//		
//		Method setM = null;
//		try {
//			switch (fieldName) {
//			case "Bhqn":
//				if(value.equals("是")){
//					value = false;
//				}else{
//					value = true;
//				}
//				System.out.println("value = "+value);
//				break;
//
//			default:
//				break;
//			}
//			setM = o.getClass().getMethod("set"+fieldName, value.getClass());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("===========setM============");
//		}
//		if(setM != null){
//			try {
//				setM.invoke(o, value);
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("============ setM.invoke()========");
//			}
//		}
//		
//	}
//	public static void invokeSetkw(Object o, String fieldName, Object value) {
//		System.out.println("fieldName:"+fieldName);
//		switch (fieldName) {
//		case "片区名称":
//			fieldName="Xhjlpqmc";
//			break;
//		case "填表人":
//			fieldName="Tbr";
//			break;
//			
//		case "简介":
//			fieldName="Qw";
//			break;
//	
//		case "记录时间":
//			fieldName="Jlsj";
//			break;
//		
//		case "种类":
//			fieldName="Zl";
//			break;
//		case "记录地点":
//			fieldName="Jlddd";
//			break;
//		case "海拔":
//			fieldName="Jldhb";
//			break;
//		
//		case "是否在保护区内":
//			fieldName="Bhqn";
//			break;
//		
//		case "年份":
//			fieldName="Nf";
//			break;
//		case "分布":
//			fieldName="Fb";
//			break;
//			
//		case "类别":
//			fieldName="Lb";
//			break;
//		case "状态":
//			fieldName="Zt";
//			break;
//		case "位置名称":
//			fieldName="Wzmc";
//			break;
//		case "联系电话":
//			fieldName="Lxdh";
//			break;
//		case "开采人":
//			fieldName="Kcr";
//			break;
//		case "矿种":
//			fieldName="Kz";
//			break;
//		case "是否开采（0-未开采，1-已开采）":
//			fieldName="Sfkc";
//			break;
//		
//		case "经度":
//			fieldName="Jd";
//			break;
//		case "纬度":
//			fieldName="Wd";
//			break;
//			
//		default:
//			break;
//		}
//		
//		Method setM = null;
//		try {
//			switch (fieldName) {
//			case "Bhqn":
//				if(value.equals("是")){
//					value = false;
//				}else{
//					value = true;
//				}
//				System.out.println("value = "+value);
//				break;
//
//			default:
//				break;
//			}
//			setM = o.getClass().getMethod("set"+fieldName, value.getClass());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("===========setM============");
//		}
//		if(setM != null){
//			try {
//				setM.invoke(o, value);
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("============ setM.invoke()========");
//			}
//		}
//		
//	}
//	/**
//	 * 处理excel特定图层
//	 * @param list
//	 * @return
//	 */
//	@SuppressWarnings("null")
//	public static List<Featuresign_esBean> getsj2(List<String> list,String type){
//		List<Featuresign_esBean> featsjs = new ArrayList<>();
//		for(String s:list){
//			try {
////				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(s));
//				XSSFWorkbook  workbook = new XSSFWorkbook (new FileInputStream(s));
//				XSSFSheet sheet = workbook.getSheetAt(0);
//				Featuresign_esBean feat = new Featuresign_esBean();
//				Field[] declaredField = feat.getClass().getDeclaredFields();
//				System.out.println("实体变量："+declaredField);
//				XSSFRow row1 = sheet.getRow(0);
//				for (int k = 1; k <= sheet.getLastRowNum(); k++) {
//					System.out.println("最后你一行："+sheet.getLastRowNum());
//					XSSFRow row = sheet.getRow(k);
//					System.out.println("row"+row);
//					feat = new Featuresign_esBean();
//					System.out.println("row.getLastCellNum():"+row.getLastCellNum());
//					for (int j = 0; j < row.getLastCellNum(); j++) {
//						System.out.println("declaredField.length"+declaredField.length);
//						
//						XSSFCell cell = row.getCell(j);
//						if(cell == null){
////							cell.setCellValue("");
//						}else{
//							System.out.println("cell:"+cell);
//							cell.setCellType(cell.CELL_TYPE_STRING);
//							
//							switch (type) {
//							
//							case "特定图层数据":
//								invokeSettdtc(feat, row1.getCell(j).getStringCellValue(), cell.getStringCellValue());
//								break;
//							default:
//								break;
//							}
//						}
//						
//					}
//					feat.setUuid(UUID.randomUUID().toString());
////					qxsjb.setQy(dm);
//					featsjs.add(feat);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return featsjs;
//	}
//	
//	public static void invokeSettdtc(Object o, String fieldName, Object value) {
//		System.out.println("fieldName:"+fieldName);
//		switch (fieldName) {
//		case "名称":
//			fieldName="Mc";
//			break;
//		case "类别":
//			fieldName="Lb";
//			break;
//		case "物种":
//			fieldName="Wzxx";
//			break;
//		case "分布":
//			fieldName="Fb";
//			break;
//		case "海拔":
//			fieldName="Hb";
//			break;
//		case "状态":
//			fieldName="Zt";
//			break;
//		case "位置":
//			fieldName="Wz";
//			break;
//		case "数量":
//			fieldName="Sl";
//			break;
//		case "采集人":
//			fieldName="Cjr";
//			break;
//		case "采集时间":
//			fieldName="Cjsj";
//			break;
//		case "描述":
//			fieldName="Xxms";
//			break;
//		case "点经纬度":
//			fieldName="Sh";
//			break;
//		case "线经纬度":
//			fieldName="Gs";
//			break;
//		case "面经纬度":
//			fieldName="Name";
//			break;
//		case "数据类型（点 线 面）":
//			fieldName="Geometry";
//			break;
//		case "巡护类型":
//			fieldName="Type";
//			break;
//		default:
//			break;
//		}
//		
//		Method setM = null;
//		try {
//			setM = o.getClass().getMethod("set"+fieldName, value.getClass());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("===========setM============");
//		}
//		if(setM != null){
//			try {
//				setM.invoke(o, value);
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("============ setM.invoke()========");
//			}
//		}
//		
//	}
//	
//	/**
//	 * 处理excel
//	 * @param list
//	 * @return
//	 */
//	public static List<SlxhrzBean> getsj3(List<String> list,String type){
//		List<SlxhrzBean> Slxhrzsj = new ArrayList<>();
//		for(String s:list){
//			try {
////				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(s));
//				XSSFWorkbook  workbook = new XSSFWorkbook (new FileInputStream(s));
//				XSSFSheet sheet = workbook.getSheetAt(0);
//				SlxhrzBean Slxhrz = new SlxhrzBean();
//				Field[] declaredField = Slxhrz.getClass().getDeclaredFields();
//				System.out.println("实体变量："+declaredField);
//				XSSFRow row1 = sheet.getRow(0);
//				for (int k = 1; k <= sheet.getLastRowNum(); k++) {
//					System.out.println("最后你一行："+sheet.getLastRowNum());
//					XSSFRow row = sheet.getRow(k);
//					System.out.println("row"+row);
//					Slxhrz = new SlxhrzBean();
//					System.out.println("row.getLastCellNum():"+row.getLastCellNum());
//					for (int j = 0; j < row.getLastCellNum(); j++) {
//						System.out.println("declaredField.length"+declaredField.length);
//						XSSFCell cell = row.getCell(j);
//						if(cell == null ){
////							cell.setCellValue("");
//						}else{
//							
//							System.out.println("cell:"+cell);
//							cell.setCellType(cell.CELL_TYPE_STRING);
//							switch (type) {
//							case "森林巡护数据":
//								invokeSetslxh(Slxhrz, row1.getCell(j).getStringCellValue(), cell.getStringCellValue());
//								break;
//								
//							default:
//								break;
//							}
//						}
//
//					}
//					Slxhrz.setUuid(UUID.randomUUID().toString());
////					qxsjb.setQy(dm);
//					Slxhrzsj.add(Slxhrz);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return Slxhrzsj;
//	}
//	
//	public static void invokeSetslxh(Object o, String fieldName, Object value) {
//		System.out.println("fieldName:"+fieldName);
//		switch (fieldName) {
//		case "管护责任区号":
//			fieldName="Ghzrqh";
//			break;
//		case "天气":
//			fieldName="Tq";
//			break;
//		case "巡山日期":
//			fieldName="Xsrq";
//			break;
//		case "防火情况":
//			fieldName="Slfhqk";
//			break;
//		case "有害生物":
//			fieldName="Lyyhswfz";
//			break;
//		case "乱砍伐情况":
//			fieldName="Lklfqk";
//			break;
//		case "非法占地、开矿":
//			fieldName="Ffzl";
//			break;
//		case "其他":
//			fieldName="Qtqk";
//			break;
//		case "经纬度":
//			fieldName="Sh";
//			break;
//		case "位置名称":
//			fieldName="Wzmc";
//			break;
//		case "巡护人":
//			fieldName="Xhr";
//			break;
//		case "类型":
//			fieldName="Type";
//			break;
//		case "年份":
//			fieldName="Nf";
//			break;
//		
//		default:
//			break;
//		}
//		
//		Method setM = null;
//		try {
//			setM = o.getClass().getMethod("set"+fieldName, value.getClass());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("===========setM============");
//		}
//		if(setM != null){
//			try {
//				setM.invoke(o, value);
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("============ setM.invoke()========");
//			}
//		}
//		
//	}
//	
//
//	public static Method getSetMethod(Class objectClass, String fieldName) {
//		try {
//			Class[] parameterTypes = new Class[1];
//			Field field = objectClass.getDeclaredField(fieldName);
//			parameterTypes[0] = field.getType();
//			StringBuffer sb = new StringBuffer();
//			sb.append("set");
//			sb.append(fieldName.substring(0, 1).toUpperCase());
//			sb.append(fieldName.substring(1));
//			Method method = objectClass.getMethod(sb.toString(), parameterTypes);
//			return method;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
