package com.makerpol.Util.Spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.htmlparser.util.ParserException;

import com.makerpol.Util.Parser.ParserHtmlThread;

public class Spider {
	private static String header_name = "User-Agent";
	private static String header_value = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0";
	
	/**
	 * HttpClient��ʽ��ȡָ����վ��ҳ�����ݣ���֧�ֶ�̬ҳ�棩
	 * @param url
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParserException
	 */
	public static String getByHttpClient(String url) throws ClientProtocolException,
			IOException, ParserException {
		// �������
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// ����get����
		HttpGet get = new HttpGet(url);
		// ��������ͷ
		get.setHeader(header_name, header_value);
		// ִ������
		CloseableHttpResponse resp = httpclient.execute(get);
		// ��ȡ����ͷ��
		Header[] he = resp.getAllHeaders();
		for (Header temp : he) {
			System.out.println(temp);
		}
		// ��ȡҳ��ʵ��
		HttpEntity entity = resp.getEntity();
		String content = EntityUtils.toString(entity, "UTF-8");

		resp.close();
		return content;
	}
	
	/**
	 * ʹ��HttpURLConnection��ȡָ����վҳ�����ݣ���֧�ֶ�̬ҳ�棩
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getByJDK(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5000);
		http.setRequestProperty(header_name, header_value);
		
		InputStream input = http.getInputStream();
		String str = "";
		StringBuffer sb = new StringBuffer();
		if(input != null){
			BufferedReader bf = new BufferedReader(new InputStreamReader(input,"UTF-8"));
			
			while((str=bf.readLine()) != null){
				sb.append(str);
			}
		}
		
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * ʹ��Phantomjs��ȡ��̬ҳ��
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getByPhantom(String url) throws IOException{
		String classpath = Spider.class.getResource("/").toString().split("file:/")[1];
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(classpath+"com/arsenal/Util/Phantomjs/phantomjs.exe "+classpath+"com/arsenal/Util/Phantomjs/getPage.js " + url);

		InputStream input = p.getInputStream();
		BufferedReader buf = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		String temp = "";
		StringBuffer sb = new StringBuffer();
		while((temp = buf.readLine())!=null){
			sb.append(temp);
		};
		System.out.println(sb.toString());
		
		return sb.toString();
	}
}
