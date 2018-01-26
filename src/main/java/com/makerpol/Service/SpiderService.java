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
		for(int I =1; I<187;I++){
			String url = "http://www.177piczz.info/html/2018/01/1789491.html/"+I;
		    //String url = "http://www.1j1j.com/gl/13701_"+I+".html";
			String content = getPageContent(url);
			new Thread(new ParserHtmlThread(content,"image")).start();
		}
	}
}
