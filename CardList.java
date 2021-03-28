import java.util.ArrayList;
import java.util.Collections;

/*
 * CardList.java - This class extends ArrayList<Card> and adds lots of useful methods.
 */
public class CardList extends ArrayList<Card> {
	public CardList() {
		super();
	}
	
	// This is important for checking for straights -- also 
	// will be used by AI to evaluate when to bet.
	public boolean contains(int number) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getNumber() == number) {
				return true;
			}
		}
		return false;
	}
	
	// This method is important for checking for straight flushes
	public boolean contains(int number, String suit) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getNumber() == number &&
				this.get(i).getSuit().equals(suit)) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(this);
	}
	
	public void sortBySuit() {
		CardList sortedCards = new CardList();
		
		// 4 Separate for-loops in order to iterate through and sort them alphabetically.
		
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getSuit().equals("Clubs")) {
				sortedCards.add(this.get(i));
			}
		}
		
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getSuit().equals("Diamonds")) {
				sortedCards.add(this.get(i));
			}
		}
		
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getSuit().equals("Hearts")) {
				sortedCards.add(this.get(i));
			}
		}
		
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getSuit().equals("Spades")) {
				sortedCards.add(this.get(i));
			}
		}
		
		// Final for loop to replace the elements in the unsorted list with the sorted ones
		
		for (int i = 0; i < this.size(); i++) {
			this.set(i, sortedCards.get(i));
		}
	}
}