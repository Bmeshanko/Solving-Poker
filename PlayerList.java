import java.util.ArrayList;
/*
 * PlayerList.java - This class extends ArrayList<Player> and adds lots of useful methods,
 * including those that evaluate the winners of each hand.
 */
public class PlayerList extends ArrayList<Player> {
	
	private int potSize;
	
	public PlayerList() {
		super();
	}
	
	public int getPotSize() {
		return this.potSize;
	}
	
	public void initializeHand(Deck deck) {
		
		int size = this.size();
		
		for (int i = 0; i < size; i++) {
			this.get(i).clearCards();
		}
				
		for (int i = 0; i < size; i++) {
			this.get(i).dealCards(deck);
		}
		
		// Initialize the flop, turn and river.
		CardList flop = new CardList();
		CardList turn = new CardList();
		CardList river = new CardList();
		
		Card firstCard = deck.deal();
		Card secondCard = deck.deal();
		Card thirdCard = deck.deal();
		Card fourthCard = deck.deal();
		Card fifthCard = deck.deal();
		
		flop.add(firstCard);
		flop.add(secondCard);
		flop.add(thirdCard);
		
		turn.add(firstCard);
		turn.add(secondCard);
		turn.add(thirdCard);
		turn.add(fourthCard);
		
		river.add(firstCard);
		river.add(secondCard);
		river.add(thirdCard);
		river.add(fourthCard);
		river.add(fifthCard);
		
		// "Deal" all the players the River Cards. It doesn't matter that they get them before betting since they will not know
		// all the cards they have until the River is showing.
		for (int i = 0; i < size; i++) {
			this.get(i).setRiver(river);
		}
	}
	
	public PlayerList evaluateWinner() {
		int size = this.size();
		PlayerList winners = new PlayerList();
		
		// At this point we can now begin to evaluate who wins based on each hand.
		// Each evaluation point will need a loop to evaluate the highest of that hand,
		// and another loop to evaluate the winner from that.
		
		
		
		// Straight Flush Evaluation:
		
		int straightFlush = 5;
		boolean straightFlushBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).straightFlush(this.get(i).getCards()) > straightFlush) {
				straightFlush = this.get(i).straightFlush(this.get(i).getCards());
				
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).straightFlush(this.get(i).getCards()) == straightFlush) {
				winners.add(this.get(i));
				straightFlushBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Straight Flush!");
			}
		}
		
		if (straightFlushBoolean) {
			return winners;
		}
		
		// Four of a Kind Evaluation:
		
		int fourKind = 2;
		int fourKindHighCard = 2;
		boolean fourKindBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fourKind(this.get(i).getCards())[0] > fourKind) {
				fourKind = this.get(i).fourKind(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fourKind(this.get(i).getCards())[1] > fourKindHighCard &&
					this.get(i).fourKind(this.get(i).getCards())[0] == fourKind) {
				fourKindHighCard = this.get(i).fourKind(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fourKind(this.get(i).getCards())[0] == fourKind &&
					this.get(i).fourKind(this.get(i).getCards())[1] == fourKindHighCard) {
				winners.add(this.get(i));
				fourKindBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Four of a Kind!");
			}
		}
		
		if (fourKindBoolean) {
			return winners;
		}
		
		// Full House Evaluation:
		
		int fullHouseTriple = 2;
		int fullHouseDouble = 2;
		boolean fullHouseBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fullHouse(this.get(i).getCards())[0] > fullHouseTriple) {
				fullHouseTriple = this.get(i).fullHouse(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fullHouse(this.get(i).getCards())[1] > fullHouseDouble &&
					this.get(i).fullHouse(this.get(i).getCards())[0] == fullHouseTriple) {
				fullHouseDouble = this.get(i).fullHouse(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).fullHouse(this.get(i).getCards())[0] == fullHouseTriple &&
					this.get(i).fullHouse(this.get(i).getCards())[1] == fullHouseDouble) {
				winners.add(this.get(i));
				fullHouseBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Full House!");
			}
		}
		
		if (fullHouseBoolean) {
			return winners;
		}
		
		// Flush Evaluation:
		
		int flush1 = 2;
		int flush2 = 2;
		int flush3 = 2;
		int flush4 = 2;
		int flush5 = 2;
		boolean flushBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[0] > flush1) {
				flush1 = this.get(i).flush(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[1] > flush2 &&
					this.get(i).flush(this.get(i).getCards())[0] == flush1) {
				flush2 = this.get(i).flush(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[2] > flush3 &&
					this.get(i).flush(this.get(i).getCards())[1] == flush2) {
				flush3 = this.get(i).flush(this.get(i).getCards())[2];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[3] > flush4 &&
					this.get(i).flush(this.get(i).getCards())[2] == flush3) {
				flush4 = this.get(i).flush(this.get(i).getCards())[3];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[4] > flush5 &&
					this.get(i).flush(this.get(i).getCards())[3] == flush4) {
				flush5 = this.get(i).flush(this.get(i).getCards())[4];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).flush(this.get(i).getCards())[0] == flush1 &&
					this.get(i).flush(this.get(i).getCards())[1] == flush2 &&
					this.get(i).flush(this.get(i).getCards())[2] == flush3 &&
					this.get(i).flush(this.get(i).getCards())[3] == flush4 &&
					this.get(i).flush(this.get(i).getCards())[4] == flush5) {
				winners.add(this.get(i));
				flushBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Flush!");
			}
		}
		
		if (flushBoolean) {
			return winners;
		}
		
		// Straight Evaluation:
		
		int straight = 2;
		boolean straightBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).straight(this.get(i).getCards()) > straight) {
				straight = this.get(i).straight(this.get(i).getCards());
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).straight(this.get(i).getCards()) == straight) {
				winners.add(this.get(i));
				straightBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Straight!");
			}
		}
		
		if (straightBoolean) {
			return winners;
		}
		
		// Three of a Kind Evaluation:
		
		int threeKind = 2;
		int threeKindHighCard1 = 2;
		int threeKindHighCard2 = 2;
		boolean threeKindBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).threeKind(this.get(i).getCards())[0] > threeKind) {
				threeKind = this.get(i).threeKind(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).threeKind(this.get(i).getCards())[1] > threeKindHighCard1 &&
					this.get(i).threeKind(this.get(i).getCards())[0] == threeKind) {
				threeKindHighCard1 = this.get(i).threeKind(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).threeKind(this.get(i).getCards())[2] > threeKindHighCard2 &&
					this.get(i).threeKind(this.get(i).getCards())[1] == threeKindHighCard1) {
				threeKindHighCard2 = this.get(i).threeKind(this.get(i).getCards())[2];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).threeKind(this.get(i).getCards())[0] == threeKind &&
					this.get(i).threeKind(this.get(i).getCards())[1] == threeKindHighCard1 &&
					this.get(i).threeKind(this.get(i).getCards())[2] == threeKindHighCard2) {
				winners.add(this.get(i));
				threeKindBoolean = true;
				//System.out.println(this.get(i).getName() + " won with a Three of a Kind!");
			}
		}
		
		if (threeKindBoolean) {
			return winners;
		}
		
		// Two Pair Evaluator:
		
		int highestPair = 2;
		int secondHighestPair = 2;
		int twoPairHighCard = 2;
		boolean twoPairBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).twoPair(this.get(i).getCards())[0] > highestPair) {
				highestPair = this.get(i).twoPair(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).twoPair(this.get(i).getCards())[1] > secondHighestPair &&
					this.get(i).twoPair(this.get(i).getCards())[0] == highestPair) {
				secondHighestPair = this.get(i).twoPair(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).twoPair(this.get(i).getCards())[2] > twoPairHighCard &&
					this.get(i).twoPair(this.get(i).getCards())[1] == secondHighestPair) {
				twoPairHighCard = this.get(i).twoPair(this.get(i).getCards())[2];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).twoPair(this.get(i).getCards())[0] == highestPair &&
					this.get(i).twoPair(this.get(i).getCards())[1] == secondHighestPair &&
					this.get(i).twoPair(this.get(i).getCards())[2] == twoPairHighCard) {
				winners.add(this.get(i));
				twoPairBoolean = true;
				//System.out.println(this.get(i).getName() + " won with Two Pair!");
			}
		}
		
		if (twoPairBoolean) {
			return winners;
		}
		
		// Pair Evaluator:
		
		int pair = 2;
		int pairHighCard1 = 2;
		int pairHighCard2 = 2;
		int pairHighCard3 = 2;
		
		boolean pairBoolean = false;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).pair(this.get(i).getCards())[0] > pair) {
				pair = this.get(i).pair(this.get(i).getCards())[0];
			}
		}
		
		//System.out.println("Pair: " + pair);
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).pair(this.get(i).getCards())[1] > pairHighCard1 &&
					this.get(i).pair(this.get(i).getCards())[0] == pair) {
				pairHighCard1 = this.get(i).pair(this.get(i).getCards())[1];
			}
		}
		
		//System.out.println("Pair 1: " + pairHighCard1);
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).pair(this.get(i).getCards())[2] > pairHighCard2 &&
					this.get(i).pair(this.get(i).getCards())[1] == pairHighCard1 &&
					this.get(i).pair(this.get(i).getCards())[0] == pair) {
				pairHighCard2 = this.get(i).pair(this.get(i).getCards())[2];
			}
		}
		
		//System.out.println("Pair 2: " + pairHighCard2);
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).pair(this.get(i).getCards())[3] > pairHighCard3 &&
					this.get(i).pair(this.get(i).getCards())[2] == pairHighCard2 &&
					this.get(i).pair(this.get(i).getCards())[1] == pairHighCard1 &&
					this.get(i).pair(this.get(i).getCards())[0] == pair) {
				pairHighCard3 = this.get(i).pair(this.get(i).getCards())[3];
			}
		}
		
		//System.out.println("Pair 3: " + pairHighCard3);
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).pair(this.get(i).getCards())[0] == pair &&
					this.get(i).pair(this.get(i).getCards())[1] == pairHighCard1 &&
					this.get(i).pair(this.get(i).getCards())[2] == pairHighCard2 && 
					this.get(i).pair(this.get(i).getCards())[3] == pairHighCard3) {
				winners.add(this.get(i));
				pairBoolean = true;
				//System.out.println(this.get(i).getName() + " won with One Pair!");
			}
		}
		
		if (pairBoolean) {
			return winners;
		}
		
		// High Card Evaluator:
		
		int highCard1 = 2;
		int highCard2 = 2;
		int highCard3 = 2;
		int highCard4 = 2;
		int highCard5 = 2;
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[0] > highCard1) {
				highCard1 = this.get(i).highCard(this.get(i).getCards())[0];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[1] > highCard2 &&
					this.get(i).highCard(this.get(i).getCards())[0] == highCard1) {
				highCard2 = this.get(i).highCard(this.get(i).getCards())[1];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[2] > highCard3 &&
					this.get(i).highCard(this.get(i).getCards())[1] == highCard2) {
				highCard3 = this.get(i).highCard(this.get(i).getCards())[2];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[3] > highCard4 &&
					this.get(i).highCard(this.get(i).getCards())[2] == highCard3) {
				highCard4 = this.get(i).highCard(this.get(i).getCards())[3];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[4] > highCard5 &&
					this.get(i).highCard(this.get(i).getCards())[3] == highCard4) {
				highCard5 = this.get(i).highCard(this.get(i).getCards())[4];
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (this.get(i).highCard(this.get(i).getCards())[0] == highCard1 &&
					this.get(i).highCard(this.get(i).getCards())[1] == highCard2 &&
					this.get(i).highCard(this.get(i).getCards())[2] == highCard3 &&
					this.get(i).highCard(this.get(i).getCards())[3] == highCard4 &&
					this.get(i).highCard(this.get(i).getCards())[4] == highCard5) {
				winners.add(this.get(i));
				//System.out.println(this.get(i).getName() + " won with a High Card!");
			}
		}
		return winners;
	}
}