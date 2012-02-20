package org.rschat.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.rschat.game.Game;
import org.rschat.game.GameHandler;

public class PacketHandler {

	public static void handle(int opcode, Packet packet,
			ChannelHandlerContext session) {
		switch (opcode) {
		case 2: {
			String name = packet.getString();
			String message = packet.getString();

			for (Game game : GameHandler.getGames()) {
				if (game.getSession() == session)
					continue;
				Packet p = new Packet(2);
				p.addString(name + ": " + message);
				game.getSession().write(p);
			}
		}
			break;

		}
	}

}