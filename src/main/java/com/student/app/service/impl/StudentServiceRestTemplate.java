package com.student.app.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.error.ErrorMessageHandler;
import com.student.app.model.Student;
import com.student.app.response.Response;
import com.student.app.service.intf.StudentServiceIntf;

@Service("studentServiceRestTemplate")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentServiceRestTemplate implements StudentServiceIntf {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceRestTemplate.class);

	private RestTemplate restTemplate;

	@Value(value = "${STUDENT.LIST.API.URL}")
	private String studentListUrl;
	
	@Value(value = "${STUDENT.DETAILS.API.URL}")
	private String studentDetailsUrl;

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public StudentServiceRestTemplate(Response response, Student student, ErrorMessageHandler errorMessageHandler,
			RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseEntity<Response> getAllStudents(Map<String, String> headersInput) throws Exception {
		logger.info("getAllStudents-----222 {} " ,studentListUrl);
		HttpHeaders headers = getHeader(headersInput);
		// Create Entity with Headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		logger.info("getAllStudents-----222 {} " ,requestEntity);
		// Make GET Request
		return restTemplate.exchange(studentListUrl, HttpMethod.GET, requestEntity, Response.class);

	}
	
	public ResponseEntity<Response> getStudentDetails(String id, Map<String, String> headersInput) throws Exception {
		logger.info("getStudentDetails-----222 {} " ,studentDetailsUrl);
		HttpHeaders headers = getHeader(headersInput);
		// Create Entity with Headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		Map<String, String> params = new HashMap<>();
		params.put("id", id); 

		logger.info("getStudentDetails-----222 {} " ,requestEntity);
		// Make GET Request
		return restTemplate.exchange(studentDetailsUrl, HttpMethod.GET, requestEntity, Response.class, params);

	}

	public HttpHeaders getHeader(Map<String, String> headersInput) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Custom-App", headersInput.get("X-Custom-App")); //
		headers.set("X-Custom-Token", headersInput.get("X-Custom-Token")); //

		return headers;
	}
//	
//	public ResponseEntity<Response> getApiDataWithHeaders(Map<String, String> headersInput) throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.set("X-Custom-App", headersInput.get("X-Custom-App")); //
//        headers.set("X-Custom-Token", headersInput.get("X-Custom-Token")); //
//        //headers.setBasicAuth("user", "pass"); //
//
//        // Create Entity with Headers
//        HttpEntity<Void> HttpHeaders = new HttpEntity<>(headers);
//
//        // Make GET Request
//        ResponseEntity<Response> response = restTemplate.exchange(
//        		studentListUrl, HttpMethod.GET, requestEntity, Response.class);
//        
//        return response;
//    }

}
