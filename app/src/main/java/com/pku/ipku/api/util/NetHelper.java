package com.pku.ipku.api.util;

import android.util.Log;

import com.pku.ipku.util.DaoHelper;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class NetHelper {

    public final static String BASE_URL = "http://162.105.205.170:8080";

    public final static String APP_KEY = "579d8718c1b911e49c500050568508a5";

    public final static String SECRET = "579d87e0c1b911e49c500050568508a5";

    public static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        converter.setObjectMapper(objectMapper);
        converter.setSupportedMediaTypes(Collections.singletonList(new MediaType("text", "javascript")));

        restTemplate.getMessageConverters().add(converter);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new ResourceHttpMessageConverter());

    }

    public static String getUrl(String uri) {
        return String.format("%s%s", BASE_URL, uri);
    }

    public static String getAuthUrl(String uri, int userId, int timestamp, String msg) {
        return String.format("%s%s?user=%d&appKey=%s&timestamp=%d&msg=%s", BASE_URL, uri, userId, APP_KEY, timestamp, msg);
    }

    /**
     * 获取对象
     *
     * @param uri
     * @param returnType 返回类型
     * @param <T>        对象类型
     * @return
     */
    public static <T> T getForObject(String uri, Class<T> returnType) throws RestClientException {
        String url = getUrl(uri);
        Log.d("http get", url);
        HttpEntity<T> httpEntity = new HttpEntity<T>(getHeader());
        T data = null;
        try {
            data = restTemplate.exchange(url, HttpMethod.GET, httpEntity, returnType).getBody();
        } catch (Exception e) {

        }
        return data;
    }

    /**
     * 获取对象
     *
     * @param uri
     * @param returnType 返回类型
     * @param <T>        对象类型
     * @return
     */
    public static <T> T getForObjectWithAuth(String uri, Class<T> returnType, int userId) throws Exception {
        int timestamp = (int) new Date().getTime();
        String msg = getMd5(concatParameter(userId, timestamp));
        if (msg == null) {
            throw new Exception("generate message failed");
        }
        String url = getAuthUrl(uri, userId, timestamp, msg);
        HttpEntity<T> httpEntity = new HttpEntity<T>(getHeader());
        T data = null;
        try {
            data = restTemplate.exchange(url, HttpMethod.GET, httpEntity, returnType).getBody();
        } catch (Exception e) {

        }
        if (data != null) {
            DaoHelper.saveData(uri + userId, data);
        }
        return data;
    }

    public static String getMd5(String toSign) {
        String result = null;
        String toSign2 = String.format("%s%s", toSign, SECRET);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(toSign2.getBytes());
            result = getEncode32(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String concatParameter(int userId, int timestamp) {
        return String.format("user=%d&appKey=%s&timestamp=%d", userId, APP_KEY, timestamp);
    }

    private static String getEncode32(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString().toUpperCase(); // 大写
    }


    public static <T> T postForObject(String uri, T object, Class<T> returnType) throws RestClientException {
        String url = getUrl(uri);
        HttpEntity<T> httpEntity = new HttpEntity<T>(object, getHeader());
        Log.d("http post", url);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, returnType, object).getBody();
    }

    public static <T> T putForObject(String uri, T object, Class<T> returnType) throws RestClientException {
        HttpEntity<T> httpEntity = new HttpEntity<T>(object, getHeader());
        String url = getUrl(uri);
        Log.d("http put", url);
        return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, returnType).getBody();
    }

    public static <T> T deleteForObject(String uri, Class<T> returnType) throws RestClientException {
        HttpEntity<T> httpEntity = new HttpEntity<T>(getHeader());
        String url = getUrl(uri);
        Log.d("http delete", url);
        return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, returnType).getBody();
    }

    public static HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
