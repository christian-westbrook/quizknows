package iclient.gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class SessionFrame extends JFrame
{
    // Width and height of the device
    private int frameWidth  = 800;
    private int frameHeight = 480;

    // Swing variables
    private JLabel questionLabel;
    private JLabel questionField;

    private JTable connectedTable;
    private JLabel studentLabel;
    private JLabel studentNameLabel;

    private JButton newQuestionButton;
    private JButton acceptAnswerButton;
    private JButton rejectAnswerButton;

    private JPanel sessionPanel;

    // Session variables
    String sessionKey;

    public SessionFrame(String sessionKey)
    {
        // Set fields
        this.sessionKey = sessionKey;

        // Create Swing variables
        questionLabel       = new JLabel("Enter a question: ");
        questionField       = new JTextField(20);

        studentLabel        = new JLabel("Buzzed In: ");
        studentNameLabel    = new JLabel("");

        newQuestionButton   = new JButton("Activate New Question");
        acceptAnswerButton  = new JButton("Correct");
        rejectAnswerButton  = new JButton("Incorrect");

        // Create session panel
        sessionPanel = new JPanel();
        sessionPanel.add(questionLabel);
        sessionPanel.add(questionField);
        sessionPanel.add(studentLabel);
        sessionPanel.add(studentNameLabel);
        sessionPanel.add(newQuestionButton);
        sessionPanel.add(acceptAnswerButton);
        sessionPanel.add(rejectAnswerButton);

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

        // Send new session request to the server
        conn = new Connection(this);
    }

    public String getSessionKey()
    {
        return sessionKey;
    }
}
