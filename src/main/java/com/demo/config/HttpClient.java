package com.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/*@Service*/
public class HttpClient
{
	

	private String sendGet(String url,Map<String,String> headerMap) throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		String result="";	
		// add request headers
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			request.addHeader(entry.getKey(),entry.getValue());
		}
		
		
		try(CloseableHttpResponse response = httpClient.execute(request))
		{
			// Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            
            if (entity != null)
            {
                // return it as a String
                result = EntityUtils.toString(entity);
                System.out.println(result);
            }
            httpClient.close();
		}
		return result;
		
	}
	
	private String sendPost(String url,Map<String,String> headerMap) throws Exception
	{
		
		String result="";
		HttpPost request=new HttpPost(url);
		
		// add request parameters or form parameters
		 List<NameValuePair> urlParameters = new ArrayList<>();
		 for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				
			 urlParameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
			
		 
		 
		 request.setEntity(new UrlEncodedFormEntity(urlParameters));

	        try (CloseableHttpClient httpClient = HttpClients.createDefault();
	             CloseableHttpResponse response = httpClient.execute(request))
	        {
	           result= EntityUtils.toString(response.getEntity());
	           httpClient.close();
	        }
	        
			return result;
		
	}
	
}
