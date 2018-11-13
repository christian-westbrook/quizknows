package sclient.network;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import sclient.gui.LogInFrame;
import sclient.gui.QuizFrame;

public class ConnectionListener implements Runnable
{
	private Socket socket;
	private Thread thread;
	private boolean running;
	private QuizFrame frame;
	private Connection conn;

	public ConnectionListener(QuizFrame frame, Connection conn, Socket socket)
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
			BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			boolean listening = true;
			while(listening)
			{
				// Get message
				String input = inServer.readLine();
				String[] message = input.split(",");

				// Successful connection with valid session key
				if(message[0].equals("0"))
				{
					frame.setVisible(true);
				}
				// Successful connection with invalid session key
				else if(message[0].equals("1"))
				{
					JOptionPane.showMessageDialog(null, "Invalid session key.");
					new LogInFrame();
					frame.setVisible(false);
				}
				// Session is now active
				else if(message[0].equals("2"))
				{
					frame.setActive(true);
					frame.setLabel(message[1]);
					frame.setIcon(frame.activeIcon);
				}
				// Session is now inactive
				else if(message[0].equals("3"))
				{
					frame.setActive(false);
					frame.setLocked(false);
					frame.setLabel("");
					frame.setIcon(frame.inactiveIcon);
				}
				// Session is locked
				else if(message[0].equals("4"))
				{
					frame.setLocked(true);
					frame.setIcon(frame.lockedIcon);
				}
				// Session is unlocked
				else if(message[0].equals("5"))
				{
					frame.setLocked(false);
					frame.setIcon(frame.activeIcon);
				}
				// Session closing
				else if(message[0].equals("6"))
				{
					inServer.close();
					frame.endSession();
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
