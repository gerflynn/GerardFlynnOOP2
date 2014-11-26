//
/**/

public class Wager{
	//attribues
	private int numerator;
	private int denominator;
	
	//no arg cons
	public Wager(){
		this(0,0);
	}
	//2 arg cons
	public Wager(int numerator, int denominator){
		this.numerator = numerator;			//alt setNum(num)
		this.denominator = denominator;
	}
	//accessors
	public int getNumerator(){
		return numerator;
	}
	public int getDenominator(){
		return denominator;
	}
	//mutators
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
	
	//decrease odds
	//increase odds
	
	//toString
	public String toString(){
		return String.format("%d/%d",getNumerator(),getDenominator());
	}
//	public int numeratorToString(){
//		return numerator;
//	}
}