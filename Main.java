/**
 * Runs the program and prints the results of each hand to a .txt file.
 * 
 * The program analyzes each hand and determines a winner based off of the methods
 * that define what hand each player has in the Player class.
 */
import java.io.*;
public class Main {
	public static void main(String[] args) {
		int iterations = 0;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("poker.txt");
			while (iterations < 1000) {
				iterations++;
				Deck deck = new Deck();
				Player playerOne = new Player("Player One", deck);
				Player playerTwo = new Player("Player Two", deck);
				Player playerThree = new Player("Player Three", deck);
				Player playerFour = new Player("Player Four", deck);
				Player playerFive = new Player("Player Five", deck);
				Player playerSix = new Player("Player Six", deck);
				Player playerSeven = new Player("Player Seven", deck);
				Player playerEight = new Player("Player Eight", deck);
				Player playerNine = new Player("Player Nine", deck);
				Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven, playerEight, playerNine};
				for (int i = 0; i < players.length; i++) {
					pw.println(players[i].toString());
				}
				deck.flop(deck);
				deck.turn(deck);
				deck.river(deck);
				pw.print("River: ");
				pw.println(deck.toString(deck.getRiver()));
				for (int i = 0; i < players.length; i++) {
					players[i].setCards(deck.getRiver());
				}
				
				//
				// STRAIGHT FLUSH TESTER
				//
				
				boolean isStraightFlush = false;
				int highestStraightFlush = 5;
				for (int i = 0; i < players.length; i++) {
					if (players[i].isStraightFlush(players[i].getCards()) > highestStraightFlush) {
						highestStraightFlush = players[i].isStraightFlush(players[i].getCards());
					}
				}
				for (int i = 0; i < players.length; i++) {
					if (players[i].isStraightFlush(players[i].getCards()) == highestStraightFlush) {
						pw.println(players[i].getName() + " wins with a Straight Flush!");
						isStraightFlush = true;
						pw.println("--------------------------------------------");
					}
				}
				
				if (isStraightFlush)
					continue;
				
				//
				// FOUR OF A KIND TESTER
				//
				
				boolean isFourOfAKind = false;
				int highestFourOfAKind = 2;
				int highestHighCard = 2;
				if (players[0].isFourOfAKind(players[0].getCards())[0] > 0 &&
						players[1].isFourOfAKind(players[1].getCards())[0] > 0 && 
						players[2].isFourOfAKind(players[2].getCards())[0] > 0) {
					for (int i = 0; i < players.length; i++) {
						if (players[i].isFourOfAKind(players[i].getCards())[1] > highestHighCard) {
							highestHighCard = players[i].isFourOfAKind(players[i].getCards())[1];
						}
					}
					
					for (int i = 0; i < players.length; i++) {
						if (players[i].isFourOfAKind(players[i].getCards())[1] == highestHighCard) {
							pw.println(players[i].getName() + " wins with a Four of a Kind!");
						}
					}
					pw.println("--------------------------------------------");
					isFourOfAKind = true;
				} else {
					for (int i = 0; i < players.length; i++) {
						if (players[i].isFourOfAKind(players[i].getCards())[0] > highestFourOfAKind) {
							highestFourOfAKind = players[i].isFourOfAKind(players[i].getCards())[0];
						}
					}
					
					for (int i = 0; i < players.length; i++) {
						if (players[i].isFourOfAKind(players[i].getCards())[0] == highestFourOfAKind) {
							pw.println(players[i].getName() + " wins with a Four of a Kind!");
							pw.println("--------------------------------------------");
							isFourOfAKind = true;
						}
					}
				}
				
				if (isFourOfAKind)
					continue;
				
				//
				// FULL HOUSE TESTER
				//
				
				int highestTripleFullHouse = 2;
				int highestDoubleFullHouse = 2;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFullHouse(players[i].getCards())[0] > highestTripleFullHouse) {
						highestTripleFullHouse = players[i].isFullHouse(players[i].getCards())[0];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFullHouse(players[i].getCards())[0] == highestTripleFullHouse) {
						if (players[i].isFullHouse(players[i].getCards())[1] > highestDoubleFullHouse) {
							highestDoubleFullHouse = players[i].isFullHouse(players[i].getCards())[1];
						}
					}
				}
				
				boolean isFullHouse = false;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFullHouse(players[i].getCards())[0] == highestTripleFullHouse &&
						players[i].isFullHouse(players[i].getCards())[1] == highestDoubleFullHouse) {
						pw.println(players[i].getName() + " wins with a Full House!");
						pw.println("--------------------------------------------");
						isFullHouse = true;
						
					}
				}
				
				if (isFullHouse)
					continue;
				
				// 
				// FLUSH TESTER
				//
				
				int highestFlush1 = 2;
				int highestFlush2 = 2;
				int highestFlush3 = 2;
				int highestFlush4 = 2;
				int highestFlush5 = 2;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[0].getNumber() > highestFlush1) {
						highestFlush1 = players[i].isFlush(players[i].getCards())[0].getNumber();
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[1].getNumber() > highestFlush2) {
						highestFlush2 = players[i].isFlush(players[i].getCards())[1].getNumber();
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[2].getNumber() > highestFlush3) {
						highestFlush3 = players[i].isFlush(players[i].getCards())[2].getNumber();
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[3].getNumber() > highestFlush4) {
						highestFlush4 = players[i].isFlush(players[i].getCards())[3].getNumber();
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[4].getNumber() > highestFlush5) {
						highestFlush5 = players[i].isFlush(players[i].getCards())[4].getNumber();
					}
				}
				
				boolean isFlush = false;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFlush(players[i].getCards())[0].getNumber() == highestFlush1 &&
							players[i].isFlush(players[i].getCards())[1].getNumber() == highestFlush2 &&
							players[i].isFlush(players[i].getCards())[2].getNumber() == highestFlush3 &&
							players[i].isFlush(players[i].getCards())[3].getNumber() == highestFlush4 &&
							players[i].isFlush(players[i].getCards())[4].getNumber() == highestFlush5) {
						pw.println(players[i].getName() + " wins with a Flush!");
						pw.println("--------------------------------------------");
						isFlush = true;
					}	
				}
				
				if (isFlush)
					continue;
				
				//
				// STRAIGHT TESTER
				//
				
				boolean isStraight = false;
				int highestStraight = 5;
				for (int i = 0; i < players.length; i++) {
					if (players[i].isStraight(players[i].getCards()) > highestStraight) {
						highestStraight = players[i].isStraight(players[i].getCards());
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isStraight(players[i].getCards()) == highestStraight) {
						pw.println(players[i].getName() + " wins with a Straight!");
						isStraight = true;
						pw.println("--------------------------------------------");
					}
				}
				
				if (isStraight)
					continue;
				
				//
				// THREE OF A KIND TESTER
				//
				
				int highestThreeOfAKind1 = 2;
				int highestThreeOfAKind2 = 2;
				int highestThreeOfAKind3 = 2;
				boolean isThreeOfAKind = false;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isThreeOfAKind(players[i].getCards())[0] > highestThreeOfAKind1) {
						highestThreeOfAKind1 = players[i].isThreeOfAKind(players[i].getCards())[0];
					}
				}
			
				for (int i = 0; i < players.length; i++) {
					if (players[i].isThreeOfAKind(players[i].getCards())[1] > highestThreeOfAKind2) {
						highestThreeOfAKind2 = players[i].isThreeOfAKind(players[i].getCards())[1];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isThreeOfAKind(players[i].getCards())[2] > highestThreeOfAKind3) {
						highestThreeOfAKind3 = players[i].isThreeOfAKind(players[i].getCards())[2];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isThreeOfAKind(players[i].getCards())[0] == highestThreeOfAKind1 &&
							players[i].isThreeOfAKind(players[i].getCards())[1] == highestThreeOfAKind2 &&
							players[i].isThreeOfAKind(players[i].getCards())[2] == highestThreeOfAKind3) {
						pw.println(players[i].getName() + " wins with a Three of a Kind!");
						isThreeOfAKind = true;
						pw.println("--------------------------------------------");
					}
				}
				
				if (isThreeOfAKind)
					continue;
				
				//
				// TWO PAIR TESTER
				//
				
				boolean isTwoPair = false;
				
				int highestTwoPair1 = 2;
				int highestTwoPair2 = 2;
				int highestTwoPair3 = 2;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isTwoPair(players[i].getCards())[0] > highestTwoPair1) {
						highestTwoPair1 = players[i].isTwoPair(players[i].getCards())[0];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isTwoPair(players[i].getCards())[0] == highestTwoPair1 &&
							players[i].isTwoPair(players[i].getCards())[1] > highestTwoPair2) {
						highestTwoPair2 = players[i].isTwoPair(players[i].getCards())[1];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isTwoPair(players[i].getCards())[1] == highestTwoPair2 &&
							players[i].isTwoPair(players[i].getCards())[2] > highestTwoPair3) {
						highestTwoPair3 = players[i].isTwoPair(players[i].getCards())[2];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isTwoPair(players[i].getCards())[0] == highestTwoPair1 &&
							players[i].isTwoPair(players[i].getCards())[1] == highestTwoPair2 &&
									players[i].isTwoPair(players[i].getCards())[2] == highestTwoPair3) {
						pw.println(players[i].getName() + " wins with Two Pair!");
						isTwoPair = true;
						pw.println("--------------------------------------------");
					}	
				}
				
				if (isTwoPair)
					continue;
				
				//
				// PAIR TESTER
				//
				
				boolean isPair = false;
				int highestPair1 = 2;
				int highestPair2 = 2;
				int highestPair3 = 2;
				int highestPair4 = 2;
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isPair(players[i].getCards())[0] > highestPair1) {
						highestPair1 = players[i].isPair(players[i].getCards())[0];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isPair(players[i].getCards())[0] == highestPair1 &&
							players[i].isPair(players[i].getCards())[1] > highestPair2) {
						highestPair2 = players[i].isPair(players[i].getCards())[1];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isPair(players[i].getCards())[1] == highestPair2 &&
							players[i].isPair(players[i].getCards())[2] > highestPair3) {
						highestPair3 = players[i].isPair(players[i].getCards())[2];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isPair(players[i].getCards())[2] == highestPair3 &&
							players[i].isPair(players[i].getCards())[3] > highestPair4) {
						highestPair4 = players[i].isPair(players[i].getCards())[3];
					}
				}
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isPair(players[i].getCards())[0] == highestPair1 &&
							players[i].isPair(players[i].getCards())[1] == highestPair2 &&
							players[i].isPair(players[i].getCards())[2] == highestPair3 &&
							players[i].isPair(players[i].getCards())[3] == highestPair4) {
						pw.println(players[i].getName() + " wins with a Pair!");
						isPair = true;
						pw.println("--------------------------------------------");
					}	
				}
				
				if (isPair)
					continue;
				
				//
				// HIGH CARD TESTER
				//
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
}
	
