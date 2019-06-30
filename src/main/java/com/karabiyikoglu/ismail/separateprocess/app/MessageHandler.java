package com.karabiyikoglu.ismail.separateprocess.app;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageHandler class holds player list, and sends message to other player
 * @author ismail
 *
 */
public class MessageHandler {
	
	
	private static List<PlayerConnection> playerList = new ArrayList<PlayerConnection>();
	
	/**
	 * Sends message to other player
	 * @param sender
	 * @param message
	 */
	public static synchronized void sendMessage(PlayerConnection sender,String message) {
		for(PlayerConnection player : playerList) {
			if(sender != player) {
				player.receiveMessage(message);
			}
		}
	}
	
	/**
	 * Adds a player to player list
	 * @param player
	 */
	public static synchronized void addPlayer(PlayerConnection player) {
		playerList.add(player);
	}
}
