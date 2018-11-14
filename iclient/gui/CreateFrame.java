package iclient.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class CreateFrame extends JFrame
{
    // Width and height of the device
    private int frameWidth  = 800;
    private int frameHeight = 480;

    // Swing variables
    private JLabel sessionKeyLabel;
    private JTextField sessionKeyField;
    private JButton createSessionButton;
    private JPanel createPanel;

    public CreateFrame()
    {
        // Create Swing objects
        sessionKeyLabel     = new JLabel("Enter a Session Key");
        sessionKeyField     = new JTextField(10);
        createSessionButton = new JButton("Create Session");

        // Create panel
        createPanel         = new JPanel();
        createPanel.add(sessionKeyLabel);
        createPanel.add(sessionKeyField);
        createPanel.add(createSessionButton);
        this.add(createPanel);

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
    }

    /**
	 *	Listens for the create session button and then sends the input
	 *	information to a new SessionFrame object.
	 */
	private class ConnectButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new SessionFrame(sessionKeyField.getText());
		}
	}
}
