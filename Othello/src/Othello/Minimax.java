package Othello;

import java.util.ArrayList;

public class Minimax {
	private int depth;
	private boolean maximize;
	private Othelloboard board;
	private int alpha;
	private int beta;
	
	public Minimax(){
		
	}
	
	public int alphabeta(){
		depth = 3;
		maximize = true;
		alpha = -1000;
		beta = 1000;
		int[] lista = new int[2];
		lista[0] = 0;
		lista[0] = 0;
		ArrayList legalmoves = board.LegalMoves();
		for(int i = 0; i < legalmoves.size(); i++){
			int value = alphaBeta(depth, (int)legalmoves.get(i), board, alpha, beta, maximize );
			if(lista[1] < value){
				lista[1] = value;
				lista[0] = i;
			}
		}
		return (int)legalmoves.get(lista[0]);
	}
	private int alphaBeta(int depth, int coordinate, Othelloboard board, int alpha, int beta, boolean maximize ){
		int y = coordinate % 10;
		int x = (coordinate - y) / 10;
		board.put(y, x);
		if(depth == 0){
			return board.points();   // metod som räknar ut antalet poäng för placerad spelpjäs
		}
		ArrayList legalmoves = board.LegalMoves();
		if(maximize){
			int value = -1000;
			for(int i = 0; i < legalmoves.size(); i++){
				value = Math.max(-1000, alphaBeta(depth -1, (int)legalmoves.get(i), board, alpha, beta, false));
				alpha = Math.max(alpha, value);
				if(beta <= alpha){
					break;
				}
				return value;
			}
		}
		else{
			int value = 1000;
			for(int j = 0; j < legalmoves.size(); j++){
				value = Math.min(-1000, alphaBeta(depth -1, (int)legalmoves.get(j), board, alpha, beta, true));
				beta = Math.min(beta, value);
				if(beta <= alpha){
					break;
				}
				return value;
			}
		}
	}

}
