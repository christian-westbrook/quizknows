package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class IListener implements Runnable
{
	private Server s;
	private int port;

	private Thread thread;
	private boolean running;

	public IListener(Server s, int port)
	{
		this.s = s;
		this.port = port;
	}

	public void run()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(port);

			boolean listening = true;
			while(listening)
			{
				System.out.println("Listening for instructors on port " + port);

				try
				{
					Socket socket = serverSocket.accept();
					System.out.println("Instructor connected on port " + port);
					BufferedReader inClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					String input = inClient.readLine();
					String[] message = input.split(",");

					// Instructor connecting for the first time
					if(message[0].equals("0"))
					{
						// Create a new session
						Session session = new Session(message[1]);
						session.setInstructor(socket, inClient);
						s.getSessions().add(session);
					}
					else
					{
						inClient.close();
						socket.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void start()
	{
		if(running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop()
	{
		if(!running)
			return;

		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}
