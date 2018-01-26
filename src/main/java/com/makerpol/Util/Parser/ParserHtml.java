package com.makerpol.Util.Parser;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ParserHtml {
	
	/**
	 * 
	 * @param html
	 * @param charset
	 * @return
	 */
	public static List<String> getImageList(String html, String charset){
		Parser parser = Parser.createParser(html, "UTF-8");
		NodeClassFilter nodeFilter = new NodeClassFilter(ImageTag.class);
		List<String> imageList = new ArrayList<String>();
		try {
			NodeList nodeList = parser.extractAllNodesThatMatch(nodeFilter);
			for(int i = 0; i < nodeList.size();i++){
				Node node = nodeList.elementAt(i);
				if(node instanceof ImageTag){
					ImageTag image = (ImageTag)node;
					imageList.add(image.getImageURL());
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return imageList;
	}
	
	/**
	 * 
	 * @param html
	 * @param charset
	 * @return
	 */
	public static List<String> getLinkList(String html, String charset){
		List<String> linkList = new ArrayList<String>();
		Parser parser = Parser.createParser(html, charset);
		NodeClassFilter filter = new NodeClassFilter(LinkTag.class);
		
		try {
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for(int i = 0;i<nodeList.size();i++){
				Node node = nodeList.elementAt(i);
				if(node instanceof LinkTag){
					LinkTag link = (LinkTag)node;
					linkList.add(link.getLink());
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return linkList;
	}
}
