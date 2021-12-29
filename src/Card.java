// Work by Gabriel Fonseca (glf2121) for COMS 1004
// Summer 2021

public class Card implements Comparable<Card>{
	
	private int suit;
	private int rank;
	
	public Card(int s, int r) {
        suit = s;
        rank = r;
	}
	
	public int compareTo(Card c) {
        return rank - c.rank;
	}
	
	public String toString() {
        return Card.suitToLetter(suit) + rank;
    }
	
    public static String suitToLetter(int ss) {
        String suitLetter = null;
        switch (ss) {
            case 1:
                suitLetter = "c";
                break;
            case 2:
                suitLetter = "d";
                break;
            case 3:
                suitLetter = "h";
                break;
            case 4:
                suitLetter = "s";
        }
        return suitLetter;
    }
    
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
  
}

