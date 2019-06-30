# separate-process-communication
Two players communicate with each other in different Java processes.
First player sends a message the other player then other player replies with a message that contains the received message concatenated with the value of a counter holding the number of messages this player already sent.
If the player sent 10 messages and received back 10 messages program terminates.

Application : separate-process-communication
Class Files :
1. com.karabiyikoglu.ismail.separateprocess.app.SeparateProcessAppServerMain : This class is server class. Waits for two different connection. Every player connects to server, sends and receives the messages via server.

2. com.karabiyikoglu.ismail.separateprocess.app.MessageHandler : MessageHandler class holds player list, and sends message to other player

3. com.karabiyikoglu.ismail.separateprocess.app.PlayerConnection : PlayerConnection class to communicate Player's sockets with each other.

4. com.karabiyikoglu.ismail.separateprocess.app.Player : Player class(Separate player processes). Connects to server, receives and sends message.

5. com.karabiyikoglu.ismail.separateprocess.app.SocketUtil : SocketUtil class for read data from socket.

6. com.karabiyikoglu.ismail.separateprocess.app.constants.IConstants : Constant interface to hold constant variables.

To run application, in shell terminal go to target directory and type "sh startall.sh" or run below scripts sequentially in distinct shell terminal
- server.sh
- player1.sh
- player2.sh

You can see the sent and received messages in console output