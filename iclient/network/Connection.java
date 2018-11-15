package iclient.network;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import iclient.gui.SessionFrame;
import iclient.data.Student;

public class Connection
{
    // Connection variables
    private Socket socket;
    private String IP = "10.183.240.165"; //165
    private int port = 6500;

    // Session variables
    private SessionFrame frame;
    private ConnectionListener listener;
    private String sessionKey;
    private boolean locked;
    String previous;
    Student buzz;

    // Server output
	PrintWriter outServer;

    public Connection(SessionFrame frame)
    {
        // Set fields
        this.frame = frame;
        this.sessionKey = frame.getSessionKey();
        locked = false;
        previous = "-1";

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
        outServer.println("0," + sessionKey);
    }

    public void newActiveQuestion(String question)
    {
        outServer.println("1," + question);
    }

    public void rejectAnswer()
    {
        locked = false;
        outServer.println("2");
        frame.getStudentNameLabel().setText("");
    }

    public void acceptAnswer()
    {
        // Increment score
        String ID = buzz.getID();

        for(int i = 0; i < frame.getModel().getRowCount(); i++)
        {
            if(frame.getModel().getValueAt(i, 0).equals(ID))
            {
                int score = Integer.parseInt((String) frame.getModel().getValueAt(i, 3));
                score++;
                String scoreStr = Integer.toString(score);
                frame.getModel().setValueAt(scoreStr, i, 3);
            }
        }

        locked = false;
        previous = "-1";
        outServer.println("3");
        frame.getStudentNameLabel().setText("");
    }

    public void endSession()
    {
        outServer.println("4");
        listener.stop(outServer);
        System.exit(1);
    }

    public void lockAnswer()
    {
        locked = true;
        outServer.println("5");
    }

    // Getters and setters
    public boolean getLocked()
    {
        return locked;
    }

    public String getPrevious()
    {
        return previous;
    }

    public void setPrevious(String previous)
    {
        this.previous = previous;
    }

    public Student getBuzz()
    {
        return buzz;
    }

    public void setBuzz(Student buzz)
    {
        this.buzz = buzz;
    }
}
