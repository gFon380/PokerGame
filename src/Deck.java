import java.util.Random;
import java.util.ArrayList;

public class Deck {
	
	private Card[] cards = new Card[52];
	private int top; 
    private ArrayList<Card> shuffledCards = new ArrayList<Card>();
    private Random r = new Random();
	
	public Deck() {
        for (int i=0; i < 4; i++) {
            for (int j=0; j < 13; j++) {
                Card c = new Card(i + 1, j + 1);
                shuffledCards.add((((i + 1) * (j + 1)) - 1), c);
            }
        }
    }
	
	public void shuffle(){
        for (int i=0; i < 52; i++) {
            if (shuffledCards.size() - 1 > 0) {
                int chosen = r.nextInt(shuffledCards.size() - 1);
                cards[i] = shuffledCards.get(chosen);
                shuffledCards.remove(chosen);

            } else if (shuffledCards.size() - 1 == 0) {
                cards[i] = shuffledCards.get(0);
            }
        }
    }
	
	public Card deal(){
        top += 1;
        if (top - 1 < 52) {
            return cards[top - 1];
        } else if (top - 1 >= 52) {
            return new Card((r.nextInt(4) + 1), (r.nextInt(14) + 1));
        } else {
            return null;
        }
    }
	
    public void printDeck() {
        for (int i=0;i < 52;i++) {
        }
    }

}
