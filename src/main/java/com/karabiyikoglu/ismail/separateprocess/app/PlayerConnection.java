package com.karabiyikoglu.ismail.separateprocess.app;

import java.io.IOException;
import java.net.Socket;

import com.karabiyikoglu.ismail.separateprocess.app.constants.IConstants;

/**
 * PlayerConnection class to communicate Players sockets with each other
 * @author ismail
 *
 */
public class PlayerConnection implements Runnable {

	private String name;			//Player's name in order to distinguish from other players
	private int sentCount;			//Message sent count	
	private int receiveCount;		//Message received count
	private Socket clientSocket;	//Socket for communicate with Player process
	
	public PlayerConnection(String name,Socket clientSocket) {
		this.name = name;
		this.clientSocket = clientSocket;
		this.sentCount = 0;
		this.receiveCount = 0;
	}
	
	@Override
	public void run() {
		
		try {
			communicate();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * In this method current player's connection sends and receives message to other player
	 */
	private void communicate() throws IOException {
		
		boolean isMessageRead = false;
		StringBuffer readMessageBuffer = new StringBuffer();
		
		while(true) {
			
			if(receiveCount >= IConstants.MAXIMUM_MESSAGE_COUNT && sentCount >= IConstants.MAXIMUM_MESSAGE_COUNT) {
				break;
			}
			
			isMessageRead = SocketUtil.readFromSocket(clientSocket, readMessageBuffer );
			
			if(isMessageRead) {//This means current player sends to other player
				sentCount++;
				System.out.println(name + " sent message     : " + readMessageBuffer.toString());
				MessageHandler.sendMessage(this, readMessageBuffer.toString());
			}
		}
		
	}
	
	/**
	 * Writes message to current player's socket output stream
	 * @param message
	 */
	public void receiveMessage(String message){
		try {
			System.out.println(name + " received message : " + message);
			clientSocket.getOutputStream().write(message.getBytes());
			receiveCount++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
