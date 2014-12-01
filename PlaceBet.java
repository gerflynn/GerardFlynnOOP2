//PlaceBet.java
/**/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;	//for i/o

public class PlaceBet extends JFrame{
	
	//Global Variables
	JMenuBar menu;
	JComboBox combobox;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	
	static ArrayList<Integer> results;	//ArrayList returned from Race() method
	JTextArea resultSheet;
	static String name1, name2, name3, name4, name5;	//shorter than calling getName() 5 times.
	Horse h1 = new Horse("Exterminator", 2, 1);
	Horse h2 = new Horse("Storm Cat", 4, 1);
	Horse h3 = new Horse("Thunder Hooves", 8, 1);
	Horse h4 = new Horse("Geoff", 8, 1);
	Horse h5 = new Horse("Odds On Favourite", 16, 1);
	Account a1 = new Account();
	static int betCount;	//Accumulator bet, 3 races per 1 stake, betCount counts each run
	int magicNumber = 0;	//stores the odds of the required horse for the purpose of calculating the winnings
	
	
	//Main Method
	public static void main(String args[]){
		PlaceBet gui = new PlaceBet();
		gui.setVisible(true);
	}//End Main Method
	
	
	//Constructor
	public PlaceBet(){
		super("Place Your Bet!!");
		setLayout(new FlowLayout());
		setSize(340,700);
		setResizable(false);
		setLocation(600,20);
		//setBackground(Color.GREY);		//ContentPane
		
		//call to read balance
		try{
			File inFile = new File("data.dat");
			FileInputStream fis = new FileInputStream(inFile);
			DataInputStream dis = new DataInputStream(fis);
			
			a1.setBalance(dis.readDouble());
			System.out.print("\n\t" + a1.getBalance());
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
								 "%-8d%-18s%s\n";
		textArea.setText(String.format(formatSpecifier,"Post","Horse","Odds",
													   "====","=====","====",
													   1,h1.getName(),h1.getOdds().toString(),
													   2,h2.getName(),h2.getOdds().toString(),
													   3,h3.getName(),h3.getOdds().toString(),
													   4,h4.getName(),h4.getOdds().toString(),
													   5,h5.getName(),h5.getOdds().toString()));
		add(textArea);
		
		//JLabel, JCombobox and JButtons to offer betting options
			//JLabel just display's euro symbol
		JLabel euroSymbol = new JLabel("€");
		add(euroSymbol);
			//JCombobox holds an array of Integers which are amounts to bet that the user can choose
			//ToolTipText displays helpful information on hovering
		Integer[] bets = {10,20,30,50,100};	//////////*********Reference 1**********//////////////
		combobox = new JComboBox(bets);		//////////*********End Reference 1**********//////////
		combobox.setSelectedIndex(2);//€30 option selected by default
		combobox.addActionListener(handler);
		add(combobox);
		combobox.setToolTipText("Set Bet");
			//JButtons to select a horse to bet on
		button1 = new JButton("Horse 1");
		add(button1);
		button1.setToolTipText("Exterminator");
		button1.addActionListener(handler);
		button2 = new JButton("Horse 2");
		add(button2);
		button2.setToolTipText("Storm Cat");
		button2.addActionListener(handler);
		button3 = new JButton("Horse 3");
		add(button3);
		button3.setToolTipText("Thunder Hooves");
		button3.addActionListener(handler);
		button4 = new JButton("Horse 4");
		add(button4);
		button4.setToolTipText("Geoff");
		button4.addActionListener(handler);
		button5 = new JButton("Horse 5");
		add(button5);
		button5.setToolTipText("Odds On Favourite");
		button5.addActionListener(handler);
		
		//2nd JTextArea to return the results as they occur
		resultSheet = new JTextArea();
		resultSheet.setText("");//initially no results
		add(resultSheet);
		
		//Refers to global String variables, required for storing to ArrayList
		name1 = h1.getName();
		name2 = h2.getName();
		name3 = h3.getName();
		name4 = h4.getName();
		name5 = h5.getName();
		
		//Anonymous Inner Class
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
					    
					    dos.writeDouble(a1.getBalance());
					    System.out.print("\n\t" + a1.getBalance());	//test...
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
		account.addSeparator();
		JMenuItem quit = new JMenuItem("Quit");
		account.add(quit);
		quit.addActionListener(handler);
	}//End createMenus Method
	
	
	//Event Handling Class
	private class MenuEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Balance Handlers
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
//			if(e.getActionCommand().equals("Quit")){
//				int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to logout?",
//				"Logout",JOptionPane.YES_NO_OPTION);
//				if(confirm == JOptionPane.YES_OPTION)
//					System.exit(0);						//doesn't save to file
//			}
			
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
		}
	}//End Event Handling Class
	
	
	//magicMethod Calculates the winnings from the race
	public void magicMethod(){	
		//get selected amount from combobox
		int n = (Integer)combobox.getSelectedItem();
		//check bet against balance
		/*Validation to make sure enough money is in the account to make the bet*/
		if(n > a1.getBalance()){
			JOptionPane.showMessageDialog(null,"Insufficient funds. Select \"Balance\" and \"Update\"...");
		}
		else{
			race();
			//find number of wins from appropriate horse in arraylist of results
			int w = results.get(magicNumber);
			//find odds of selected horse
			int i = 0;
			switch(magicNumber){
				case 0:
					i = Character.getNumericValue(h1.getOdds().toString().charAt(0));	/////*************Find Reference***************/////
					break;
				case 1:
					i = Character.getNumericValue(h2.getOdds().toString().charAt(0));
					break;
				case 2:
					i = Character.getNumericValue(h3.getOdds().toString().charAt(0));
					break;
				case 3:
					i = Character.getNumericValue(h4.getOdds().toString().charAt(0));
					break;
				case 4:
					i = Integer.parseInt(h5.getOdds().toString().substring(0,2));
					break;
			}
			
			//calculate payout
			int payout = (w*i*n);
			//append to display
			resultSheet.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
			
			//update user's balance
			a1.decrimentStake(n);
			a1.incrementWinnings(payout);				
			
			System.out.print("\n" + payout);	//test...
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
		int race, winner, h1count=0, h2count=0, h3count=0, h4count=0, h5count=0;
		
		//For Loop
		/*Decifers Winner of race, randomly.
		 *3 races per bet, like an accumulator.
		 *The higher the odds of the horse,
		 *the more entries in the random generator pool*/
		for(race=0; race<3; race++){
			winner = (int)(Math.random()*16)+1;
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
			//System.out.print(winner);
		}//End For Loop
		System.out.print("h1" + h1count + " h2" + h2count + " h3" + h3count + " h4" + h4count + " h5" + h5count);	//test...
		
		betCount++;
		/*adding the number of wins per horse to the array,
		 *each horse represented by an array element*/
		results.add(h1count);
		results.add(h2count);
		results.add(h3count);
		results.add(h4count);
		results.add(h5count);
		return results;
	}//End Race Method
	
	
	//method to validate the amount entered is a number
	/*try to parse is at as double
	 *if it cant then re-enter rather than crashing
	 *use try catch as is quicker than searching characters*/
	
	//**************************throw exception for cancel button
	
	public static boolean isValidAmount(String lodgement){
		//if statement to check for negative value which would pass the parse test
		if(lodgement.charAt(0) == '-')
			return false;
		else{
			try{
				Double.parseDouble(lodgement);
			}	
			catch(NumberFormatException e){
				return false;
			}
		}
		return true;
	}
//	public void saveData(double saveBal) throws IOException, FileNotFoundException{
//		File outFile = new File("data.dat");
//	    FileOutputStream fos = new FileOutputStream(outFile);
//	    DataOutputStream dos = new DataOutputStream(fos);
//	    
//	    dos.writeDouble(saveBal);
//	    System.out.print("\n\t" + saveBal);
//	    dos.close();
//	    
//	}
//	public double readData() throws IOException, FileNotFoundException{
//		double setBal=0;
//		try{
//			File inFile = new File("data.dat");
//			FileInputStream fis = new FileInputStream(inFile);
//			DataInputStream dis = new DataInputStream(fis);
//			
//			setBal = dis.readDouble();
//			System.out.print("\n\t" + setBal);
//			dis.close();
//		}
//		
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return setBal;
//	}
}