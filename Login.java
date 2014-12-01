//Login.java
/*This class provides an interface to login to an account*/
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	JTextField nameField;		//make private?????
	JPasswordField passField;
	static int attempts=0;
	
	Account a1 = new Account("user","p",0);
	
	public static void main(String args[]){
		Login gui = new Login();
		gui.setVisible(true);
	}
	
	public Login(){
		super("Login");
		setLayout(new FlowLayout());
		setSize(320,200);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		
		JLabel nameLabel = new JLabel("Please enter your username:");
		add(nameLabel);	
		nameField = new JTextField(15);
		add(nameField);
		TextFieldEventHandler handler = new TextFieldEventHandler();
		nameField.addActionListener(handler);
		
		JLabel passLabel = new JLabel("Please enter your password:");
		add(passLabel);	
		passField = new JPasswordField(15);
		add(passField);
		passField.addActionListener(handler);
			
	}
	
	private class TextFieldEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name = nameField.getText();
			
			if(e.getSource() == nameField){	//when the user hits enter on the username field...
				passField.requestFocus();	//...neat way to point the cursor to the password field
			}
			if(e.getSource() == passField){
				char[] jpassword = passField.getPassword(); //JPasswordField returns array of chars
				String password = new String(jpassword);	//Building a String out of this array
				
				if(name.equalsIgnoreCase(a1.getUsername()) && password.equals(a1.getPassword())){
					JOptionPane.showMessageDialog(null,"Welcome to the system ",
					"Success!",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					MainGUI m1 = new MainGUI();
					m1.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null,"Invalid username/passsword combination\n\n" +
					(2 - (attempts) ) + " attempts remaining...","Error",JOptionPane.ERROR_MESSAGE);
					attempts++;
					if(attempts == 3)
						System.exit(0);
				}
					
			}	
		}
	}
}