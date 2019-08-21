package com.jsu.action.chen;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.util.DataAnalysis;

@Controller
public class DataAnalysisAction {
	@Resource
	private DataAnalysis dataAnalysis;
	private String jsondata;
	private List<Object> list;

	public String dataAnalysisrefund() {
		list = dataAnalysis.dataAnalysisrefund();
		JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		if (list.size() > 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		} else {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		}
		jsondata=obj.toString();
		System.out.println(jsondata);
		return "success";
	}
	public String dataAnalysissexmoneyorder() {
		list = dataAnalysis.dataAnalysissexmoneyorder();
		JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		if (list.size() > 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		} else {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		}
		jsondata=obj.toString();
		System.out.println(jsondata);
		return "success";
	}
	public String dataAnalysisagemoneyorder() {
		list = dataAnalysis.dataAnalysisagemoneyorder();
		JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		if (list.size() > 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		} else {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		}
		jsondata=obj.toString();
		System.out.println(jsondata);
		return "success";
	}
	public String dataAnalysisagesexmoneyorder() {
		list = dataAnalysis.dataAnalysisagesexmoneyorder();
		JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		if (list.size() > 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		} else {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("results", json);
		}
		jsondata=obj.toString();
		System.out.println(jsondata);
		return "success";
	}
	public DataAnalysis getDataAnalysis() {
		return dataAnalysis;
	}

	public void setDataAnalysis(DataAnalysis dataAnalysis) {
		this.dataAnalysis = dataAnalysis;
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}
