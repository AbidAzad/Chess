package components;

import util.Parser;
/**
 * ChessPiece class inherited by all other subclass ChessPieces
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 *
 */
public class ChessPiece {
	/**
	 * ChessPiece X position
	 */
	public int x;
	/**
	 * ChessPiece Y position
	 */
	public int y;
	/**
	 * ChessPiece player color 1 or -1
	 */
	public int playerColor;
	/**
	 * list of vertical moves that a ChessPiece can make
	 */
	public int[] vmoves;
	/**
	 * list of horrizontal moves that a ChessPiece can make
	 */
	public int[] hmoves;
	/**
	 * ally string representation for a ChessPiece
	 */
	public String ally;
	/**
	 * enemy string representation for a ChessPiece
	 */
	public String enemy;
	/**
	 * name of the ChessPiece
	 */
	public String name;
	protected int totalMoves;
	protected final int BOARD_SIZE = 8;
	
	
	/**
	 * Initializes ChessPiece basic information
	 * @param x int position
	 * @param y int position
	 * @param type ChessPiece category
	 * @param player 1 for PLAYER_1 or -1 for PLAYER_2
	 * @param totalMoves int
	 * @author diegocastellanos
	 */
	public ChessPiece(int x, int y, char type, int player, int totalMoves) {
		this.x = x;
		this.y = y;
		this.name = (player == 1)? "w"+type : "b"+type;
		this.ally = (player == 1)? "w": "b";
		this.enemy = (player == 1)? "b": "w";
		this.totalMoves = totalMoves;
		this.playerColor = player;
	}
	
	/**
	 * gets a string array of all moves that a piece can make
	 * @param board String[][] gameboard
	 * @return String[] posible moves list
	 * @author diegocastellanos
	 */
	public String[] getValidMoves(String[][] board) {
		String[] pos = new String[totalMoves];
		int k = 0;
		for(int i = 0; i < this.totalMoves; i++) {
			int tempX = this.x + this.hmoves[i];
			int tempY = this.y + this.vmoves[i];
			if( (tempX>-1 && tempX < BOARD_SIZE) && (tempY>-1 && tempY<BOARD_SIZE) ){
				String posible_move = Parser.translate(tempX,tempY);
				pos[k] = posible_move;
				k++;
			}
		}
		String[] statement = new String[k];
		for(int i = 0; i < statement.length;i++) {
			statement[i] = pos[i];
		}
		return statement;
	}
	
	/**
	 * Gets a string array of all moves that a special piece can make. Special piece are those which can make more than one move in a single directions. 
	 * @param maxMoves int max move number
	 * @param scalar int scalar for special pieces
	 * @param board String[][] gameboard
	 * @return String[] posible moves
	 * @author diegocastellanos
	 */
	public String[] getValidMoves(int maxMoves, int scalar, String[][] board) {
		String[] posible = new String[maxMoves];
		int k = 0;
		for(int i = 0; i < this.totalMoves; i++) {
			for(int scale = 1; scale < scalar; scale++) {
				int tempX = this.x + this.hmoves[i]*scale;
				int tempY = this.y + this.vmoves[i]*scale;
				if( (tempX>-1 && tempX < BOARD_SIZE) && (tempY>-1 && tempY<BOARD_SIZE) ) {
					String posible_move = Parser.translate(tempX,tempY);
					posible[k] = posible_move;
					k++;
				}
			}
		}
		if(k != maxMoves) {
			return Parser.adjustArraySize(posible, k);
		}
		return posible;
	}
	
	/**
	 * Used to get the in[] position of a piece
	 * @return int[]
	 * @author diegocastellanos
	 */
	public int[] getPosition() {
		int[] pos = {this.x, this.y};
		return pos;
	}
	/**
	 * Used to get the String position of a piece
	 * @return String
	 * @author diegocastellanos
	 */
	public String stringPosition() {
		return Parser.translate(this.x, this.y);
	}
	/**
	 * returns the String representation of a piece formated specifically for the board.
	 * @return String
	 * @author diegocastellanos
	 */
	public String toString() {
		return name+" ";
	}
	/**
	 * returns the String name of a piece and used for more general cases than the toString method.
	 * @return String
	 * @author diegocastellanos
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Method used to decide whether a piece can capture another piece
	 * @param target position String
	 * @param board String[][] gameboard
	 * @return boolean can a piece capture target
	 * @author diegocastellanos
	 */
	public boolean canCapture(String target, String[][] board) {
		return canMoveTo(target, board);
	}
	/**
	 * Overloaded method used to decide whether a special piece can capture another piece
	 * @param scalar for special pieces
	 * @param end target position
	 * @param board String[][]
	 * @return boolean tru or false
	 * @author diegocastellanos
	 */
	public boolean canCapture(int scalar, String end, String[][] board) {
		return canMoveTo(scalar,end,board);
	}
	/**
	 * Overloaded method deciding whether a special piece can move to a position on the board.
	 * @param scalar for special pieces
	 * @param end target position
	 * @param board String[][] gamboard 
	 * @return boolean true or false
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(int scalar, String end,String[][] board) {
		for(int i = 0; i < this.totalMoves; i++) {
			for(int scale = 1; scale < scalar; scale++) {
				int tempX = this.x + this.hmoves[i]*scale;
				int tempY = this.y + this.vmoves[i]*scale;
				if( (tempX>-1 && tempX < BOARD_SIZE) && (tempY>-1 && tempY<BOARD_SIZE) ) {
					String spot = board[tempX][tempY];
					if(!spot.isBlank() && spot.contains(this.ally)) {
						break;
					}
					String posible_move = Parser.translate(tempX,tempY);
					if(posible_move.equals(end))
						return true;
					if(!spot.isBlank() && spot.contains(this.enemy) && !posible_move.equals(end))
						break;
				}
			}
		}
		return false;
	}
	/**
	 * Method used to decide if a piece is allowed to move to a specific position on the board.
	 * @param target position
	 * @param board String[][] gamboard
	 * @return boolean true or false
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(String target, String[][] board) {
		for(int i = 0; i < this.totalMoves; i++) {
			int tempX = this.x + this.hmoves[i];
			int tempY = this.y + this.vmoves[i];
			if( (tempX>-1 && tempX < BOARD_SIZE) && (tempY>-1 && tempY<BOARD_SIZE) ){
				String spot = board[tempX][tempY];
				if(!spot.isBlank() && spot.contains(this.ally)) {
					continue;
				}
				String posible_move = Parser.translate(tempX,tempY);
				if(posible_move.equals(target))
					return true;
			}
		}
		return false;
	}

	/**
	 * gets the number of moves that a piece can make
	 * @return int total moves
	 * @author diegocastellanos
	 */
	public int getTotalMoves() {
		return this.totalMoves;
	}
	/**
	 * updates the internal position of a piece
	 * @param dest int[] destination
	 * @author diegocastellanos
	 */
	public void updatePosition(int[] dest) {
		this.x = dest[0];
		this.y = dest[1];
	}




}
