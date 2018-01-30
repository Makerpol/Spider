package com.makerpol.Service;

import com.makerpol.Util.Parser.ParserHtmlThread;
import com.makerpol.Util.Spider.Spider;

public class SpiderService {
	
	/**
	 *  
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static String getPageContent(String url) throws Exception{
		return Spider.getByHttpClient(url);
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String url = "www.baidu.com";
		String content = getPageContent(url);
		new Thread(new ParserHtmlThread(content,"image")).start();
	}
}
