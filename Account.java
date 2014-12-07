//Account.java
/*This is an instantiable class.
 *It provides methods to create an account and manage the balance*/


/**This is an instantiable Account class.
 @author Gerard Flynn
 @version 1.0 */

public class Account{
	//Attributes
	private String username;
	private String password;
	private double balance;
	private int wins;
	private int races;
	
	//Constructors
	
	/**No argument constructor method
	 *@param allows Account objects to be created, sets default account details*/
	public Account(){
		this("user","p",0,0,0);
	}
	
	/**Multiple argument constructor method
	 *@param allows Account objects to be created, calls values set in the mutator*/
	public Account(String username, String password, double balance, int wins, int losses){
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.wins = wins;
		this.races = races;
	}
	
	//Accessor Methods
	
	/**Access method to return username
	 *@return username of the account*/
	public String getUsername(){
		return username;
	}
	/**Access method to return password
	 *@return password of the account*/
	public String getPassword(){
		return password;
	}
	/**Access method to return balance
	 *@return balance of the account*/
	public double getBalance(){
		return balance;
	}
	/**Access method to return number of races won
	 *@return number of races the account has won*/
	public int getWins(){
		return wins;
	}
	/**Access method to return number of races bet on
	 *@return number of races the account has bet on*/
	public int getTotalRaces(){
		return races;
	}
	
	//Mutator Methods
	
	/**Mutator method to set the account username
	 *@param name the username of the account */
	public void setUsername(String username){
		this.username = username;
	}
	/**Mutator method to set the account password
	 *@param name the password of the account */
	public void setPassword(String password){
		this.password = password;
	}
	/**Mutator method to set the account balance
	 *@param name the balance of the account */
	public void setBalance(double balance){
		this.balance = balance;
	}
	/**Mutator method to set the number of races the account has won
	 *@param name the number of wins on the account has won*/
	public void setWins(int wins){
		this.wins = wins;
	}
	/**Mutator method to set the number of races the account has betted on
	 *@param name the number of races the account has betted on*/
	public void setTotalRaces(int races){
		this.races = races;
	}
	
	//Add Balance Method
	
	/**updateBalance method to add money to the account
	 *@param add a lodgement to the balance of the account*/
	public void updateBalance(double lodgement){
		balance += lodgement;
	}
	
	//Decriment Stake
	
	/**decrimentStake method to subtract a stake from the account balance
	 *@param subtract the stake from the account balance*/
	public void decrimentStake(int stake){
		//validation here?
		balance -= stake;
	}
	
	//Increment Winnings
	
	/**incrementWinnings method to add winnings to the account balance
	 *@param add money won to the balance of the account*/
	public void incrementWinnings(int winnings){
		balance += winnings;
	}
	
	//Increment Total Wins
	
	/**incrementTotalWins method to add current wins to the account's previous wins
	 *@param add current wins to the account's previous wins*/
	public void incrementTotalWins(int winnings){
		wins += winnings;
	}
	
	//Increment Total Losses
	
	/**incrementTotalRaces method to add each race the account bets on to the total races
	 *@param increment the total races the account has on*/
	public void incrementTotalRaces(int raceCount){
		races += raceCount;
	}
	
	//toString Method
	
	/**toString method to return the account details
	 *@return the balance of the account as a string */
	public String toString(){
		return "Current balance is " + String.format("€%.2f",balance);
	}
}//End Account Class