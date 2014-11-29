//
/**/

import javax.swing.*;

public class UpdateBalance{
	public static void main(String args[]){
		Account a1 = new Account();
		//double lodgement;
		//boolean valid = false;
		String lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
		
		while(!isValid(lodgement)){
			JOptionPane.showMessageDialog(null,"Amount should be numeric","Invalid Number",JOptionPane.WARNING_MESSAGE);
			lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
		}
		a1.updateBalance(Double.parseDouble(lodgement));
		
		JOptionPane.showMessageDialog(null,a1.toString());
		//**************************throw exception for cancel button************************************
		//validate amount
	}
	public static boolean isValid(String lodgement){
		try{
			Double.parseDouble(lodgement);
		}	
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}
//	public static boolean isValid(String lodgement){
//		boolean valid = false;
//		
//		for(int i=0; i<lodgement.length(); i++){			//change to new for loop
//			if(lodgement.equals(""))
//				break;
//			if(!Character.isDigit(lodgement.charAt(i))){
//				break;
//			}
//			else if(Character.isDigit(lodgement.charAt(i)) && i == lodgement.length())
//				valid = true;
//		}
//		return valid;
//	}
//	public static double updateBalance(double lodgement){
//		
//	} 
}