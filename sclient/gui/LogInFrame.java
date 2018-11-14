package sclient.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *	Swing GUI that allows a user to connect to a QuizKnows session.
 */
public class LogInFrame extends JFrame
{
	// Width and height of the device
	private int frameWidth = 800;
	private int frameHeight = 480;

	// Swing objects
	private JLabel			emailLabel;
	private JTextField		emailField;
	private JLabel 			fnameLabel;
	private JTextField 		fnameField;
	private JLabel			lnameLabel;
	private JTextField		lnameField;
	private JLabel			sessionKeyLabel;
	private JTextField		sessionKeyField;
	private JButton 		connectButton;

	private JPanel 			loginPanel;

	/**
	 *	The constructor creates the frame and connects to a button listener.
	 */
	public LogInFrame()
	{
		// Create swing objects
		fnameLabel		= new JLabel("First Name");
		fnameField		= new JTextField(10);
		lnameLabel		= new JLabel("Last Name");
		lnameField		= new JTextField(10);
		emailLabel 		= new JLabel("University Email");
		emailField 		= new JTextField(10);
		sessionKeyLabel	= new JLabel("Session Key");
		sessionKeyField	= new JTextField(10);
		connectButton 	= new JButton("Connect");
		connectButton.addActionListener(new ConnectButtonListener());

		// Create loginPanel
		loginPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 1;
		c.gridy = 0;
		loginPanel.add(fnameLabel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0,5,0,0);
		loginPanel.add(fnameField, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(2,0,0,0);
		loginPanel.add(lnameLabel, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(2,5,0,0);
		loginPanel.add(lnameField, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(2,0,0,0);
		loginPanel.add(emailLabel, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(2,5,0,0);
		loginPanel.add(emailField, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(2,0,0,0);
		loginPanel.add(sessionKeyLabel, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(2,5,0,0);
		loginPanel.add(sessionKeyField, c);
		c.insets = new Insets(0,0,0,0);

		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		c.insets = new Insets(2,0,0,0);
		loginPanel.add(connectButton, c);
		c.insets = new Insets(0,0,0,0);
		this.add(loginPanel, BorderLayout.CENTER);

		// Configure frame
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));
		this.setMaximumSize(new Dimension(frameWidth, frameHeight));
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("QuizKnows");

		// Display frame
		this.pack();
		this.setVisible(true);
		}

	/**
	 *	Listens for the connect button and then sends the input information
	 *	to a new QuizFrame object.
	 */
	private class ConnectButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new QuizFrame(fnameField.getText(), lnameField.getText(), emailField.getText(), sessionKeyField.getText());
			LogInFrame.this.setVisible(false);
		}
	}
}
