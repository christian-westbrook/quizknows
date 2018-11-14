package iclient.network;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import iclient.gui.SessionFrame;

public class ConnectionListener implements Runnable
{
	private Socket socket;
	private Thread thread;
	private boolean running;
	private SessionFrame frame;
	private Connection conn;

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
			BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			boolean listening = true;
			while(listening)
			{
				// Get message
				String input = inServer.readLine();
				String[] message = input.split(",");

				// Student connecting to session
				if(message[0].equals("0"))
				{
					System.out.println("Connecting!!!!");
				}
                // Student buzzing in
                if(message[0].equals("1"))
                {
                    System.out.println("BUZZ!!!!!");
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
