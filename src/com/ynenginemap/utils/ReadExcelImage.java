package com.ynenginemap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;


public class ReadExcelImage {

	//从03格式excel中获取图片
	public static Map<String, PictureData> getSheetPictrues03(int sheetNum,  
	            HSSFSheet sheet, HSSFWorkbook workbook) {  
	        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();  
	        List<HSSFPictureData> pictures = workbook.getAllPictures();  
	        if (pictures.size() != 0) {  
	            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {  
	                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();  
	                if (shape instanceof HSSFPicture) {  
	                    HSSFPicture pic = (HSSFPicture) shape;  
	                    int pictureIndex = pic.getPictureIndex() - 1;  
	                    HSSFPictureData picData = pictures.get(pictureIndex);  
	                    String picIndex = String.valueOf(sheetNum) + "_"  
	                            + String.valueOf(anchor.getRow1()) + "_"  
	                            + String.valueOf(anchor.getCol1());  
	                    sheetIndexPicMap.put(picIndex, picData);  
	                }  
	            }  
	            return sheetIndexPicMap;  
	        } else {  
	            return null;  
	        }  
	    }

	 //07格式excel获取图片。
	public static Map<String, PictureData> getSheetPictrues07(int sheetNum,  
	            XSSFSheet sheet, XSSFWorkbook workbook) {  
	        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();  

	        for (POIXMLDocumentPart dr : sheet.getRelations()) {  
	            if (dr instanceof XSSFDrawing) {  
	                XSSFDrawing drawing = (XSSFDrawing) dr;  
	                List<XSSFShape> shapes = drawing.getShapes();  
	                for (XSSFShape shape : shapes) {  
	                    XSSFPicture pic = (XSSFPicture) shape;  
	                    XSSFClientAnchor anchor = pic.getPreferredSize();  
	                    CTMarker ctMarker = anchor.getFrom();  
	                    String picIndex = String.valueOf(sheetNum) + "_"  
	                            + ctMarker.getRow() + "_" + ctMarker.getCol();
//	                    pic.getPictureData().getData();
	                    sheetIndexPicMap.put(picIndex, pic.getPictureData());  
	                }  
	            }  
	        }  

	        return sheetIndexPicMap;  
	    }

	//图片及位置获取

	public static Map<String,Object> getAllDate(String excelPath) throws InvalidFormatException, IOException {  
	        // 创建文件  
	        File file = new File(excelPath);  
	        // 创建流  
	        InputStream input = new FileInputStream(file);  
	        // 获取文件后缀名  
	        String fileExt =  file.getName().substring(file.getName().lastIndexOf(".") + 1);  
	        // 创建Workbook  
	        Workbook wb = null;  
	        // 创建sheet  
	        Sheet sheet = null;  
	        //根据后缀判断excel 2003 or 2007+  
	        if (fileExt.equals("xls")) {  //2003
	            wb = (HSSFWorkbook) WorkbookFactory.create(input);  
	        } else {  //2007
	            wb = new XSSFWorkbook(input);  
	        }  
	        //获取excel sheet总数  
	        int sheetNumbers = wb.getNumberOfSheets();  
	        // sheet list  
	        List<Map<String, PictureData>> sheetList = new ArrayList<Map<String, PictureData>>();  
	        // 循环sheet  
	        for (int i = 0; i < sheetNumbers; i++) {  
	            sheet = wb.getSheetAt(i);  
	            // map等待存储excel图片  
	            Map<String, PictureData> sheetIndexPicMap;   
	            // 判断用07还是03的方法获取图片  
	            if (fileExt.equals("xls")) {  
	                sheetIndexPicMap = getSheetPictrues03(i, (HSSFSheet) sheet, (HSSFWorkbook) wb);  
	            } else {  
	                sheetIndexPicMap = getSheetPictrues07(i, (XSSFSheet) sheet, (XSSFWorkbook) wb);  
	            }  
	            // 将当前sheet图片map存入list  
	            sheetList.add(sheetIndexPicMap);  
	        }  
//	        Map map = getData(excelPath);
	        String fileImgPath = "F:/FileUpImg";
	        Map<String,Object> map = printImg(fileImgPath,sheetList);
	        return map;

	    } 

	//将图片保存到指定位置
	 public static Map<String,Object> printImg(String path,List<Map<String, PictureData>> sheetList) throws IOException {
		 Map<String,Object> map0 = new HashMap<String,Object>();
	        for (Map<String, PictureData> map : sheetList) {  
	            Object key[] = map.keySet().toArray();  
	            for (int i = 0; i < map.size(); i++) { 
	            	Map<String,Object> map1 = new HashMap<String,Object>();
	                // 获取图片流  
	                PictureData pic = map.get(key[i]);  
	                // 获取图片索引  
//	                String picName = key[i].toString();  
	                // 获取图片格式  
	                String ext = pic.suggestFileExtension();  
	                byte[] data = pic.getData();  
	                String imgId = UUID.randomUUID().toString();
	                String imgGs = "." + ext;
			    	// 文件保存路径  
			    	File files=new File(path);
			    	if(!files.exists()){//不存在创建
						files.mkdirs();
					}
	                String imgPath = path+imgId+imgGs;
	                FileOutputStream out = new FileOutputStream(imgPath);  
	                out.write(data);  
	                out.close();  
	                map1.put("imgGs", imgGs);
	                map1.put("imgId", imgId);
	                map1.put("imgPath", imgPath);
	                map0.put((String)key[i], map1);
	            }  
	        }
			return map0;  

	    }
	 

	    public static void main(String[] args) throws Exception, Exception { 
	        try {  
	        	String filePath = "F:/徐三村合法一户一档信息表（表一）.xls";
//	        	String filePath = "F:/徐三村存量违法建筑调查登记表（表三）.xlsx";
//	        	String filePath = "F:/徐三村非法“一户多宅”调查登记表..xls";
//	        	String filePath = "F:/徐三村违法建筑调查摸底表和分类处置登记表（表二）.xlsx";
	            Map<String, Object> map = getAllDate(filePath);
	            Object key[] = map.keySet().toArray();  
	            System.out.println("key[0] = "+key[0]);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	    }
	    
	    
}
