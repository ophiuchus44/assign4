import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//@author Paul

public class Ballots extends JPanel{


	String parts [];
	String categoryID;
	String categoryName;
	String catDataRaw;
	String [] categoryData;

	/////////////////////////////

	JPanel thePanel; // panel
	JLabel theLabel; // categoryname
	JButton [] theButtons;

	//ArrayList<JButton> theButtons;
	JTextField writeIn;
    JButton btn_writeIn;

    
    String vote = "" ; // this will store any votes, if clicked, turns null


    boolean writeInSelected;
    boolean buttonsSelected;

	public boolean clicked;

/////////////////////////////////

	//String categoryItems [][];

	// arrayList of jbuttons 
	//ArrayList<JButton> categoryBtns [][];

	public Ballots(String ballotData){

		parts = ballotData.split(":");

		categoryID = parts[0];
		categoryName = parts[1];
		catDataRaw = parts[2];

		categoryData = catDataRaw.split(",");


		thePanel = new JPanel();
		theLabel = new JLabel(categoryName);

		// plus one because we need to have space for the btn_writIn button
		//theButtons = new JButton[categoryData.length];
		theButtons = new JButton[categoryData.length+1]; // need extra button for btn_writeIN

		theLabel.setHorizontalAlignment(SwingConstants.CENTER);
		theLabel.setFont(new Font("Serif", Font.BOLD, 30));

		thePanel.setLayout(new GridLayout (0,1));
		thePanel.add(theLabel);

		
		for (int i=0; i< categoryData.length; i++){

			theButtons[i]= new JButton(categoryData[i]);

			theButtons[i].setFont(new Font("Serif", Font.BOLD, 30));

			ActionListener listen = new BListener();
			theButtons[i].addActionListener(listen);

			//clicked = false;

			//setLayout(new GridLayout (0,1));

			thePanel.add(theButtons[i]);

		}

		writeIn = new JTextField();
		btn_writeIn = new JButton("SELECT WRITE IN");

		//clicked = false;

		// add the btn_writein to the list of buttons
		theButtons[categoryData.length] = btn_writeIn;
		ActionListener listen2 = new BListener(writeIn);

		//ActionListener listen2 = new BListener();

		//listen2.addTxt(writeIn);
					//BListener.addTxt(writeIn);

		//theButtons[categoryData.length].addActionListener(listen2);


		//ActionListener listen2 = new myTextListener(writeIn);
        theButtons[categoryData.length].addActionListener(listen2);

		thePanel.add(writeIn);
		thePanel.add(btn_writeIn);

		add(thePanel);

	}

//	public boolean getStatus()
//	{
//		return clicked;
//	}

	public String getVote(){
		return vote;
	}



/* private class myTextListener implements ActionListener{
            JTextField txtLocalField;
            String txtLocal;

            public myTextListener(JTextField txt){
                  txtLocal = txt.getText();
                  txtLocalField = txt;
            }

            public void actionPerformed(ActionEvent e){


                  //JButton buttonPressed = (JButton) e.getSource();




                  if (e.getSource() == theButtons[categoryData.length]){
                  	txtLocal = txtLocalField.getText();

                  	if (vote.equals(txtLocal)){
                  		theButtons[categoryData.length].setForeground(Color.BLACK);	
                  		vote = "";
                  		//writeInSelected = false; //not selected

                  	}
                  	else if (!vote.equals(txtLocal)){
                  		theButtons[categoryData.length].setForeground(Color.RED);
                  		vote = txtLocal;
                  		//writeInSelected = true; // if the vot is the write in it's selected

                  		//buttonsSelected = false; // talks to buttons 
                  	}
					//txtLocal = txtLocalField.getText();
                  	//vote = txtLocal;
					//theButtons[categoryData.length].setForeground(Color.RED);
                  }
                 else{

                  	theButtons[categoryData.length].setForeground(Color.BLACK);	
                  }
                  System.out.println(txtLocal);


				//if (writeInSelected){
                //theButtons[categoryData.length].setForeground(Color.RED);	
                 //writeInSelected = false; //not selectede
                  	//}



                  }

            }

*/

	private class BListener implements ActionListener
	{

		public BListener(){

		}

		JTextField txtLocalField;
        String txtLocal;
		public BListener(JTextField txt){
			//BListener.addTxt(writeIn);
			 txtLocal = txt.getText();
             txtLocalField = txt;

		}

		public void actionPerformed(ActionEvent e)
		{

			//clicked = !clicked;
			for (int i=0; i<theButtons.length;i++){ //-1 because the last button is different 

				if (e.getSource() == theButtons[i]){

					if (vote.equals(theButtons[i].getText())) {

						theButtons[i].setForeground(Color.BLACK);
						vote = "";
						//buttonsSelected = false;
					}

					else if (!vote.equals(theButtons[i].getText())) {
						// first check if it is the write in btn
						if(theButtons[i].getText().equals("SELECT WRITE IN")){

							if(vote.equals(txtLocalField.getText())){
								theButtons[i].setForeground(Color.BLACK);
								vote = "";
								System.out.println("Select btn clicked");
								//System.out.println(txtLocal);
								return; // break out after so vote doesn't get assigned to select write in
								}
							else if (!vote.equals(txtLocalField.getText())) {
								theButtons[i].setForeground(Color.RED);
								vote = txtLocalField.getText();
								System.out.println("Select btn clicked");
								//System.out.println(txtLocal);
								return; // break out after so vote doesn't get assigned to select write in
							}

							//theButtons[i].setForeground(Color.RED);
							//vote = txtLocalField.getText();
							//System.out.println("Select btn clicked");
							//System.out.println(txtLocal);
							//return; // break out after so vote doesn't get assigned to select write in
						}
		
						theButtons[i].setForeground(Color.RED);
						vote = theButtons[i].getText();
			
						}

					}



				else {
					theButtons[i].setForeground(Color.BLACK);
					//clicked = false;
					//buttonsSelected = false;
				}

			}
			System.out.println(vote);

		/*	if (buttonsSelected){
                theButtons[categoryData.length].setForeground(Color.BLACK);	
                 writeInSelected = false; //not selectede
                  	}

                  	*/

		}
	}
	

}