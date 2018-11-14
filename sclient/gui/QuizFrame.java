package sclient.gui;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import sclient.network.Connection;

/**
 *	Swing GUI that allows a student to interact with an open QuizKnows session.
 */
public class QuizFrame extends JFrame
{
	// Width and height of the device
	private int frameWidth  = 800;
	private int frameHeight = 480;

	// Icons
	public static ImageIcon inactiveIcon;
	public static ImageIcon activeIcon;
	public static ImageIcon lockedIcon;

	// Swing objects
	private JLabel  questionLabel;
	private JButton quizButton;
	private JPanel  quizPanel;

	// User variables
	String fname;
	String lname;
	String email;

	// Control variables
	private String sessionKey;
	private boolean active;
	private boolean locked;

	// Connection variables
	Connection conn;

	public QuizFrame(String fname, String lname, String email, String sessionKey)
	{
		// Set user variables
		this.fname 			= fname;
		this.lname 			= lname;
		this.email 			= email;

		// Set control variables
		this.sessionKey 	= sessionKey;
		active 				= false;
		locked				= false;

		// Create icons
		inactiveIcon 		= new ImageIcon("img/inactive.png");
		activeIcon			= new ImageIcon("img/active.png");
		lockedIcon			= new ImageIcon("img/locked.png");

		// Create swing objects
		questionLabel 	= new JLabel("");
		Font labelFont = questionLabel.getFont();
		questionLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		quizButton		= new JButton(inactiveIcon);
		quizButton.addActionListener(new QuizButtonListener());

		// Create JPanel
		quizPanel		= new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 1;
		c.gridy = 0;
		quizPanel.add(questionLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,0);
		quizPanel.add(quizButton, c);
		this.add(quizPanel);

		// Configure frame
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));
		this.setMaximumSize(new Dimension(frameWidth, frameHeight));
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("QuizKnows");
		this.pack();

		// Connect to QuizKnows session
		conn = new Connection(this);
	}

	// Getters and setters
	public boolean getActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean getLocked()
	{
		return locked;
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	public String getSessionKey()
	{
		return sessionKey;
	}

	public String getFname()
	{
		return fname;
	}

	public String getLname()
	{
		return lname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setIcon(ImageIcon icon)
	{
		quizButton.setIcon(icon);
	}

	public void setLabel(String label)
	{
		questionLabel.setText(label);
	}

	public void endSession()
	{
		conn.getListener().stop();
		conn.closeConnection();
		this.setVisible(false);
		new LogInFrame();
	}

	private class QuizButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(active && !locked)
			{
				conn.buzz();
			}
		}
	}
}
