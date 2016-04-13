package Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Main.ClientController;

/**
 * ClientSocket class.
 * Responsible for making the connection to the game server.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public class ClientSocket {
	
	//private static final String IPADDRESS = "hanzegameserver.nl";
	private static final String IPADDRESS = "play-serverzone.ddns.net";
	//private static final String IPADDRESS = "127.0.0.1";
	
	private static final int PORTNUMBER = 7789;
	private Socket socket;
	
	private ClientController controller;
	private ServerMessageHandler serverMessageHandler;
	
	/**
	 * Constructor for a ClientSocket object.
	 * Sets the controller.
	 * Creates a new ServerMessageHandler for this class.
	 * 
	 * @param controller ClientController the controller of the Client.
	 */
	public ClientSocket(ClientController controller) {
		this.controller = controller;
		serverMessageHandler = new ServerMessageHandler(controller);
	}
	
	/**
	 * Makes the connection to the server.
	 * Creates a new Socket and a new SocketListener and starts a SocketListener Thread.
	 * 
	 * @throws UnknownHostException thrown when the host is unknown.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void connectToServer() throws UnknownHostException, IOException {
		socket = new Socket(IPADDRESS, PORTNUMBER);
		Thread socketListener = new Thread(new SocketListener(socket, serverMessageHandler));
		socketListener.start();			
		System.out.println("CONNECTION MADE");
	}

	/**
	 * Method to send a message to the server.
	 * 
	 * @param message String the message that is send.
	 * @throws IOException thrown when it failed to send the message.
	 */
	public void writeToServer(String message) throws IOException {
		PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
		pout.println(message);
	}
	
	/**
	 * Method to login the player.
	 * 
	 * @param name String the name for the player.
	 * @return String name the input name.
	 * @throws IOException when the name isn't checked.
	 */
	public String clientLogin(String name) throws IOException {
		writeToServer("login " + name);
		return name;
	}

}
