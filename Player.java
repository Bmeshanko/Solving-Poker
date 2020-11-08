/**
 * Defines Player objects, which have 7 cards (2 in hand, 5 in community).
 * 
 * This class includes several methods that determine what poker hand the Player has.
 */
import java.util.ArrayList;
import java.util.Collections;
public class Player {
	private Card cardOne;
	private Card cardTwo;
	private Card[] cards = new Card[7];
	private String name;
	private int numChips = 1500;
	
	public Player(String name, Deck deck) {
		this.name = name;
		this.cardOne = deck.deal();
		this.cardTwo = deck.deal();
	}

	public String getName() {
		return this.name;
	}
	public String toString() {
		String s = this.name + "'s cards: ";
		s += this.cardOne.toString() + " | ";
		s += this.cardTwo.toString();
		return s;
	}
	
	public void setCards(Card[] river) {
		this.cards[0] = this.cardOne;
		this.cards[1] = this.cardTwo;
		for (int i = 0; i < 5; i++) {
			this.cards[i+2] = river[i];
		}
	}
	
	public Card[] getCards() {
		return this.cards;
	}
	
	public Card[] sortCardsByNumber(Card[] hand) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < hand.length; i++) {
			cards.add(hand[i]);
		}
		Collections.sort(cards);
		for (int j = 0; j < hand.length; j++) {
			hand[j] = cards.get(j);
		}
		return hand;
		
	}
	
	public int isStraightFlush(Card[] hand) {
		if (this.isFlush(this.getCards())[0].getNumber() == 0 && this.isStraight(this.getCards()) > 0) {
			return 0;
		}
		int numOfClubs = 0;
		int numOfSpades = 0;
		int numOfDiamonds = 0;
		int numOfHearts = 0;
		int highest = 0;
		
		for (int i = 0; i < 7; i++) {
			if (hand[i].getSuite().equals("Clubs")) {
				numOfClubs++;
			} else if (hand[i].getSuite().equals("Spades")) {
				numOfSpades++;
			} else if (hand[i].getSuite().equals("Diamonds")) {
				numOfDiamonds++;
			} else if (hand[i].getSuite().equals("Hearts")) {
				numOfHearts++;
			}
		}
		
		if (numOfSpades > highest) {
			highest = numOfSpades;
		}
		
		if (numOfDiamonds > highest) {
			highest = numOfDiamonds;
		}
		
		if (numOfHearts > highest) {
			highest = numOfHearts;
		}
		
		if (numOfClubs > highest) {
			highest = numOfClubs;
		}
	
		ArrayList <Integer> sameSuitNumbers = new ArrayList<Integer>();
		
		if (numOfSpades == highest) {
			for (int a = 0; a < 7; a++) {
				if (hand[a].getSuite().equals("Spades")) {
					sameSuitNumbers.add(hand[a].getNumber());
				}
			}
		}
		
		if (numOfDiamonds == highest) {
			for (int a = 0; a < 7; a++) {
				if (hand[a].getSuite().equals("Diamonds")) {
					sameSuitNumbers.add(hand[a].getNumber());
				}
			}
		}
		
		if (numOfHearts == highest) {
			for (int a = 0; a < 7; a++) {
				if (hand[a].getSuite().equals("Hearts")) {
					sameSuitNumbers.add(hand[a].getNumber());
				}
			}
		}
		
		if (numOfClubs == highest) {
			for (int a = 0; a < 7; a++) {
				if (hand[a].getSuite().equals("Clubs")) {
					sameSuitNumbers.add(hand[a].getNumber());
				}
			}
		}

		Collections.sort(sameSuitNumbers);
		int[] sameSuitArray = new int[highest];
		for (int k = 0; k < highest; k++) {
			sameSuitArray[k] = sameSuitNumbers.get(k);
		}
		
		if (highest == 5) {
			if (sameSuitArray[4] == 14 && sameSuitArray[0] == 2 &&
							sameSuitArray[1] == 3 && sameSuitArray[2] == 4 && sameSuitArray[3] == 5) {
				return 5;
			} else if (sameSuitArray[1] - sameSuitArray[0] == 1 &&
					sameSuitArray[2] - sameSuitArray[1] == 1 &&
					sameSuitArray[3] - sameSuitArray[2] == 1 &&
					sameSuitArray[4] - sameSuitArray[3] == 1) {
				return sameSuitArray[4];
			} else {
				return 0;
			}
			
			
		} else if (highest == 6) {
			if (sameSuitArray[5] == 14 && sameSuitArray[0] == 2 &&
					sameSuitArray[1] == 3 && sameSuitArray[2] == 4 && sameSuitArray[3] == 5) {
				return 5;
			}
			
			if (sameSuitArray[1] - sameSuitArray[0] == 1 &&
					sameSuitArray[2] - sameSuitArray[1] == 1 &&
					sameSuitArray[3] - sameSuitArray[2] == 1 &&
					sameSuitArray[4] - sameSuitArray[3] == 1) {
				return sameSuitArray[4];
			} else if (sameSuitArray[2] - sameSuitArray[1] == 1 &&
					sameSuitArray[3] - sameSuitArray[2] == 1 &&
					sameSuitArray[4] - sameSuitArray[3] == 1 &&
					sameSuitArray[5] - sameSuitArray[4] == 1) {
				return sameSuitArray[5];
			} else {
				return 0;
			}
			
			
		} else {
			return isStraight(hand);
		}
	}
	
	public int[] isFourOfAKind(Card[] hand) {
		int[] winner = new int[2];
		hand = this.sortCardsByNumber(hand);
		
		if (hand[0].getNumber() == hand[1].getNumber() &&
				hand[0].getNumber() == hand[2].getNumber() && 
						hand[0].getNumber()  == hand[3].getNumber()) {
			winner[0] = hand[0].getNumber();
		} 
		
		else if (hand[1].getNumber() == hand[2].getNumber() &&
				hand[1].getNumber() == hand[3].getNumber() && 
					hand[1].getNumber()  == hand[4].getNumber()) {
			winner[0] = hand[1].getNumber();
		} 
		
		else if (hand[2].getNumber() == hand[3].getNumber() &&
				hand[2].getNumber() == hand[4].getNumber() && 
					hand[2].getNumber()  == hand[5].getNumber()) {
			winner[0] = hand[2].getNumber();
		} 
		
		else if (hand[3].getNumber() == hand[4].getNumber() &&
				hand[3].getNumber() == hand[5].getNumber() && 
					hand[3].getNumber()  == hand[6].getNumber()) {
			winner[0] = hand[3].getNumber();
		} else {
			winner[0] = 0;
			winner[1] = 0;
			return winner;
		}
		
		if (winner[0] > 0) {
			for (int i = 0; i < 7; i++) {
				if (hand[i].getNumber() == winner[0]) {
					hand[i] = new Card("Temp", 1);
				}
			}
			
			hand = this.sortCardsByNumber(hand);
			winner[1] = hand[6].getNumber();
			return winner;
		}
		return winner;
	}
	
	public int[] isFullHouse(Card[] hand) {
		int[] xFullY = new int[2];
		xFullY[1] = this.isPair(hand)[0];
		xFullY[0] = this.isThreeOfAKind(hand)[0];
		return xFullY;
	}
	
	public Card[] isFlush(Card[] hand) {
		Card[] highCards = new Card[7];
		
		Card[] sortedBySuite = new Card[7];
		for (int i = 0; i < 7; i++) {
			sortedBySuite[i] = new Card("Temp", 0);
		}
		int temp = 0;
		
		for (int a = 0; a < 7; a++) {
			if (hand[a].getSuite().equals("Clubs")) {
				sortedBySuite[temp] = hand[a];
				temp++;
			}
		}
		
		for (int a = 0; a < 7; a++) {
			if (hand[a].getSuite().equals("Diamonds")) {
				sortedBySuite[temp] = hand[a];
				temp++;
			}
		}
		
		for (int a = 0; a < 7; a++) {
			if (hand[a].getSuite().equals("Hearts")) {
				sortedBySuite[temp] = hand[a];
				temp++;
			}
		}
		
		for (int a = 0; a < 7; a++) {
			if (hand[a].getSuite().equals("Spades")) {
				sortedBySuite[temp] = hand[a];
				temp++;
			}
		}
		
		String flushSuite = "";
		
		if (sortedBySuite[0].getSuite().equals(sortedBySuite[1].getSuite()) &&
				sortedBySuite[0].getSuite().equals(sortedBySuite[2].getSuite()) &&
				sortedBySuite[0].getSuite().equals(sortedBySuite[3].getSuite()) &&
				sortedBySuite[0].getSuite().equals(sortedBySuite[4].getSuite())) {
			
			flushSuite = sortedBySuite[0].getSuite();
			
		} else if (sortedBySuite[1].getSuite().equals(sortedBySuite[2].getSuite()) &&
				sortedBySuite[1].getSuite().equals(sortedBySuite[3].getSuite()) &&
				sortedBySuite[1].getSuite().equals(sortedBySuite[4].getSuite()) &&
				sortedBySuite[1].getSuite().equals(sortedBySuite[5].getSuite())) {
			
			flushSuite = sortedBySuite[1].getSuite();
			
		} else if (sortedBySuite[2].getSuite().equals(sortedBySuite[3].getSuite()) &&
				sortedBySuite[2].getSuite().equals(sortedBySuite[4].getSuite()) &&
				sortedBySuite[2].getSuite().equals(sortedBySuite[5].getSuite()) &&
				sortedBySuite[2].getSuite().equals(sortedBySuite[6].getSuite())) {
			
			flushSuite = sortedBySuite[2].getSuite();
			
		} else {
			for (int i = 0; i < 7; i ++) {
				highCards[i] = new Card("Temp", 0);
			}
			return highCards;
		}
		
		ArrayList<Card> flushCards = new ArrayList<Card>();
		
		for (int i = 0; i < 7; i++) {
			if (sortedBySuite[i].getSuite().equals(flushSuite)) {
				flushCards.add(sortedBySuite[i]);
			}
		}
		
		Collections.sort(flushCards);
		
		for (int i = 0; i < flushCards.size(); i++) {
			highCards[i] = flushCards.get(flushCards.size() - i - 1);
		}
		
		if (flushCards.size() == 6) {
			highCards[5] = new Card("Temp", 0);
		} else if (flushCards.size() == 7) {
			highCards[5] = new Card("Temp", 0);
			highCards[6] = new Card("Temp", 0);
		}
		
		
		return highCards;
		
	}
	
	public int isStraight(Card[] hand) {
		int[] numbers = new int[7];
		
		ArrayList<Integer> sortedNumbers = new ArrayList<Integer>();
		for (int j = 0; j < hand.length; j++) {
			sortedNumbers.add(hand[j].getNumber());
		}
		
		Collections.sort(sortedNumbers);
		for (int k = 0; k < hand.length; k++) {
			numbers[k] = sortedNumbers.get(k);
		}
		
		
		//Moves Duplicates to the end.
		for (int l = 1; l < hand.length; l++) {
			if (numbers[l] == numbers[l - 1]) {
				int temp = numbers[l];
				for (int m = l + 1; m < 7; m++) {
					numbers[m - 1] = numbers[m];
				}
				numbers[6] = temp;
			}
		}
		
		int highestStraight = 0;
		
		if ((numbers[4] != 6) && 
				(numbers[6] == 14 && numbers[0] == 2 &&
				numbers[1] == 3 && numbers[2] == 4 && numbers[3] == 5)) {
			highestStraight = 5;
		} else if (numbers[1] - numbers[0] == 1 && 
				numbers[2] - numbers[1] == 1 &&
				numbers[3] - numbers[2] == 1 && 
				numbers[4] - numbers[3] == 1 && 
				numbers[4] > highestStraight) {
			highestStraight = numbers[4];
		} else if (numbers[2] - numbers[1] == 1 && 
				numbers[3] - numbers[2] == 1 &&
				numbers[4] - numbers[3] == 1 && 
				numbers[5] - numbers[4] == 1 &&
				numbers[5] > highestStraight) {
			highestStraight = numbers[5];
		} else if (numbers[3] - numbers[2] == 1 && 
				numbers[4] - numbers[3] == 1 &&
				numbers[5] - numbers[4] == 1 && 
				numbers[6] - numbers[5] == 1 &&
				numbers[6] > highestStraight) {
			highestStraight = numbers[6];
		}
		return highestStraight;
	}
	
	public int[] isThreeOfAKind(Card[] hand) {
		int[] winner = new int[3];
		winner[0] = 0;
		Card[] sortedHand = this.sortCardsByNumber(hand);

		if (sortedHand[0].getNumber() == sortedHand[1].getNumber() && 
				sortedHand[0].getNumber() == sortedHand[2].getNumber() &&
						sortedHand[0].getNumber() > winner[0]) {
			winner[0] = sortedHand[0].getNumber();
		}
		if (sortedHand[1].getNumber() == sortedHand[2].getNumber() && 
				sortedHand[1].getNumber() == sortedHand[3].getNumber() &&
						sortedHand[1].getNumber() > winner[0]) {
			winner[0] = sortedHand[1].getNumber();
		}
		
		if (sortedHand[2].getNumber() == sortedHand[3].getNumber() && 
				sortedHand[2].getNumber() == sortedHand[4].getNumber() &&
						sortedHand[2].getNumber() > winner[0]) {
			winner[0] = sortedHand[2].getNumber();
		}
		
		if (sortedHand[3].getNumber() == hand[4].getNumber() && 
				sortedHand[3].getNumber() == hand[5].getNumber() &&
						sortedHand[3].getNumber() > winner[0]) {
			winner[0] = sortedHand[3].getNumber();
		}
		
		if (sortedHand[4].getNumber() == sortedHand[6].getNumber() && 
				sortedHand[4].getNumber() == sortedHand[6].getNumber() &&
						sortedHand[4].getNumber() > winner[0]) {
			winner[0] = sortedHand[4].getNumber();
		}
		if (winner[0] > 0) {
			this.sortCardsByNumber(sortedHand);
			winner[1] = sortedHand[6].getNumber();
			winner[2] = sortedHand[5].getNumber();
			return winner;
		}
		return winner;
	}
	
	public int[] isTwoPair(Card[] hand) {
		hand = this.sortCardsByNumber(hand);
		int highestPair = 1;
		int secondHighestPair = 1;
		int[] winners = new int[3];
		winners[0] = 0;
		winners[1] = 0;
		winners[2] = 0;
		
		if (hand[0].getNumber() == hand[1].getNumber() && hand[0].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[1].getNumber() == hand[2].getNumber() && hand[1].getNumber() > highestPair) {
			highestPair = hand[1].getNumber();
		}
		if (hand[2].getNumber() == hand[3].getNumber() && hand[2].getNumber() > highestPair) {
			highestPair = hand[2].getNumber();
		}
		if (hand[3].getNumber() == hand[4].getNumber() && hand[3].getNumber() > highestPair) {
			highestPair = hand[3].getNumber();
		}
		if (hand[4].getNumber() == hand[5].getNumber() && hand[4].getNumber() > highestPair) {
			highestPair = hand[4].getNumber();
		}
		if (hand[5].getNumber() == hand[6].getNumber() && hand[5].getNumber() > highestPair) {
			highestPair = hand[5].getNumber();
		}
		
		if (hand[0].getNumber() == hand[1].getNumber() && hand[0].getNumber() != highestPair && hand[0].getNumber() > secondHighestPair) {
			secondHighestPair = hand[0].getNumber();
		}
		if (hand[1].getNumber() == hand[2].getNumber() && hand[1].getNumber() != highestPair && hand[1].getNumber() > secondHighestPair) {
			secondHighestPair = hand[1].getNumber();
		}
		if (hand[2].getNumber() == hand[3].getNumber() && hand[2].getNumber() != highestPair && hand[2].getNumber() > secondHighestPair) {
			secondHighestPair = hand[2].getNumber();
		}
		if (hand[3].getNumber() == hand[4].getNumber() && hand[3].getNumber() != highestPair && hand[3].getNumber() > secondHighestPair) {
			secondHighestPair = hand[3].getNumber();
		}
		if (hand[4].getNumber() == hand[5].getNumber() && hand[4].getNumber() != highestPair && hand[4].getNumber() > secondHighestPair) {
			secondHighestPair = hand[4].getNumber();
		}
		if (hand[5].getNumber() == hand[6].getNumber() && hand[5].getNumber() != highestPair && hand[5].getNumber() > secondHighestPair) {
			secondHighestPair = hand[5].getNumber();
		}
		
		if (highestPair > 1 && secondHighestPair > 1) {
			winners[0] = highestPair;
			winners[1] = secondHighestPair;
			this.sortCardsByNumber(hand);
			
			int c = 6;
			while (hand[c].getNumber() != winners[0] && hand[c].getNumber() != winners[1]) {
				c--;
			}
			winners[2] = hand[c].getNumber();
		}
		return winners;
	}
	
	public int[] isPair(Card[] hand) {
		hand = this.sortCardsByNumber(hand);
		int highestPair = 1;
		int[] winners = new int[4];
		
		if (hand[0].getNumber() == hand[1].getNumber() && hand[0].getNumber() != hand[2].getNumber() && hand[0].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[1].getNumber() == hand[2].getNumber() && hand[1].getNumber() != hand[3].getNumber() && hand[1].getNumber() != hand[0].getNumber() && hand[1].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[2].getNumber() == hand[3].getNumber() && hand[2].getNumber() != hand[4].getNumber() && hand[2].getNumber() != hand[1].getNumber() && hand[2].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[3].getNumber() == hand[4].getNumber() && hand[3].getNumber() != hand[5].getNumber() && hand[3].getNumber() != hand[2].getNumber() && hand[3].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[4].getNumber() == hand[5].getNumber() && hand[4].getNumber() != hand[6].getNumber() && hand[4].getNumber() != hand[3].getNumber() && hand[4].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		if (hand[5].getNumber() == hand[6].getNumber() && hand[5].getNumber() != hand[4].getNumber() && hand[5].getNumber() > highestPair) {
			highestPair = hand[0].getNumber();
		}
		
		if (highestPair > 1) {
			winners[0] = highestPair;
			hand = this.sortCardsByNumber(hand);
			winners[1] = hand[6].getNumber();
			winners[2] = hand[5].getNumber();
			winners[3] = hand[4].getNumber();
		}
		return winners;
	}
	
	public int[] highCards(Card[] hand) {
		int[] highToLow = new int[7];
		ArrayList<Integer> lowToHigh = new ArrayList<Integer>();
		
		Collections.sort(lowToHigh);
		for (int j = 0; j < 7; j++) {
			highToLow[j] = lowToHigh.get(6 - j);
		}
		return highToLow;
	}
}
