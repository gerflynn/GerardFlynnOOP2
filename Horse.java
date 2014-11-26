/**/

//import javax.swing.*;

public class Horse{
	//attributes
	private String name;
	private Wager odds;
	
	//constructors
	public Horse(){
		name = "name";
		odds = new Wager();
	}
	public Horse(String name, int numerator, int denominator){
		setName(name);
		odds = new Wager(numerator, denominator);
	}
	
	//accessor
	public String getName(){
		return name;
	}
	public Wager getOdds(){
		return odds;
	}
	//mutator
	public void setName(String name){
		this.name = name;
	}
	public void setOdds(int numerator, int denominator){
		odds.setNewOdds(numerator, denominator);
	}
	
	//method
	public void decreaseOdds(int numerator){
		numerator++;
	}
	/*
	//toString() method
	public String toString(){
		return "Name: " + getName() + " Odds: " + odds.toString();
	}
	//nameToString() method
	public String nameToString(){
		return "Name: " + getName();
	}
	//oddsToString() method
	public String oddsToString(){
		return "Odds: " + odds.toString();
	}*/
	//numerator toString method
//	public String numeratorToString(int numerator){
//		return numerator.toString
//	}
}