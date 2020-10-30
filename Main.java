import java.util.Scanner;
import java.io.*;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean criteria = false;
		int counter = 0;
		int iterations = 0;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("poker.txt");
			while (iterations < 2000) {
				iterations++;
				Deck deck = new Deck();
				Player playerOne = new Player("Player One", deck);
				Player playerTwo = new Player("Player Two", deck);
				Player playerThree = new Player("Player Three", deck);
				Player playerFour = new Player("Player Four", deck);
				Player playerFive = new Player("Player Five", deck);
				Player playerSix = new Player("Player Six", deck);
				Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix};
				Game game = new Game(players);
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
							isFourOfAKind = true;
							pw.println("--------------------------------------------");
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
				
				for (int i = 0; i < players.length; i++) {
					if (players[i].isFullHouse(players[i].getCards())[0] == highestTripleFullHouse &&
						players[i].isFullHouse(players[i].getCards())[1] == highestDoubleFullHouse) {
						pw.println(players[i].getName() + " wins with a Full House!");
						pw.println("--------------------------------------------");
					}
				}
				
				// 
				// FLUSH TESTER
				//
				
				//
				// STRAIGHT TESTER
				//
				
				//
				// THREE OF A KIND TESTER
				//
				
				//
				// TWO PAIR TESTER
				//
				
				//
				// PAIR TESTER
				//
				
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
	
