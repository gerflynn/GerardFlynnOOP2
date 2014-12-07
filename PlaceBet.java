//PlaceBet.java
/*This class is triggered from a successful login
 *This in turn loads the previous progress of the user
 *Also provides:
 *	An interface to the user to manage account
 *	The main processing element of gambling on a horse
 *	Saves progress*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;	//JFrame methods
import java.util.*;	//ArrayList
import java.io.*;	//Saving/reading

public class PlaceBet extends JFrame{
	
	//Global Variables
	private JMenuBar menu;
	private JComboBox combobox;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	
	private static ArrayList<Integer> results;	//ArrayList returned from Race() method
	private JTextArea resultSheet;
	private static String name1, name2, name3, name4, name5, name6;	//shorter than calling getName() 6 times.
	private Horse h1 = new Horse("Exterminator", 2, 1);
	private Horse h2 = new Horse("Storm Cat", 4, 1);
	private Horse h3 = new Horse("Thunder Hooves", 8, 1);
	private Horse h4 = new Horse("Geoff", 8, 1);
	private Horse h5 = new Horse("Odds On Favourite", 16, 1);
	private Horse h6 = new Horse("Roscoe", 16, 1);
	private Account a1 = new Account();
	private static int betCount;	//Accumulator bet, 3 races per 1 stake, betCount counts each run
	private static int raceCount;	//raceCount counts each race (3 per run)
	private int magicNumber = 0;	//stores the odds of the required horse for the purpose of calculating the winnings
	private int winRace, loseRace;
	
	//Main Method
	public static void main(String args[]){
		PlaceBet gui = new PlaceBet();
		gui.setVisible(true);
	}//End Main Method
	
	
	//Constructor
	public PlaceBet(){
		super("Place Your Bet!!");
		setLayout(new FlowLayout());
		setSize(400,700);
//		setResizable(false);
		setLocation(600,20);
		getContentPane().setBackground(Color.BLACK);
		setIconImage(new ImageIcon("H:\\OOP Project\\horse.png").getImage());
		
		//call to read balance
		try{
			File inFile = new File("data.dat");
			FileInputStream fis = new FileInputStream(inFile);
			DataInputStream dis = new DataInputStream(fis);
			
			a1.setBalance(dis.readDouble());	//load up cash earned in previous races
			a1.setWins(dis.readInt());			//load total races ever won
			a1.setTotalRaces(dis.readInt());	//load total races ever bet on
			System.out.print("\n\tB " + a1.getBalance() + " W " + a1.getWins() + " W " + a1.getTotalRaces()); //test...
			dis.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		MenuEventHandler handler = new MenuEventHandler();
		
		//JMenuBar
		menu = new JMenuBar();	
		setJMenuBar(menu);
		createMenus();	//Call to populate JMenuBar
		
		//JTextArea to display the form
		JTextArea textArea = new JTextArea(7,40);
		Font font = new Font("monospaced",Font.BOLD,14);
		textArea.setFont(font);
		String formatSpecifier = "%-8s%-18s%s\n" +
								 "%-8s%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n";
		textArea.setText(String.format(formatSpecifier,"Post","Horse","Odds",
													   "====","=====","====",
													   1,h1.getName(),h1.getOdds().toString(),
													   2,h2.getName(),h2.getOdds().toString(),
													   3,h3.getName(),h3.getOdds().toString(),
													   4,h4.getName(),h4.getOdds().toString(),
													   5,h5.getName(),h5.getOdds().toString(),
													   6,h6.getName(),h6.getOdds().toString()));
		add(textArea);
		
		
		//Create Master JPanel to fix other panel to
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));	//align, horizontal gap, vertical gap
		masterPanel.setBackground(Color.DARK_GRAY);	
		add(masterPanel);
		
			//JLabel, JCombobox and JButtons to offer betting options
				//JLabel informs purpose of JComboBox
			JLabel euroSymbol = new JLabel("Set the Stake € ");
			//add(euroSymbol);
			
				//JCombobox holds an array of Integers which are amounts to bet that the user can choose
				//ToolTipText displays helpful information on hovering
			Integer[] bets = {10,20,30,50,100};	//////////*********Reference 1**********//////////////
			combobox = new JComboBox(bets);		//////////*********End Reference 1**********//////////
			combobox.setSelectedIndex(2);//€30 option selected by default
			combobox.addActionListener(handler);
			//add(combobox);
			
				//JPanel to add JComboBox and JLabel
			JPanel stakePanel = new JPanel();
			stakePanel.setLayout(new GridLayout());
			stakePanel.add(euroSymbol);
			stakePanel.add(combobox);
			//add(stakePanel);
			
				//Create JButtons to select a horse to bet on
			button1 = new JButton("Horse 1");
			button1.setToolTipText("Exterminator");
			button1.addActionListener(handler);
			
			button2 = new JButton("Horse 2");
			button2.setToolTipText("Storm Cat");
			button2.addActionListener(handler);
			
			button3 = new JButton("Horse 3");
			button3.setToolTipText("Thunder Hooves");
			button3.addActionListener(handler);
			
			button4 = new JButton("Horse 4");
			button4.setToolTipText("Geoff");
			button4.addActionListener(handler);
			
			button5 = new JButton("Horse 5");
			button5.setToolTipText("Odds On Favourite");
			button5.addActionListener(handler);
			
			button6 = new JButton("Horse 6");
			button6.setToolTipText("Roscoe");
			button6.addActionListener(handler);
			
				//JPanel Grid to add buttons to
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(3, 2, 4, 4));	//rows, columns, horizontal gap, vertical gap
			buttonPanel.add(button1);	//add to grid
			buttonPanel.add(button2);
			buttonPanel.add(button3);
			buttonPanel.add(button4);
			buttonPanel.add(button5);
			buttonPanel.add(button6);
			
		//Add Labels to masterLabel
		masterPanel.add(stakePanel);
		masterPanel.add(buttonPanel);
		
		//2nd JTextArea to return the results as they occur
		resultSheet = new JTextArea();
		//JScrollPane sp = new JScrollPane(resultSheet);
		//resultSheet.add(sp);
		//add(sp);
		resultSheet.setText("");//initially no results
		add(resultSheet);
		
		//Refers to global String variables, required for storing to ArrayList
		name1 = h1.getName();
		name2 = h2.getName();
		name3 = h3.getName();
		name4 = h4.getName();
		name5 = h5.getName();
		name6 = h6.getName();
		
		//Anonymous Inner Class to save on close
		/*This overrides window closing function
		 *adds the function of saving progress to file*/ 
		addWindowListener(new WindowAdapter(){			//extends WindowAdapter which extends WindowListener
			public void windowClosing(WindowEvent e){	//this method overrides WindowAdapter method
				JOptionPane.showMessageDialog(null,"Saving Progress...");
					//Call to save balance
					try{
						File outFile = new File("data.dat");	//Reference to file
					    FileOutputStream fos = new FileOutputStream(outFile);	//Create stream
					    DataOutputStream dos = new DataOutputStream(fos);
					    
					    dos.writeDouble(a1.getBalance());	//save total cash earned
					    dos.writeInt(a1.getWins());			//save total number of races won
					    dos.writeInt(a1.getTotalRaces());	//save total number of races played
					    System.out.print("\n\tB " + a1.getBalance() + " W " + a1.getWins() + " R " + a1.getTotalRaces());	//test...
					    dos.close();
					}
					catch(IOException eve){
						eve.printStackTrace();
					}
	      		System.exit(0);
			}//End Overriding Class
		});//End Anonymous Class
	}//End Constructor
	
	
	//Method to populate the JMenuBar
	public void createMenus(){
		
		//Account Menus
		MenuEventHandler handler = new MenuEventHandler();
		JMenu account = new JMenu("Account");
		menu.add(account);
		JMenuItem view = new JMenuItem("View");
		account.add(view);
		view.addActionListener(handler);
		JMenuItem update = new JMenuItem("Update");
		account.add(update);
		update.addActionListener(handler);
		
		//Stats Menu
		JMenu stats = new JMenu("View Stats");
		menu.add(stats);
		JMenuItem success = new JMenuItem("Success");
		stats.add(success);
		success.addActionListener(handler);
		
	}//End createMenus Method
	
	
	//Event Handling Class
	private class MenuEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Menu Handlers
			if(e.getActionCommand().equals("View")){
				//View account details
				JOptionPane.showMessageDialog(null,"Your balance is €" + String.format("%.2f",a1.getBalance()),"Balance",JOptionPane.PLAIN_MESSAGE);
			}
			if(e.getActionCommand().equals("Update")){
				String lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
				
				//Calls isValidAmount validation method
				while(!isValidAmount(lodgement)){
					JOptionPane.showMessageDialog(null,"Amount should be numeric","Invalid Number",JOptionPane.WARNING_MESSAGE);
					lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
				}
				a1.updateBalance(Double.parseDouble(lodgement));
				System.out.print("\n" + a1.toString());	//test...
			}
			if(e.getActionCommand().equals("Success")){
				JOptionPane.showMessageDialog(null,"Number of races played: " + a1.getTotalRaces() + 
												   "\nNumber of races one: " + a1.getWins() + 
												   "\nSuccess Rating: " + String.format("%.2f%%",(float)((float)a1.getWins()/(float)a1.getTotalRaces())*100));
			}
			
			//Button Handlers
			if(e.getSource() == button1){
				/*magicNumber refers to the button pressed,
				 *Each button refers to each horse.
				 *The race method will return an ArrayList of wins per horse.
				 *magicNumber refers to the particular horse selected ie the corresponding element*/
				magicNumber = 0;
				//magicMethod will calculate the payout from the bet	
				magicMethod();
			}
			if(e.getSource() == button2){
				magicNumber = 1;
				magicMethod();
			}
			if(e.getSource() == button3){
				magicNumber = 2;
				magicMethod();
			}
			if(e.getSource() == button4){
				magicNumber = 3;
				magicMethod();
			}
			if(e.getSource() == button5){
				magicNumber = 4;
				magicMethod();
			}
			if(e.getSource() == button6){
				magicNumber = 5;
				magicMethod();
			}
		}
	}//End Event Handling Class
	
	
	//magicMethod Calculates the winnings from the race
	public void magicMethod(){	
		//get selected amount from combobox
		int n = (Integer)combobox.getSelectedItem();
		//check bet against balance
		/*Validation to make sure enough money is in the account to make the bet*/
		if(n > a1.getBalance()){
			JOptionPane.showMessageDialog(null,"Insufficient funds. Select \"Account\" and \"Update\"...");
		}
		else{
			race();
			//find number of wins from appropriate horse in arraylist of results
			int w = results.get(magicNumber);
				
				//store stats
				/*Calls to relevant Account methods which calculate the success of the user*/
				a1.incrementTotalWins(w);
				a1.incrementTotalRaces(raceCount);
				raceCount = 0;	//raceCount must be reset after every race as it must accumulate in three's
				System.out.print("\nW " + a1.getWins() + " T" + a1.getTotalRaces());	//test...
			
			//find odds of selected horse
			int i = 0;
			switch(magicNumber){
				case 0:
					i = Character.getNumericValue(h1.getOdds().toString().charAt(0));	//Character.getNumericValue()//*******Reference 5*********//
					break;
				case 1:
					i = Character.getNumericValue(h2.getOdds().toString().charAt(0));	//extracts the first character from the odds object
					break;
				case 2:
					i = Character.getNumericValue(h3.getOdds().toString().charAt(0));
					break;
				case 3:
					i = Character.getNumericValue(h4.getOdds().toString().charAt(0));
					break;
				case 4:
					i = Integer.parseInt(h5.getOdds().toString().substring(0,2));	//substring because odds are 16/1 so need the first two characters
					break;
				case 5:
					i = Integer.parseInt(h6.getOdds().toString().substring(0,2));
					break;
			}
			
			//calculate payout
			int payout = (w*i*n);	//(w=number of wins<eg won 2 races> i=odds<eg 16/1> n=selected amount<eg €30>)
			
			//append to display
			resultSheet.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
			
			//update user's balance
			a1.decrimentStake(n);
			a1.incrementWinnings(payout);				
			
			System.out.print("\npayout " + payout);	//test...
			System.out.print("\n" + a1.toString());	//test...
		}
	}
	
	
	//Race Method
	/*returns an ArrayList of wins per horse
	 *the first element carries the number of wins on the first horse
	 *the second.......wins on the second horse
	 *the third.....and so on*/
	public static ArrayList<Integer> race(){
		results = new ArrayList<Integer>();
		int race, winner, h1count=0, h2count=0, h3count=0, h4count=0, h5count=0, h6count=0;
		
		//For Loop
		/*Decifers Winner of race, randomly.
		 *3 races per bet, like an accumulator.
		 *The higher the odds of the horse,
		 *the more entries in the random generator pool*/
		for(race=0; race<3; race++){
			winner = (int)(Math.random()*17)+1;
			if(winner == 1 || winner == 2 || winner == 4 || winner == 9 || winner == 12 || winner == 14 || winner == 16 || winner == 17){
				h1count++;
				JOptionPane.showMessageDialog(null,name1 + " wins race " + (race+1));
			}
			else if(winner == 3 || winner == 7 || winner == 11 || winner == 15){
				h2count++;
				JOptionPane.showMessageDialog(null,name2 + " wins race " + (race+1));
			}
			else if(winner == 5 || winner == 10){
				h3count++;
				JOptionPane.showMessageDialog(null,name3 + " wins race " + (race+1));
			}
			else if(winner == 6 || winner == 13){
				h4count++;
				JOptionPane.showMessageDialog(null,name4 + " wins race " + (race+1));
			}
			else if(winner == 8){
				h5count++;
				JOptionPane.showMessageDialog(null,name5 + " wins race " + (race+1));
			}
			else if(winner == 18){
				h6count++;
				JOptionPane.showMessageDialog(null,name6 + " wins race " + (race+1));
			}
			raceCount++;
			
			//System.out.print(winner);
		}//End For Loop
		System.out.print("h1" + h1count + " h2" + h2count + " h3" + h3count + " h4" + h4count + " h5" + h5count + " h6" + h6count);	//test...
		
		betCount++;
		
		/*adding the number of wins per horse to the array,
		 *each horse represented by an array element*/
		results.add(h1count);
		results.add(h2count);
		results.add(h3count);
		results.add(h4count);
		results.add(h5count);
		results.add(h6count);
		return results;
	}//End Race Method
	
	
	//isValidAmount method to validate the amount entered is a number
	/*try to parse is at as double
	 *if it cant then re-enter rather than crashing
	 *use try catch as is quicker than searching characters*/		//**************************throw exception for cancel button
	public static boolean isValidAmount(String lodgement){
		//if statement to check for negative value which would pass the parse test
		if(lodgement.charAt(0) == '-')
			return false;	//dont want negative values entered as lodgements
		else{
			try{
				Double.parseDouble(lodgement);
			}	
			catch(NumberFormatException e){	//if it cant be parsed as a number then it is invalid
				return false;				//return false to trigger re-enter message
			}
		}
		return true;
	}//End isValidAmount Method
}//End PlaceBet Class