package com.hzkd.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CheckProgressInfroAction extends ActionSupport {
	
	private static Logger log = Logger.getLogger(CheckProgressInfroAction.class);
	
	public int count = 100;
	public static int index = 0;

	public String execute() {
		
		log.info("*****************************************************************");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		JsonGenerator jsonGenerator = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    OutputStream os = null;
		try {
			os = response.getOutputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	    try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(os, JsonEncoding.UTF8);
			jsonGenerator.writeObject(new InforBean(index++, count));
			
			jsonGenerator.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/*String json = "[{'index':56}, {'count' : 100}]";
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {

		}*/
		System.out.println("---success");
		return null;
	}

}

class InforBean {
	private int index;
	private int count;

	public InforBean(int index, int count) {
		super();
		this.index = index;
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}