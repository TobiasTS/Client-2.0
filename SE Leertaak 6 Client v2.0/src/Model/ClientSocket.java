package Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Main.ClientController;

public class ClientSocket {
	
	private static final String IPADDRESS = "127.0.0.1";
	private static final int PORTNUMBER = 7789;
	private Socket socket;
	
	private ClientController controller;
	
	private ServerMessageHandler serverMessageHandler;
	
	public ClientSocket(ClientController controller) {
		this.controller = controller;
		serverMessageHandler = new ServerMessageHandler(controller);
	}
	
	public void connectToServer() throws UnknownHostException, IOException {
		socket = new Socket(IPADDRESS, PORTNUMBER);
		Thread socketListener = new Thread(new SocketListener(socket, serverMessageHandler));
		socketListener.start();			
		System.out.println("CONNECTION MADE");
	}

	public void writeToServer(String message) throws IOException {
		PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
		pout.println(message);
	}
	
	public String clientLogin(String name) throws IOException {
		writeToServer("login " + name);
		controller.getModel().setMessageChecked(false);
		while(!controller.getModel().getMessageChecked()) {}
		return name;
	}

}
