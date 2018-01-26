package com.makerpol.Util.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * ͼƬ�����߳�
 * @author THINK
 *
 */
public class imageDownThread implements Runnable {
	private static String header_name = "User-Agent";
	private static String header_value = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
	private static String host = "host";
	private static String host_value = "img.177piczz.info";
	private static String cookie_value = "__cfduid=dffeb6205a60c3fbd2b5a1fe12c843fdc1496755836";
	private String FileName = "C:\\Pimage";
	private String URL = "";
	private List<String> URList = null;
	
	public imageDownThread(String url){
		this.URL=checkImageURL(url);
	}
	
	public imageDownThread(List<String> URList){
		this.URList = URList;
	}
	
	@Override
	public void run() {
		File file = getFile(FileName);
		if(this.URList!=null && !this.URList.isEmpty()){
			for(String imageURL : URList){
				downImage(checkImageURL(imageURL), file.getPath());
			}
		}else{
			downImage(this.URL,file.getPath());
		}
	}
	
	private void downImage(String imageURL, String filePath){
		String imageName = getImageName(imageURL);
		try {
			File imageFile = new File(filePath+"/"+imageName);
			FileOutputStream output = new FileOutputStream(imageFile);
			InputStream input = null;
			URL url = new URL(imageURL);
			if(imageURL.startsWith("https")){
				//httpsЭ��ͼƬ��ȡ��ʽ
				HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
				https.setRequestProperty(header_name, header_value);
				input = https.getInputStream();
				
			}else{
				URLConnection http = url.openConnection();
				//��������ͷ��Ϣ
				http.setRequestProperty(header_name, header_value);
				http.setRequestProperty(host, host_value);
				http.setRequestProperty("cookie", cookie_value);
				http.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				http.setRequestProperty("Accept-Encoding","gzip, deflate");
				http.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
				http.setRequestProperty("Cache-Control","no-cache");
				http.setRequestProperty("Connection","keep-alive");
				http.setRequestProperty("Pragma","no-cache");
				http.setRequestProperty("Upgrade-Insecure-Requests","1");
				//httpЭ���ȡ��ʽ
				input = http.getInputStream();
			}

			byte[] buff = new byte[1024];
			while(true) {
				int readed = input.read(buff);
				if(readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				//д���ļ�
				output.write(temp);
			}
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private String checkImageURL(String url){
		if(!url.startsWith("http:")&& !url.startsWith("//")){
			url +="http://"+url;
		}
		return url;
	}
	
	private String getImageName(String url){
		return url.substring(url.lastIndexOf("/")+1);
	}
	
	private File getFile(String fileName){
		File file = new File(FileName);
		if(file==null||!file.exists()){
			file.mkdirs();
		}
		return file;
	}
}
