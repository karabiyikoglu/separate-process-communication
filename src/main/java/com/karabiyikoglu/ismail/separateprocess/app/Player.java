package com.karabiyikoglu.ismail.separateprocess.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.karabiyikoglu.ismail.separateprocess.app.constants.IConstants;

/**
 * Player class. Connects to server, receives and sends message
 * @author ismail
 *
 */
public class Player {

	private static int 		sentCount = 0;
	private static int 		receiveCount = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread.sleep(1000);//Wait for server ready
		
		//Try to establish connection with server
		try(Socket socket = new Socket("127.0.0.1", IConstants.PORT_NUMBER)){
			StringBuffer message = new StringBuffer();
			
			//If this process initiator, sends init message to server
			if(args != null && args.length > 0 && IConstants.INITIATOR.equals(args[0])) {
				message.append(IConstants.DEFAULT_INIT_MESSAGE);
				sendMessage(socket, message);
			}
			
			while(true) {
				
				//Receive a message from server
				receiveMessage(socket, message);
				
				if(receiveCount >= IConstants.MAXIMUM_MESSAGE_COUNT && sentCount >= IConstants.MAXIMUM_MESSAGE_COUNT) {
					//Player reached the limit. Exit the while loop, and finalize the process
					break;
				}
				
				//Reply message to server
				sendMessage(socket, message);
				
				if(receiveCount >= IConstants.MAXIMUM_MESSAGE_COUNT && sentCount >= IConstants.MAXIMUM_MESSAGE_COUNT) {
					//Player reached the limit. Exit the while loop, and finalize the process
					break;
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receives message from server process
	 * @param socket
	 * @param message
	 * @throws IOException
	 */
	private static void receiveMessage(Socket socket, StringBuffer message) throws IOException {
		boolean isMessageRead = false;
		StringBuffer receiveMessage = new StringBuffer();
		
		while(true) {
			isMessageRead = SocketUtil.readFromSocket(socket, receiveMessage );
			if(isMessageRead) {
				message.setLength(0);
				message.append(receiveMessage.toString()).append(" ").append(sentCount);
				receiveCount++;
				break;
			}
		}
	}
	
	/**
	 * Sends message to server process
	 * @param socket
	 * @param message
	 * @throws IOException
	 */
	private static void sendMessage(Socket socket, StringBuffer message) throws IOException {
		socket.getOutputStream().write(message.toString().getBytes());
		sentCount++;
	}
}
