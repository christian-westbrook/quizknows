package client.gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInFrame extends JFrame
{
	private int frameWidth = 800;
	private int frameHeight = 480;
	
	private JLabel 			userLabel;
	private JTextField 		userField;
	private JLabel 			passLabel;
	private JPasswordField 	passField;
	private JButton 		loginButton;
	
	
	private JPanel loginPanel;
	
	public LogInFrame()
	{
		userLabel 	= new JLabel("Username");
		userField 	= new JTextField(10);
		passLabel 	= new JLabel("Password");
		passField 	= new JPasswordField(10);
		loginButton = new JButton("Log In");
		
		// Create loginPanel
		loginPanel = new JPanel();
		loginPanel.add(userLabel);
		loginPanel.add(userField);
		loginPanel.add(passLabel);
		loginPanel.add(passField);
		loginPanel.add(loginButton);
		
		// Add loginPanel to frame
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

	private class LoginButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}