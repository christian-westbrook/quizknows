package iclient.network;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import iclient.gui.SessionFrame;

public class Connection
{
    // Connection variables
    private Socket socket;
    private String IP = "code.cis.uafs.edu";
    private int port = 6500;

    // Session variables
    private SessionFrame frame;
    private ConnectionListener listener;
    private String sessionKey;

    // Server output
	PrintWriter outServer;

    public Connection(SessionFrame frame)
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

			createSession();
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

    public void createSession()
    {
        outServer.write("0," + sessionKey + "\n");
    }

    public void newActiveQuestion(String question)
    {
        outServer.write("1," + question + "\n");
    }

    public void rejectAnswer()
    {
        outServer.write("2\n");
    }

    public void acceptAnswer()
    {
        outServer.write("3\n");
    }

    public void endSession()
    {
        outServer.write("4\n");
    }
}
