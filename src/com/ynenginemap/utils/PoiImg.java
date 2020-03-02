package com.ynenginemap.utils;

import java.awt.image.BufferedImage;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;  
  


import javax.imageio.ImageIO;  
  


import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;  
import org.apache.poi.hssf.usermodel.HSSFPatriarch;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
  
/**
 * @author HCY
 * @version 创建时间：2018年9月18日 下午7:33:21
 * 类说明
 */
public class PoiImg {

    public static void main(String[] args) {  
  
        FileOutputStream fileOut = null;  
        BufferedImage bufferImg = null;//图片一  
        BufferedImage bufferImg1 = null;//图片二  
        try {  
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray  
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();  
            ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();  
              
            //将两张图片读到BufferedImage  
            bufferImg = ImageIO.read(new File("F:/FileUpImg/0a3ac53a-c37b-48fb-9649-ae309de672b7.png"));  
            bufferImg1 = ImageIO.read(new File("F:/FileUpImg/0aa7fc37-717e-4787-93a5-43cdb0792564.png"));  
            ImageIO.write(bufferImg, "png", byteArrayOut);  
//            ImageIO.write(bufferImg1, "png", byteArrayOut1);  

        	//创建excel文件
      		String pathExcel = "F:/tomcat/apache-tomcat-7.0.82/webapps/ImageServer/WebRoot/EcportExcel2/";  
//      //创建Excel工作簿;      
            // 创建一个工作薄  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(pathExcel+"/1537269810144.xlsx"));  
            //创建一个sheet  
            HSSFSheet sheet = wb.createSheet("out put excel");  
  
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
            /** 
             * 该构造函数有8个参数 
             * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离 
             * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum， 
             * excel中的cellNum和rowNum的index都是从0开始的 
             *  
             */  
            //图片一导出到单元格B2中  
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,  
                    (short)  4, 15, (short) 9, 29);
            anchor.setAnchorType(3);     
//            //图片二导出到单元格C3到E5中，且图片的left和top距离边框50  
//            HSSFClientAnchor anchor1 = new HSSFClientAnchor(50, 50, 0, 0,  
//                    (short) 10, 15, (short) 16, 29);  
  
            // 插入图片  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut  
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));  
//            patriarch.createPicture(anchor1, wb.addPicture(byteArrayOut1  
//                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));  
//  
            fileOut = new FileOutputStream(pathExcel+"/153.xlsx");  
            // 写入excel文件  
            wb.write(fileOut);  
        } catch (IOException io) {  
            io.printStackTrace();  
            System.out.println("io erorr : " + io.getMessage());  
        } finally {  
            if (fileOut != null) {  
                try {  
                    fileOut.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}
