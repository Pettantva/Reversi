package Othello;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);  
		Othelloboard board = new Othelloboard();
		board.printBoard();
		
		boolean game = true;
		while(game){
			if(!board.finnished()){
				String winner = board.winner();
				System.out.println("Game is over, winner is: " + winner);
				if(winner != null){
					game = false;
				}
			}
			else{
				System.out.println();
				System.out.println(board.playersTurn() + " is up");
				System.out.println("Where do you want to put the next?");
				System.out.print("Row: ");
				int i = scan.nextInt();
				System.out.print("Column: ");
				int j = scan.nextInt();
				System.out.println();
				boolean legal = board.put(i, j);
				if(legal){
					board.printBoard();
				}
				else{
					System.out.println("Illegal move, try again");
				}
			}
		}
	}
}
