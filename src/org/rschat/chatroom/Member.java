package org.rschat.chatroom;

public class Member {

	private String name;
	private String room;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Room getRoom(String name) {
		for (Room room : RoomHandler.getRooms()) {
			if (room.getRoomName().equalsIgnoreCase(name))
				return room;
		}
		return null;
	}

	public void joinRoom(String name) {
		for (Room room : RoomHandler.getRooms()) {
			if (room.getRoomName().equalsIgnoreCase(name)) {
				room.joinRoom(this);
				return;
			}
		}
		new Room(this, name);
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoom() {
		return room;
	}

}
