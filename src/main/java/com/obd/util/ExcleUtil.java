package com.obd.util;

import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 导出excle工具类
 * @author shuebi.liu
 * @version 1.0 at 2013-9-6
 * @since 1.0
 *
 */
public class ExcleUtil {
	
 /**
  * 导出excle          
  * @param fileName
  * @param sheetname
  * @param header
  * @param titles
  * @param vect
  * @param request
  * @param response
  * @throws Exception
  */
public static void ExportToExcel(
		        String fileName,
				String sheetname,
				String header,
				String[] titles, 
				Vector<Object> vect,
				HttpServletRequest request,
				HttpServletResponse response
				) throws Exception { 
	ServletOutputStream servletOS=null;
	try{
		servletOS = response.getOutputStream();
		//清空输出流
		response.reset();
		response.setHeader( "Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )+".xls" );
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		//单元格格式1
		WritableCellFormat format = new WritableCellFormat();
		//把水平对齐方式指定为居中 
		format.setAlignment(jxl.format.Alignment.CENTRE); 
		//把垂直对齐方式指定为居中 
		format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12, 
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, 
                Colour.BLACK);
		format.setFont(font);
		 //单元格格式2
		WritableCellFormat format2 = new WritableCellFormat();
		//把水平对齐方式指定为居中 
		format2.setAlignment(jxl.format.Alignment.CENTRE); 
		//把垂直对齐方式指定为居中 
		format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		//字体
		font = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		format2.setFont(font);
		format2.setBackground(Colour.GRAY_25);
		//单元格格式3
		   WritableCellFormat format3 = new WritableCellFormat();
		   format3.setAlignment(jxl.format.Alignment.CENTRE); 
		   //把垂直对齐方式指定为居中 
		   format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		   font = new WritableFont(WritableFont.createFont("宋体"), 10, 
                  WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, 
                  Colour.BLACK);
		   format3.setFont(font);
		// 创建一个工作文件
		WritableWorkbook writableWorkbook = Workbook.createWorkbook(servletOS);
		// 创建一个工作簿
		WritableSheet sheet1 = writableWorkbook.createSheet(sheetname, 0);
		// 设置行的值
		sheet1.mergeCells(0,0,titles.length-1,0);
		sheet1.setRowView(0, 700);
		sheet1.setRowView(1, 400);
		//合并单元格
		sheet1.addCell(new Label(0, 0, header , format2));
		for (int i = 0; i < titles.length; i++) {
			sheet1.addCell(new jxl.write.Label(i, 1, titles[i], format));
		} 
		       for (int i = 2; i < vect.size() + 2; i++) {
		    	     Object[] sdata = (Object[]) vect.elementAt(i-2);
		    	     sheet1.setRowView(i, 300);
		           for (int j = 0; j < sdata.length; j++) { 
		        	   // 在索引0的位置创建单元格（左上端） 
		        	   sheet1.setColumnView(j, sdata[j].toString().length()*2);
		        	   sheet1.addCell(new Label(j, i, (String)sdata[j], format3));
		           } 
		       } 
							  writableWorkbook.write();
							  writableWorkbook.close();
					            	   }catch (Exception e) {
					   e.printStackTrace();
					  }
					  finally{
						    if( servletOS !=null)
						    	servletOS.close();
					        }
		  }
}
