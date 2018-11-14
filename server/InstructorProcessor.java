package server;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class InstructorProcessor implements Runnable
{
	private Thread thread;
	private boolean running;

	private Session session;
	private Socket socket;
	private BufferedReader inClient;
	private PrintWriter outClient;

	private boolean active;
	private boolean locked;

	public InstructorProcessor(Session session, Socket socket, BufferedReader inClient)
	{
		try
		{
			this.session = session;
			this.socket = socket;
			this.inClient = inClient;
			outClient = new PrintWriter(socket.getOutputStream(), true);

			active = false;
			locked = false;
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

				// New active question
				if(message[0].equals("1"))
				{
					active = true;
					send("2," + message[1] + "\n");
				}
				// Unlock active question
				else if(message[0].equals("2"))
				{
					locked = false;
					send("5" + "\n");
				}
				// Question answered. Send inactive signal
				else if(message[0].equals("3"))
				{
					active = false;
					locked = false;
					send("3" + "\n");
				}
				// End session
				else if(message[0].equals("4"))
				{
					send("6" + "\n");
					session.close();
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public void send(String output)
	{
		for(StudentProcessor s : session.getStudents())
		{
			s.send(output);
		}
	}

	public void buzz(StudentProcessor student)
	{
		if(active && !locked)
		{
			outClient.write("1," + student.getSessionID() + "\n");
			locked = true;
		}
	}

	public void addStudent(String fname, String lname, String email, String sessionID)
	{
		outClient.write("0," + fname + "," + lname + "," + email + "," + sessionID + "\n");
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
