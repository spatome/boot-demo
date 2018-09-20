package com.spatome.boot.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil
{
	//private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

/*	private final static CloseableHttpClient CLIENT = HttpClients.createDefault();
	private static CloseableHttpClient TM_CLIENT = null;
	static{
		RequestConfig config = RequestConfig.custom().setConnectTimeout(4000).setSocketTimeout(3000).build();
		TM_CLIENT = HttpClients.custom().setDefaultRequestConfig(config).build();
	}

	public static String post(String URL, String transType, String xmlData)
	{
		LOGGER.info("=============center============>>");
		LOGGER.info(xmlData);

		HttpPost post = new HttpPost(URL);
		CloseableHttpResponse response = null;
		try
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("transType", transType));
			params.add(new BasicNameValuePair("transMessage", xmlData));
			post.setEntity(new UrlEncodedFormEntity(params, "GBK"));

			post.setHeader("Content-type", "application/x-www-form-urlencoded");
			post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			response = CLIENT.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String content = EntityUtils.toString(entity, "UTF-8");
				LOGGER.info("<<=============center============");
				LOGGER.info(content);
				// return getParameter(content, XML_KEY);
				return "";
			}
			else
			{
				LOGGER.error("<<=============center============");
				LOGGER.error("Response httpEntity is null");
				throw new RuntimeException("Http post异常：没有回应数据");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			LOGGER.error("编码处理异常：" + e);
			throw new RuntimeException("编码处理异常:" + e.getMessage());
		}
		catch (ClientProtocolException e)
		{
			LOGGER.error("协议处理异常：" + e);
			throw new RuntimeException("协议处理异常:" + e.getMessage());
		}
		catch (ParseException e)
		{
			LOGGER.error("数据格式处理异常：" + e);
			throw new RuntimeException("数据格式处理异常:" + e.getMessage());
		}
		catch (IOException e)
		{
			LOGGER.error("IO异常：" + e);
			throw new RuntimeException("IO异常:" + e.getMessage());
		}
		finally
		{
			if (response != null)
			{
				try
				{
					EntityUtils.consume(response.getEntity());
				}
				catch (IOException e)
				{
				}
			}
			if (post != null)
				post.abort();
		}
	};*/

	public static Map<String, String> toMap(HttpServletRequest request) throws Exception
	{
		Map<String, String> result = new HashMap<String, String>();

		Enumeration<?> enu = request.getParameterNames();
		while (enu.hasMoreElements())
		{
			String key = (String) enu.nextElement();
			result.put(key, request.getParameter(key));
		}

		return result;
	}

/*	public static String get(String URL)
	{
		HttpGet get = new HttpGet(URL);
		CloseableHttpResponse response = null;
		try
		{
			get.setHeader("Content-type", "application/x-www-form-urlencoded");
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			response = TM_CLIENT.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String content = EntityUtils.toString(entity, "UTF-8");
				return content;
			}
			else
			{
				LOGGER.error("Response httpEntity is null");
				throw new RuntimeException("Http get异常：没有回应数据");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			LOGGER.error("编码处理异常：" + e);
			throw new RuntimeException("编码处理异常:" + e.getMessage());
		}
		catch (ClientProtocolException e)
		{
			LOGGER.error("协议处理异常：" + e);
			throw new RuntimeException("协议处理异常:" + e.getMessage());
		}
		catch (ParseException e)
		{
			LOGGER.error("数据格式处理异常：" + e);
			throw new RuntimeException("数据格式处理异常:" + e.getMessage());
		}
		catch (IOException e)
		{
			LOGGER.error("IO异常：" + e);
			throw new RuntimeException("IO异常:" + e.getMessage());
		}
		finally
		{
			if (response != null)
			{
				try
				{
					EntityUtils.consume(response.getEntity());
				}
				catch (IOException e)
				{
				}
			}
			if (get != null)
				get.abort();
		}
	};*/
}
