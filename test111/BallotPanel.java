// CS 0401 Fall 2017
// This handout demonstrates extending the JPanel class to encapsulate and
// access information in a graphical environment.

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//@author Paul

public class BallotPanel extends JPanel{


	//VoteInterface voteInterface;
	String file;
	int numCategories = 0;
	//String voterID;
	String ballotData;

// 	JPanel controlPanel;
// 	JPanel panel;

// 	JLabel [] jLabels;


///////////////////////////////
 	JFrame theWindow;
 	Ballots [] theBallots;
 	JButton countClicks;


 	String [] votes;
 	// votes is array of 
		

////////////////////////////

	//JButton [][] buttons; // list of buttons of jbuttons


	//public BallotPanel(String fileName, VoteInterface V) 
	public BallotPanel(String fileName) 
	{
		
		// load voteinterface
	//	voteInterface = V;
		// load filename into file for later saving
		file = fileName;
		//controlPanel = new JPanel();
		// this might need be a JPanel???
		theWindow = new JFrame("Voting Portion");


		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


		try{
		
            Scanner scan = new Scanner(new File(fileName));

        	numCategories = Integer.parseInt(scan.nextLine());

        	//jLabels = new JLabel[numCategories];

        	theBallots = new Ballots[numCategories]; // create new ballot objects

        	votes = new String[numCategories]; // creates array for final votes info

        	for(int i = 0; i< numCategories; i++){
            	ballotData = scan.nextLine();
            	theBallots[i] = new Ballots(ballotData);
            	theWindow.add(theBallots[i]);
            	}
           }

        catch(Exception e) {
			e.printStackTrace();
			}
		
		theWindow.setLayout(new GridLayout(0,numCategories));
		theWindow.pack();
		theWindow.setVisible(true);

	






		// everything should be loaded into Ballots Objects

			// system check to make sure everything was loaded correctly into ballots objects

			for (int a=0; a< numCategories; a++){
				System.out.println("Cat ID num: " +theBallots[a].categoryID);
				System.out.println("Cat name: " +theBallots[a].categoryName);

		//		jLabels[a] = new JLabel(ballots[a].categoryName);
				
		//		controlPanel.add(jLabels[a]);

				for (int y = 0; y< theBallots[a].categoryData.length; y++){
						System.out.println("Item: " + theBallots[a].categoryData[y]);				
//						buttons[a][y] = new JButton(ballots[a].categoryData[y]);
				

				}								
			}

/*			for (int x =0; x<ballots[x].categoryData.length; x++){
				for(int z = 0; z< numCategories; z++){
					controlPanel[z].add(buttons[z][x]);
					controlPanel[z].setLayout(new GridLayout(0,1,5,5));
					controlPanel[z].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
				}

			}
*/			


		//	controlPanel.setLayout(new GridLayout(numCategories,categoryData.length,5,5));
			//controlPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		

		countClicks = new JButton("Submit Vote(s)");
		countClicks.setFont(new Font("Serif", Font.BOLD, 30));

		countClicks.addActionListener(new voteListener());
		theWindow.add(countClicks);
		//theWindow.setSize(800,600);
		theWindow.pack();
		theWindow.setVisible(true);



		}

/*	private class btnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			for(int i = 0; i< numCategories; i++){


			}


		}
	} //end of btnListener

	*/



	
	class voteListener implements ActionListener
	{
	 	// Listener to check how many of the buttons within the JPanels have
	 	// been clicked.  Note how we iterate through the panels, calling a
		// method in each panel to test for this, since we cannot access
		// the buttons directly (because they are encapsulated within the
		// JPanel subclasses)
		public void actionPerformed(ActionEvent e)
		{

			int count = 0;

			for (int i=0; i< numCategories; i++){
				votes[i]= theBallots[i].getVote();
				if (theBallots[i].getVote().equals("")){

				}
				else{
					count++;
				}

				//System.out.println("Vote: " + votes[i]);

			}


			for (int x=0; x< numCategories; x++){
				System.out.println("the votes: " + votes[x]);

			}

			//System.out.println("the votes: " + votes);


			JOptionPane.showMessageDialog(theWindow, count + " of the buttons are clicked");
		
		}


	}




      public static void main (String [] args)
      {
            new BallotPanel("ballots2.txt");


      }
}// end of class





	