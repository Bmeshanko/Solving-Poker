/**
 * Defines Player objects, which have 7 cards (2 in hand, 5 in community).
 * 
 * This class includes several methods that determine what poker hand the Player has.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Player {	
	private Card cardOne;
	private Card cardTwo;
	private CardList cards;
	
	// The following five instance variables will be used with Game.java
	private int chips = 1500; 
	private int currentBet; 
	private boolean littleBlind; 
	private boolean bigBlind;
	private boolean isHuman;
	
	private String name;
	
	
	public Player(String name, boolean isHuman) {
		this.name = name;
		this.isHuman = isHuman;
	}

	
	public String getName() {
		return this.name;
	}
	
	
	public CardList getCards() {
		if (!this.cards.contains(this.getCardOne().getNumber(), this.getCardOne().getSuit())) {
			this.cards.add(0, cardOne);
		}
		
		if (!this.cards.contains(this.getCardTwo().getNumber(), this.getCardTwo().getSuit())) {
			this.cards.add(0, cardTwo);
		}
		
		return this.cards;
	}
	
	public int getChips() {
		return this.chips;
	}
	
	// Used to determine their original hand.
	public Card getCardOne() {
		return cardOne;
	}

	// Used to determine their original hand.
	public Card getCardTwo() {
		return cardTwo;
	}


	public String toString() {
		return this.getCardOne().toString() + " | " + this.getCardTwo().toString();
	}
	
	// Poker-Data.xlsx uses this method to print out a list of hundreds of thousands of data points, and
	// whether or not they won or lost to determine the relative win percentages.
	public String suitlessString() {
		if (cardTwo.getNumber() > cardOne.getNumber()) {
			Card temp = cardOne;
			cardOne = cardTwo;
			cardTwo = temp;
		}
		
		String type = "";
		switch (cardOne.getNumber()) {
			
		case 14:
			type += "A";
			break;
		case 13:
			type += "K";
			break;
		case 12:
			type += "Q";
			break;
		case 11:
			type += "J";
			break;
		case 10:
			type += "0";
			break;
		default:
			type += Integer.toString(cardOne.getNumber());
			break;
		}
		
		switch (cardTwo.getNumber()) {
		
		case 14:
			type += "A";
			break;
		case 13:
			type += "K";
			break;
		case 12:
			type += "Q";
			break;
		case 11:
			type += "J";
			break;
		case 10:
			type += "0";
			break;
		default:
			type += Integer.toString(cardTwo.getNumber());
			break;
		}
		
		if (cardOne.getSuit().equals(cardTwo.getSuit())) {
			type += "S";
		} else {
			type += "O";
		}
		return type;
	}
	
	// Deals the cards to the player.
	public void dealCards(Deck deck) {
		this.cardOne = deck.deal();
		this.cardTwo = deck.deal();
	}
	
	public void setRiver(CardList river) {
		// Add the two hand cards to the overall ArrayList of cards.
		this.cards = new CardList();
		this.cards.add(this.getCardOne());
		this.cards.add(this.getCardTwo());
		
		// Add river cards to the overall ArrayList of cards.
		for (int i = 0; i < river.size(); i++) {
			this.cards.add(river.get(i));
		}
	}
	
	public void setBigBlind(boolean isBigBlind) {
		this.bigBlind = isBigBlind;
	}
	
	public void setLittleBlind(boolean isLittleBlind) {
		this.littleBlind = isLittleBlind;
	}
	
	public CardList getRiver() {
		CardList river = this.cards;
		river.remove(getCardOne());
		river.remove(getCardTwo());
		return river;
	}
	
	public int getCurrentBet() {
		return this.currentBet;
	}
	
	public boolean getBigBlind() {
		return this.bigBlind;
	}
	
	public boolean getLittleBlind() {
		return this.littleBlind;
	}
	
	public void clearCards() {
		this.cardOne = null;
		this.cardTwo = null;
		this.cards = null;
	}
	
	
	public void betChips(int chips) {
		this.chips -= chips;
		this.currentBet = chips;
	}
	
	
	public void winPot(int chips, int winners) {
		this.chips += chips / winners;
	}
	
	// IN DEVELOPMENT
	public int evaluateBet(int call, int betNumber) {
		
		Scanner scan = new Scanner(System.in);
		
		if (!this.isHuman) {
			int randomChance = (int) (2 * Math.random());
			if (randomChance > 0) {
				return call;
			}
			return 0;
		}
		
		System.out.println("Your Chips: " + this.getChips());
		System.out.println("Your Hand: " + this.getCardOne() + " " + this.getCardTwo());
		if (betNumber == 1) {
			System.out.println("Flop: " + this.getCards().get(2) + this.getCards().get(3) + this.getCards().get(4));
		} else if (betNumber == 2) {
			System.out.println("Turn: " + this.getCards().get(2) + this.getCards().get(3) + this.getCards().get(4) + 
					this.getCards().get(5));
		} else if (betNumber == 3) {
			System.out.println("River: " + this.getCards().get(2) + this.getCards().get(3) + this.getCards().get(4) + 
					this.getCards().get(5) + this.getCards().get(6));
		}
		
		System.out.println("Bet " + call + " to call. Bet 0 to fold.");
		int bet = scan.nextInt();
		return bet;
		
		
	}
	
	public int straightFlush(CardList cards) {
		
		cards.sort();
		
		// If you don't have a straight AND a flush, you don't have a straight flush!
		// However, it is possible to have both a straight and a flush, but not a straight flush.
		if (this.straight(this.getCards()) == 0 || this.flush(this.getCards())[0] == 0) {
			return 0;
		}
		
		int highestStraight = this.straight(this.getCards());
		
		// The reason we need this code is to determine what suit is predominant, since we can't
		// get that information from the flush method. (Methods cannot have multiple return types)

		// (FUTURE) It is a good point to bring up here - eventually I will have to add the predominant suit
		// when I modify the suitlessString method to also include the flops. Example: a Royal Flush on the flop will
		// look like this: AKQJ05 - the final 5 representing the amount of cards in the predominant suit. AKQJ04, AKQJ03, 
		// and AKQJ02 are strategically different.
		int clubs = 0;
		int diamonds = 0;
		int hearts = 0;
		int spades = 0;
		String flushSuit = "";
		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().equals("Clubs")) {
				clubs++;
			} else if (cards.get(i).getSuit().equals("Diamonds")) {
				diamonds++;
			} else if (cards.get(i).getSuit().equals("Hearts")) {
				hearts++;
			} else {
				spades++;
			}
		}
		
		if (clubs >= 5) {
			flushSuit = "Clubs";
		} else if (diamonds >= 5) {
			flushSuit = "Diamonds";
		} else if (hearts >= 5) {
			flushSuit = "Hearts";
		} else {
			flushSuit = "Spades";
		}
		
		// Now we can use the contains method to see if all of the needed cards are present.
		// Of course, A-5 Straight Flush should be denoted.
		if (highestStraight == 5) {
			if (cards.contains(14, flushSuit) &&
					cards.contains(2, flushSuit) &&
					cards.contains(3, flushSuit) &&
					cards.contains(4, flushSuit) &&
					cards.contains(5, flushSuit)) {
				return 5;
			}
		}
		
		if (cards.contains(highestStraight, flushSuit) &&
				cards.contains(highestStraight - 1, flushSuit) &&
				cards.contains(highestStraight - 2, flushSuit) &&
				cards.contains(highestStraight - 3, flushSuit) &&
				cards.contains(highestStraight - 4, flushSuit)) {
			return highestStraight;
		}
		
		return 0;
	}
	
	
	public int[] fourKind(CardList cards) {
		// Sort the cards, then check all possible locations for a four of a kind. Multiple four of a kinds are impossible.
		
		cards.sort();
		
		int highest = 0;
		
		for (int i = 0; i < cards.size() - 4; i++) {
			if (cards.get(i).getNumber() == cards.get(i + 1).getNumber() &&
					cards.get(i).getNumber() == cards.get(i + 2).getNumber() &&
					cards.get(i).getNumber() == cards.get(i + 3).getNumber()) {
				highest = cards.get(i).getNumber();
			}
		}
		
		// Return blank array if no four of a kind is found.
		if (highest == 0) {
			int[] blankArray = {0, 0};
			return blankArray;
		}
		
		int i = 6;
		int highCard = 0;
		
		// Sets highCard to the highest card that isn't a part of the four of a kind.
		do {
			highCard = cards.get(i).getNumber();
			i--;
		} while (highCard == highest);
		
		int[] fourHighCard = {highest, highCard};
		return fourHighCard;
		
	}
	
	
	public int[] fullHouse(CardList cards) {
		
		cards.sort();
		
		if (this.threeKind(this.getCards())[0] == 0 && this.twoPair(this.getCards())[0] == 0) {
			int[] blankArray = {0, 0};
			return blankArray;
		}
		
		// At this point, we know that the player has a Three of a Kind.
		
		int[] tripleDouble = new int[2];
		
		tripleDouble[0] = this.threeKind(this.getCards())[0];
		if (this.twoPair(this.getCards())[0] == tripleDouble[0]) { // If the highest pair is the three of a kind, the second highest pair
			tripleDouble[1] = this.twoPair(this.getCards())[1];    // Should be added to the "double" part of the int[2] array.
		} else {										 // Else, the highest pair should be added.
			tripleDouble[1] = this.twoPair(this.getCards())[0];
		}
		
		return tripleDouble;
	}
	
	
	public int[] flush(CardList cards) {
		cards.sortBySuit();
		
		String flushSuit = "No Flush!";
		
		// Since they're sorted by suit, 5 of the same suit in a row would mean a flush.
		for (int i = 0; i < cards.size() - 5; i++) {
			if (cards.get(i).getSuit().equals(cards.get(i + 1).getSuit()) &&
					cards.get(i).getSuit().equals(cards.get(i + 2).getSuit()) && 
					cards.get(i).getSuit().equals(cards.get(i + 3).getSuit()) && 
					cards.get(i).getSuit().equals(cards.get(i + 4).getSuit())) {
						flushSuit = cards.get(i).getSuit();
			}
		}
		
		// We need to return an array of 0s if there is no flushSuit.
		
		if (flushSuit.equals("No Flush!")) {
			int[] blankArray = {0, 0, 0, 0, 0};
			return blankArray;
		}
		
		// Create a new CardList, and add all of the Cards in the Flush to it.
		CardList flushCards = new CardList();
			
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().equals(flushSuit)) {
					flushCards.add(cards.get(i));
			}
		}
			
		// Now we sort the cards by number, and return an int[] with the highest 5 numbers of the suit.
		
		flushCards.sort();
		
		int[] flushNumbers = {flushCards.get(flushCards.size() - 1).getNumber(), 
				flushCards.get(flushCards.size() - 2).getNumber(), 
				flushCards.get(flushCards.size() - 3).getNumber(), 
				flushCards.get(flushCards.size() - 4).getNumber(), 
				flushCards.get(flushCards.size() - 5).getNumber()};
		
		return flushNumbers;
	}
	
	
	public int straight(CardList cards) {
		cards.sort();
		
		int highestStraight = 0;
		
		// First we need to check the Ace-5 Straight.
		
		if (cards.contains(14) && cards.contains(2) && cards.contains(3) && cards.contains(4) && cards.contains(5)) {
			highestStraight = 5;
		}
		
		// Since they're sorted, it should check 5 cards in a row and if the difference in their numbers is 1 all around,
		// you have a straight whose highest card is the 5th one.
		for (int i = 0; i < cards.size() - 5; i++) {
			if (cards.get(i + 4).getNumber() - cards.get(i + 3).getNumber() == 1 &&
					cards.get(i + 3).getNumber() - cards.get(i + 2).getNumber() == 1 &&
					cards.get(i + 2).getNumber() - cards.get(i + 1).getNumber() == 1 &&
					cards.get(i + 1).getNumber() - cards.get(i).getNumber() == 1 
					&& cards.get(i + 4).getNumber() > highestStraight) {
				highestStraight = cards.get(i + 4).getNumber();
			}
		}
		
		return highestStraight;
	}
	
	
	public int[] threeKind(CardList cards) {
		
		cards.sort();
		
		int highest = 0; // Theoretically, since you have seven cards, you could have two Three of A Kinds.
		
		for (int i = 0; i < cards.size() - 2; i++) {
			if (cards.get(i).getNumber() == cards.get(i + 1).getNumber() &&
					cards.get(i).getNumber() == cards.get(i + 2).getNumber() &&
					cards.get(i).getNumber() > highest) {
				highest = cards.get(i).getNumber();
			}
		}
		
		if (highest == 0) {
			int[] blankArray = {0, 0, 0};
			return blankArray;
		}
		
		int highCard1 = 0;
		int highCard2 = 0;
		int i = 6;
		
		do {
			highCard1 = cards.get(i).getNumber();
			i--;
		} while (highCard1 == highest);
		
		i = 5;
		do {
			highCard2 = cards.get(i).getNumber();
			i--;
		} while (highCard2 == highest || highCard2 == highCard1 && i > -1);
		
		int[] threeHighCard = {highest, highCard1, highCard2};
		return threeHighCard;
	}
	
	
	public int[] twoPair(CardList cards) {
		
		int[] blankArray = {0, 0, 0}; // Declared up here because it is needed twice.
		
		cards.sort();
		
		int highestPair = this.pair(this.getCards())[0];
		
		if (highestPair == 0) {
			return blankArray;
		}
		
		int secondHighestPair = 0;
		
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getNumber() == cards.get(i + 1).getNumber() &&
					cards.get(i).getNumber() > secondHighestPair && 
					cards.get(i).getNumber() != highestPair) {
			secondHighestPair = cards.get(i).getNumber();
			}
		}
		
		if (secondHighestPair == 0) {
			return blankArray;
		}
		
		int highCard = 0;
		
		int i = 6;
		do {
			highCard = cards.get(i).getNumber();
			i--;
		} while (highCard == highestPair || highCard == secondHighestPair && i > 0);
		
		int[] pairsHighCard = {highestPair, secondHighestPair, highCard};
		return pairsHighCard;
		
	}
	
	
	public int[] pair(CardList cards) {
		
		cards.sort();
		
		// Since the cards are smallest to largest, a pair will always be in a row. 
		// Just checks for the highest pair.
		
		// This will also count a Three of a Kind as a pair.
		
		int highestPair = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getNumber() == cards.get(i + 1).getNumber() &&
						cards.get(i).getNumber() > highestPair) {
				highestPair = cards.get(i).getNumber();
			}
		}
		
		if (highestPair == 0) {
			int[] blankArray = {0, 0, 0, 0};
			return blankArray;
		}
		
		int highCard1 = 0;
		int highCard2 = 0;
		int highCard3 = 0;
		
		// High Card (Pair Tie-breaker) has to be the highest 3 cards that are not in the pair. 
		// Will set three int variables to the three highest cards that aren't in the pair.
		
		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNumber() != highestPair && 
					cards.get(i).getNumber() > highCard1) {
				highCard1 = cards.get(i).getNumber();
			}
		}
		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNumber() != highestPair &&
					cards.get(i).getNumber() != highCard1 && 
					cards.get(i).getNumber() > highCard2) {
				highCard2 = cards.get(i).getNumber();
			}
		}
		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNumber() != highestPair &&
					cards.get(i).getNumber() != highCard1 &&
					cards.get(i).getNumber() != highCard2 &&
					cards.get(i).getNumber() > highCard3) {
				highCard3 = cards.get(i).getNumber();
			}
		}
			
		// Creates an int[4] of {Pair, Highest non-Pair, Second... Third...}
		int[] pairHighCard = {highestPair, highCard1, highCard2, highCard3};
		
		
		return pairHighCard;
		
	}
	
	
	public int[] highCard(CardList cards) {
		
		// Sorts the cards, then returns the highest 5. Size of cards should always be 7.
		cards.sort();
		
		int[] highCards = {cards.get(6).getNumber(), cards.get(5).getNumber(), cards.get(4).getNumber(),
				cards.get(3).getNumber(), cards.get(2).getNumber()};
		
		return highCards;
	}
	
}
