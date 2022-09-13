package components;
/**
 * Extends from ChessPiece to generate Rook piece object.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Rook extends ChessPiece{
	private static final int MOVES = 4;
	private static final int SCALAR = 8;
	private static final int MAX_MOVES = 14;
	/**
	 * Used for castling tracking
	 */
	public boolean hasntMoved = true;
	/**
	 * Initializes a Rook instance object
	 * @param x initial position
	 * @param y initial position
	 * @param type of ChessPiece
	 * @param player 1 for white and -1 for black
	 */
	public Rook(int x,int y, char type, int player) {
		super(x,y, type, player, MOVES);
		super.hmoves = new int[]{-1,0,1, 0};	// up,right,down,left
		super.vmoves = new int[]{ 0,1,0,-1};	// up,right,down,left
	}
	/**
	 * calls on the super class canMoveTo method for special pieces, to decide whether a piece can move to a target position or not.
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(String target, String[][] board) {
		return super.canMoveTo(SCALAR, target, board);
	}
	/**
	 * calls on the super class canCapture method for special pieces, to decide whether a piece can capture a piece in the target position or not.
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canCapture(String target, String[][] board) {
		return super.canCapture(SCALAR, target, board);
	}
	/**
	 * Used for getting a String[] of all the possible moves the rook piece can make from its current position
	 * @param board Stringp[][] board
	 * @return String[]
	 * @author diegocastellanos
	 */
	public String[] getValidMoves(String[][] board) {
		return super.getValidMoves(MAX_MOVES, SCALAR, board);
	}
	/**
	 * Used for tracking castling rook ability
	 * @author AbidAzad
	 */
	public void moved() {
		hasntMoved = false;
	}
}
