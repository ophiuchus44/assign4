
import java.util.*;
import java.io.*;

public class Voter
{

	public String voterId;
	public String voterName;
	public String voterVoted;
	//private String fileNAME;
	//private File file;
	//private Scanner inScan;

	// constructor method
	public Voter()
	{
		voterId = "";
		voterName = "";
		voterVoted = "";

	}

//load voter info?
	public static Voter getVoter(String fileName, String voterIDnum){
		
		Voter currentVoter = null;

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


                
//				System.out.println("Voter ID: " + id);
//               System.out.println("Voter Name: " + name);
//                System.out.println("Voter Voted: " + voted);

                // check if current voter matches searching for voter
                if(id.equals(voterIDnum)){
        			currentVoter = new Voter();
        			currentVoter.voterId = id;
        			currentVoter.voterName = name;
        			currentVoter.voterVoted = voted;
        			return currentVoter;
        			}



    		}
    // closing the scanner stream
    		scan.close();

		}
		
		catch(Exception e) {
			e.printStackTrace();
			}
			

		return currentVoter;

	}


	public String getId(){
		return voterId;
	}

//return voter id number
	public String getName(){
		return voterName;
	}

	public String toString(){
		return "ID: " + voterId + "  Name: " + voterName + "  Voted? " + voterVoted;
	}


// return boolean 
	public Boolean hasVoted(){
		if(voterVoted.equals("false")){
			return false;
		}
		
		return true;

	}

// vote and change boolean to true
	public void vote(){
		voterVoted = "true";		

	}

// save Voter object V to filename2
	public static void saveVoter(String fileName2, Voter V){


		Voter currentVoter = null;

		ArrayList voterData33  = new ArrayList();

		// first read in the contents of the file
		try{
			Scanner scan = new Scanner(new File(fileName2));
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


                // if the id matches the object voters voter id, override current variables
       			if(id.equals(V.voterId)){
        			parts[0] = V.voterId;
        			parts[1] = V.voterName;
        			parts[2] = V.voterVoted;
        			}

        		// string of data to be added to arraylist 
        			// should have updated fields for everything!
        		String voterData2 = parts[0] + ":" + parts[1] + ":"+ parts[2];

        		// add string to arraylist voterdata
				voterData33.add(voterData2);
			}
					
    // closing the scanner stream
    		scan.close();

		}
		
		catch(Exception e) {
			e.printStackTrace();
			}


// scanner should be closed by now... now write to file over riding everthing
        // update contents of file

		try{
			//System.out.println("SAVE LOCATION: " + fileNAME);

			FileWriter wr = new FileWriter(fileName2);
		

			for (int i=0; i<voterData33.size(); i++){
				wr.write(voterData33.get(i) + "\n");
			}
			

			wr.close();
		}
		catch (Exception ex) {
            ex.printStackTrace();

		}


	}


} // end of class