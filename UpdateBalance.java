





						/*No longer required*/















//
/**/

import javax.swing.*;

public class UpdateBalance{
	public static void main(String args[]){
		Account a1 = new Account();
		
		String lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
		
		while(!isValid(lodgement)){
			JOptionPane.showMessageDialog(null,"Amount should be numeric","Invalid Number",JOptionPane.WARNING_MESSAGE);
			lodgement = JOptionPane.showInputDialog("Please enter amount you wish to add to your account");
		}
		
		a1.updateBalance(Double.parseDouble(lodgement));
		
		JOptionPane.showMessageDialog(null,a1.toString());
		//**************************throw exception for cancel button************************************maybe exit on close
	}
	
	//method to validate the amount entered is a number
	/*try to parse is at as double
	 *if it cant then re-enter rather than crashing
	 *use try catch as is quicker than searching characters*/
	public static boolean isValid(String lodgement){
		try{
			Double.parseDouble(lodgement);
		}	
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}