package com.jsu.action.chen;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.jsu.util.ExcelImport;
@Controller
public class ExcelAction {
	@Resource
	private ExcelImport excelImport;
	
      public String excelgift(){
    	  System.out.println("开始导入gift表的数据");
    	  excelImport.creategiftExcel();
		return null;  	  
      }
      
      public String excelorder(){
    	  System.out.println("开始导入order表的数据");
    	  excelImport.createorderExcel();
		return null;  	  
      }
      
	public ExcelImport getExcelImport() {
		return excelImport;
	}
	
	public void setExcelImport(ExcelImport excelImport) {
		this.excelImport = excelImport;
	}
      
}
