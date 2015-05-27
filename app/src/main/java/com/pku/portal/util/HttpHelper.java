package com.pku.portal.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by R on 2014/10/14.
 */
public class HttpHelper {


    private static final String UTF_8 = "UTF-8";

    private final static int TIMEOUT_CONNECTION = 20000;
    private final static int TIMEOUT_SOCKET = 20000;
    private final static int RETRY_TIME = 3;

    private static String appUserAgent;

    public static String getUserAgent() {
        if (appUserAgent == null || appUserAgent == "") {
            StringBuilder ua = new StringBuilder("Onboard");
            ua.append('/' + SystemHelper.getPackageInfo().versionName + '_'
                    + SystemHelper.getPackageInfo().versionCode);// App版本
            ua.append("/Android");// 手机系统平台
            ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
            ua.append("/" + android.os.Build.MODEL); // 手机型号
            ua.append("/" + AppContextHolder.getAppContext().getAppId());// 客户端唯一标识
            appUserAgent = ua.toString();
        }
        return appUserAgent;
    }

    public static HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient();
        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        // 设置 默认的超时重试处理策略
        httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        // 设置 连接超时时间
        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(TIMEOUT_CONNECTION);
        // 设置 读数据超时时间
        httpClient.getHttpConnectionManager().getParams()
                .setSoTimeout(TIMEOUT_SOCKET);
        // 设置 字符集
        httpClient.getParams().setContentCharset(UTF_8);
        return httpClient;
    }

    public static GetMethod getHttpGet(String url, String userAgent) {

        GetMethod httpGet = new GetMethod(url);
        // 设置 请求超时时间
        httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpGet.setRequestHeader("Connection", "Keep-Alive");
        httpGet.setRequestHeader("User-Agent", userAgent);
        return httpGet;
    }

    public static PostMethod getHttpPost(String url, String userAgent) {
        PostMethod httpPost = new PostMethod(url);
        // 设置 请求超时时间
        httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpPost.setRequestHeader("Connection", "Keep-Alive");
        httpPost.setRequestHeader("User-Agent", userAgent);
        httpPost.setDoAuthentication(true);
        return httpPost;
    }

    public static PostMethod getHttpPostWithAuthorization(String url, String userAgent) {
        PostMethod httpPost = getHttpPost(url, userAgent);
        httpPost.setDoAuthentication(true);
        return httpPost;
    }


    /**
     * @param url
     * @return
     * @throws AppException
     */
    public static InputStream http_get(String url)
            throws AppException {
        String userAgent = getUserAgent();

        HttpClient httpClient = null;
        GetMethod httpGet = null;

        String responseBody = "";
        int time = 0;
        int statusCode;
        do {
            try {
                httpClient = getHttpClient();
                httpGet = getHttpGet(url, userAgent);
                statusCode = httpClient.executeMethod(httpGet);
                if (statusCode != HttpStatus.SC_OK) {
                    throw AppException.http(statusCode);
                }
                responseBody = httpGet.getResponseBodyAsString();
                // System.out.println("XMLDATA=====>"+responseBody);
                break;
            } catch (HttpException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
                throw AppException.http(e);
            } catch (IOException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
                throw AppException.network(e);
            } finally {
                // 释放连接
                httpGet.releaseConnection();
                httpClient = null;
            }
        } while (time < RETRY_TIME);

        responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
        return new ByteArrayInputStream(responseBody.getBytes());
    }

    /**
     * 公用post方法
     *
     * @param url
     * @param params
     * @param files
     * @throws AppException
     */
    public static InputStream _post(String url,
                                    Map<String, Object> params, Map<String, File> files)
            throws AppException {
        String userAgent = getUserAgent();

        HttpClient httpClient = null;
        PostMethod httpPost = null;

        // post表单参数处理
        int length = (files == null ? 0 : files.size());
        Part[] parts = new Part[length];
        int i = 0;
        if (files != null)
            for (String file : files.keySet()) {
                try {
                    parts[i++] = new FilePart(file, files.get(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // System.out.println("post_key_file==> "+file);
            }

        String responseBody = "";
        int time = 0;
        do {
            try {
                httpClient = getHttpClient();
                //认证
                // httpClient.getParams().setAuthenticationPreemptive(true);
                // httpClient.getState().setCredentials(AuthScope.ANY, new
                // UsernamePasswordCredentials("xuchen109@gmail.com",
                // "12345678"));
                httpPost = getHttpPostWithAuthorization(url, userAgent);
                for (String name : params.keySet()) {
                    httpPost.setParameter(name,
                            String.valueOf(params.get(name)));
                }
                if (length != 0) {
                    httpPost.setRequestEntity(new
                            MultipartRequestEntity(parts, httpPost.getParams()));
                }
                int statusCode = httpClient.executeMethod(httpPost);
                if (statusCode != HttpStatus.SC_OK) {
                    throw AppException.http(statusCode);
                }
                responseBody = httpPost.getResponseBodyAsString();
                // System.out.println("XMLDATA=====>"+responseBody);
                break;
            } catch (HttpException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
                throw AppException.http(e);
            } catch (IOException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
                throw AppException.network(e);
            } finally {
                // 释放连接
                httpPost.releaseConnection();
                httpClient = null;
            }
        } while (time < RETRY_TIME);

        responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
        return new ByteArrayInputStream(responseBody.getBytes());
    }

    public static class MySSLProtocolSocketFactory implements ProtocolSocketFactory {

        private SSLContext sslcontext = null;

        private SSLContext createSSLContext() {
            SSLContext sslcontext = null;
            try {
                sslcontext = SSLContext.getInstance("SSL");
                sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            return sslcontext;
        }

        private SSLContext getSSLContext() {
            if (this.sslcontext == null) {
                this.sslcontext = createSSLContext();
            }
            return this.sslcontext;
        }

        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
                throws IOException, UnknownHostException {
            return getSSLContext().getSocketFactory().createSocket(
                    socket,
                    host,
                    port,
                    autoClose
            );
        }

        public Socket createSocket(String host, int port) throws IOException,
                UnknownHostException {
            return getSSLContext().getSocketFactory().createSocket(
                    host,
                    port
            );
        }


        public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
                throws IOException, UnknownHostException {
            return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
        }

        public Socket createSocket(String host, int port, InetAddress localAddress,
                                   int localPort, HttpConnectionParams params) throws IOException,
                UnknownHostException, ConnectTimeoutException {
            if (params == null) {
                throw new IllegalArgumentException("Parameters may not be null");
            }
            int timeout = params.getConnectionTimeout();
            SocketFactory socketfactory = getSSLContext().getSocketFactory();
            if (timeout == 0) {
                return socketfactory.createSocket(host, port, localAddress, localPort);
            } else {
                Socket socket = socketfactory.createSocket();
                SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
                SocketAddress remoteaddr = new InetSocketAddress(host, port);
                socket.bind(localaddr);
                socket.connect(remoteaddr, timeout);
                return socket;
            }
        }

        //自定义私有类
        private static class TrustAnyTrustManager implements X509TrustManager {

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        }


    }
}
