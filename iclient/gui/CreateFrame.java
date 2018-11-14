package iclient.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

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
        createSessionButton.addActionListener(new ConnectButtonListener());

        // Create panel
        createPanel         = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,2,0,0);
        createPanel.add(sessionKeyLabel, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(2,0,0,0);
        createPanel.add(sessionKeyField, c);
        c.insets = new Insets(0,0,0,0);

        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10,0,0,0);
        createPanel.add(createSessionButton, c);
        c.insets = new Insets(0,0,0,0);
        this.add(createPanel, BorderLayout.CENTER);

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
            CreateFrame.this.setVisible(false);
		}
	}
}
