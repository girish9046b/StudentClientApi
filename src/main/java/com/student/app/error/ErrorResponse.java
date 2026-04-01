package com.student.app.error;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ErrorResponse {

	private int errorCode;
	//private String error;
	private String errorMessage;
	//private String path;
	private Date timestamp;

}
