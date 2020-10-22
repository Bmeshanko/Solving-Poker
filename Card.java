public class Card {
	private String suite;
	private String number;
	
	public Card(String suite, String number) {
		this.suite = suite;
		this.number = number;
	}
	
	public String getSuite() {
		return this.suite;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String toString() {
	  return = this.number + " of " + this.suite;
	}
}
