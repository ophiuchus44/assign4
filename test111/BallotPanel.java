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

	int [] newVoteValue;


	VoteInterface voteInterface;
	String file;
	int numCategories = 0;
	//String voterID;
	String ballotData;

// 	JPanel controlPanel;
// 	JPanel panel;

// 	JLabel [] jLabels;


///////////////////////////////
 	//JFrame theWindow;
 	JPanel thePanel;
 	Ballots [] theBallots;
 	JButton countClicks;


 	String [] votes;
 	// votes is array of 
		

////////////////////////////

	//JButton [][] buttons; // list of buttons of jbuttons


	 File[] resultsFile; // results Files for votes



	public BallotPanel(String fileName, VoteInterface V) 
	//public BallotPanel(String fileName) 
	{
		
		// load voteinterface
		voteInterface = V;
		// load filename into file for later saving
		file = fileName;
		//controlPanel = new JPanel();
		// this might need be a JPanel???
		//theWindow = new JFrame("Voting Portion");

		thePanel = new JPanel();


		//theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


		try{
		
            Scanner scan = new Scanner(new File(fileName));

        	numCategories = Integer.parseInt(scan.nextLine());

        	//jLabels = new JLabel[numCategories];

        	newVoteValue = new int[numCategories];

        	theBallots = new Ballots[numCategories]; // create new ballot objects

        	votes = new String[numCategories]; // creates array for final votes info

        	for(int i = 0; i< numCategories; i++){
            	ballotData = scan.nextLine();
            	theBallots[i] = new Ballots(ballotData);
            	//theWindow.add(theBallots[i]);
				thePanel.add(theBallots[i]);
            	}
           }

        catch(Exception e) {
			e.printStackTrace();
			}
		
		//theWindow.setLayout(new GridLayout(0,numCategories));
		thePanel.setLayout(new GridLayout(0,numCategories));

		//theWindow.pack();
		//thePanel.pack();

		//theWindow.setVisible(true);

		thePanel.setVisible(true);

	
/////////////////////////////
/// create results file
/////////////////////////////


        resultsFile = new File[numCategories];
        FileWriter wr;

        for (int z =0; z<numCategories; z++){
        	resultsFile[z] = new File(theBallots[z].categoryID+ ".txt");
        	try{
        		wr = new FileWriter(resultsFile[z]);

        		for (int j=0; j<theBallots[z].categoryData[j].length(); j++){
        			wr.write(theBallots[z].categoryData[j]+ ":" + "0" + "\n");
        			wr.flush();
        		}
        	}
        	catch (Exception ex) {
            	ex.printStackTrace();

			}
        }



/*

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

			for (int x =0; x<ballots[x].categoryData.length; x++){
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
		//theWindow.add(countClicks);

		thePanel.add(countClicks);
		//theWindow.setSize(800,600);

		//thePanel.pack();
		thePanel.setVisible(true);
		//theWindow.pack();
		//theWindow.setVisible(true);

		add(thePanel);

		}

public void resetBallots(){
	for (int i=0; i<numCategories; i++){
		theBallots[i].resetBallots();
	}
}


/*
public void resetBallots(){
	for (int i=0; i<theBallots.length; i++){
		for (int a=0; a<theButtons.length; a++)
	}
}

	private class btnListener implements ActionListener{
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
					// do something with the votes? save them here??
					// don't save them til the vote is confirmed
				}

				//System.out.println("Vote: " + votes[i]);

			}


		//	for (int x=0; x< numCategories; x++){
		//		System.out.println("the votes: " + votes[x]);

		//	}

			//System.out.println("the votes: " + votes);


			//JOptionPane.showMessageDialog(theWindow, count + " of the buttons are clicked");
			//JOptionPane.showMessageDialog(thePanel, count + " of the buttons are clicked");



			String [] response = new String[]{"Yes (submits vote)", "No (returns to ballots)", "Cancel (leaves ballot)"};
			int ans = JOptionPane.showOptionDialog(null, "Confirm vote?", "Select an Option",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, response, response[0]);

			if (ans ==0){
				
				loadResults(); // gets new values for results
				
				saveResults();

				for (int x=0; x< numCategories; x++){
					 System.out.println("the votes: " + votes[x]);
					//	saveResults(resultsFile[x]);
						}

				System.out.println("Save votes to file!");

/*				for (int file =0; file<numCategories; file++){
					//Scanner tmpScan = new Scanner(new File(theBallots[z].categoryID+ ".txt");
					try{
		
            			Scanner tmpScan = new Scanner(resultsFile[file]);

            			String [] tmpData;
            			String tmpLine;
            			String tmpItem;
            			int tmpTotal;

            			while(tmpScan.hasNextLine()){
            				tmpLine = tmpScan.nextLine();
            				tmpData = tmpLine.split(":");
            				tmpTotal = Integer.parseInt(tmpData[1]);

            				for (int x=0; x< numCategories; x++){
									System.out.println("the votes: " + votes[x]);
									if (tmpData[0]==votes[x]){
										System.out.println("tmpItem: " + tmpData[0]);
									}

								}

            				

            			}


        				//String [] tmpData = scan.nextLine();
        				
        	

        



/*
				    
				    FileWriter wr2;
        			//resultsFile[z] = new File(theBallots[z].categoryID+ ".txt");
        			try{
        				wr2 = new FileWriter(resultsFile[file]);

        				for (int j=0; j<theBallots[z].categoryData[j].length(); j++){
        					if(theBallots[z].categoryData[i].equals(theBallots[z].getVote())){

        						if tmpParts[j]

//        						Scanner tmpScan = new Scanner(new File(resultsFile[file]))
  //      						if(tmpScan.nextLine()== theBallots[z].categoryData[j]+ + ":" +  + "\n")
    //    						wr.write(theBallots[z].categoryData[j]+ ":" + 1 + "\n");	
        					}
        					wr.write(theBallots[z].categoryData[j]+ ":" + "0" + "\n");
        					wr.flush();
        		
        					}
        				}

        			}
        		catch (Exception ex) {
        	    	ex.printStackTrace();

					}
	        	}


*/



			






				voteInterface.voted();

			}
			else if (ans==2){
				System.out.println("Clicked cancel!");
				voteInterface.voted();

			}
			//else if (ans ==1){
//
//				System.out.println("Clicked no!");


//			}





			//voteInterface.voted();

/////////////////////////////////////////////////////////////////
	// RESET BALLOTS here??? 	
//			for (int a=0; a<numCategories; a++){
//				theBallots[a].resetBallots();

//			}
//////////////////////////////////////////////////////////////////			

		}


	}


public void loadResults(){

	//ArrayList voterData33  = new ArrayList();
	//int tmpInt;

	//int [] newVoteValue = new int[numCategories];

	try{
		for (int x =0; x<numCategories; x++){

			if(!votes[x].equals("")){ // if vote isnt empty
				Scanner tmpScan = new Scanner(resultsFile[x]); // find its file and scan
				int tmpInt;

				while(tmpScan.hasNextLine()){
					String tmpData = tmpScan.nextLine();
					String tmpParts[] =tmpData.split(":");
					String tmp = tmpParts[0];
					if(votes[x].equals(tmp)){
						tmpInt = Integer.parseInt(tmpParts[1]); // fill with hopefully current value in txt file

						tmpInt++; // add one

						newVoteValue[x] = tmpInt;

						System.out.println("Should be 1? " + tmpInt);

						/// write back to file
						}
					}
			
				}

			}
		}
	catch(Exception e){
		e.printStackTrace();
		}

		
}



public void saveResults(){
//	for (int L=0; L<numCategories; L++){
//			System.out.println("New VOte Value: " + newVoteValue[L]);
//		}
	for (int x =0; x<numCategories; x++){

		//String [] tmpParts;
    	//String tmpLine;
    	//Scanner scan1, scan2, scan3;
    	//PrintWriter pw1, pw2, pw3;
		
		try{
		
			if(!votes[x].equals("")){ // if vote isnt empty


					String [] tmpParts;
    				String tmpLine1;
    				String tmpLine2;

		//		File tmpFile = new File(resultsFile[x])

				//BufferedReader br = new BufferedReader(new FileReader(resultsFile[x]));
    			//BufferedWriter wr = new BufferedWriter(new FileWriter(resultsFile[x], false));
				Scanner tmpScan = new Scanner(resultsFile[x]); 

				//String [] tmpParts;
				//System.out.println("test???");

				FileWriter fr1 = new FileWriter(new File("tmp" + votes[x] + ".txt"));

    			
				while(tmpScan.hasNextLine()){




					tmpLine1 = tmpScan.nextLine();
					//fr1.write(tmpLine1); // copy everything to new file?
					//fr1.flush();
					//fr1.close();

					tmpParts = tmpLine1.split(":");
//
//					FileWriter fr1 = new FileWriter(new File("tmp.txt"));
//					fr1.write(tmpLine1); // copy everything to new file?

					//System.out.println("tmpline test???");
				
					if(tmpParts[0].equals(votes[x])){
						// use new value int
						tmpLine2 = tmpParts[0] + ":" + newVoteValue[x]; // stores tmpLine with updatedLine
						//tmpScan.close();

						//FileWriter fr = new FileWriter(resultsFile[x])
						System.out.println("tmpline test: " + tmpLine2);
						fr1.write(tmpLine2); // copy everything to new file?
						fr1.flush();
						fr1.close();

					}
				}



				//FileWriter fr = new FileWriter



			}
			

			

		} // end of try

		catch(IOException e) {
        	e.printStackTrace();
    	}




    	


	}// end of for loop




}

/*public void saveResults(){

	//ArrayList voterData33  = new ArrayList();
	//int tmpInt;

	try{
		for (int x =0; x<numCategories; x++){

			if(!votes[x].equals("")){ // if vote isnt empty
				Scanner tmpScan = new Scanner(resultsFile[x]); // find its file and scan
				int tmpInt;

				while(tmpScan.hasNextLine()){
					String tmpData = tmpScan.nextLine();
					String tmpParts[] =tmpData.split(":");
					String tmp = tmpParts[0];
					if(votes[x].equals(tmp)){
						tmpInt = Integer.parseInt(tmpParts[1]); // fill with hopefully current value in txt file

						tmpInt++; // add one

						newVoteValue[x] = tmpInt;

						System.out.println("Should be 1? " + tmpInt);

						/// write back to file
						}
					}
			
				}

			}
		}
	catch(Exception e){
		e.printStackTrace();
		}

		for (int L=0; L<numCategories; L++){
			System.out.println("New VOte Value: " + newVoteValue[L]);
		}
}
*/


/*	public void loadResults(){
		String [] items;
		String [] values;

		for (int x =0; x<numCategories; x++){
					//Scanner tmpScan = new Scanner(new File(theBallots[z].categoryID+ ".txt");
					try{
		
            			Scanner tmpScan = new Scanner(resultsFile[x]);

            			String [] tmpData;
            			String tmpLine;
            			String tmpItem;
            			int tmpTotal;

            			while(tmpScan.hasNextLine()){
            				tmpLine = tmpScan.nextLine();
            				tmpData = tmpLine.split(":");
            				tmpTotal = Integer.parseInt(tmpData[1]);

            				for (int x=0; x< numCategories; x++){
									System.out.println("the votes: " + votes[x]);
									if (tmpData[0]==votes[x]){
										System.out.println("tmpItem: " + tmpData[0]);
									}

								}

            				

            			



		try{
			Scanner scan = new Scanner(new File(fileName));
        // Printing the delimiter used
        // Printing the tokenized Strings
            while (scan.hasNext()) {

            	// read in by line

       			String voterData = scan.nextLine();

       			// split data into an array of parts
                String parts [] = voterData.split(":");

                // assign parts to temp variables
                String id = parts[0];
                String name = parts[1];
                String voted = parts[2];


					}

				}
			}


		}	
	}
}

*/
      //public static void main (String [] args)
      //{
            //new BallotPanel("ballots2.txt");
      	//	new BallotPanel(args[0]);

      //}
}// end of class





	