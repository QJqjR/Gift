package com.jsu.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.jsu.dao.chen.DataAnalysisDao;

@Repository
public class DataAnalysis {
    @Resource
    private DataAnalysisDao dataAnalysisDao;
    public List<Object> dataAnalysisrefund(){
		return dataAnalysisDao.findrefund();	
    }
    public List<Object> dataAnalysissexmoneyorder(){
		return dataAnalysisDao.findsexmoneyorder();	
    }
    public List<Object> dataAnalysisagemoneyorder(){
		return dataAnalysisDao.findagemoneyorder();
    }
    public List<Object> dataAnalysisagesexmoneyorder(){
		return dataAnalysisDao.findagesexmoneyorder();
    }
	public DataAnalysisDao getDataAnalysisDao() {
		return dataAnalysisDao;
	}
	public void setDataAnalysisDao(DataAnalysisDao dataAnalysisDao) {
		this.dataAnalysisDao = dataAnalysisDao;
	}
    
}
