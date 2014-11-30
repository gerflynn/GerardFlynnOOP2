//
/**/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;	//for i/o

public class PlaceBet extends JFrame{
	JComboBox combobox;
	JButton button1;			//make private????
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton test;
	
	static ArrayList<Integer> results;
	JTextArea resultSheet;
	static String name1, name2, name3, name4, name5;
	Horse h1 = new Horse("Exterminator", 2, 1);
	Horse h2 = new Horse("Storm Cat", 4, 1);
	Horse h3 = new Horse("Thunder Hooves", 8, 1);
	Horse h4 = new Horse("Geoff", 8, 1);
	Horse h5 = new Horse("Odds On Favourite", 16, 1);
	Account a1 = new Account();
	static int betCount;
	double saveBal = a1.getBalance();
	int magicNumber;
	
	public static void main(String args[]){
		PlaceBet gui = new PlaceBet();
		//gui.setVisible(true);
	}
	public PlaceBet(){
		super("Place Your Bet!!");
		setLayout(new FlowLayout());
		setSize(345,700);
		//setResizable(false);
		setLocation(200,20);
		//setBackground(Color.GREY);		//ContentPane
		setVisible(true);
		
		
		//call to read balance
		try{
			readData();
			a1.setBalance(readData());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		//Anonymous Inner Class
		/*This overrides window closing
		 *adds the function of saving progress to file*/ 
		addWindowListener(new WindowAdapter(){			//extends WindowAdapter which extends WindowListener
			public void windowClosing(WindowEvent e){	//this method overrides WindowAdapter method
				JOptionPane.showMessageDialog(null,"Saving Progress...");
					//Call to save balance
					try{
						saveData(saveBal);
					}
					catch(IOException eve){
						eve.printStackTrace();
					}
	      		System.exit(0);
			}
		});
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		MenuEventHandler handler = new MenuEventHandler();
		test = new JButton("Test");
		add(test);
		test.addActionListener(handler);
		
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
		
		JLabel euroSymbol = new JLabel("€");
		add(euroSymbol);
		Integer[] bets = {10,20,30,50,100};
		combobox = new JComboBox(bets);
		combobox.setSelectedIndex(2);
		combobox.addActionListener(handler);
		add(combobox);
		combobox.setToolTipText("Set Bet");
		
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
		
		resultSheet = new JTextArea();
		resultSheet.setText("");
		add(resultSheet);
		
		name1 = h1.getName();
		name2 = h2.getName();
		name3 = h3.getName();
		name4 = h4.getName();
		name5 = h5.getName();
	}
	private class MenuEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Account a1 = new Account();
			if(e.getSource() == test){
				a1.updateBalance(Double.parseDouble(JOptionPane.showInputDialog("Please enter amount you wish to add to your account")));
				System.out.print("\n" + a1.toString());
			}
			
			if(e.getSource() == button1){
				magicNumber = 0;
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
	}
	public static ArrayList<Integer> race(){
		results = new ArrayList<Integer>();
		int race, winner, h1count=0, h2count=0, h3count=0, h4count=0, h5count=0;
		
		for(race=0; race<3; race++){		//consider the fancy for loop
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
		}
		System.out.print("h1" + h1count + " h2" + h2count + " h3" + h3count + " h4" + h4count + " h5" + h5count);
		
		betCount++;
		results.add(h1count);
		results.add(h2count);
		results.add(h3count);
		results.add(h4count);
		results.add(h5count);
		return results;
	}
	public void magicMethod(){	
		//check bet against balance
		//get selected amount from combobox
		int n = (Integer)combobox.getSelectedItem();
		if(n > a1.getBalance()){
			JOptionPane.showMessageDialog(null,"Insufficient funds. Redirecting...");
		}
		else{
			race();
			//find number of wins from appropriate horse in arraylist of results
			int w = results.get(magicNumber);
			//find odds of selected horse
			int i = 0;
			switch(magicNumber){
				case 0:
					i = Character.getNumericValue(h1.getOdds().toString().charAt(0));
				case 1:
					i = Character.getNumericValue(h2.getOdds().toString().charAt(0));
				case 2:
					i = Character.getNumericValue(h3.getOdds().toString().charAt(0));
				case 3:
					i = Character.getNumericValue(h4.getOdds().toString().charAt(0));
				case 4:
					i = Integer.parseInt(h5.getOdds().toString().substring(0,2));
			}
			
			//calculate payout
			int payout = (w*i*n);
			//add to display
			resultSheet.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
			
			a1.decrimentStake(n);
			a1.incrementWinnings(payout);				
			
			System.out.print("\n" + payout);
			System.out.print("\n" + a1.toString());
		}
	}
	public void saveData(double saveBal) throws IOException, FileNotFoundException{
		File outFile = new File("data.data");
        FileOutputStream fos = new FileOutputStream(outFile);
        DataOutputStream dos = new DataOutputStream(fos);
        
        dos.writeDouble(saveBal);
        System.out.print("\n\t" + saveBal);
        dos.close();
        //set up file and stream
		//BufferedReader bufReader = new BufferedReader(new FileReader("data.txt"));
		
	}
	public double readData() throws IOException, FileNotFoundException{
		double setBal=0;
//		try{
			File inFile = new File("data.txt");
			FileInputStream fis = new FileInputStream(inFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			setBal = ois.readDouble();
//		}
//		
//		catch(Exception e){
//			e.printStackTrace();
//		}
		return setBal;
	}
}