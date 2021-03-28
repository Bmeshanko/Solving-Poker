/**
 * Game.java - In Development.
 * Attempts to run an actual Poker game.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		
		// Initialize the Players into an ArrayList.
		
		Player pOne = new Player("AI 1", false);
		Player pTwo = new Player("AI 2", false);
		Player pThree = new Player("AI 3", false);
		Player pFour = new Player("AI 4", false);
		Player pFive = new Player("AI 5", false);
		Player benjamin628 = new Player("Benjamin628", true);
		
		PlayerList players = new PlayerList();
		
		players.add(pOne);
		players.add(pTwo);
		players.add(pThree);
		players.add(pFour);
		players.add(pFive);
		players.add(benjamin628);

		int handsPlayed = 0;
		
		while (players.size() > 1) { // Game should run until there is one player left with chips.
			
			handsPlayed++;
			
			// Initialize the Deck that is used for the specific hand.
			
			Deck deck = new Deck();
			players.initializeHand(deck);
			
			// Decide who is Big Blind and Little Blind
			
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setLittleBlind(false);
				players.get(i).setBigBlind(false);
			}
			
			int littleBlind = handsPlayed % players.size();
			int bigBlind = handsPlayed % players.size() + 1;
			
			players.get(littleBlind).setLittleBlind(true);
			
			if (bigBlind == players.size()) {
				players.get(0).setBigBlind(true);
			} else {
				players.get(bigBlind).setBigBlind(true);
			}
			
			int firstBetter = 0;
			
			if (bigBlind != players.size() - 1) {
				firstBetter = bigBlind + 1;
			}
			
			PlayerList preFlopFolders = players.preFlopBets(firstBetter);
			
			PlayerList flopFolders = players.flopBets(littleBlind);
			
			PlayerList turnFolders = players.turnBets(littleBlind);
			
			PlayerList riverFolders = players.riverBets(littleBlind);
			
			
			
			PlayerList winners = players.evaluateWinner();
			
			for (int i = 0; i < winners.size(); i++) {
				winners.get(i).winPot(players.getPotSize(), winners.size());
				System.out.println(winners.get(i).getName() + " won the hand and won " + players.getPotSize() + " chips!");
			}
			System.out.println("---------------------------------------------");
			
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getChips() == 0) {
					players.remove(i);
				}
			}
			
		} // End of the Loop.
		
		System.out.println(pOne.getChips());
		System.out.println(pTwo.getChips());
		System.out.println(pThree.getChips());
		System.out.println(pFour.getChips());
	}
}
	