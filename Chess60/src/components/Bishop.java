package components;
/**
 * Extends from ChessPiece to generate Bishop piece object.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Bishop extends ChessPiece{
	private static final int MOVES = 4;
	private static final int SCALAR = 8;
	private static final int MAX_MOVES = 13;
	/**
	 * Initialized a Bishop instance
	 * @param x initial position
	 * @param y initial position
	 * @param type of ChessPiece
	 * @param player 1 or -1 for white or black
	 * @author diegocastellanos
	 */
	public Bishop(int x,int y, char type,int player) {
		super(x,y, type, player, MOVES);
		super.hmoves = new int[]{-1,1, 1,-1};
		super.vmoves = new int[]{ 1,1,-1,-1};
	}
	/**
	 * Calls on superclass canMoveTo method for special piece to decide whether a bishop can move to a target position or not
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(String target, String[][] board) {
		return super.canMoveTo(SCALAR, target, board);
	}
	/**
	 * Calls on superclass canCapture method for special piece to decide whether a bishop can capture a piece at a target position or not
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canCapture(String target, String[][] board) {
		return super.canCapture(SCALAR, target, board);
	}
	/**
	 * Used for getting a String[] of all possible moves a bishop can make from its current position.
	 * @param board String[][] gameboard
	 * @return String[]
	 * @author diegocastellanos
	 */
	public String[] getValidMoves(String[][] board) {
		return super.getValidMoves(MAX_MOVES, SCALAR, board);
	}
}
