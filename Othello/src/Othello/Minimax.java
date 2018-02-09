package Othello;

import java.util.ArrayList;

public class Minimax {
	private int depth;
	private boolean maximize;
	private Othelloboard board;
	private int alpha;
	private int beta;
	
<<<<<<< HEAD
	public Minimax(Othelloboard board){
		this.board = board;
=======
	public Minimax(Othelloboard board, int depth, int alpha, int beta, boolean maximize){
		
>>>>>>> 3d8aecb5793da3fc9fc71c65485e29b9ad7020a9
	}
	
	public int alphabeta(){
		depth = 1;
		maximize = true;
		alpha = -1000;
		beta = 1000;
		int[] lista = new int[2];
		lista[0] = 0;
		lista[0] = 0;
		ArrayList legalmoves = this.board.LegalMoves();
		for(int i = 0; i < legalmoves.size(); i++){
			int value = alphaBeta(depth, (int)legalmoves.get(i), board, alpha, beta, maximize );
			if(lista[1] < value){
				lista[1] = value;
				lista[0] = i;
			}
		}
		return 0;
		//return (int)legalmoves.get(lista[0]);
		
	}
	private int alphaBeta(int depth, int coordinate, Othelloboard board, int alpha, int beta, boolean maximize ){
		int y = coordinate % 10;
		int x = (coordinate - y) / 10;
		board.put(y, x);
		if(depth == 0){
			if(board.playersTurn() == "White"){
				return board.whiteScore();
			}
			else{
				return board.blackScore();
			}
		}
		ArrayList legalmoves = board.LegalMoves();
		if(maximize){
			int value = -1000;
			for(int i = 0; i < legalmoves.size(); i++){
				value = Math.max(value, alphaBeta(depth -1, (int)legalmoves.get(i), board, alpha, beta, false));
				alpha = Math.max(alpha, value);
				if(beta <= alpha){
					break;
				}
				return value;
			}
			return value;
		}
		else{
			int value = 1000;
			for(int j = 0; j < legalmoves.size(); j++){
				value = Math.min(value, alphaBeta(depth -1, (int)legalmoves.get(j), board, alpha, beta, true));
				beta = Math.min(beta, value);
				if(beta <= alpha){
					break;
				}
				return value;
			}
			return value;
		}
<<<<<<< HEAD
		
=======
		return 0;
>>>>>>> 3d8aecb5793da3fc9fc71c65485e29b9ad7020a9
	}

}
