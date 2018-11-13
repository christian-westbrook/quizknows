package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	private ServerSocket studentSocket;
	private ServerSocket instructorSocket;

	private final int SPORT = 5000;
	private final int IPORT = 4999;

	private ArrayList<Session> sessions;

	public Server()
	{
		sessions = new ArrayList<Session>();

		IListener iListener = new IListener(this, IPORT);
		iListener.start();

		SListener sListener = new SListener(this, SPORT);
		sListener.start();
	}

	// Getters and setters
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	public static void main(String[] args)
	{
		new Server();
	}
}
