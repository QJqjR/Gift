package com.jsu.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import com.jsu.dao.chen.ExcelDao;

@Repository
public class ExcelImport {
	@Resource
	private ExcelDao excelDao;

	public void creategiftExcel() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth((short) 20);
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCell cell = null;
		String[] headers = { "礼品id", "礼品标题", "礼品原价区间", "礼品现价区间", "评论数", "交易数",
				"礼品品牌", "礼品类别id", "礼品类别价格", "礼品类别名", "礼品库存" };
		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
		}
		List<Object[]> lists = excelDao.findgiftexcel();
		int i = 0;
		for (Object[] obj : lists) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < obj.length; j++) {
				row.createCell((short) j).setCellValue(String.valueOf(obj[j]));
			}
			i++;
		}
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			String path = "gift.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ path);
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * try { FileOutputStream fout = new FileOutputStream(
		 * "C:/Users/Administrator/Desktop/Members.xls"); wb.write(fout);
		 * fout.close(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}
	public void createorderExcel() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth((short) 20);
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCell cell = null;
		String[] headers = { "订单id","用户id", "礼品类别名","礼品名", "数量","付款时间", "付款金额", "交易账号",
				"订单状态","地址" ,"备注"};
		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
		}
		List<Object[]> lists = excelDao.findorderexcel();
		int i = 0;
		for (Object[] obj : lists) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < obj.length; j++) {
				if(j==2){
					List<Object> list=excelDao.findgiftparameter(String.valueOf(obj[j]));
					row.createCell((short) j).setCellValue(String.valueOf(list));
				}else if(j==3){
					List<Object> list=excelDao.findgiftname(String.valueOf(obj[j]));
					row.createCell((short) j).setCellValue(String.valueOf(list));
				}else{
					row.createCell((short) j).setCellValue(String.valueOf(obj[j]));
				}
				
			}
			i++;
		}
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			String path = "order.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ path);
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * try { FileOutputStream fout = new FileOutputStream(
		 * "C:/Users/Administrator/Desktop/Members.xls"); wb.write(fout);
		 * fout.close(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}
	public ExcelDao getExcelDao() {
		return excelDao;
	}

	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}

}