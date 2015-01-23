package com.pku.ipku.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkTool 
{
	private static NetWorkTool network=null;
	private DefaultHttpClient client;
	
	
	private HttpParams httpParameters;
	private int timeoutConnection = 60000;
	private int timeoutSocket = 10000;


	public NetWorkTool() 
	{
		
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		client = new DefaultHttpClient(httpParameters);
	}
	public static NetWorkTool getInstance() 
	{
		if (network == null) 
		{
			network = new NetWorkTool();
		}
		return network;
	}

	public void clear() 
	{
		client = new DefaultHttpClient();
	}

	public String HttpGet(String URL) throws Exception 
	{
		String resultString="";
		HttpGet sourceaddr= new HttpGet(URL);
		try {
			HttpResponse httpResponse = client.execute(sourceaddr);

			if (httpResponse.getStatusLine().getStatusCode()==200) 
			{
				resultString = readstream(httpResponse.getEntity().getContent());
			}
			else {
				throw new Exception("网页无法打开！");
			}
			return resultString.toString();
		}catch (Exception e) {
			throw e;
		}
	}
	
	/////////////////特殊情况///////////////////////////////////
	public String getHall(String URL) throws Exception 
	{
		String resultString;
		HttpGet sourceaddr= new HttpGet(URL);
		
		try {
			HttpResponse httpResponse = client.execute(sourceaddr);
			
			if (httpResponse.getStatusLine().getStatusCode()==200) 
			{
				resultString = readstreamHall(httpResponse.getEntity().getContent());
			}
			else {
				throw new Exception("can't connect the network");
			}
			return resultString.toString();
		}catch (Exception e) {
			throw e;
		}
	}
	
	////这里才是百讲的编码---注意了
	public String getHall8859(String URL) throws Exception 
	{
		String resultString;
		HttpGet sourceaddr= new HttpGet(URL);
		
		try {
			HttpResponse httpResponse = client.execute(sourceaddr);
			
			if (httpResponse.getStatusLine().getStatusCode()==200) 
			{
				resultString = readstream8859(httpResponse.getEntity().getContent());
			}
			else {
				throw new Exception("can't connect the network");
			}
			return resultString.toString();
		}catch (Exception e) {
			throw e;
		}
	}

	public String post(String URL,List <NameValuePair> params) throws Exception 
	{
		String resultString;
		try {
			HttpPost httpRequest = new HttpPost(URL);
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "GB2312"));  //
			HttpResponse httpResponse = client.execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200)    
			{   
				resultString= readstream(httpResponse.getEntity().getContent());
			}else {
				throw new Exception("can't connect the network");
			}
			return resultString;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public String postUtf8(String URL,List <NameValuePair> params) throws Exception 
	{
		String resultString;
		try {
			HttpPost httpRequest = new HttpPost(URL);
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "GB2312"));  //
			HttpResponse httpResponse = client.execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200)    
			{   
				resultString= readstreamHall(httpResponse.getEntity().getContent());
			}else {
				throw new Exception("can't connect the network");
			}
			return resultString;
		}catch (Exception e) {
			throw e;
		}
	}
	

	public static boolean checknetwork(Context context) 
	{
		ConnectivityManager connectivity = (ConnectivityManager) context  
		.getSystemService(Context.CONNECTIVITY_SERVICE);  
		if (connectivity != null) {  
			NetworkInfo info = connectivity.getActiveNetworkInfo();  
			if (info != null) {  
				if (info.getState() == NetworkInfo.State.CONNECTED) {  
					return true;  
				}  
			}  
		}  
		return false;  
	}
	
	//////这里要注意了----------------因为这个有一个网址  就是百年大讲堂的比较特殊-----
	
	private String readstreamHall(InputStream in) 
	{
		StringBuffer resultString = new StringBuffer() ;
		try {
			BufferedReader inbuff = new BufferedReader(new InputStreamReader(in,"UTF-8"));//百讲是  8859_1
			String line = "";
			while ((line = inbuff.readLine()) != null){
				resultString.append('\n');
				resultString.append(line);
			}

		}catch (Exception e) {
		}
		return resultString.toString();
	}
	
	///////这个百讲的编码----
	private String  readstream8859(InputStream in)
	{
		StringBuffer resultString = new StringBuffer() ;
		try {
			BufferedReader inbuff = new BufferedReader(new InputStreamReader(in,"8859_1"));//百讲是  8859_1
			String line = "";
			while ((line = inbuff.readLine()) != null){
				resultString.append('\n');
				resultString.append(line);
			}

		}catch (Exception e) {
		}
		return resultString.toString();
	}
	
	
	private String readstream(InputStream in) 
	{
		StringBuffer resultString = new StringBuffer() ;
		try {
			BufferedReader inbuff = new BufferedReader(new InputStreamReader(in,"GB2312"));
			String line = "";
			while ((line = inbuff.readLine()) != null){
				resultString.append('\n');
				resultString.append(line);
			}

		}catch (Exception e) {
		}
		return resultString.toString();
	}
}
