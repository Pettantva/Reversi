package Othello;

import java.util.ArrayList;

public class Minimax {
	private int depth;
	public Minimax(){
		
	}
	public int alphabeta(){
		int[] lista = new int[2];
		lista[0] = 0;
		lista[0] = 0;
		ArrayList legalmoves = board.legalMoves();
		for(int i = 0; i < legalmoves.size(); i++){
			int value = alphaBeta(legalmoves.get(i));
			if(lista[1] < value){
				lista[1] = value;
				lista[0] = i;
			}
		}
		return (int)legalmoves.get(lista[0]);
	}
	private int alphaBeta(){
		
	}

}
