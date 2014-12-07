//Wager.java
/*This is an instantiable aggregate class of Horse.java
 *It provides methods to set odds on each horse
 *It exists on the basis of the existance of the Horse class*/

public class Wager{
	//Attribues
	private int numerator;
	private int denominator;
	
	//No argument constructor
	public Wager(){
		this(0,0);
	}
	//2 argument constructor
	public Wager(int numerator, int denominator){
		this.numerator = numerator;			//alt setNum(num)
		this.denominator = denominator;
	}
	
	//Accessor Methods
	public int getNumerator(){
		return numerator;
	}
	public int getDenominator(){
		return denominator;
	}
	
	//Mutators Methods
	public void setNewOdds(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	public void setNumerator(int numerator){
		this.numerator = numerator;
	}
	public void setDenominator(int denominator){
		this.denominator = denominator;
	}
	
	//toString Methods
	public String toString(){
		return String.format("%d/%d",getNumerator(),getDenominator());
	}
}//End Wager Class