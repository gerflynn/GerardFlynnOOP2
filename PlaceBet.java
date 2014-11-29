//
/**/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class PlaceBet extends JFrame{
	JButton button1;			//make private????
	JButton button2;
	JButton button3;
	JComboBox combobox;
	//static int results[];	//static????
	//ArrayList<int> results = new ArrayList<int>();
	static ArrayList<Integer> results;
	JTextArea winnings;
	static String name1, name2, name3;
	Horse h1 = new Horse("Exterminator", 2, 1);
	Horse h2 = new Horse("Storm Cat", 4, 1);
	Horse h3 = new Horse("Even Odds", 8, 1);
	static int betCount, totalStake, totalWinnings;
	JLabel balance;
	
	public static void main(String args[]){
		PlaceBet gui = new PlaceBet();
		//gui.setVisible(true);
	}
	public PlaceBet(){
		super("Place Your Bet!!");
		setLayout(new FlowLayout());
		setSize(335,400);
		//setResizable(false);
		setLocation(200,200);
		//setBackground(Color.WHITE);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		Wager odds = new Wager();
//		Horse h1 = new Horse("Desert Orchid", 2, 1);
//		Horse h2 = new Horse("Hurricane Fly", 4, 1);
//		Horse h3 = new Horse("Storm Cat", 8, 1);//space cat.hoof hearted.exterminator.gay crusader.barbra.even odds.only choice.plastic gold
		
		JTextArea textArea = new JTextArea(7,40);
		Font font = new Font("monospaced",Font.BOLD,14);
		textArea.setFont(font);
		String formatSpecifier = "%-8s%-18s%s\n" +
								 "%-8s%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n" +
								 "%-8d%-18s%s\n";
		//String name
		textArea.setText(String.format(formatSpecifier,"Post","Horse","Odds",
													   "====","=====","====",
													   1,h1.getName(),h1.getOdds().toString(),
													   2,h2.getName(),h2.getOdds().toString(),
													   3,h3.getName(),h3.getOdds().toString()));
		add(textArea);
		JLabel euroSymbol = new JLabel("€:");
		add(euroSymbol);
		Integer[] bets = {10,20,30,50,100};
		combobox = new JComboBox(bets);
		combobox.setSelectedIndex(2);
		MenuEventHandler handler = new MenuEventHandler();
		combobox.addActionListener(handler);
		add(combobox);
		combobox.setToolTipText("Set Bet");
		//addSeparator();
		
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
		button3.setToolTipText("Even Odds");
		button3.addActionListener(handler);
		
		winnings = new JTextArea();
		winnings.setText("");
		add(winnings);
		
		balance = new JLabel();
		if(betCount<1)
			balance.setText("");
		else
			balance.setText("Total Stake: €" + totalStake + " Total Winnings: €" + totalWinnings);
		add(balance);
		
		name1 = h1.getName();
		name2 = h2.getName();
		name3 = h3.getName();
		//System.out.print(h1.getNumerator());
	}
	private class MenuEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Account a1 = new Account();
			if(e.getSource() == button1){
				//check bet against balance
				race();
				//find number of wins
				int w = results.get(0);
				//global variable to find odds
//				char s = h1.getOdds().toString().charAt(0);
				int i = Character.getNumericValue(h1.getOdds().toString().charAt(0));	/*ref 5*/
				//get selected amount combobox
				int n = (Integer)combobox.getSelectedItem();							/*ref 4*/
				//calculate payout
				int payout = (w*i*n);
				//add to display
				winnings.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
				totalStake += n;
				totalWinnings += payout;
				
				//store sums
				//a1
				
				System.out.print("\n" + payout);
				
//				int a = results.get(0);
//				int a = results[0];
//				System.out.print(a);
			}
			if(e.getSource() == button2){
				race();
				int w = results.get(1);
				int i = Character.getNumericValue(h2.getOdds().toString().charAt(0));
				int n = (Integer)combobox.getSelectedItem();
				int payout = (w*i*n);
				System.out.print("\n" + payout);
				winnings.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
				totalStake += n;
				totalWinnings += payout;
			}
			if(e.getSource() == button3){
				race();
				int w = results.get(2);
				int i = Character.getNumericValue(h3.getOdds().toString().charAt(0));
				int n = (Integer)combobox.getSelectedItem();
				int payout = (w*i*n);
				System.out.print("\n" + payout);
				winnings.append("\nRace " + betCount + ": Stake €" + n + " Winnings €" + payout + " ");
				totalStake += n;
				totalWinnings += payout;
			}
			
		}
	}
	public static ArrayList<Integer> race(){
		results = new ArrayList<Integer>();
		int race, winner, h1count=0, h2count=0, h3count=0;
		
		for(race=0; race<3; race++){		//consider the fancy for loop
			winner = (int)(Math.random()*6)+1;
			if(winner == 1 || winner == 3 || winner == 5 || winner == 7){
				h1count++;
				//h1.decreaseOdds(3);
				JOptionPane.showMessageDialog(null,name1 + " wins race " + (race+1));
			}
			else if(winner == 2 || winner == 6){
				h2count++;
				JOptionPane.showMessageDialog(null,name2 + " wins race " + (race+1));
			}
			else if(winner == 4){
				h3count++;
				JOptionPane.showMessageDialog(null,name3 + " wins race " + (race+1));
			}
			//System.out.print(winner);
		}
		System.out.print("h1" + h1count + " h2" + h2count + " h3" + h3count);
		
		betCount++;
		results.add(h1count);
		results.add(h2count);
		results.add(h3count);
		return results;
	} 
}