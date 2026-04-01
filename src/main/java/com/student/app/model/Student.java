package com.student.app.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Student {
	
	private long id;
	private String name;
	private String phone;
	private String standard;
	private String country;
	private String address;
	private String age;
	private String gender;
	private String acceptForm;
	

}
