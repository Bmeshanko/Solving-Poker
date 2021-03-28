import java.io.*;
/* 
 * Main.java - In development. Currently being used to simulate hundreds of thousands of Poker
 * Hands, then print out all the winners/losers.
 */
public class Main {
	public static void main(String[] args) {
		Player pOne = new Player("Player 1", false);
		Player pTwo = new Player("Player 2", false);
		Player pThree = new Player("Player 3", false);
		Player pFour = new Player("Player 4", false);
		Player pFive = new Player("Player 5", false);
		Player benjamin628 = new Player("Player 6", true);
		
		PlayerList players = new PlayerList();
		
		players.add(pOne);
		players.add(pTwo);
		players.add(pThree);
		players.add(pFour);
		players.add(pFive);
		players.add(benjamin628);
		
		int handsPlayed = 0;

		PrintWriter pw = null;
		
		try {
			
			String allWinners = "Winners:\t";
			String allLosers = "Losers:\t";
			
			pw = new PrintWriter("poker.txt");

			int winlen = 0;
			int loslen = 0;
			
			while (handsPlayed++ < 250000) {
				
				Deck deck = new Deck();
				
				players.initializeHand(deck);
				
				PlayerList winners = players.evaluateWinner();
				
				PlayerList losers = new PlayerList();
				
				
				
				for (int i = 0; i < players.size(); i++) {
					boolean flag = true;
					for (int j = 0; j < winners.size(); j++) {
						if (players.get(i).equals(winners.get(j))) {
							flag = false;
						}
					}
					
					if (flag) {
						losers.add(players.get(i));
					}
				}
				
				
				for (int i = 0; i < winners.size(); i++) {
					allWinners += winners.get(i).suitlessString() + "\t";
					winlen++;
				}
				
				if (winlen > 16000) {
					pw.println(allWinners);
					winlen = 0;
					allWinners = "Winners:\t";
				}
				
				
				for (int i = 0; i < losers.size(); i++) {
					allLosers += losers.get(i).suitlessString() + "\t";
					loslen++;
				}
				
				if (loslen > 16000) {
					pw.println(allLosers);
					loslen = 0;
					allLosers = "Losers:\t";
				}
			}
			pw.println(allWinners);
			pw.println(allLosers);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}