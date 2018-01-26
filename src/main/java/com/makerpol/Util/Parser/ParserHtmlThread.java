package com.makerpol.Util.Parser;

import java.util.ArrayList;
import java.util.List;

import com.makerpol.Util.IO.imageDownThread;



public class ParserHtmlThread implements Runnable {
	private String html = "";
	private String flag = "";
	
	public ParserHtmlThread(String html,String flag){
		this.html = html;
		this.flag = flag;
	}
	
	
	public void run() {
		List<String> URList = new ArrayList<String>();
		switch(flag){
		case "image":
			URList = ParserHtml.getImageList(html, "UTF-8");
			new Thread(new imageDownThread(URList)).start();
		case "link":
			URList = ParserHtml.getLinkList(html, "UTF-8");
		}
	}
}
