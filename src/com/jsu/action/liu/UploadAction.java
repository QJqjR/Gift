package com.jsu.action.liu;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.util.CopyUtil;


@Controller
public class UploadAction {

	private File file;
	private String fileFileName;
	private String json;

	
	public String upload() {
		System.out.println("upload");
		System.out.println(file);
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(request.getParameter("file"));
		System.out.println(request.getParameter("name"));
		request.setAttribute("url", "imageload/" + fileFileName);
		System.out.println(fileFileName);
		String path = ServletActionContext.getServletContext().getRealPath("/imageload/" + fileFileName);
		System.out.println(path);
		System.out.println(ServletActionContext.getServletContext().getRealPath("/imageload"));
		File target = new File(path);
		fileFileName = "/imageload/" + fileFileName;
		CopyUtil.copyFile(file, target);
		return "success";
	}


	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String load() {
		return "success";
	}



	public String getJson() {
		return json;
	}



	public void setJson(String json) {
		this.json = json;
	}
	
	
	
}
