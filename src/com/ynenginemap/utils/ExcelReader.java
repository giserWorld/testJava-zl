package com.ynenginemap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {  
    /** 
     * 对外提供读取excel 的方法 
     * */  
    public static List<List<Object>> readExcel(File file,Workbook wb,int i) throws IOException {  
        String fileName = file.getName();  
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName  
                .substring(fileName.lastIndexOf(".") + 1);  
        if ("xls".equals(extension)) {  
            HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
            return read2003Excel(hwb,i);
        } else if ("xlsx".equals(extension)) {  
        	XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));  
            return read2007Excel(xwb,i);  
        } else {  
            throw new IOException("不支持的文件类型");  
        }  
    }  
    /**
     * 对外读取excel图片的方法
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     */
    public static  Map<String, PictureData> readExcelImage(File file,Workbook wb) throws IOException, EncryptedDocumentException, InvalidFormatException{ 
//        // 创建流  
        InputStream input = new FileInputStream(file);  
        // 获取文件后缀名  
        String fileName = file.getName();  
        String fileExt = fileName.lastIndexOf(".") == -1 ? "" : fileName  
                .substring(fileName.lastIndexOf(".") + 1);  
//        // 创建Workbook  
//        Workbook wb = null;  
//        // 创建sheet  SSS
        Sheet sheet = null;  
//        //根据后缀判断excel 2003 or 2007+  
        if (fileExt.equals("xls")) {  
            wb = (HSSFWorkbook) WorkbookFactory.create(input);  
        } else {  
            wb = new XSSFWorkbook(input);  
        }  
        //获取excel sheet总数  
//        int sheetNumbers = wb.getNumberOfSheets();  
        // sheet list  
        List<Map<String, PictureData>> sheetList = new ArrayList<Map<String, PictureData>>();  
//         int imageNum = 0; 
        // 循环sheet  
//        for (int i = 0; i < sheetNumbers; i++) {  
            sheet = wb.getSheetAt(0);  
            // map等待存储excel图片  
            Map<String, PictureData> sheetIndexPicMap;   
//            if(sheet.getLastRowNum() == 0){//若有效行数为零则该工作簿没记录
//            	continue;
//            }
            // 判断用07还是03的方法获取图片  
            if (fileExt.equals("xls")) {  
                sheetIndexPicMap = getSheetPictrues03((HSSFSheet) sheet, (HSSFWorkbook) wb);  
            } else {  
//                sheetIndexPicMap = getSheetPictrues07((XSSFSheet) sheet, (XSSFWorkbook) wb); 
//            	sheetIndexPicMap = getSheetPictrues07_1((XSSFSheet) sheet, (XSSFWorkbook) wb);  
            	sheetIndexPicMap = getSheetPictrues07_2((XSSFSheet) sheet, (XSSFWorkbook) wb);  
            }  
            // 将当前sheet图片map存入list  
//            imageNum = imageNum + sheetIndexPicMap.size();//得到图片数量
            sheetList.add(sheetIndexPicMap);  
//        }  
//        printImg(sheetList,path);  
        return sheetIndexPicMap;
    }
    
    /** 
     * 读取 office 2003 excel 
     * @throws IOException 
     * @throws FileNotFoundException 
     */  
    private static List<List<Object>> read2003Excel(Workbook hwb,int k)  
            throws IOException {  
        List<List<Object>> list = new LinkedList<List<Object>>();  
//        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));  
//        int  a =hwb.getNumberOfSheets();
//        System.out.println("a:"+a);
        HSSFSheet sheet = (HSSFSheet) hwb.getSheetAt(k);  
        Object value = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        int counter = 0;  
        for (int i = sheet.getFirstRowNum(); counter < sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            } else {  
                counter++;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0");// 格式化数字  
                switch (cell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  //字符串型
                   // System.out.println(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    break;  
                case XSSFCell.CELL_TYPE_NUMERIC:  //数值型
                    //System.out.println(i + "行" + j  
                          //  + " 列 is Number type ; DateFormt:"  
                       //     + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
                        value = df.format(cell.getNumericCellValue());  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormatString())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  //布尔型
                    //System.out.println(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK: //空值 
                    // System.out.println(i + "行" + j + " 列 is Blank type");  
                     value = "";  
                     break; 
                case XSSFCell.CELL_TYPE_FORMULA:  //公式型
                    // System.out.println(i + "行" + j + " 列 is Blank type");  
//                     value = cell.getCellFormula() + ""; 
//                     System.out.println("CELL_TYPE_FORMULA = "+value);
                    try {
                    	double dou = cell.getNumericCellValue();
                    	int aa = Integer.parseInt(new java.text.DecimalFormat("0").format(dou));
        	         	value = String.valueOf(aa);
                	} catch (IllegalStateException e) {
                	    value = String.valueOf(cell.getRichStringCellValue());
                	}  
//                    System.out.println("CELL_TYPE_FORMULA = "+value);
                     break;  
                default:  
                    //System.out.println(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                }  
             /*   if (value == null || "".equals(value)) {  
                    continue;  
                }  */
                linked.add(value);  
            }  
            list.add(linked);  
        }  
        return list;  
    }  
    /** 
     * 读取Office 2007 excel 
     * */  
    private static List<List<Object>> read2007Excel(Workbook xwb,int g)  
            throws IOException {  
        List<List<Object>> list = new LinkedList<List<Object>>();  
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径  
//        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file)); 
        int  a =xwb.getNumberOfSheets();
        System.out.println("a:"+a);
        // 读取第一章表格内容  
        XSSFSheet sheet = (XSSFSheet) xwb.getSheetAt(g);  
        Object value = null;  
        XSSFRow row = null;  
        XSSFCell cell = null;  
        int counter = 0;  
        for (int i = sheet.getFirstRowNum(); counter < sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            } else {  
                counter++;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
                switch (cell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  
                    //System.out.println(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    break;  
                case XSSFCell.CELL_TYPE_NUMERIC:  
                    //System.out.println(i + "行" + j  
                          //  + " 列 is Number type ; DateFormt:"  
                         //   + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
                        value = df.format(cell.getNumericCellValue());  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormatString())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  
                    //System.out.println(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK:  
                   // System.out.println(i + "行" + j + " 列 is Blank type");  
                    value = "";  
                    break;  
                case XSSFCell.CELL_TYPE_FORMULA:  //公式型
                    // System.out.println(i + "行" + j + " 列 is Blank type");  
//                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);  
//                    value = cell.getCellFormula() + ""; 
//                    value = String.valueOf(cell.getNumericCellValue());
                    try {
                    	double dou = cell.getNumericCellValue();
                    	int aa = Integer.parseInt(new java.text.DecimalFormat("0").format(dou));
        	         	value = String.valueOf(aa);
                	} catch (IllegalStateException e) {
                	    value = String.valueOf(cell.getRichStringCellValue());
                	}  
//                    System.out.println("CELL_TYPE_FORMULA = "+value);      
                     break;  
                default:  
                   // System.out.println(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                }  
          /*      if (value == null || "".equals(value)) {  
                    continue;  
                }  */
                linked.add(value);  
            }  
            list.add(linked);  
        }  
        return list;  
    }
    
    
    /** 
     * 获取Excel2003图片 
     * @param sheetNum 当前sheet编号 
     * @param sheet 当前sheet对象 
     * @param workbook 工作簿对象 
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData 
     * @throws IOException 
     */  
    public static Map<String, PictureData> getSheetPictrues03(  
            HSSFSheet sheet, HSSFWorkbook workbook) {  
  
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();  
        List<HSSFPictureData> pictures = workbook.getAllPictures();  
        if (pictures.size() != 0) {  
//        	int i = 0;
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {  
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();  
                if (shape instanceof HSSFPicture) {  
                    HSSFPicture pic = (HSSFPicture) shape;  
                    int pictureIndex = pic.getPictureIndex() - 1;  
                    HSSFPictureData picData = pictures.get(pictureIndex);  
                    String picIndex = String.valueOf(anchor.getRow1()) + "_"  
                            + String.valueOf(anchor.getCol1());  
                    sheetIndexPicMap.put(picIndex+"", picData);
//                    i++;
                }  
            }  
            return sheetIndexPicMap;  
        } else {  
            return null;  
        }  
    }  

    /** 
     * 获取Excel2007图片 
     * @param sheetNum 当前sheet编号 
     * @param sheet 当前sheet对象 
     * @param workbook 工作簿对象 
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData 
     */  
    public static Map<String, PictureData> getSheetPictrues07(  
            XSSFSheet sheet, XSSFWorkbook workbook) {  
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();  
        
        List<XSSFPictureData> pictures = workbook.getAllPictures();
        
       for (int i = 0; i < pictures.size(); i++) {
	         XSSFPictureData pictureData = pictures.get(i);
//	         byte[] data = pictureData.getData();
//	        String ext = pictureData.suggestFileExtension();//获取扩展名
//	         String picIndex = imageID[i];
	        sheetIndexPicMap.put(i+"", pictureData);
      }
        
//        XSSFSheet xSheet = (XSSFSheet) db.getSheet();
//        XSSFDrawing drawing = xSheet.getDrawingPatriarch();
//        List<XSSFShape> xShapeList = drawing.getShapes();
        
        
        
//        for (POIXMLDocumentPart dr : sheet.getRelations()) {  
//            if (dr instanceof XSSFDrawing) {  
////                XSSFDrawing drawing = (XSSFDrawing) dr;  
//            	XSSFDrawing drawing = sheet.getDrawingPatriarch(); 
//                List<XSSFShape> shapes = drawing.getShapes();  
//                for (XSSFShape shape : shapes) {  
//                    XSSFPicture pic = (XSSFPicture) shape;  
//                    XSSFClientAnchor anchor = pic.getPreferredSize();  
//                    CTMarker ctMarker = anchor.getFrom();  
//                    String picIndex = String.valueOf(sheetNum) + "_"  
//                            + ctMarker.getRow() + "_" + ctMarker.getCol();  
//                    sheetIndexPicMap.put(picIndex, pic.getPictureData());  
//                }  
//            }  
//        }  
  
        return sheetIndexPicMap;  
    }  
    

    /** 
     * 获取Excel2007图片 
     * @param sheetNum 当前sheet编号 
     * @param sheet 当前sheet对象 
     * @param workbook 工作簿对象 
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData 
     */  
    public static Map<String, PictureData> getSheetPictrues07_1(  
            XSSFSheet sheet, XSSFWorkbook workbook) {  
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();  
        if (sheet instanceof XSSFSheet) {
            XSSFSheet sheetTemp = (XSSFSheet) sheet;
            //此形状组中的形状列表
            if(sheetTemp.getDrawingPatriarch() != null){
            	for (XSSFShape shape : sheetTemp.getDrawingPatriarch().getShapes()) {
//                  若果形状组是图片 还有自定义图形的情况。
                  if (shape instanceof XSSFPicture) {
                      XSSFPicture picture = (XSSFPicture) shape;
//               获取图片的锚点
                      XSSFAnchor anchor = picture.getAnchor();
                      if (anchor instanceof XSSFClientAnchor) {
  	           	         XSSFPictureData pictureData = picture.getPictureData();
                          int row1 = ((XSSFClientAnchor) anchor).getRow1();
                          int cell1 = ((XSSFClientAnchor) anchor).getCol1();
  	        	        sheetIndexPicMap.put(row1+"_"+cell1, pictureData);
                      }
                  }
              }
            }
            
        }
  
        return sheetIndexPicMap;  
    }  
    
    /**
     * 获取Excel2007图片
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getSheetPictrues07_2(
            XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
 
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
//                    XSSFPicture pic = (XSSFPicture) shape;
//                    XSSFPicture picture = (XSSFPicture) shape;
////             获取图片的锚点
//                    XSSFAnchor anchor = picture.getAnchor();
////                    XSSFClientAnchor anchor = pic.getPreferredSize();
////                    CTMarker ctMarker = anchor.getFrom();
////                    String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
////                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
//                    if (anchor instanceof XSSFClientAnchor) {
// 	           	         XSSFPictureData pictureData = picture.getPictureData();
//                         int row1 = ((XSSFClientAnchor) anchor).getRow1();
//                         int cell1 = ((XSSFClientAnchor) anchor).getCol1();
// 	        	        sheetIndexPicMap.put(row1+"_"+cell1, pictureData);
//                     }

                    if (shape instanceof XSSFPicture) {
                        XSSFPicture picture = (XSSFPicture) shape;
//                 获取图片的锚点
                        XSSFAnchor anchor = picture.getAnchor();
                        if (anchor instanceof XSSFClientAnchor) {
                            int row1 = ((XSSFClientAnchor) anchor).getRow1();
                            int cell1 = ((XSSFClientAnchor) anchor).getCol1();
     	        	        sheetIndexPicMap.put(row1+"_"+cell1, picture.getPictureData());
                        }
                    }
                }
            }
        }
 
        return sheetIndexPicMap;
    }

    
    /**
     * 将图片输出到指定位置
     * @param sheetList
     * @throws IOException
     */
    public static void printImg(Map<String, PictureData> map,String path,String[] imageId) throws IOException {  
          
//        for (Map<String, PictureData> map : sheetList) {  
            Object key[] = map.keySet().toArray();  
            for (int i = 0; i < map.size(); i++) {  
                // 获取图片流  
                PictureData pic = map.get(key[i]);  
                // 获取图片索引  
//                String picName = key[i].toString();  
                String picName = imageId[i];  
                // 获取图片格式  
                String ext = pic.suggestFileExtension();
                if("jpg".equals(ext)){
                	ext = "jpg";
                }
                  
                byte[] data = pic.getData();  
                  
                FileOutputStream out = new FileOutputStream(path + "\\" + picName + "." + ext);  
                out.write(data);  
                out.close();  
            }  
//        }  
          
    } 
    
    
    public static  Map<Object,Object> getWorkbook(File file) throws Exception{
    	// 创建流  
        InputStream input = new FileInputStream(file);  
          
        // 获取文件后缀名  
        String fileExt =  file.getName().substring(file.getName().lastIndexOf(".") + 1);  
          
        // 创建Workbook  
        Workbook wb = null;  
          
        // 创建sheet  
//        Sheet sheet = null;  
  
        //根据后缀判断excel 2003 or 2007+  
        if (fileExt.equals("xls")) {  
            wb = (HSSFWorkbook) WorkbookFactory.create(input);  
        } else {  
            wb = new XSSFWorkbook(input);  
        }  
          
        Map<Object,Object> map = new HashMap<Object,Object>();
        //获取excel sheet总数  
        int sheetNumbers = wb.getNumberOfSheets();
        map.put("number", sheetNumbers);
        map.put("Workbook", wb);
        
        return map;
    }
    
    
    public static void main(String[] args) throws Exception, Exception { 
    	
//    	copyFile(new File("C:\\Users\\admin\\Documents\\专项审核工作报表_带校验功能.xls"), new File("C:\\Users\\admin\\Documents\\专项审核工作报表_带校验功能.xlsx"));
        try {  
        	String filePath = "F:/徐三村合法一户一档信息表（表一）.xls";
//        	String filePath = "F:/徐三村存量违法建筑调查登记表（表三）.xlsx";
//        	String filePath = "F:/徐三村非法“一户多宅”调查登记表..xls";
//        	String filePath = "F:/徐三村违法建筑调查摸底表和分类处置登记表（表二）.xlsx";
            File excelFile = new File(filePath);
//        	List<List<Object>> list =   readExcel(excelFile, null, 0); 
//        	System.out.println(list.size());
//        	for(int i=0;i<list.size();i++){
//        		String[] s1 =	list.get(i).toString().replace("[", "").replace("]", "").split(",");
//        		for(String str : s1){
//        			System.out.print(" | "+str);
//        		}
//        		System.out.println();
//        	}
            Map<String, PictureData> map = readExcelImage(excelFile,null); 
            System.out.println("0 = "+map.get("0"));
            System.out.println("1 = "+map.get("1"));
            System.out.println("2 = "+map.get("2"));
            System.out.println("30 = "+map.get("30"));
            System.out.println(map.size());
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
    
    
    /**
	 * 把指定的文件复制到指定的位置
	 * 
	 * @param src 上传的文件
	 * @param dest 指定的文件夹
	 */
	@SuppressWarnings("unused")
	private static void copyFile(File src, File dest) {
		try {
			InputStream fis = new FileInputStream(src);
			OutputStream fos = new FileOutputStream(dest);

			byte[] buf = new byte[5 * 1024];

			while (fis.read(buf) != -1) {
				fos.write(buf);
			}

			fis.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
} 