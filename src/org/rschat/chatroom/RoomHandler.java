package org.rschat.chatroom;

import java.util.ArrayList;
import java.util.List;

public class RoomHandler {
	
	private static List<Room> rooms = new ArrayList<Room>();

	public static List<Room> getRooms() {
		return rooms;
	}

}
