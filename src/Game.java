// Work by Gabriel Fonseca (glf2121) for COMS 1004
// Summer 2021

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
	
	private Player p = new Player();
	private Deck cards;
    private Card[] testingHand = new Card[5];
    private Scanner s = new Scanner(System.in);
    private int bet;
	
	
	public Game(String[] testHand){
        for (int i=0; i < 5; i++) {
            testingHand[i] = stringToCards(testHand[i]);
        }	
        printHand(testingHand);
        System.out.println(checkHand(testingHand));
	}
	
	public Game(){
        boolean keepPlaying = true;
        while (keepPlaying == true) {
            play();
            if (p.getBankroll() <= 0) {
                System.out.println("You ran out of money. Thanks for playing!");
                keepPlaying = false;
            } else {
                System.out.println("Your current bankroll is " + p.getBankroll() + ". Would you like to keep playing? (y/n)");
                String response = s.next();
                if (response.equals("y")) {
                    keepPlaying = true;
                } else if (response.equals("n")) {
                    keepPlaying = false;
                }
            }
        }
        System.out.println("Thanks for playing!");
	}
	
	public void play(){
        Deck cards = new Deck();
        cards.shuffle();
        for (int i=0; i < 5; i++) {
            testingHand[i] = cards.deal();
        }
        System.out.println("Here is your bankroll: " + p.getBankroll());
        boolean validBet = false;
        while (validBet == false) {
            System.out.println("How much would you like to bet? (Choose between 1 and 5 tokens.)");
            bet = s.nextInt();
            if (bet < 1 || bet > 5 || bet > p.getBankroll()) {
                System.out.println("Invalid bet input, please input again.");
            } else {
                validBet = true;
                p.bets(bet);
            }
        }
        System.out.println("Here are your cards: ");
        printHand(testingHand);
        System.out.println("Which cards would you like to replace? Keep answering using the numbers 1-5."
                           + " You may only replace a card once. When you are done replacing, enter 0. ");
        boolean oneUsed = false;
        boolean twoUsed = false;
        boolean threeUsed = false;
        boolean fourUsed = false;
        boolean fiveUsed = false;
        boolean replaceDone = false;
        
        while (replaceDone == false) {
            int replace = s.nextInt();
            if (replace == 1 && oneUsed == false) {
                testingHand[0] = cards.deal();
                System.out.println("Card has been replaced with: " + testingHand[0]);
                System.out.println("Your new hand is: ");
                printHand(testingHand);
                oneUsed = true;
            } else if (replace == 2 && twoUsed == false) {
                testingHand[1] = cards.deal();
                System.out.println("Card has been replaced with: " + testingHand[1]);
                System.out.println("Your new hand is: ");
                printHand(testingHand);
                twoUsed = true;
            } else if (replace == 3 && threeUsed == false) {
                testingHand[2] = cards.deal();
                System.out.println("Card has been replaced with: " + testingHand[2]);
                System.out.println("Your new hand is: ");
                printHand(testingHand);
                threeUsed = true;
            } else if (replace == 4 && fourUsed == false) {
                testingHand[3] = cards.deal();
                System.out.println("Card has been replaced with: " + testingHand[3]);
                System.out.println("Your new hand is: ");
                printHand(testingHand);
                fourUsed = true;
            } else if (replace == 5 && fiveUsed == false) {
                testingHand[4] = cards.deal();
                System.out.println("Card has been replaced with: " + testingHand[4]);
                System.out.println("Your new hand is: ");
                printHand(testingHand);
                fiveUsed = true;
            } else if (replace == 0) {
                replaceDone = true;
                System.out.println("Your new hand is: ");
                printHand(testingHand);
            } else {
                System.out.println("Invalid input.");
            }
        }
        
        System.out.println(checkHand(testingHand));
        if (checkHand(testingHand) == "Royal flush.") {
            p.winnings(250);
            System.out.println("Your winnings are: " + (bet * 250));
        } else if (checkHand(testingHand) == "Straight flush.") {
            p.winnings(50);
            System.out.println("Your winnings are: " + (bet * 50));
        } else if (checkHand(testingHand) == "Four of a kind.") {
            p.winnings(25);
            System.out.println("Your winnings are: " + (bet * 25));
        } else if (checkHand(testingHand) == "Full house.") {
            p.winnings(6);
            System.out.println("Your winnings are: " + bet * 6);
        } else if (checkHand(testingHand) == "Flush.") {
            p.winnings(5);
            System.out.println("Your winnings are: " + bet * 5);
        } else if (checkHand(testingHand) == "Straight.") {
            p.winnings(4);
            System.out.println("Your winnings are: " + bet * 4);
        } else if (checkHand(testingHand) == "Three of a kind.") {
            p.winnings(3);
            System.out.println("Your winnings are: " + bet * 3);
        } else if (checkHand(testingHand) == "Two of a kind.") {
            p.winnings(2);
            System.out.println("Your winnings are: " + bet * 2);
        } else if (checkHand(testingHand) == "One pair.") {
            p.winnings(1);
            System.out.println("Your winnings are: " + bet);
        } else {
            System.out.println("Your winnings are: 0.");
        }
        
	}
	
    
	public String checkHand(Card[] hand){
		Arrays.sort(hand);
        if (isRoyalFlush(hand)) {
            return "Royal flush.";
        } else if (isStraightFlush(hand)) {
            return "Straight flush.";
        } else if (isFourOfAKind(hand)) {
            return "Four of a kind.";
        } else if (isFullHouse(hand)) {
            return "Full house.";
        } else if (isFlush(hand)) {
            return "Flush.";
        } else if (isStraight(hand)) {
            return "Straight.";
        } else if (isThreeOfAKind(hand)) {
            return "Three of a kind.";
        } else if (isTwoPair(hand)) {
            return "Two of a kind.";
        } else if (isOnePair(hand)) {
            return "One pair.";
        } else {
            return "Junk/high card.";
        }
	}
	
    
    public Card stringToCards(String input) {
        if (input.charAt(0) == 'c') {
            Card c = new Card(1, Integer.parseInt(input.substring(1, input.length())));
            return c;
        } else if (input.charAt(0) == 'd') {
            Card c = new Card(2, Integer.parseInt(input.substring(1, input.length())));
            return c;
        } else if (input.charAt(0) == 'h') {
            Card c = new Card(3, Integer.parseInt(input.substring(1, input.length())));
            return c;
        } else if (input.charAt(0) == 's') {
            Card c = new Card(4, Integer.parseInt(input.substring(1, input.length())));
            return c;
        } else {
            return null;
        }
    }
    
    
    public boolean isStraight(Card[] hand) {
        for (int i=1;i < 5; i++) {
            if (hand[0].getRank() == 1 && hand[1].getRank() == 10) {
                i = 2;
            }
            if ((hand[i].getRank() - hand[i - 1].getRank() != 1)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isFlush(Card[] hand) {
        for (int i=1;i < 5; i++) {
            if (hand[i].getSuit() - hand[i-1].getSuit() != 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isRoyalFlush(Card[] hand) {
        if (isStraight(hand) && isFlush(hand) && hand[0].getRank() == 1 && hand[4].getRank() == 13) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isStraightFlush(Card[] hand) {
        if (isStraight(hand) && isFlush(hand)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isFourOfAKind(Card[] hand) {
        int matching = 1;
        int matchingVal = 0;
        for (int i = 0; i < 4;i++) {
            if (hand[i + 1].getRank() == hand[i].getRank() && matchingVal == 0) {
                matching++;
                matchingVal = hand[i].getRank();
            } else if (hand[i + 1].getRank() == matchingVal) {
                matching++;
            } 
        }
        
        if (matching == 4) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean isFullHouse(Card[] hand) {
        if (isFlush(hand)) {
            return false;
        }
        int matchingFirstCounter = 1;
        int matchingSecondCounter = 1;
        int matchingFirstVal = 0;
        int matchingSecondVal = 0;
        for (int i=0; i < 4; i++) {
            if (hand[i+1].getRank() == hand[i].getRank() && matchingFirstVal == 0 && matchingSecondVal == 0) {
                matchingFirstVal = hand[i].getRank();
                matchingFirstCounter += 1;
            } else if (hand[i + 1].getRank() == hand[i].getRank() && hand[i + 1].getRank() != matchingFirstVal && matchingSecondVal == 0) {
                matchingSecondVal = hand[i].getRank();
                matchingSecondCounter += 1;
            } else if (hand[i + 1].getRank() == matchingFirstVal) {
                matchingFirstCounter += 1;
            } else if (hand[i + 1].getRank() == matchingSecondVal) {
                matchingSecondCounter += 1;
            }
          
        }
        if (matchingFirstCounter + matchingSecondCounter == 5) {
            return true;
        } 
        return false;
    }
    
    public boolean isThreeOfAKind(Card[] hand) {
        int matching = 1;
        int matchingVal = 0;
        for (int i = 0; i < 4;i++) {
            if (hand[i + 1].getRank() == hand[i].getRank() && matchingVal == 0) {
                matching++;
                matchingVal = hand[i].getRank();
            } else if (hand[i + 1].getRank() == matchingVal) {
                matching++;
            } 
        }
        if (matching == 3) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean isTwoPair(Card[] hand) {
        int matchingFirstCounter = 1;
        int matchingSecondCounter = 1;
        int matchingFirstVal = 0;
        int matchingSecondVal = 0;
        for (int i=0; i < 4; i++) {
            if (hand[i+1].getRank() == hand[i].getRank() && matchingFirstVal == 0 && matchingSecondVal == 0) {
                matchingFirstVal = hand[i].getRank();
                matchingFirstCounter += 1;
            } else if (hand[i + 1].getRank() == hand[i].getRank() && hand[i + 1].getRank() != matchingFirstVal && matchingSecondVal == 0) {
                matchingSecondVal = hand[i].getRank();
                matchingSecondCounter += 1;
            } else if (hand[i + 1].getRank() == matchingFirstVal) {
                matchingFirstCounter += 1;
            } else if (hand[i + 1].getRank() == matchingSecondVal) {
                matchingSecondCounter += 1;
            }
          
        }
        if (matchingFirstCounter + matchingSecondCounter == 4) {
            return true;
        } 
        return false;
    }
    
    public boolean isOnePair(Card[] hand) {
        int matching = 1;
        int matchingVal = 0;
        for (int i = 0; i < 4;i++) {
            if (hand[i + 1].getRank() == hand[i].getRank() && matchingVal == 0) {
                matching++;
                matchingVal = hand[i].getRank();
            } else if (hand[i + 1].getRank() == matchingVal) {
                matching++;
            } 
        }
        
        if (matching == 2) {
            return true;
        } else {
            return false;
        }
    }
    public void printHand(Card[] testHand) {
        for (int i=0; i < 5; i++) {
            System.out.print(testHand[i] + " ");
        }
        System.out.println(" ");
    }
    
}
