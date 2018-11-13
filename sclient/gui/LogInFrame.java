package sclient.gui;

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
		loginPanel = new JPanel();
		loginPanel.add(fnameLabel);
		loginPanel.add(fnameField);
		loginPanel.add(lnameLabel);
		loginPanel.add(lnameField);
		loginPanel.add(emailLabel);
		loginPanel.add(emailField);
		loginPanel.add(sessionKeyLabel);
		loginPanel.add(sessionKeyField);
		loginPanel.add(connectButton);
		this.add(loginPanel);

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

	private class ConnectButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new QuizFrame(fnameField.getText(), lnameField.getText(), emailField.getText(), sessionKeyField.getText());
		}
	}
}
