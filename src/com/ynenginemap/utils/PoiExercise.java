package com.ynenginemap.utils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by SunMing on 2016/9/4.
 */
public class PoiExercise {

    private JPanel mainPanel;
    private JButton but;
    private JTextField textFieldExcelPath;
    private JTextField textFieldFilePath;

    public static void main(String[] args){

        JFrame frame = new JFrame("ExcelPictureOutPutTool");
        frame.setContentPane(new PoiExercise().mainPanel);
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setSize(500,200);
        frame.setVisible(true);
    }

    /**
         * Created by SunMing on 2016/9/4.
         * 获取一个表内所有的图片，并根据标签号进行图片的分类存储
         */
    public static void getSheetPictrues07(XSSFSheet sheet, XSSFWorkbook workbook, String Path) throws IOException {

        for (POIXMLDocumentPart dr : sheet.getRelations()) {

            if (dr instanceof XSSFDrawing) {

                XSSFDrawing drawing = (XSSFDrawing) dr;

                java.util.List<XSSFShape> shapes = drawing.getShapes();

                for (XSSFShape shape : shapes) {

                    XSSFPicture pic = (XSSFPicture) shape;

                    XSSFClientAnchor anchor = pic.getPreferredSize();

                    org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker ctMarker = anchor.getFrom();

                    String fileName = sheet.getRow(ctMarker.getRow()-2).getCell(3).toString().substring(5);

                    String pictureName = "unhandle.png";
                    String savePic = Path + fileName;
                    File file = new File(savePic);
                    if(!file.exists()){
                        file.mkdir();
                        pictureName = "handle.png";
                    }
                    String savePath = savePic + "\\" + pictureName;

                    FileOutputStream fos = new FileOutputStream(savePath);

                    XSSFPictureData data = pic.getPictureData();

                    fos.write(data.getData());
                }
            }
        }
    }



    public PoiExercise(){
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ExcelPath = textFieldExcelPath.getText();
                String FilePath = textFieldFilePath.getText();

                File file = new File(ExcelPath);

                try {
                    FileInputStream fis = new FileInputStream(file);
                    XSSFWorkbook workbook = new XSSFWorkbook(fis);
                    XSSFSheet sheet = workbook.getSheetAt(0);

                    File fileRoot = new File(FilePath);
                    if(!fileRoot.exists()){
                        fileRoot.mkdir();
                    }

                    getSheetPictrues07(sheet, workbook,FilePath);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
