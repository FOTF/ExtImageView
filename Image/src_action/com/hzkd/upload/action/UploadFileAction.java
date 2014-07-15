package com.hzkd.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import util.FileUpload;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadFileAction extends ActionSupport{
	
	private static Logger log = Logger.getLogger(UploadFileAction.class);
	
	private File files = null;
	private String title = null;
	private String filesFileName = null;
	private String filesContentType = null;
	
	public String execute(){
		log.info("开始保存文件。。。");
		log.info("文件名称是：" + filesFileName);
		FileUpload.saveFile(filesFileName, files);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		try {
			response.flushBuffer();
		} catch (IOException e) {

		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write("success:true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public File getFiles() {
		return files;
	}
	public void setFiles(File files) {
		this.files = files;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}
	public String getFilesContentType() {
		return filesContentType;
	}
	public void setFilesContentType(String filesContentType) {
		this.filesContentType = filesContentType;
	}
	
	
	

}
