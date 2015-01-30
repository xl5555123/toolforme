package com.pku.ipku.api.util;

import android.util.Log;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class NetHelper {

    public final static String BASE_URL = "http://162.105.205.170:8080";

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
        T data = restTemplate.exchange(url, HttpMethod.GET, httpEntity, returnType).getBody();
        return data;
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
