package org.rschat.chat;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private String roomName;
	private List<Member> members = new ArrayList<Member>();
	private Member owner;

	public Room(Member owner, String roomName) {
		this.setOwner(owner);
		this.roomName = roomName;
		joinRoom(owner);
	}

	public void joinRoom(Member member) {
		members.add(member);
	}

	public void leaveRoom(Member member) {
		members.remove(member);
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public Member getOwner() {
		return owner;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

}
