package org.rschat;

import org.rschat.web.Database;

/**
 * @author Robbie
 * 
 */
public class Server {

	private static Database chatDatabase = new Database(null, null, null, null,
			false, 30000);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the chat database
	 */
	public static Database getChatDatabase() {
		return chatDatabase;
	}

}
