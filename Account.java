//Account.java
/*This is an instantiable class.
 *It provides methods to create an account and manage the balance*/

public class Account{
	//Attributes
	private String username;
	private String password;
	private double balance;
	private int wins;
	private int races;
	
	public Account(){
		this("user","p",0,0,0);
	}
	public Account(String username, String password, double balance, int wins, int losses){
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.wins = wins;
		this.races = races;
	}
	
	//Accessor Methods
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public double getBalance(){
		return balance;
	}
	public int getWins(){
		return wins;
	}
	public int getTotalRaces(){
		return races;
	}
	
	//Mutator Methods
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public void setWins(int wins){
		this.wins = wins;
	}
	public void setTotalRaces(int races){
		this.races = races;
	}
	
	//Add Balance Method
	public void updateBalance(double lodgement){
		balance += lodgement;
	}
	
	//Decriment Stake
	public void decrimentStake(int stake){
		//validation here?
		balance -= stake;
	}
	//Increment Winnings
	public void incrementWinnings(int winnings){
		balance += winnings;
	}
	//Increment Total Wins
	public void incrementTotalWins(int winnings){
		wins += winnings;
	}
	//Increment Total Losses
	public void incrementTotalRaces(int betCount){
		races += betCount*3;
	}
	
	//Return balance details toString Method
	public String toString(){
		return "Current balance is " + String.format("€%.2f",balance);
	}
}