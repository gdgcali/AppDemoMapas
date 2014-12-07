package com.demo.encuestahotel.models;

public class Room {

	public final static String LUXURY_ROOM = "De Lujo";
	public final static String STANDARD_ROOM = "Estandar";
	public String roomNumber;
	public String roomType;

	public Room(String roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
		
}
