package server;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class StudentProcessor implements Runnable
{
	private Thread thread;
	private boolean running;

	private Session session;
	private Socket socket;
	private BufferedReader inClient;
	private PrintWriter outClient;
	private int sessionID;

	String fname;
	String lname;
	String email;

	public StudentProcessor(Session session, Socket socket, BufferedReader inClient, String[] message)
	{
		try
		{
			this.session = session;
			this.socket = socket;
			this.inClient = inClient;
			outClient = new PrintWriter(socket.getOutputStream(), true);
			outClient.write("0" + "\n");

			sessionID = session.generateID();

			fname = message[2];
			lname = message[3];
			email = message[4];

			session.getInstructor().addStudent(fname, lname, email, Integer.toString(sessionID));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void run()
	{
		boolean listening = true;
		while(listening)
		{
			try
			{
				String input = inClient.readLine();
				String[] message = input.split(",");

				// Buzzing in
				if(message[0].equals("1"))
				{
					session.getInstructor().buzz(this);
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public String getSessionID()
	{
		return Integer.toString(sessionID);
	}

	public void send(String output)
	{
		outClient.write(output);
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
			inClient.close();
			outClient.close();
			thread.join();
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
