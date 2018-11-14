package iclient.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JFrame;
import iclient.data.Student;
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
    private String sessionKey;
    private ArrayList<Student> students;

    // Connection variables
	private Connection conn;

    public SessionFrame(String sessionKey)
    {
        // Set fields
        this.sessionKey = sessionKey;
        students = new ArrayList<Student>();

        // Create Swing variables
        keyLabel            = new JLabel("Session Key: " + sessionKey);

        questionLabel       = new JLabel("Enter a question: ");
        questionField       = new JTextField(20);

        studentLabel        = new JLabel("Buzzed In: ");
        studentNameLabel    = new JLabel("");

        newQuestionButton   = new JButton("Activate New Question");
        newQuestionButton.addActionListener(new NewQuestionButtonListener());
        acceptAnswerButton  = new JButton("Correct");
        acceptAnswerButton.addActionListener(new AcceptAnswerButtonListener());
        rejectAnswerButton  = new JButton("Incorrect");
        rejectAnswerButton.addActionListener(new RejectAnswerButtonListener());
        endSessionButton    = new JButton("End Session");
        endSessionButton.addActionListener(new EndSessionButtonListener());

        // Create session panel
        sessionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 1;
        c.gridy = 0;
        sessionPanel.add(questionLabel, c);

        c.gridx = 2;
        c.gridy = 0;
        sessionPanel.add(questionField, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(2,0,0,0);
        sessionPanel.add(studentLabel, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(2,0,0,0);
        sessionPanel.add(studentNameLabel, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(4,0,0,0);
        sessionPanel.add(newQuestionButton, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(4,0,0,0);
        sessionPanel.add(acceptAnswerButton, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 3;
        c.gridy = 2;
        c.insets = new Insets(4,0,0,0);
        sessionPanel.add(rejectAnswerButton, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 4;
        c.gridy = 2;
        c.insets = new Insets(4,0,0,0);
        sessionPanel.add(endSessionButton, c);
        c.insets = new Insets(0,0,0,0);

        this.add(sessionPanel, BorderLayout.CENTER);

        // Configure frame
        this.setMinimumSize(new Dimension(frameWidth, frameHeight));
        this.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        //this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("QuizKnows");

        // Display frame
        //this.pack();
        this.setVisible(true);

        // Send new session request to the server
        conn = new Connection(this);
    }

    // Getters and setters

    public String getSessionKey()
    {
        return sessionKey;
    }

    public JLabel getStudentNameLabel()
    {
        return studentNameLabel;
    }

    public ArrayList<Student> getStudents()
    {
        return students;
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
