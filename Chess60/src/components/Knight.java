package components;
/**
 * Extends from ChessPiece to generate a Knight object
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class Knight extends ChessPiece{
	private final static int MOVES = 8;
	/**
	 * Initializes a Knight instance object
	 * @param x initial position
	 * @param y initial position
	 * @param type of ChessPiece
	 * @param player 1 for white and -1 for black
	 */
	public Knight(int x,int y, char type,int player) {
		super(x,y, type, player, MOVES);
		super.hmoves = new int[]{-2,-1, 1, 2, 2, 1,-1,-2};	// up,right,down,left
		super.vmoves = new int[]{ 1, 2, 2, 1,-1,-2,-2,-1};	// up,right,down,left
	}
	/**
	 * decides whether the king is allowed to move or not
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canMoveTo(String target, String[][] board) {
		return super.canMoveTo(target, board);
	}
	/**
	 * decides whether the king is allowed to move or not
	 * @param target String position
	 * @param board String[][] gameboard
	 * @return boolean
	 * @author diegocastellanos
	 */
	public boolean canCapture(String target, String[][] board) {
		return super.canCapture(target, board);
	}
	

}
