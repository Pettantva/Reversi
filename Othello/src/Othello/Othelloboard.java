package Othello;

import java.util.ArrayList;

public class Othelloboard {
	private int[][] board;
	private int playersTurn; // vit = (-1), svart = (1)
	private boolean[][] legalMoves; // om det går att lägga på denna plats för
									// nuvarande spelare
	private int blackPoints;
	private int whitePoints;
	private int counter;
	private int legalMovesCounter;

	public Othelloboard() {
		this.board = new int[8][8]; // nytt tomt bord	
		this.playersTurn = 1; // svart börjar
		this.legalMoves = new boolean[8][8]; // sätter upp legalmoves
		setBoard();
		this.legalMovesCounter = 0;
		setLegalMoves();
	}

	// sätter upp bordet. Alla tomma förutom de två i mitten.
	private void setBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j] = 0;
			}
		}
		this.board[3][3] = -1;
		this.board[4][4] = -1;
		this.board[3][4] = 1;
		this.board[4][3] = 1;
		this.counter = 4;
		this.blackPoints = 2;
		this.whitePoints = 2;

	}

	// sätter legalMoves i början för svart spelare
	private void setLegalMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.legalMoves[i][j] = false;
			}
		}
		updateLegalMoves(); // ska göra så svart kan lägga på 4 ställen i detta
							// fall.
	}

	private void changeTurn() {
		if (playersTurn == 1) {
			playersTurn = -1;
		} else {
			playersTurn = 1;
		}
	}

	// metod som lägger ny bit
	public boolean put(int i, int j) {
		//if (this.legalMoves[i][j]) {
		if (true) {
			this.board[i][j] = this.playersTurn;
			updateBoard(i, j);
			changeTurn();
			//updateLegalMoves();
			updatePoints();
			this.counter++;
			return true;
		} else {
			return false;
		}
	}

	private void updateBoard(int i, int j) {
		int[][] updatedBoard = this.board;
		if(i != 0 && i != 1){ //upp
			if(this.board[i-1][j] != 0){
				int a = i - 1;
				boolean cont = true;
				while(a >= 0 && cont){
					int val = this.board[a][j];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateCol(updatedBoard, j, a, i);
					}
					a--;
				}
			}
		}
		if(i != 7 && i != 6){ //ner
			if(this.board[i+1][j] != 0){
				int a = i + 1;
				boolean cont = true;
				while(a <= 7 && cont){
					int val = this.board[a][j];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateCol(updatedBoard, j, i, a);
					}
					a++;
				}
			}
		}
		if(j != 0 && j != 1){ // vänster
			if(this.board[i][j-1] != 0){
				int a = j - 1;
				boolean cont = true;
				while(a >= 0 && cont){
					int val = this.board[i][a];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateRow(updatedBoard, i, a, j);
					}
					a--;
				}
			}
		}
		if(j != 7 && j != 6){ //höger
			if(this.board[i][j+1] != 0){
				int a = j + 1;
				boolean cont = true;
				while(a <= 7 && cont){
					int val = this.board[i][a];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateRow(updatedBoard, i, j, a);
					}
					a++;
				}
			}	
		}
		if(j != 7 && j != 6 && i != 7 && i != 6){ // nerhöger
			if(this.board[i+1][j+1] != 0){
				int a = i + 1;
				int b = j + 1;
				int f = 1;
				boolean cont = true;
				while(a <= 7 && b <= 7 && cont){
					int val = this.board[a][b];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateDiagHlLr(updatedBoard, i, j, f);
					}
					f++;
					a++;
					b++;
				}
			}
		}
		if(j != 0 && j != 1 && i != 0 && i != 1){ // uppvänster
			if(this.board[i-1][j-1] != 0){
				int a = i - 1;
				int b = j - 1;
				int f = 1;
				boolean cont = true;
				while(a >= 0 && b >= 0 && cont){
					int val = this.board[a][b];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateDiagHlLr(updatedBoard, a, b, f);
					}
					f++;
					a--;
					b--;
				}
			}
		}
		if(j != 7 && j != 6 && i != 0 && i != 1){ // upphöger
			if(this.board[i-1][j+1] != 0){
				int a = i - 1;
				int b = j + 1;
				int f = 1;
				boolean cont = true;
				while(a >= 0 && b <= 7 && cont){
					int val = this.board[a][b];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateDiagHrLl(updatedBoard, a, b, f);
					}
					f++;
					a--;
					b++;
				}
			}
		}
		if(j != 1 && j != 0 && i != 7 && i != 6){ // nervänster
			if(this.board[i+1][j-1] != 0){
				int a = i + 1;
				int b = j - 1;
				int f = 1;
				boolean cont = true;
				while(a <= 7 && b >= 0 && cont){
					int val = this.board[a][b];
					if(val == 0){
						cont = false;
					}
					else if(val == this.playersTurn){
						updatedBoard = updateDiagHrLl(updatedBoard, i, j, f);
					}
					f++;
					a++;
					b--;
				}
			}
		}
		this.board = updatedBoard;
	}

	// uppdaterar kolumnen j från rad a (toppen) till rad b(botten)
	private int[][] updateCol(int[][] uB, int j, int a, int b) {
		int[][] uBoard = uB;
		for (int i = a; i <= b; i++) {
			uBoard[i][j] = this.playersTurn;
		}
		return uBoard;
	}

	// uppdaterar rad i från kolumn a(vänster) till kolumn b(höger)
	private int[][] updateRow(int[][] uB, int i, int a, int b) {
		int[][] uBoard = uB;
		for (int j = a; j <= b; j++) {
			uBoard[i][j] = this.playersTurn;
		}
		return uBoard;
	}

	// uppdaterar diagonalen från uppe vänster till nere höger, från i,j, plus f steg nertill höger
	private int[][] updateDiagHlLr(int[][] uB, int i, int j, int f) {
		int[][] uBoard = uB;
		int c = j;
		for (int r = i; r <= i + f; r++) {
			uBoard[r][c] = this.playersTurn;
			c++;
		}
		return uBoard;
	}

	// uppdaterar diagonalen från uppe höger till nere vänster, från i,j plus f steg ner till vänster
	private int[][] updateDiagHrLl(int[][] uB, int i, int j, int f) {
		int[][] uBoard = uB;
		int c = j;
		for (int r = i; r <= i + f; r++) {
			uBoard[r][c] = this.playersTurn;
			c--;
		}
		return uBoard;
	}
	
	private void updatePoints(){
		int white = 0;
		int black = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.board[i][j] == 1){
					black++;
				}
				else if(this.board[i][j] == -1){
					white++;
				}
			}
		}
		this.blackPoints = black;
		this.whitePoints = white;
	}
	
	public String playersTurn(){
		if(playersTurn == 1){
			return "Black";
		}
		else{
			return "White";
		}
	}
	
	public boolean end(){
		if(this.counter > 63 || legalMovesCounter == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String winner(){
		if(this.blackPoints > this.whitePoints){
			return "Black";
		}
		else if(this.blackPoints < this.whitePoints){
			return "White";
		}
		else{
			return null;
		}
	}

	// metod som uppdaterar legal moves

	private ArrayList CheckNeighbors(int x, int y) {
		ArrayList neighbors = new ArrayList<Integer>();
		for (int xn = x - 1; xn <= x - 2; xn++) { 
			if (xn >= 0 && xn <= 7) {
				for (int yn = y + 1; yn <= y + 1; yn++) { 
					if (yn >= 0 && yn <= 7) {
						if (xn != x && yn != y && Math.abs(board[y][x]) > 0) {
							neighbors.add(10 * x + y); 
						}								
					}
				}
			}
		}
		return neighbors;
	}

	public ArrayList LegalMoves() {
		ArrayList legal = new ArrayList<Integer>();
		int count = 0;
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				int color = playersTurn;
				if (board[y][x] != 0) {
					ArrayList neighbors = CheckNeighbors(x, y);
					for (int i = 0; i < neighbors.size(); i++) {
						int cordinate = (int) neighbors.get(i);
						int yn = cordinate % 10;
						int xn = (cordinate - yn) / 10;
						int xdir = xn - x;
						int ydir = yn - y;
						while (board[yn][xn] != color && board[yn][xn] != 0) {
							count++;
							xn = xn + xdir;
							yn = yn + ydir;
							if (xn < 0 && xn > 7 && yn < 0 && yn > 7) {
								break;
							}
						}
						if (board[yn][xn] != color && count > 0) {
							legal.add(xn);
							count = 0;
							break;
						}
					}
				}
			}
		}
		return legal;
	}

	private void updateLegalMoves() {
		ArrayList legal = LegalMoves();
		this.legalMovesCounter = 0;
		for(int i = 0; i < legal.size() ; i++){
			int cordinate = (int) legal.get(i);
			int y = cordinate % 10;
			int x = (cordinate - y) / 10;
			legalMoves[y][x] = true;
			this.legalMovesCounter++;
		}
	}
	public void printBoard(){
		System.out.print("  ");
		for(int i = 0; i < 8 ; i++){
			System.out.print(i + " ");
		}
		for(int r = 0; r <= 7 ; r++){
			System.out.println();
			System.out.print(r +" ");
			for(int c = 0; c <= 7; c++){
				if(board[r][c] == -1){
					System.out.print("\u25E6" + " ");
				}
				else if(board[r][c] == 1){
					System.out.print("\u2022" + " ");
				}
				else{
					if(legalMoves[r][c]){
						System.out.print("x" +" ");
					}
					else{
						System.out.print("-" +" ");						
					}

				}
			}
		}
	}

	// metod som räknar ut hur nya matrisen ser ut vid

}