import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BallotTest3{

	public static void main (String [] args){
	


		JFrame frame = new JFrame("Test Ballot");
		frame.setVisible(true);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,400);

		JPanel panel = new JPanel();

		int numCategories = 0;
		// some sort of loop to see how many categories there are?

		try{
			Scanner scan = new Scanner(new File("ballots2.txt"));
        	numCategories = Integer.parseInt(scan.nextLine());



            for(int i = 0; i< numCategories; i++){

            	String ballotData = scan.nextLine();
            	String parts [] = ballotData.split(":");

            	String ballotInt = parts[0];
            	String officeName = parts[1];
            	String candidates = parts[2];

            	String candidateParts [] = candidates.split(",");

            	System.out.println("BallotID: " + ballotInt);
            	System.out.println("OfficeName: " + officeName);

            	JPanel panelCat = new JPanel();
            	JLabel catName = new JLabel(officeName);
            	//panelCat.add(catName);
            	JPanel paneltmp = new JPanel();

            	paneltmp.add(catName);
            	for (int x = 0; x< candidateParts.length; x++){
    
            		paneltmp.setLayout(new GridLayout(0,1,2,3));

            		JButton btn1 = new JButton(candidateParts[x]);
            		


            		paneltmp.add(btn1);
            		
            		
            		System.out.println("Candidates Item: " + candidateParts[x]);

            		paneltmp.setVisible(true);

            		//panel1.add(x);
            	}

            	JTextField writeIn = new JTextField();
            	JButton btn_writeIn = new JButton("SELECT WRITE IN");
            	//JLabel btn_warning = new JLabel("WARNING. WRITE");

            	paneltmp.add(writeIn);
            	paneltmp.add(btn_writeIn);

            	panelCat.add(paneltmp);
            	//panelCat.add(writeIn);
            	//panelCat.add(btn_writeIn);

				//theWindow.setLayout(new FlowLayout());




            	panelCat.setVisible(true);
            	//panelCat.setComponentOrientation(ComponentOrientation.NORTH_);


            	panel.add(panelCat);


            	System.out.println();
            
         
            }

		}
		
		catch(Exception e) {
			e.printStackTrace();
			}



		panel.setLayout(new GridLayout(0,numCategories,5,10));

		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


		frame.add(panel);
		frame.pack();
		frame.setVisible(true);




	}
}