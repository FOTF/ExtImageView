package com.hzkd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import util.ProxyHttpUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ImageListAction extends ActionSupport {
	
	public String execute() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(ProxyHttpUtil.getImageListInfor(null));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
