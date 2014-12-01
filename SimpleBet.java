





						/*No longer required*/















import javax.swing.*;
import java.awt.event.*;

public class SimpleBet{
	public static void main(String args[]){
		
		int race, winner, h1count=0, h2count=0, h3count=0;
		
		for(race=0; race<3; race++){		//consider the fancy for loop
			winner = (int)(Math.random()*6)+1;
			if(winner == 1 || winner == 3 || winner == 5 || winner == 7){
				h1count++;
				//h1.decreaseOdds(3);
			}
			else if(winner == 2 || winner == 6){
				h2count++;
			}
			else if(winner == 4){
				h3count++;
			}
			System.out.print(winner);
		}
		System.out.print("h1" + h1count + " h2" + h2count + " h3" + h3count);
	}
}