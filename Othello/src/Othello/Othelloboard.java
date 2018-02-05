package Othello;

import java.util.ArrayList;

public class Othelloboard {
	private int[][] board;
	private int playersTurn; // vit = (-1), svart = (1)
	private boolean[][] legalMoves; // om det går att lägga på denna plats för
									// nuvarande spelare
	private int blackPoints;
	private int whitePoints;

	public Othelloboard() {
		this.board = new int[8][8]; // nytt tomt bord
		setBoard();
		this.playersTurn = 1; // svart börjar
		this.legalMoves = new boolean[8][8]; // sätter upp legalmoves
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

	// metod som lägger ny bit
	public boolean put(int i, int j) {
		if (this.legalMoves[i][j] == true) {
			updateBoard(i, j);
			updateLegalMoves();
			return true;
		} else {
			return false;
		}
	}

	private void updateBoard(int i, int j) {
		int[][] updatedBoard = this.board;
		for (int a = 7; a > j; j--) {
			// if(board[i][a] = )
		}

		this.board = updatedBoard;
	}

	// uppdaterar kolumnen j från rad a till rad b
	private int[][] updateCol(int[][] uB, int j, int a, int b) {
		int[][] uBoard = uB;
		for (int i = a; i <= b; i++) {
			uBoard[i][j] = this.playersTurn;
		}
		return uBoard;
	}

	// uppdaterar rad i från kolumn a till kolumn b
	private int[][] updateRow(int[][] uB, int i, int a, int b) {
		int[][] uBoard = uB;
		for (int j = a; j <= b; j++) {
			uBoard[i][j] = this.playersTurn;
		}
		return uBoard;
	}

	// uppdaterar diagonalen från uppe vänster till nere höger ia,ja till ib,jb
	private int[][] updateDiagHlLr(int[][] uB, int i, int j, int f) {
		int[][] uBoard = uB;
		for (int r = i; r <= i + f; r++) {
			int c = j;
			uBoard[r][c] = this.playersTurn;
			c++;
		}
		return uBoard;
	}

	// uppdaterar diagonalen från uppe höger till nere vänster ia,ja till ib,jb
	private int[][] updateDiagHrLl(int[][] uB, int ia, int ib, int ja, int jb) {
		int[][] uBoard = uB;
		return uBoard;
	}

	// metod som uppdaterar legal moves

	private ArrayList CheckNeighbors(int x, int y) {
		ArrayList neighbors = new ArrayList<Integer>();
		for (int xn = x - 1; xn <= x - 2; xn++) { // går igenom x-kordinaterna
			if (xn >= 0 && xn <= 7) {
				for (int yn = y + 1; yn <= y + 1; yn++) { // går igenom
															// y_kordinaterna
					if (yn >= 0 && yn <= 7) {
						if (xn != x && yn != y && Math.abs(board[y][x]) > 0) {
							neighbors.add(10 * x + y); // lägger till
														// kordinaterna i en
														// lista
						}
					}
				}
			}
		}
		return neighbors;
	}

	private ArrayList LegalMoves() {
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
		for(int i = 0; i < legal.size() ; i++){
			int cordinate = (int) legal.get(i);
			int y = cordinate % 10;
			int x = (cordinate - y) / 10;
			legalMoves[y][x] = true;
		}

	}

	// metod som räknar ut hur nya matrisen ser ut vid

}