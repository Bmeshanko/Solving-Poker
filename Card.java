public class Card implements Comparable {
	private String suite;
	private int number;
	
	public Card(String suite, int number) {
		this.suite = suite;
		this.number = number;
	}
	
	public String getSuite() {
		return this.suite;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String toString() {
		return this.number + " of " + this.suite;
		
	}

	@Override
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
