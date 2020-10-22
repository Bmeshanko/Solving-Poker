import java.util.Arrays;
public class Deck {
	private Card aceDiamonds = new Card("Diamonds", "Ace");
	private Card aceHearts = new Card("Hearts", "Ace");
	private Card aceClubs = new Card("Clubs", "Ace");
	private Card aceSpades = new Card("Spades", "Ace");
	private Card twoDiamonds = new Card("Diamonds", "2");
	private Card twoHearts = new Card("Hearts", "2");
	private Card twoClubs = new Card("Clubs", "2");
	private Card twoSpades = new Card("Spades", "2");
	private Card threeDiamonds = new Card("Diamonds", "3");
	private Card threeHearts = new Card("Hearts", "3");
	private Card threeClubs = new Card("Clubs", "3");
	private Card threeSpades = new Card("Spades", "3");
	private Card fourDiamonds = new Card("Diamonds", "4");
	private Card fourHearts = new Card("Hearts", "4");
	private Card fourClubs = new Card("Clubs", "4");
	private Card fourSpades = new Card("Spades", "4");
	private Card fiveDiamonds = new Card("Diamonds", "5");
	private Card fiveHearts = new Card("Hearts", "5");
	private Card fiveClubs = new Card("Clubs", "5");
	private Card fiveSpades = new Card("Spades", "5");
	private Card sixDiamonds = new Card("Diamonds", "6");
	private Card sixHearts = new Card("Hearts", "6");
	private Card sixClubs = new Card("Clubs", "6");
	private Card sixSpades = new Card("Spades", "6");
	private Card sevenDiamonds = new Card("Diamonds", "7");
	private Card sevenHearts = new Card("Hearts", "7");
	private Card sevenClubs = new Card("Clubs", "7");
	private Card sevenSpades = new Card("Spades", "7");
	private Card eightDiamonds = new Card("Diamonds", "8");
	private Card eightHearts = new Card("Hearts", "8");
	private Card eightClubs = new Card("Clubs", "8");
	private Card eightSpades = new Card("Spades", "8");
	private Card nineDiamonds = new Card("Diamonds", "9");
	private Card nineHearts = new Card("Hearts", "9");
	private Card nineClubs = new Card("Clubs", "9");
	private Card nineSpades = new Card("Spades", "9");
	private Card tenDiamonds = new Card("Diamonds", "10");
	private Card tenHearts = new Card("Hearts", "10");
	private Card tenClubs = new Card("Clubs", "10");
	private Card tenSpades = new Card("Spades", "10");
	private Card jackDiamonds = new Card("Diamonds", "Jack");
	private Card jackHearts = new Card("Hearts", "Jack");
	private Card jackClubs = new Card("Clubs", "Jack");
	private Card jackSpades = new Card("Spades", "Jack");
	private Card queenDiamonds = new Card("Diamonds", "Queen");
	private Card queenHearts = new Card("Hearts", "Queen");
	private Card queenClubs = new Card("Clubs", "Queen");
	private Card queenSpades = new Card("Spades", "Queen");
	private Card kingDiamonds = new Card("Diamonds", "King");
	private Card kingHearts = new Card("Hearts", "King");
	private Card kingClubs = new Card("Clubs", "King");
	private Card kingSpades = new Card("Spades", "King");
	private Card[] cards = {aceDiamonds, aceHearts, aceClubs, aceSpades, twoDiamonds, 
			twoHearts, twoClubs, twoSpades, threeDiamonds, threeHearts, threeClubs, 
			threeSpades, fourDiamonds, fourHearts, fourClubs, fourSpades, fiveDiamonds, 
			fiveHearts, fiveClubs, fiveSpades, sixDiamonds, sixHearts, sixClubs, sixSpades, 
			sevenDiamonds, sevenHearts, sevenClubs, sevenSpades, eightDiamonds, eightHearts, 
			eightClubs, eightSpades, nineDiamonds, nineHearts, nineClubs, nineSpades,
			tenDiamonds, tenHearts, tenClubs, tenSpades, jackDiamonds, jackHearts, jackClubs, 
			jackSpades, queenDiamonds, queenHearts, queenClubs, queenSpades, kingDiamonds, 
			kingHearts, kingClubs, kingSpades};
	private Card[] flop = new Card[3];
	private Card[] turn = new Card[4];
	private Card[] river = new Card[5];
	
	public Deck() {
		shuffle();
	}
	
	public Card[] getCards() {
		return this.cards;
	}
	
	public void shuffle() {
		Card[] shuffledCards = new Card[52];
		for (int i = 0; i < 52; i++) {
			int j = (int) (52*Math.random());
			while (shuffledCards[j] != null) {
				j = (int) (52*Math.random());
			}
			shuffledCards[j] = this.cards[i];
		}
		this.cards = shuffledCards;
	}
	
	public Card deal() {
		Card[] nonDealtCards = new Card[cards.length - 1];
		for (int i = 1; i < cards.length; i++) {
			nonDealtCards[i-1] = this.cards[i];
		}
		Card dealtCard = this.cards[0];
		this.cards = nonDealtCards;
		return dealtCard;
	}

	public String toString(Card[] cards) {
		String s = "";
		for (int i = 0; i < cards.length; i++) {
			s += cards[i].toString() + "\n";
		}
		return s;
	}
	
	
	public void flop(Deck deck) {
		for (int i = 0; i < 3; i++) {
			Card temp = deck.deal();
			this.flop[i] = temp;
			this.turn[i] = temp;
			this.river[i] = temp;
		}
	}
	
	public Card[] getFlop() {
		return this.flop;
	}
	
	public void turn(Deck deck) {
		Card temp = deck.deal();
		this.turn[3] = temp;
		this.river[3] = temp;
	}
	
	public Card[] getTurn() {
		return this.turn;
	}
	
	public void river(Deck deck) {
		this.river[4] = deck.deal();
	}
	
	public Card[] getRiver() {
		return this.river;
	}
}
