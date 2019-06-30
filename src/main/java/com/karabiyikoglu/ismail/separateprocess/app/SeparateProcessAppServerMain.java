package com.karabiyikoglu.ismail.separateprocess.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.karabiyikoglu.ismail.separateprocess.app.constants.IConstants;

/**
 * This class is server class. Waits for two different connection.
 * Every player connects to server, sends and receives the messages via server
 * @author ismail
 *
 */
public class SeparateProcessAppServerMain {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		int counter = 1;
		try {
			//we create a server socket to accept clients
			serverSocket = new ServerSocket(IConstants.PORT_NUMBER, 2);
			List<Thread> communicationThreads = new ArrayList<Thread>();
			
			do {
				Socket clientSocket = serverSocket.accept();//waits until a player connects
				synchronized(SeparateProcessAppServerMain.class) {
					///Player names given by connection order
					PlayerConnection client = new PlayerConnection("Player " + counter, clientSocket);
					MessageHandler.addPlayer(client);
					communicationThreads.add(new Thread(client));
					counter++;
				}
			}while(counter <= 2);//two player must connect to server
			
			//Two player connected to the server. Now we start their communication threads
			for(Thread connectionThread : communicationThreads) {
				connectionThread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
