package com.pku.ipku.model.networkHelper;

import com.pku.ipku.util.MyProtocolSocketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;


public class Ipgw {

	private HttpClient httpclient;

	private PostMethod post;

	private NameValuePair uid;

	private NameValuePair password;

	private NameValuePair range;

	private NameValuePair operation;

	private NameValuePair timeout = new NameValuePair("timeout", "1");

	/**
	 * Initial the Ipgw Object, Properties userinfo should have 
	 * at least 3 properties:uid, password, range
	 * @param userinfo
	 */
	public Ipgw(Properties userinfo) {
		httpclient = new HttpClient();
		Protocol myhttps = new Protocol("https", new MyProtocolSocketFactory(),
				443);
		httpclient.getHostConfiguration().setHost("its.pku.edu.cn", 5428,
				myhttps);
		post = new PostMethod("/ipgatewayofpku");
		uid = new NameValuePair("uid", userinfo.getProperty("uid"));
		password = new NameValuePair("password", userinfo
				.getProperty("password"));
		range = new NameValuePair("range", userinfo.getProperty("range"));
	}

	public String connect() {
		operation = new NameValuePair("operation", "connect");
		NameValuePair[] data = new NameValuePair[] { uid, password, range,
				operation, timeout };
		post.setRequestBody(data);
		
		return doPostMethod();
	}

	public String disConnect() {
		operation = new NameValuePair("operation", "disconnect");
		NameValuePair[] data = new NameValuePair[] { uid, password, range,
				operation, timeout };
		post.setRequestBody(data);

		return doPostMethod();
	}

	public String disConnectAll() {
		operation = new NameValuePair("operation", "disconnectall");
		NameValuePair[] data = new NameValuePair[] { uid, password, range,
				operation, timeout };
		post.setRequestBody(data);

		return doPostMethod();
	}

	private String doPostMethod() {
		try {
			httpclient.executeMethod(post);
			String charset = post.getResponseCharSet();
			InputStream in = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					charset));
			String line;
			StringBuilder html = new StringBuilder();
			while ((line = br.readLine()) != null) {
				html.append(line);
			}
			post.releaseConnection();
			
		//	String result = removeAllHtmlTags(parseHtml(html.toString()));
		//	return result;
			
		//	return html.toString();
			String str=html.toString();
			if(str.contains("超过预定值"))
				return "当前连接超过预定值";
			else if(str.contains("口令错误"))
				return "密码错误";
			else if(str.contains("账户名错"))
				return "账户名错";
			else
				return  removeAllHtmlTags(parseHtml(html.toString()));
		} catch (HttpException e) {
			e.printStackTrace();
			return "网络连接出现问题";
		} catch (IOException e) {
			e.printStackTrace();
			return "网络IO出现问题";
		}
	}

	public static String parseHtml(String html) {
		int ifrom = html.indexOf("<table");
		int ito = html.indexOf("</table>") + 8;
		String result = html.substring(ifrom, ito);
		return result;
	}

	public static String removeAllHtmlTags(String html){
		String result = html.replaceAll("</tr>", "\n").replaceAll("<br>", "\n").replaceAll("&nbsp;", " ");
		result  = result.replaceAll("<[^>]*>", "");
		return result;
	}
}
