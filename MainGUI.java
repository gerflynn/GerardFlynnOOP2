/*
 *
 **/

import javax.swing.*;
import java.awt.event.*;

public class MainGUI extends JFrame{
	JMenuBar menu;
	
	public static void main(String args[]){
		
		MainGUI gui = new MainGUI();
		gui.setVisible(true);
	}
	
	public MainGUI(){
		super("Title");
		setSize(300,300);
		setResizable(false);
		setLocation(200,200);
		//setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menu = new JMenuBar();	
		setJMenuBar(menu);
		createMenus();
	}
	
	public void createMenus(){
		//Manage Accounts
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
		
		//Manage Races
		JMenu play = new JMenu("Play");
		menu.add(play);
		JMenuItem bet = new JMenuItem("Place Bet");
		play.add(bet);
		bet.addActionListener(handler);
		/*JMenuItem trial = new JMenuItem("Free trial");
		play.add(trial);*/
	}
	private class MenuEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Balance Handlers
			if(e.getActionCommand().equals("View")){
				//view account details
			}
			if(e.getActionCommand().equals("Update")){
				//Calls UpdateBalance method
				String[] args={};
				UpdateBalance.main(args);
			}
			if(e.getActionCommand().equals("Quit")){
				int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to logout?",
				"Logout",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION)
					System.exit(0);						//or save and return to jframe
			}
			
			//Race Handlers
			if(e.getActionCommand().equals("Place Bet")){
				//validate login & balance
				new PlaceBet();
			}
		}
	}
}