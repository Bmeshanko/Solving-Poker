import java.util.ArrayList;
/**
 * Defines Deck Objects, which are arrays of 52 cards.
 * Also defines methods relating to randomization and dealing.
 */
public class Deck {
	// Initialize all 52 Cards in the deck.
	private Card aceDiamonds = new Card("Diamonds", 14);
	private Card aceHearts = new Card("Hearts", 14);
	private Card aceClubs = new Card("Clubs", 14);
	private Card aceSpades = new Card("Spades", 14);
	private Card twoDiamonds = new Card("Diamonds", 2);
	private Card twoHearts = new Card("Hearts", 2);
	private Card twoClubs = new Card("Clubs", 2);
	private Card twoSpades = new Card("Spades", 2);
	private Card threeDiamonds = new Card("Diamonds", 3);
	private Card threeHearts = new Card("Hearts", 3);
	private Card threeClubs = new Card("Clubs", 3);
	private Card threeSpades = new Card("Spades", 3);
	private Card fourDiamonds = new Card("Diamonds", 4);
	private Card fourHearts = new Card("Hearts", 4);
	private Card fourClubs = new Card("Clubs", 4);
	private Card fourSpades = new Card("Spades", 4);
	private Card fiveDiamonds = new Card("Diamonds", 5);
	private Card fiveHearts = new Card("Hearts", 5);
	private Card fiveClubs = new Card("Clubs", 5);
	private Card fiveSpades = new Card("Spades", 5);
	private Card sixDiamonds = new Card("Diamonds", 6);
	private Card sixHearts = new Card("Hearts", 6);
	private Card sixClubs = new Card("Clubs", 6);
	private Card sixSpades = new Card("Spades", 6);
	private Card sevenDiamonds = new Card("Diamonds", 7);
	private Card sevenHearts = new Card("Hearts", 7);
	private Card sevenClubs = new Card("Clubs", 7);
	private Card sevenSpades = new Card("Spades", 7);
	private Card eightDiamonds = new Card("Diamonds", 8);
	private Card eightHearts = new Card("Hearts", 8);
	private Card eightClubs = new Card("Clubs", 8);
	private Card eightSpades = new Card("Spades", 8);
	private Card nineDiamonds = new Card("Diamonds", 9);
	private Card nineHearts = new Card("Hearts", 9);
	private Card nineClubs = new Card("Clubs", 9);
	private Card nineSpades = new Card("Spades", 9);
	private Card tenDiamonds = new Card("Diamonds", 10);
	private Card tenHearts = new Card("Hearts", 10);
	private Card tenClubs = new Card("Clubs", 10);
	private Card tenSpades = new Card("Spades", 10);
	private Card jackDiamonds = new Card("Diamonds", 11);
	private Card jackHearts = new Card("Hearts", 11);
	private Card jackClubs = new Card("Clubs", 11);
	private Card jackSpades = new Card("Spades", 11);
	private Card queenDiamonds = new Card("Diamonds", 12);
	private Card queenHearts = new Card("Hearts", 12);
	private Card queenClubs = new Card("Clubs", 12);
	private Card queenSpades = new Card("Spades", 12);
	private Card kingDiamonds = new Card("Diamonds", 13);
	private Card kingHearts = new Card("Hearts", 13);
	private Card kingClubs = new Card("Clubs", 13);
	private Card kingSpades = new Card("Spades", 13);
	
	private CardList deck = new CardList();

	public Deck() {
		// Add all 52 cards to the deck.
		deck.add(aceDiamonds);
		deck.add(aceHearts);
		deck.add(aceClubs);
		deck.add(aceSpades);
		deck.add(twoDiamonds);
		deck.add(twoHearts);
		deck.add(twoClubs);
		deck.add(twoSpades);
		deck.add(threeDiamonds);
		deck.add(threeHearts);
		deck.add(threeClubs);
		deck.add(threeSpades);
		deck.add(fourDiamonds);
		deck.add(fourHearts);
		deck.add(fourClubs);
		deck.add(fourSpades);
		deck.add(fiveDiamonds);
		deck.add(fiveHearts);
		deck.add(fiveClubs);
		deck.add(fiveSpades);
		deck.add(sixDiamonds);
		deck.add(sixHearts);
		deck.add(sixClubs);
		deck.add(sixSpades);
		deck.add(sevenDiamonds);
		deck.add(sevenHearts);
		deck.add(sevenClubs);
		deck.add(sevenSpades);
		deck.add(eightDiamonds);
		deck.add(eightHearts);
		deck.add(eightClubs);
		deck.add(eightSpades);
		deck.add(nineDiamonds);
		deck.add(nineHearts);
		deck.add(nineClubs);
		deck.add(nineSpades);
		deck.add(tenDiamonds);
		deck.add(tenHearts);
		deck.add(tenClubs);
		deck.add(tenSpades);
		deck.add(jackDiamonds);
		deck.add(jackHearts);
		deck.add(jackClubs);
		deck.add(jackSpades);
		deck.add(queenDiamonds);
		deck.add(queenHearts);
		deck.add(queenClubs);
		deck.add(queenSpades);
		deck.add(kingDiamonds);
		deck.add(kingHearts);
		deck.add(kingClubs);
		deck.add(kingSpades);
		
		// Then, we need to shuffle the cards.
		shuffle();
	}
	
	public CardList getCards() {
		return this.deck;
	}
	
	public void shuffle() {
		CardList shuffledDeck = new CardList();
		while(deck.size() > 0) { 
			// Removes the cards from the deck in random order,
			// Puts them into a new deck. A Reverse Insertion Sort!
			int random = (int) (deck.size() * Math.random());
			shuffledDeck.add(deck.get(random));
			deck.remove(random);
		}
		this.deck = shuffledDeck;
	}
	
	// Deal the top card in the Deck. No need to "burn" a card.
	// Removes that deck from the deck - We will need to reinitialize a
	// Deck() object for every hand that we deal.
	public Card deal() {
		Card dealCard = deck.get(0);
		this.deck.remove(0);
		return dealCard;
	}
}
