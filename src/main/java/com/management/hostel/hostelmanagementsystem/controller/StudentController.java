package com.management.hostel.hostelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.hostel.hostelmanagementsystem.entity.dto.StudentDTO;
import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.service.RoomService;
import com.management.hostel.hostelmanagementsystem.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RoomService roomService;

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentService.getAllStudents();
	}

	@PostMapping("/students")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
		Student student = new Student(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getPhone(),
				studentDTO.getFoodPreference(), studentDTO.getParentName(), studentDTO.getParentPhone());
		String roomNumber = roomService.allocateRoom(student);
		if (roomNumber == null) {
			return new ResponseEntity<>("Rooms not available", HttpStatus.CONFLICT);
		}
		studentService.saveStudent(student);
		return new ResponseEntity<>("Allocated Room: " + roomNumber, HttpStatus.ACCEPTED);

	}

	@GetMapping("/student-room-Number")
	public ResponseEntity<String> getStudentRoomNumber(@RequestParam long id) {
		String studentRoomNumber = roomService.getRoomNumber(id);
		if (studentRoomNumber == null) {
			return new ResponseEntity<String>("Student is not registered/assigned to any room", HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("Room Number: " + studentRoomNumber, HttpStatus.OK);
	}

}
