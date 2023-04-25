package com.management.hostel.hostelmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.hostel.hostelmanagementsystem.entity.room.Room;
import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	//to get all rooms
	public List<Room> getAvailableRooms(){
		return roomRepository.findByStatus("available");
	}
	
	//to get room by id
	public Optional<Room> getRoomById(Long id){
		return roomRepository.findById(id);
	}
	
	//to allocateRoom
	public void allocateRoom(Student student , Room room) {
		room.setStatus("occupied");
		roomRepository.save(room);
		
		student.setRoom(room);
		
		room.getStudents().add(student);
	}
	
	public void releaseRoom(Room room) {
		room.setStatus("available");
		roomRepository.save(room);
		
		room.getStudents().clear();
	}
}
