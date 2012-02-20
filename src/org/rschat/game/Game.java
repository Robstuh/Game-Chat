package org.rschat.game;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.rschat.net.Packet;

public class Game {

	private String name;
	private ChannelHandlerContext session;

	public Game(String name, ChannelHandlerContext session) {
		this.name = name;
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public Channel getSession() {
		return session.getChannel();
	}

	public void write(Packet packet) {
		session.getChannel().write(packet);
	}

}
