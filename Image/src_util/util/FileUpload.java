package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileUpload {
	
	private static Logger log = Logger.getLogger(FileUpload.class);
	
	
	//保存的文件路径
	private static String fileSavePath;
	
	//保存的文件名称
	private static String fileSaveName;
	
	//保存的文件
	private static File savefile;
	
	static {
		log.info("初始化文件上传工具类。。。");
		try {
			log.info("读取配置文件。。。");
			PropertiesParser pp = new PropertiesParser("config.properties");
			fileSavePath = pp.getInfoFromConfiguration("XMLSavePath");
		} catch (Exception e) {
			log.error("读取配置文件异常", e);
		}
	}
	
	
	/**
	 * 初始化保存信息
	 * @param filesFileName 保存的文件名称
	 */
	public static void init(){
		log.info("文件保存路径是：" + FileUpload.getFileSavePath());
		File dirFile = new File(FileUpload.getFileSavePath());
		
		if(!dirFile.exists()){
				log.info("创建保存文件夹。。。");
				try {
					dirFile.mkdir();
				} catch (Exception e) {
					log.error("创建保存文件夹失败");
				}
		}
		savefile = new File(dirFile, FileUpload.fileSaveName);
		
	}
	
	
	/**
	 * 保存文件
	 * @param filesFileName 保存的文件名称
	 * @param files 保存的文件
	 */
	public static void saveFile(String fileSaveName, File files){
		
		FileUpload.fileSaveName = fileSaveName;
		
		init();
		try {
			log.info("保存文件。。。");
			FileUtils.copyFile(files, savefile);
		} catch (IOException e) {
			log.error("保存文件失败", e);
		}
	}

	public static String getFileSavePath() {
		return fileSavePath;
	}

	public static void setFileSavePath(String fileSavePath) {
		FileUpload.fileSavePath = fileSavePath;
	}


	public static Logger getLog() {
		return log;
	}


	public static void setLog(Logger log) {
		FileUpload.log = log;
	}


	public static String getFileSaveName() {
		return fileSaveName;
	}


	public static void setFileSaveName(String fileSaveName) {
		FileUpload.fileSaveName = fileSaveName;
	}


	public static File getSavefile() {
		return savefile;
	}


	public static void setSavefile(File savefile) {
		FileUpload.savefile = savefile;
	}
	
	

}
