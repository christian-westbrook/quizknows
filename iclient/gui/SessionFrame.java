package iclient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JFrame;
import iclient.network.Connection;

public class SessionFrame extends JFrame
{
    // Width and height of the device
    private int frameWidth  = 800;
    private int frameHeight = 480;

    // Swing variables
    private JLabel keyLabel;

    private JLabel questionLabel;
    private JTextField questionField;

    private JTable connectedTable;
    private JLabel studentLabel;
    private JLabel studentNameLabel;

    private JButton newQuestionButton;
    private JButton acceptAnswerButton;
    private JButton rejectAnswerButton;
    private JButton endSessionButton;

    private JPanel sessionPanel;

    // Session variables
    String sessionKey;

    // Connection variables
	Connection conn;

    public SessionFrame(String sessionKey)
    {
        // Set fields
        this.sessionKey = sessionKey;

        // Create Swing variables
        keyLabel            = new JLabel("Session Key: " + sessionKey);

        questionLabel       = new JLabel("Enter a question: ");
        questionField       = new JTextField(20);

        studentLabel        = new JLabel("Buzzed In: ");
        studentNameLabel    = new JLabel("");

        newQuestionButton   = new JButton("Activate New Question");
        acceptAnswerButton  = new JButton("Correct");
        rejectAnswerButton  = new JButton("Incorrect");
        endSessionButton    = new JButton("End Session");

        // Create session panel
        sessionPanel = new JPanel();
        sessionPanel.add(questionLabel);
        sessionPanel.add(questionField);
        sessionPanel.add(studentLabel);
        sessionPanel.add(studentNameLabel);
        sessionPanel.add(newQuestionButton);
        sessionPanel.add(acceptAnswerButton);
        sessionPanel.add(rejectAnswerButton);
        sessionPanel.add(endSessionButton);
        this.add(sessionPanel);

        // Configure frame
        this.setMinimumSize(new Dimension(frameWidth, frameHeight));
        this.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("QuizKnows");

        // Display frame
        this.pack();
        this.setVisible(true);

        // Send new session request to the server
        conn = new Connection(this);
    }

    // Getters and setters

    public String getSessionKey()
    {
        return sessionKey;
    }

    // Inner classes

    private class NewQuestionButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
            conn.newActiveQuestion(questionField.getText());
		}
	}

    private class AcceptAnswerButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
            conn.acceptAnswer();
		}
	}

    private class RejectAnswerButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
            conn.rejectAnswer();
		}
	}

    private class EndSessionButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
            conn.endSession();
		}
	}
}
