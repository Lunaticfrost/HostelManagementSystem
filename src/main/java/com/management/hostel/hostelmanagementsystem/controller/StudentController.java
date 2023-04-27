package com.management.hostel.hostelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.service.StudentService;
import jakarta.validation.Valid;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents(){
		return studentService.getAllStudents();
	}
	
	@PostMapping("/students")
	public String addStudent(@Valid @RequestBody Student student) {
		studentService.saveStudent(student);
		
		
		return "User created successfully";
		
	}
	
}
