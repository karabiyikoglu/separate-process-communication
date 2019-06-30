package com.karabiyikoglu.ismail.separateprocess.app;

import java.io.IOException;
import java.net.Socket;

/**
 * SocketUtil class for read data from socket
 * @author ismail
 *
 */
public class SocketUtil {
	/**
	 * Read message from player's socket
	 * @return
	 * @throws IOException
	 */
	public static boolean readFromSocket(Socket socket,StringBuffer messageBuffer) throws IOException {
		boolean isMessageRead = true;
		messageBuffer.setLength(0);
		do {
			int readData = socket.getInputStream().read();//waits until player send a message
			if (readData <= 0) {
				if (messageBuffer.length() <= 0) {
					isMessageRead = false;
				}
				break;
			}
			char readSingleChar = (char) readData;
			messageBuffer.append(readSingleChar);
		} while (socket.getInputStream().available() > 0);
		return isMessageRead;
	}
}
