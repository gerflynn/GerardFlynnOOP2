//Horse.java
/*This is an instantiable class.
 *It provides methods to create a Horse and manage his details
 *It calls a nested class Wager which provides methods for setting the odds of each horse*/

public class Horse{
	//Attributes
	private String name;
	private Wager odds;
	
	//Constructors
	public Horse(){
		name = "name";
		odds = new Wager();
	}
	public Horse(String name, int numerator, int denominator){
		setName(name);
		odds = new Wager(numerator, denominator);
	}
	
	//Accessor Methods
	public String getName(){
		return name;
	}
	public Wager getOdds(){
		return odds;
	}
	
	//Mutator Methods
	public void setName(String name){
		this.name = name;
	}
	public void setOdds(int numerator, int denominator){
		odds.setNewOdds(numerator, denominator);
	}
}//End Horse Class