package com.ynenginemap.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * @author HCY
 * @version 创建时间：2018年9月18日 下午9:48:20
 * 类说明
 */
public class ExportExcelYhdz {


	public Map<String, Object> ecportExcelYhdz(HSSFWorkbook workbook,HSSFSheet sheet,List<Map<String, Object>> list){
		Map<String, Object> map0 = new HashMap<String, Object>();
		try {
//			Map<String, Object> map0 = new HashMap<String, Object>();
			  //构建数据源中的key值
			String[] keysStrings={"序号","应拆老屋户主","应拆宗数","违法地点","应拆老屋建筑面积(㎡)","应拆老屋相邻现状","备注"};

			List<Map<String, String>> dataSourceList=new ArrayList<Map<String,String>>();
			List<Map<String, String>> dataSourceList1=new ArrayList<Map<String,String>>();
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("序号", "序号");
			map1.put("应拆老屋户主", "性别");
			map1.put("应拆宗数", "应拆宗数");
			map1.put("违法地点", "违法地点");
			map1.put("应拆老屋建筑面积(㎡)", "应拆老屋建筑面积(㎡)");
			map1.put("应拆老屋相邻现状", "应拆老屋相邻现状");
			map1.put("备注", "备注");
			dataSourceList1.add(map1);
			for(int i=0;i<list.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("序号", i+1+"");
				map.put("应拆老屋户主", (String)list.get(i).get("hzmc"));
//				map.put("应拆宗数", (String)list.get(i).get("yczs"));
				map.put("应拆宗数", "1");
				map.put("违法地点", (String)list.get(i).get("jzdd"));
				map.put("应拆老屋建筑面积(㎡)", (String)list.get(i).get("wzjzmj"));
				map1.put("应拆老屋相邻现状", (String)list.get(i).get("xlqk"));
				map.put("备注", (String)list.get(i).get("bz"));
				dataSourceList.add(map);
			}
			String tbdw = (String) list.get(0).get("jddw");
			String tbsj = (String) list.get(0).get("cjsj");
			yhdz(workbook,dataSourceList,dataSourceList1,keysStrings,tbdw,tbsj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map0;
	}
	

	//往非法“一户多宅”调查登记表写入数据
	public void yhdz(HSSFWorkbook workBook,List<Map<String, String>> dataSource,
			List<Map<String, String>> dataSource1, String[] keyNames,
			String tbdw,String tbsj) throws Exception {

		  // 设置边框样式
		HSSFCellStyle style = workBook.createCellStyle();
		  style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		  style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		  style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		  style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		  // 设置边框样式的颜色
		  style.setBottomBorderColor(HSSFColor.BLACK.index);
		  style.setLeftBorderColor(HSSFColor.BLACK.index);
		  style.setRightBorderColor(HSSFColor.BLACK.index);
		  style.setTopBorderColor(HSSFColor.BLACK.index);
		  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中  
		  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		  style.setWrapText(true);//设置换行
		  HSSFFont font = workBook.createFont();
		  font.setFontName("宋体");
		  font.setFontHeightInPoints((short) 11);//设置字体大小
		  style.setFont(font);//选择需要用到的字体格式  
		  
		  //获取创建的工作簿第一页
		  HSSFSheet sheet = workBook.getSheetAt(0);
		  // 创建行
		  HSSFRow row = null;
		  // 创建列
		  HSSFCell cell = null;
	  	  row=sheet.createRow(0);//非法“一户多宅”调查登记表
	  	  addMerged(sheet,workBook,0, 0, (short) 0, (short) 6);
	      cell = row.createCell(0); 
	      cell.setCellStyle(style); 
	      cell.setCellValue("非法“一户多宅”调查登记表");
	  	  row=sheet.createRow(2);//填报单位
	  	  addMerged(sheet,workBook,2, 2, (short) 0, (short) 4);
	      cell = row.createCell(0); 
	      cell.setCellStyle(style); 
		  	String[] str = {};
		  	if(tbdw != null && !"".equals(tbdw)){
			  	str = tbdw.split(",");
		  	}
		  	tbdw = str[str.length-2]+"镇(街道)"+str[str.length-1]+"(社区)";
	      cell.setCellValue("填报单位（盖章）："+tbdw);
//	  	  row.getCell(0).setCellValue("填报单位（盖章）："+tbdw);
	  	  addMerged(sheet,workBook,2, 2, (short) 5, (short) 6);
	     cell = row.createCell(5); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("填报时间："+tbsj);
//	  	row.getCell(5).setCellValue("填报时间："+tbsj);
	  	row=sheet.createRow(3);
	  	addMerged(sheet,workBook,3, 5, (short) 0, (short) 0);
	     cell = row.createCell(0); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("序号");
	  	addMerged(sheet,workBook,3, 5, (short) 1, (short) 1);
	     cell = row.createCell(1); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("应拆老屋户主");
	  	addMerged(sheet,workBook,3, 5, (short) 2, (short) 2);
	     cell = row.createCell(2); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("应拆宗数");
	  	addMerged(sheet,workBook,3, 5, (short) 3, (short) 3);
	     cell = row.createCell(3); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("违法地点");
	  	addMerged(sheet,workBook,3, 5, (short) 4, (short) 4);
	     cell = row.createCell(4); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("应拆老屋建筑面积(㎡)");
	  	addMerged(sheet,workBook,3, 5, (short) 5, (short) 5);
	     cell = row.createCell(5); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("应拆老屋相邻现状");
	  	addMerged(sheet,workBook,3, 5, (short) 6, (short) 6);
	     cell = row.createCell(6); 
	     cell.setCellStyle(style); 
	     cell.setCellValue("备注");

	  	// 遍历数据源 开始写入数据(因为Excel中是从0开始,所以减一)
	  	int num = 6;
	  	for (Map<String, String> item : dataSource) {
		    // 循环遍历,新建行
		    row = sheet.createRow(num);
		    //判断有多少列数据
			     // 设置每列的数据   设置每列的样式   设置每列的值
		     cell = row.createCell(0); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("序号"));
		     cell = row.createCell(1); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("应拆老屋户主"));
		     cell = row.createCell(2); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("应拆宗数"));
		     cell = row.createCell(3); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("违法地点"));
		     cell = row.createCell(4); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("应拆老屋建筑面积(㎡)"));
		     cell = row.createCell(5); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("应拆老屋相邻现状"));
		     cell = row.createCell(6); 
		     cell.setCellStyle(style); 
		     cell.setCellValue(item.get("备注"));
		    num++;
	   }
	}


	private static void addMerged(HSSFSheet sheet,HSSFWorkbook wb,int a,int b, short c, short d)      
           throws Exception {    
	  	CellRangeAddress region1=new CellRangeAddress(a,b,c,d); 
		sheet.addMergedRegion(region1);
		setBorder(region1,sheet,wb);
   }      

	public static void setBorder(CellRangeAddress cellRangeAddress, HSSFSheet sheet,  
			HSSFWorkbook wb) throws Exception {  
	    RegionUtil.setBorderLeft(1, cellRangeAddress, sheet, wb);  
	    RegionUtil.setBorderBottom(1, cellRangeAddress, sheet, wb);  
	    RegionUtil.setBorderRight(1, cellRangeAddress, sheet, wb);  
	    RegionUtil.setBorderTop(1, cellRangeAddress, sheet, wb);  
	} 
}
