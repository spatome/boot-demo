package com.spatome.boot.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * httpClient工具
 */
@Slf4j
public class HttpUtil {
	private static volatile HttpUtil instance;
	private HttpUtil() {
		this.init();
	};
	public static HttpUtil getInstance() {
		if (instance == null) {
			synchronized (HttpUtil.class) {
				if (instance == null) {
					instance = new HttpUtil();
				}
			}
		}
		return instance;
	}

	private RequestConfig requestConfig = null;
	private CloseableHttpClient httpClient = null;

	public void init() {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		httpClient = HttpClients.createDefault();
	}

	public String httpPost(String url, Map<String, String> maps) {
		log.debug("httpPost:" + maps.toString());

		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : maps.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			post.setHeader("Content-type", "application/x-www-form-urlencoded");
			post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			} else {
				log.error("http post失败:" + url);
				throw new RuntimeException("http post异常：没有回应数据");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("编码处理异常：" + e);
			throw new RuntimeException("编码处理异常:" + e.getMessage());
		} catch (ClientProtocolException e) {
			log.error("协议处理异常：" + e);
			throw new RuntimeException("协议处理异常:" + e.getMessage());
		} catch (ParseException e) {
			log.error("数据格式处理异常：" + e);
			throw new RuntimeException("数据格式处理异常:" + e.getMessage());
		} catch (IOException e) {
			log.error("IO异常：" + e);
			throw new RuntimeException("IO异常:" + e.getMessage());
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
				}
			}
			if (post != null)
				post.abort();
		}
	};

	public String httpGet(String url) {
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);

		CloseableHttpResponse response = null;
		try {
			get.setHeader("Content-type", "application/x-www-form-urlencoded");
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			} else {
				log.error("http get失败:" + url);
				throw new RuntimeException("http get异常：没有回应数据");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("编码处理异常：" + e);
			throw new RuntimeException("编码处理异常:" + e.getMessage());
		} catch (ClientProtocolException e) {
			log.error("协议处理异常：" + e);
			throw new RuntimeException("协议处理异常:" + e.getMessage());
		} catch (ParseException e) {
			log.error("数据格式处理异常：" + e);
			throw new RuntimeException("数据格式处理异常:" + e.getMessage());
		} catch (IOException e) {
			log.error("IO异常：" + e);
			throw new RuntimeException("IO异常:" + e.getMessage());
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
				}
			}
			if (get != null)
				get.abort();
		}
	};
}
