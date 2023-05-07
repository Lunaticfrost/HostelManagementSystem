package com.management.hostel.hostelmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Optional<Student> getStudentById(Long id){
		return studentRepository.findById(id);
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
		
	}
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public void deleteStudent(long id) {
		studentRepository.deleteById(id);;
	}
	
}
