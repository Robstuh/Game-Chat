package org.rschat.chat;

import java.util.ArrayList;
import java.util.List;

public class RoomHandler {

	private static List<Room> rooms = new ArrayList<Room>();

	public static List<Room> getRooms() {
		return rooms;
	}

	public static Room getRoomByName(String name) {
		for (Room room : RoomHandler.getRooms()) {
			if (room.getRoomName().equalsIgnoreCase(name))
				return room;
		}
		return null;
	}

	public static void joinRoom(String roomname, Member member) {
		for (Room room : RoomHandler.getRooms()) {
			if (room.getRoomName().equalsIgnoreCase(roomname)) {
				room.joinRoom(member);
				return;
			}
		}
		new Room(member, roomname);
	}

}
