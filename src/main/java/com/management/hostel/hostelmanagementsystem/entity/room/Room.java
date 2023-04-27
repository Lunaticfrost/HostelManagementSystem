package com.management.hostel.hostelmanagementsystem.entity.room;

import java.util.List;

import com.management.hostel.hostelmanagementsystem.entity.student.Student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="room_number",nullable=false,unique=true)
	private String roomNumber;
	
	@Column(name="capacity",nullable=false)
	private int capacity;
	
	@Column(name="includes_ac",nullable=false)
	private boolean includesAc;
	
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Column(name="vacancy",nullable=false)
	private int vacancy;
	
	@Column(name="student_ids")
	private List<Long> studentIds;
	
	@OneToMany(mappedBy ="room",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Student> students;
	
	

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public boolean getIncludesAc() {
		return includesAc;
	}

	public void setIncludesAc(boolean includesAc) {
		this.includesAc = includesAc;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public List<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(long id) {
		this.studentIds.add(id);
	}
	 
	
	

}
