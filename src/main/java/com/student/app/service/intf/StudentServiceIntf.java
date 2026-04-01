package com.student.app.service.intf;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.response.Response;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface StudentServiceIntf {
	public ResponseEntity<Response> getAllStudents(Map<String, String> headers) throws Exception;
	public ResponseEntity<Response> getStudentDetails(String id,Map<String, String> headers) throws Exception;

}
