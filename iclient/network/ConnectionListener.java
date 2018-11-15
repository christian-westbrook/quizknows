package iclient.network;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import iclient.gui.SessionFrame;
import iclient.data.Student;

public class ConnectionListener implements Runnable
{
	private Socket socket;
	private Thread thread;
	private boolean running;
	private SessionFrame frame;
	private Connection conn;
	private BufferedReader inServer;

	public ConnectionListener(SessionFrame frame, Connection conn, Socket socket)
	{
		// Set fields
		this.frame 	= frame;
		this.conn 	= conn;
		this.socket = socket;
		running 	= false;
	}

    public void run()
	{
		try
		{
			inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			boolean listening = true;
			while(listening)
			{
				// Get message
				String input = inServer.readLine();
				String[] message = input.split(",");

				// Student connecting to session
				if(message[0].equals("0"))
				{
					System.out.println(message[2] + " " + message[3] + " connected!");
					Student student = new Student(message[1], message[2], message[3], message[4]);
					frame.getStudents().add(student);
				}
                // Student buzzing in
                if(message[0].equals("1") && !conn.getLocked())
                {
					for(Student student : frame.getStudents())
					{
						if(message[1].equals(student.getID()))
						{
							conn.lockAnswer();
							frame.getStudentNameLabel().setText(student.getFname() + " " + student.getLname());
							conn.setPrevious(message[1]);
						}
					}
                }
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
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

	public void stop(PrintWriter outServer)
	{
		if(!running)
			return;

		running = false;
		try
		{
			inServer.close();
			outServer.close();
			thread.join();
			System.out.println("HERE");
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
