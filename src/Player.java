
import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand;
	private double bankroll;
    private double bet;

		
	public Player(){		
	    bankroll = 100;   
	}

	public void addCard(Card c) {
	    hand.add(c);
	}

	public void removeCard(Card c) {
	    hand.remove(c);
    }
		
    public void bets(double amt) {
        bet = amt;
        bankroll -= bet;
    }

    public void winnings(double odds) {
        bankroll += odds * bet;
    }

    public double getBankroll(){
        return bankroll;
    }

}


