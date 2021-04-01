/**
 * Game.java - In Development.
 * Attempts to run an actual Poker game.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		
		// Initialize the Players into an ArrayList.
		
		Player pOne = new Player("Player 1", false);
		Player pTwo = new Player("Player 2", false);
		Player pThree = new Player("Player 3", false);
		Player pFour = new Player("Player 4", false);
		Player pFive = new Player("Player 5", false);
		Player pSix = new Player("Player 6", true);
		
		PlayerList players = new PlayerList();
		
		players.add(pOne);
		players.add(pTwo);
		players.add(pThree);
		players.add(pFour);
		players.add(pFive);
		players.add(pSix);

		int handsPlayed = 0;
		
		while (handsPlayed < 1) { // Game should run until there is one player left with chips.
			
			handsPlayed++;
			
			Deck deck = new Deck(); // We need a new Deck for each hand.
			
			players.initializeHand(deck); // Initialize the hand.
			
			int littleBlind = (handsPlayed - 1) % players.size(); 
			int bigBlind = handsPlayed % players.size();
			int firstBetter = (handsPlayed + 1) % players.size();
			
			
		} // End of the Loop.
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getChips());
		}
	}
}
	