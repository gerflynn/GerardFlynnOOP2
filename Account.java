/**/

import javax.swing.*;

public class Account{
	//attributes
	private String username;
	private String password;	//char[]
	//private Race score;
	private double balance;
	
	public Account(){
		this("username","p",0);
	}
	public Account(String username, String password, double balance){
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	//accessor methods
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public double getBalance(){
		return balance;
	}
	
	//mutator methods
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	//add balance method
	public void updateBalance(double lodgement){
		balance += lodgement;
	}
	
	//driver for new account
	
	//decriment stake
	public void decrimentStake(int stake){
		//validation error here?
		balance -= stake;
	}
	//increment winnings
	public void incrementWinnings(int winnings){
		balance =+ winnings;
	}
	
	//return details toString method
	public String toString(){
		return "Current balance is " + String.format("€%.2f",balance);
	}
}