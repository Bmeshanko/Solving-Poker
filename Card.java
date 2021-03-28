/**
 * Defines Card Objects, which have a suite and number.
 * Implements Comparable to use Collections.sort later on.
 * 
 */
public class Card implements Comparable {
	private String suit;
	private int number;
	
	public Card(String suit, int number) {
		this.suit = suit;
		this.number = number;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String toString() {
		return this.number + " of " + this.suit;
		
	}

	public int compareTo(Object o) {
		if (((Card) o).getNumber() > this.getNumber()) {
			return -1;
		} else if (((Card) o).getNumber() < this.getNumber()) {
			return 1;
		} else {
			return 0;
		}
	}
}
