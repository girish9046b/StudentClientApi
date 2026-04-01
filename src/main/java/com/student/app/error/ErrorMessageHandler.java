package com.student.app.error;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.student.app.response.Response;

@Component

@PropertySource("classpath:status.properties")
public class ErrorMessageHandler {

//	@Autowired
//	Environment env;

//	@Autowired
//	ErrorResponse errorResponse;

//	@Autowired
//	Response response;

	

	
	private  Response response; // Field is final, ensuring immutability
	private   ErrorResponse errorResponse; // Field is final, ensuring immutability
	private Environment env;

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public ErrorMessageHandler(Response response,ErrorResponse errorResponse,Environment env) {
		this.response = response;
		this.errorResponse = errorResponse;
		this.env = env;
	}
	


	// Constructor for injection (Spring automatically injects ReportDAO here)
//	public ErrorMessageHandler(ErrorResponse errorResponse) {
//		this.response = new Response();
//		this.errorResponse = errorResponse;
//	}
	

	private static final Logger logger = LoggerFactory.getLogger(ErrorMessageHandler.class);

	public void addErrortoList(int code) {
		errorResponse.setErrorCode(code);
		errorResponse.setErrorMessage(env.getProperty("" + code));
		errorResponse = new ErrorResponse(code, env.getProperty("" + code), new Date());
		// ArrayList<ErrorResponse> errorResponseList = response.getErrorResponseList();
		ArrayList<ErrorResponse> errorResponseList = response.getErrorResponseList();
		logger.info("{}............ErrorMessageHandler..errorResponseList11111..........{}", code, errorResponseList);
		if (errorResponseList == null) {
			errorResponseList = new ArrayList<>();
			logger.info("{}............ErrorMessageHandler..errorResponseList..........{}", code, errorResponseList);
		}

		errorResponseList.add(errorResponse);
		logger.info("{}............ErrorMessageHandler..errorResponseList...............{}", errorResponseList.size(),
				errorResponseList);
		response.setErrorResponseList(errorResponseList);
	}

}
