// CS 0401 Fall 2017
// This handout demonstrates extending the JPanel class to encapsulate and
// access information in a graphical environment.

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends JPanel{

	Voter voter;
	LoginInterface logInt;
	String file;
	//String test1;

	String voterID;
 	JTextField input;
 	JPanel controlPanel;


    JLabel greeting;
	JLabel voterid;
	JButton login;

	public LoginPanel(String filename, LoginInterface L) 
	{
		logInt = L;
		file = filename;

		//JFrame frame = new JFrame();


		controlPanel = new JPanel();

		controlPanel.setLayout(new GridLayout(3,2,10,10));
		//frame.getContentPane().add(controlPanel);
		
		//JPanel inputpanel = new JPanel();
        //inputpanel.setLayout(new FlowLayout());
        input = new JTextField(10);
        //voterID = input.getText();


        greeting =  new JLabel("Please log into the site");
        voterid =  new JLabel("Voter ID:");
        login = new JButton("Submit");

        ActionListener listen = new myListener();
        login.addActionListener(listen);


        controlPanel.add(greeting);
        controlPanel.add(voterid);
        controlPanel.add(input);
        controlPanel.add(login);
        add(controlPanel);

/*        login.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		// get info from text
        		test1 = input.getText();
        		voter = Voter.getVoter(file, test1);

        		if (voter != null){
        			JOptionPane.showMessageDialog(null, test1);
        			//instVar.getVoter(voter);	
        			instVar.setVoter(voter);
        		}
        	}
        });
   
*/
        //ControlListener theListener = new ControlListener();
		//input.addActionListener(theListener);
		//login.addActionListener(theListener, voterID);
		
		/*JFrame theWindow = new JFrame();

		theWindow.setLayout(new GridLayout(1,2));

		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.add(greeting);
		theWindow.add(voterid);
		theWindow.add(input);
		theWindow.add(login);
		theWindow.pack();
		theWindow.setVisible(true);
		*/

	}

	private class myListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			voterID = input.getText();
			voter = Voter.getVoter(file, voterID);

			if(voter == null){
				JOptionPane.showMessageDialog(null, "User not found");
				input.setText("");
			}
			else if(voter.hasVoted() == false){
				logInt.setVoter(voter);
				input.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null, "Sorry, " + voter.voterName + ", but you already voted");
				input.setText("");

			}


		}
	}
	
}
	