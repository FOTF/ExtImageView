package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class ProxyHttpUtil {
	
//	public static StringBuilder sb = new StringBuilder("");
	public static HttpURLConnection conn = null;
	
	public static String imageListURL;
	public static String svmListURL;
	public static String imgSrcURL;
	public static String fileUploadURL;
	
	
	public static Logger log = Logger.getLogger(ProxyHttpUtil.class);
	
	static {
		log.info("初始化网络代理工具类。。。");
		try {
			log.info("读取配置文件。。。");
			
			PropertiesParser pp = new PropertiesParser("config.properties");
			imageListURL = pp.getInfoFromConfiguration("imageListURL");
			svmListURL = pp.getInfoFromConfiguration("svmListURL");
			imgSrcURL = pp.getInfoFromConfiguration("imgSrcURL");
			fileUploadURL = pp.getInfoFromConfiguration("fileUploadURL");
			
		} catch (Exception e) {
			log.error("读取配置文件异常", e);
		}
	}
	
	private static void init(String url){
		try {
			URL getUrl = new URL(url);
			conn = (HttpURLConnection) getUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	private static String getConnectionInfor(String url){
		init(url);
		
		StringBuilder sb = new StringBuilder("");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String lines;
			while((lines = reader.readLine()) != null){
				sb.append(lines);
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			
		} catch (IOException e) {
			
		}
		
		close();
		log.info("*************************************************");
		log.info("读取的信息为：" + sb.toString());
		
		System.out.println("*************************" + sb.toString());
		return sb.toString();
	}
	
	/**
	 * 获取训练器列表json
	 */
	public static String getSvmListInfor(){
		/*StringBuilder url = new StringBuilder("");
		url.append(ProxyHttpUtil.svmListURL);
		if(obj.length > 0){
			url.append("?");
			for(int i = 0; i < obj.length; i++){
				url.append("");
			}
		}*/
		
		return getConnectionInfor(ProxyHttpUtil.svmListURL);
	}
	
	/**
	 * 获取图片列表
	 * @return 图片列表json
	 */
	public static String getImageListInfor(String svmName){
		if(svmName != null){
			return getConnectionInfor(ProxyHttpUtil.imageListURL + "?svmName=" + svmName);
		}else{
			return getConnectionInfor(ProxyHttpUtil.imageListURL);
		}
		
	}
	
	private static void close(){
		conn.disconnect();
	}

}
