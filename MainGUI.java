/*
 *
 **/

import javax.swing.*;
import java.awt.event.*;

public class MainGUI extends JFrame{
	JMenuBar menu;
	
	public static void main(String args[]){
		
		//new Login();
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
		//addWindowListener
	}
	
	public void createMenus(){
		//Manage Accounts
		MenuEventHandler handler = new MenuEventHandler();
		JMenu acc = new JMenu("Accounts");
		menu.add(acc);
		/*JMenuItem create = new JMenuItem("New");
		acc.add(create);
		create.addActionListener(handler);*/
		JMenuItem login = new JMenuItem("Login");
		acc.add(login);
		login.addActionListener(handler);
		acc.addSeparator();
		JMenuItem quit = new JMenuItem("Quit");
		acc.add(quit);
		quit.addActionListener(handler);
		
		//Manage Balance
		JMenu balance = new JMenu("Balance");
		menu.add(balance);
		JMenuItem view = new JMenuItem("View");
		balance.add(view);
		JMenuItem update = new JMenuItem("Update");
		balance.add(update);
		update.addActionListener(handler);
		
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
			
			if(e.getActionCommand().equals("Login")){
				new Login();
			}
			if(e.getActionCommand().equals("Quit")){
				int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to logout?",
				"Logout",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION)
					System.exit(0);						//or save and return to jframe
			}
			
			//Balance Handlers
			if(e.getActionCommand().equals("View")){
				/*Using BufferedReader*/
        /*
        BufferedReader bufReader = new BufferedReader(new FileReader("data.txt"));
		String str;
	
		str = bufReader.readLine();
		int n = Integer.parseInt(str);
		System.out.println("Int was " + n);
		//str = bufReader.readLine();
    	//System.out.println("String was " + str);
    	str = bufReader.readLine();
    	boolean b = Boolean.parseBoolean(str);
    	System.out.println("Boolean was " + b);
    	str = bufReader.readLine();
    	double d = Double.parseDouble(str);
    	System.out.println("Double was " + d);

		bufReader.close();
		*/
		
				//view account
			}
			if(e.getActionCommand().equals("Update")){
				String[] args={};
				UpdateBalance.main(args);
				//validate login
			}
			
			//Race Handlers
			
			if(e.getActionCommand().equals("Place Bet")){
				//validate login & balance
				new PlaceBet();
			}
		}
	}
}