package com.management.hostel.hostelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.hostel.hostelmanagementsystem.entity.room.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	Room findByRoomNumber(String roomNumber);
	
	List<Room> findByCapacityAndFoodPreferenceAndStatus(int capacity, String foodPreference, String status);

	List<Room> findByStatus(String string);
	

}
