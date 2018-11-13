package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class SListener implements Runnable
{
	private Server s;
	private int port;

	private Thread thread;
	private boolean running;

	public SListener(Server s, int port)
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
				Socket socket = serverSocket.accept();

				BufferedReader inClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String input = inClient.readLine();
				String[] message = input.split(",");

				// Student connecting for the first time
				if(message[0].equals("0"))
				{
					// Find the session the student is trying to connect to
					boolean foundSession = false;
					for(Session session : s.getSessions())
					{
						if(session.getKey().equals(message[1]))
						{
							session.addStudent(socket, inClient, message);
							foundSession = true;
						}
					}

					if(!foundSession)
					{
						PrintWriter outClient = new PrintWriter(socket.getOutputStream(), true);
						outClient.write("1" + "\n");
						outClient.close();
						inClient.close();
						socket.close();
					}
				}
				else
				{
					inClient.close();
					socket.close();
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
