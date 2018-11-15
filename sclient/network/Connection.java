package sclient.network;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import sclient.gui.LogInFrame;
import sclient.gui.QuizFrame;

public class Connection
{
	// Connection variables
	private Socket socket;
	private String IP 	= "10.183.240.165";
	private int port	= 6501;

	// Session variables
	private QuizFrame frame;
	private ConnectionListener listener;
	private String sessionKey;

	// Server output
	PrintWriter outServer;

	public Connection(QuizFrame frame)
	{
		// Set fields
		this.frame = frame;
		this.sessionKey = frame.getSessionKey();

		try
		{
			// Create socket connection
			socket = new Socket(IP, port);
			listener = new ConnectionListener(frame, this, socket);
			listener.start();

			// Create server output object
			outServer = new PrintWriter(socket.getOutputStream(), true);

			connectToSession();
		}
		catch(UnknownHostException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public void connectToSession()
	{
		// Connect to open session
		outServer.println("0," + frame.getSessionKey() + "," + frame.getEmail()
					+ "," + frame.getFname() + "," + frame.getLname());
	}

	public void buzz()
	{
		// Buzz in
		outServer.println("1");
	}

	public void closeConnection()
	{
		try
		{
			outServer.close();
			socket.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}

	// Getters and setters
	public ConnectionListener getListener()
	{
		return listener;
	}
}
