package com.student.app.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.student.app.response.Response;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
//	@Autowired
//	public Response response;
//
//	@Autowired
//	public ErrorResponse errorResponse;
	
	private  Response response; // Field is final, ensuring immutability
	private   ErrorResponse errorResponse; // Field is final, ensuring immutability


	// Constructor for injection (Spring automatically injects ReportDAO here)
	public GlobalExceptionHandler(Response response,ErrorResponse errorResponse) {
		this.response = response;
		this.errorResponse = errorResponse;
	}
		
		

	// log the error messages
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		logger.info("..................handleResourceNotFoundExceptionhandleResourceNotFoundException.......");

		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		// errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(),
		// ex.getMessage(),
		// ((ServletWebRequest) request).getRequest().getRequestURI(), new Date());

		// response.getErrorResponseList().add(errorResponse);
		return new ResponseEntity<>(response, status);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Response> notFoundHandler(Exception ex, WebRequest request) {
		logger.info("..................handleExceptionhandleException.......");

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
		// errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(),
		// ex.getMessage(),
		// ((ServletWebRequest) request).getRequest().getRequestURI(), new Date());

		// response.getErrorResponseList().add(errorResponse);
		return new ResponseEntity<>(response, status);
	}

	public String getError(Exception ex) {
		String error = "";
		if (ex != null) {
			error = getStackTrace(ex);
		}
		return error;
	}

	// Method to get stack trace from the Exception
	public String getStackTrace(Exception e) {
		Writer writer = new StringWriter();
		String s = "";
		try {
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			s = writer.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
//	@ExceptionHandler(Error.class)
//	public ResponseEntity<Response> handleError(Error ex,
//			WebRequest request) {
//		System.out.println("..................handleErrorhandleError.......");
//
//		HttpStatus status = HttpStatus.NOT_FOUND; // 404
//		 errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
//				((ServletWebRequest) request).getRequest().getRequestURI(),new Date());
//
//		response.setErrorResponse(errorResponse); 
//		return new ResponseEntity<>(response, status);
//	}
	// You can add more @ExceptionHandler methods for other custom or built-in
	// exceptions
}
