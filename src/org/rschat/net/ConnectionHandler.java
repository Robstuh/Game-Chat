package org.rschat.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.rschat.game.Game;
import org.rschat.game.GameHandler;

public class ConnectionHandler extends SimpleChannelHandler {

	public void messageReceived(ChannelHandlerContext session,
			MessageEvent event) throws Exception {
		Packet packet = (Packet) event.getMessage();
		if (packet == null) {
			return;
		}
		int opcode = packet.getByte();
		if (opcode == 1) {
			String name = packet.getString();
			Game game = new Game(name, session);
			GameHandler.getGames().add(game);
		} else {
			try {
				PacketHandler.handle(opcode, packet, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void channelConnected(ChannelHandlerContext session,
			ChannelStateEvent e) throws Exception {
		System.out.println("Connection accepted from "
				+ session.getChannel().getLocalAddress() + ".");
	}

	public void channelDisconnected(ChannelHandlerContext session,
			ChannelStateEvent e) throws Exception {
		System.out.println("Connection dropped from "
				+ session.getChannel().getLocalAddress() + ".");
	}

	public void exceptionCaught(ChannelHandlerContext session,
			ExceptionEvent exception) throws Exception {
		//exception.getCause().printStackTrace();
	}

}
