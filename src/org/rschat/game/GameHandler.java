package org.rschat.game;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.channel.ChannelHandlerContext;

public class GameHandler {

	private static List<Game> games = new ArrayList<Game>();
	
	public static List<Game> getGames(){
		return games;
	}

	public static Game getGameBySession(ChannelHandlerContext session) {
		for (Game game : games) {
			if (game.getSession() == session)
				return game;
		}
		return null;

	}
}
