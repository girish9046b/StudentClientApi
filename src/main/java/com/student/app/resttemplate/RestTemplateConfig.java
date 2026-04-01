package com.student.app.resttemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.CloseableHttpClient;
////import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;

@Configuration
public class RestTemplateConfig {

	

	@Bean
	public RestTemplate restTemplate() {
	    // 1. Create a modern connection manager for HttpClient 5
	    PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
	            .setMaxConnTotal(100)               // Total connections across all routes
	            .setMaxConnPerRoute(50)             // Connections per specific host
	            .build();

	    RequestConfig requestConfig = RequestConfig.custom()
        .setResponseTimeout(3,TimeUnit.MINUTES) // Connection response timeout in MINUTES
        .setConnectionRequestTimeout(3,TimeUnit.MINUTES) // Connection request timeout
        .build();
	    
	    
	    // 2. Build the HttpClient
	    CloseableHttpClient httpClient = HttpClients.custom()
	            .setConnectionManager(connectionManager)
	            .setDefaultRequestConfig(requestConfig)
	            .evictIdleConnections(TimeValue.ofMinutes(1)) // Evict idle connections after 1 MINUTE
	            .evictExpiredConnections()
	            .build();

	    // 3. Configure the Factory
	    HttpComponentsClientHttpRequestFactory requestFactory = 
	            new HttpComponentsClientHttpRequestFactory(httpClient);
	    
	    return new RestTemplate(requestFactory);
	}
	
	
	
	
//	@Bean
//	public RestTemplate createRestTemplateWithConnectionPool() {
//	    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//	    connectionManager.setMaxTotal(100); // Maximum total connections
//	    connectionManager.setDefaultMaxPerRoute(20); // Maximum connections per route
//	    RequestConfig requestConfig = RequestConfig.custom()
//	        .setConnectTimeout(3000) // Connection timeout in milliseconds
//	        .setSocketTimeout(3000) // Socket timeout in milliseconds
//	        .setConnectionRequestTimeout(3000) // Connection request timeout
//	        .build();
//	    
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	        .setConnectionManager(connectionManager)
//	        .setDefaultRequestConfig(requestConfig)
//	        .evictIdleConnections(30, TimeUnit.SECONDS) // Evict idle connections after 30 seconds
//	        .evictExpiredConnections() // Evict expired connections
//	        .build();
//	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//	    requestFactory.setHttpClient((HttpClient)httpClient);
//	    
//	    return new RestTemplate(requestFactory);
//	}
	
//	
//	public RestTemplate createRestTemplateWithConnectionPool() {
//	    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//	    connectionManager.setMaxTotal(100); // Maximum total connections
//	    connectionManager.setDefaultMaxPerRoute(20); // Maximum connections per route
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	        .setConnectionManager(connectionManager)
//	        .build();
//	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//	    return new RestTemplate(requestFactory);
//	}
	
	
	
//	   @Bean
//	    public RestTemplate restTemplate() {
//	        PoolingAsyncClientConnectionManager connectionManager = new PoolingAsyncClientConnectionManager();
//	        connectionManager.setMaxTotal(100);
//	        connectionManager.setDefaultMaxPerRoute(20);
//
//	        HttpClientConnectionManager httpClientConnectionManager = new 
//	    RequestConfig requestConfig = RequestConfig
//	        .custom()
//	        .setConnectionRequestTimeout(1,TimeUnit.MINUTES) // timeout to get connection from pool
//	        .setResponseTimeout(2, TimeUnit.MINUTES) //timeout to get response from destination API
//	        .build();
//
//	    HttpClient httpClient = HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager) connectionManager)
//	                                             .setConnectionManager(connectionManager)
//	                                             .setDefaultRequestConfig(requestConfig).build();
//
//	    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//
//	    return new RestTemplate(requestFactory);
//	}
//	   
}
