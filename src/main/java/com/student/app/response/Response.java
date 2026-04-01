package com.student.app.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.student.app.error.ErrorResponse;
import com.student.app.model.Student;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Component
public class Response {
	
	private Student student;
	private ArrayList<Student> studentlist;
	//private ConcurrentLinkedQueue<ErrorResponse> errorResponseList = new ConcurrentLinkedQueue<>();
	private ArrayList<ErrorResponse> errorResponseList ;

	
}
