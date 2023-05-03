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

	// to get all rooms
	public List<Room> getAvailableRooms() {
		return roomRepository.findByStatus("available");
	}

	// to get rooms acc to capacity
	public List<Room> getRoomsAccordingToCapacity(int capacity) {
		return roomRepository.findByCapacity(capacity);
	}

	public List<Room> getAvailableRoomsAccordingToCapacity(int roomType) {
		return roomRepository.findByCapacityAndStatus(roomType, "available");

	}

	// to get room by id
	public Optional<Room> getRoomById(Long id) {
		return roomRepository.findById(id);
	}

	// to find room according to AC preference
	public List<Room> getRoomsAccordingToAc() {
		return roomRepository.findByIncludesAc(true);
	}

	// to allocateRoom
	public String allocateRoom(Student student) {
		List<Room> rooms = roomRepository.findByCapacityAndStatus(student.getRoomType(), "available");
		for (Room room : rooms) {
			if (room.getVacancy() > 0) {
				room.setVacancy(room.getVacancy() - 1);
				room.setStudentIds(student.getId());
				if (room.getVacancy() == 0) {
					room.setStatus("not available");
				}
				return room.getRoomNumber();
			}

		}

		return null;

	}

	// to release room
	public void releaseRoom(Room room) {
		room.setStatus("available");
		room.setVacancy(room.getVacancy() + 1);
		roomRepository.save(room);

		room.getStudents().clear();
	}

	// to return room number
	public String getRoomNumber(Long id) {

		List<Room> rooms = roomRepository.findAll();

		for (Room room : rooms) {
			for (Long studentId : room.getStudentIds()) {
				if (studentId == id) {
					return room.getRoomNumber();
				}
			}
		}
		return null;

	}
}
