package components;

import util.Parser;
/**
 * Extends from ChessPiece to generate Pawn piece object.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Pawn extends ChessPiece{
	/**
	 * Pawn horizontal capture modifier
	 */
	public int[] hCapture;
	/**
	 * Pawn vertical capture modifier
	 */
	public int[] vCapture;
	/**
	 * used for Pawn initial move tracking
	 */
	public boolean firstMoveDone = false;
	/**
	 * used of passant tracking
	 */
	public boolean passantable = true;
	/**
	 * special case purposes
	 */
	public String test;
	private static final int MOVES = 2;
	private static final int MAX_INPUTS = 3;
	/**
	 * Initializes a Pawn instance object
	 * @param x initial position
	 * @param y initial position
	 * @param type of ChessPiece
	 * @param player 1 for white and -1 for black
	 */
	public Pawn(int x,int y, char type, int player) {
		super(x,y, type, player, MOVES);
		super.hmoves = new int[]{-1*this.playerColor, -2*this.playerColor};
		super.vmoves = new int[]{ 0, 0};
		this.hCapture = new int[]{-1*this.playerColor,-1*this.playerColor};
		this.vCapture = new int[]{ 1*this.playerColor,-1*this.playerColor};
		
	}
	/**
	 * Overrides the ChessPiece method to decide whether a piece can move to a target spot or not
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(String target, String[][] board) {
		for(int i = 0; i < this.totalMoves; i++) {
			int tempX = this.x + this.hmoves[i];
			int tempY = this.y + this.vmoves[i];
			if( (tempX > -1 && tempX < BOARD_SIZE) && (tempY > -1 && tempY < BOARD_SIZE) ) {
				String spot = board[tempX][tempY];
				if(!spot.isBlank() && (spot.contains(this.ally)|| spot.contains(this.enemy))) {
					break;
				}
				String posible_move = Parser.translate(tempX,tempY);
				if(posible_move.equals(target))
					return true;
			}
		}
		return false;
	}
	/**
	 * Overrides superclass method to decide whether a pawn can capture an enemy piece in the target position
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canCapture(String target, String[][] board) {

		int captureMoves = 2;
		test = board[x][y];
		for(int i = 0; i < captureMoves; i++) {
			int tempX = this.x + hCapture[i];
			int tempY = this.y + vCapture[i];
			if( (tempX > -1 && tempX < BOARD_SIZE) && (tempY > -1 && tempY < BOARD_SIZE) ) {
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
	 * returns String[] of moves that a pawn is allowed to make
	 * @return String[]
	 * @author diegocastellanos
	 */
	public String[] getValidMoves() {
		String[] posible = new String[this.totalMoves];
		
		for(int i = 0; i < this.totalMoves; i++) {
			int tempX = this.x + this.hmoves[i];
			int tempY = this.y + this.vmoves[i];
			if( (tempX > -1 && tempX < BOARD_SIZE) && (tempY > -1 && tempY < BOARD_SIZE) ) {
				String posible_move = Parser.translate(tempX,tempY);
				posible[i] = posible_move;
			}
		}
		
		return posible;
		
	}
	/**
	 * Used whenever a pawn piece is moved to check whether it can be promoted and to reduce the moves that a pawn is allowed to make. 
	 * returns char ChessPiece type:
	 * 1. DEAFAULT_CHAR 'Q' if it can promote and not other type was specified
	 * 2. INVALID_CHAR 'X' if pawn cannot promote or promotion type was invalid.
	 * 3. specified piece type if specified and pawn in being promoted.s
	 * @param input String[]
	 * @return type of ChessPiece
	 * @author diegocastellanos
	 */
	public char wasMoved(String[] input) {
		super.totalMoves = 1;
		boolean canPromote = false;
		boolean defaultPromotion = input.length < MAX_INPUTS;		
		if(this.playerColor == Parser.PLAYER_1) {
			canPromote = Parser.canPromoteWhite(this.getPosition());
		}else if(this.playerColor == Parser.PLAYER_2) {
			canPromote = Parser.canPromoteBlack(this.getPosition());
		}
		if(canPromote && defaultPromotion) {
			return Parser.DEFAULT_CHAR;
		}else if(canPromote && !defaultPromotion) {
			return input[2].charAt(0);
		}
		
		return Parser.INVALID_CHAR;
	}
	/**
	 * reverts totalMove count back to 2
	 * @author AbidAzad
	 */
	public void revert() {
		if(!firstMoveDone)
			super.totalMoves = 2;
	}
	/**
	 * Used to keep track of passant posibility.
	 * @author AbidAzad
	 */
	public void passantPassed() {
		if(firstMoveDone) {
			passantable = false;
		}
	}

}
