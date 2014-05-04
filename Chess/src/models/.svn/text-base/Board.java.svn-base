package models;

import java.util.ArrayList;
import java.util.Stack;

import com.example.chess.R;

public class Board {

	static Board board;
	private Pieces[][] boardPieces = new Pieces[8][8];
	private ArrayList<Pieces> pieces = new ArrayList<Pieces>(32); // create 16
																	// piece for
																	// each
																	// player
	private ArrayList<Pieces> removedWhite = new ArrayList<Pieces>();
	private ArrayList<Pieces> removedBlack = new ArrayList<Pieces>();
	private Stack<Pieces> virtualremoved = new Stack<Pieces>();

	/**
	 * Constructor.
	 */
	private Board() {
		initBoard();
		populateBoard();
	}

	private Board(int i) {

	}

	public static Board getInstance() {

		if (board == null) {
			board = new Board();
		}
		return board;
	}

	public boolean isPieceOnSquare(int x, int y) {
		Pieces piece;
		for (Pieces p : board.getPieces()) {
			piece = p;
			if (piece.getPosition().getX() == x
					&& piece.getPosition().getY() == y) {
				return true;
			}
		}
		return false;
	}

	// return true if the same color
	public boolean isPieceOnSquare(int x, int y, int color) {
		for (Pieces p : board.getPieces()) {
			if (p.getColor() == color) {
				if (p.getPosition().getX() == x && p.getPosition().getY() == y) {
					return true;
				}
			}
		}
		return false;
	}

	public void initBoard() {
		for (int i = 0; i < 8; i++) {
			pieces.add(new Pawns(R.drawable.pawn, 1, Position.getPos(1, i), i)); // black
																					// pawns
			pieces.add(new Pawns(R.drawable.pawnw, 0, Position.getPos(6, i), i)); // white
																					// pawns
		}
		pieces.add(new Rooks(R.drawable.rook, 1, Position.getPos(0, 0), 9)); // black
																				// rook
		pieces.add(new Rooks(R.drawable.rook, 1, Position.getPos(0, 7), 10)); // black
																				// rook
		pieces.add(new Rooks(R.drawable.rookw, 0, Position.getPos(7, 0), 9)); // white
																				// rook
		pieces.add(new Rooks(R.drawable.rookw, 0, Position.getPos(7, 7), 10)); // white
																				// rook

		pieces.add(new Knights(R.drawable.knight, 1, Position.getPos(0, 1), 11)); // black
																					// knight
		pieces.add(new Knights(R.drawable.knight, 1, Position.getPos(0, 6), 12)); // black
																					// knight
		pieces.add(new Knights(R.drawable.knightw, 0, Position.getPos(7, 1), 11)); // white
																					// knight
		pieces.add(new Knights(R.drawable.knightw, 0, Position.getPos(7, 6), 12)); // white
																					// knight

		pieces.add(new Bishops(R.drawable.bishop, 1, Position.getPos(0, 2), 13)); // black
																					// bishop
		pieces.add(new Bishops(R.drawable.bishop, 1, Position.getPos(0, 5), 14)); // black
																					// bishop
		pieces.add(new Bishops(R.drawable.bishopw, 0, Position.getPos(7, 2), 13)); // white
																					// bishop
		pieces.add(new Bishops(R.drawable.bishopw, 0, Position.getPos(7, 5), 14)); // white
																					// bishop

		pieces.add(new Queen(R.drawable.queen, 1, Position.getPos(0, 3), 15)); // black
																				// queen
		pieces.add(new Queen(R.drawable.queenw, 0, Position.getPos(7, 3), 15)); // white
																				// queen

		pieces.add(new King(R.drawable.king, 1, Position.getPos(0, 4), 16)); // black
																				// king
		pieces.add(new King(R.drawable.kingw, 0, Position.getPos(7, 4), 16)); // white
																				// king

	}

	public ArrayList<Pieces> getPieces() {
		return pieces;
	}

	public ArrayList<Pieces> getRemovedWhite() {
		return removedWhite;
	}

	public ArrayList<Pieces> getRemovedBlack() {
		return removedBlack;
	}

	public Pieces[][] getBoardPieces() {
		return boardPieces;
	}

	public void removePiece(Position pos, boolean isAI) {
		ArrayList<Pieces> pieces = getPieces();
		for (Pieces p : pieces) {
			if (p.getPosition().getX() == pos.getX()
					&& p.getPosition().getY() == pos.getY()) {
				pieces.remove(p);
				if (isAI) {
					virtualremoved.push(p);
				} else {
					if (p.getColor() == 0) { // white
						Global.removedWhite = true;
						removedWhite.add(p);
					} else {
						Global.removedBlack = true;
						removedBlack.add(p);
					}
				}
				setPieces(pieces);
				updateGameState();
				break;
			}
		}
	}

	public void setPieces(ArrayList<Pieces> pieces) {
		this.pieces = pieces;
	}

	public Stack<Pieces> getVirtualremoved() {
		return virtualremoved;
	}

	public void setVirtualremoved(Stack<Pieces> virtualremoved) {
		this.virtualremoved = virtualremoved;
	}

	public void updateGameState() {
		emptyBoard();
		populateBoard();
	}

	public void emptyBoard() {
		for (int i = 0; i < 8; i++) { // rows
			for (int j = 0; j < 8; j++) { // columns
				boardPieces[i][j] = null;
			}
		}
	}

	public void populateBoard() {
		for (Pieces p : pieces) {
			if (p.getPosition().getX() == -1 || p.getPosition().getY() == -1)
				System.out.println();
			boardPieces[p.getPosition().getX()][p.getPosition().getY()] = p;
		}
	}

	public void convertPawnsToQueen(int color) {
		int enemyEndRow;
		if (color == 0) {
			enemyEndRow = 0;
		} else {
			enemyEndRow = Global.boardSize - 1;
		}
		ArrayList<Pieces> pieces = getPieces();
		int i = 0;
		for (Pieces p : pieces) {
			if (p.getPosition().getX() == enemyEndRow && p.getColor() == color
					&& p.getType() == Global.PAWNS) {
				// p.setType(Global.QUEEN);
				// pieces.set(i, p);
				pieces.remove(i);
				Pieces k = null;
				if (color == 0)
					k = new Queen(R.drawable.queenw, 0, p.getPosition(), 15);
				else
					k = new Queen(R.drawable.queen, 1, p.getPosition(), 15);

				pieces.add(k);
				setPieces(pieces);
				updateGameState();
				break;
			}
			i++;
		}
	}

	// 0 is white and 1 is black
	public ArrayList<Pieces> getCertainColor(int color) {
		ArrayList<Pieces> returned = new ArrayList<Pieces>();
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getColor() == color)
				returned.add(pieces.get(i));
		}
		return returned;

	}

	public ArrayList<Position> getValidMoves() {
		ArrayList<Position> res = new ArrayList<Position>();
		for (int i = 0; i < boardPieces.length; i++) {
			for (int j = 0; j < boardPieces[i].length; j++) {
				res.addAll(boardPieces[i][j].getValidPositions());
			}
		}
		return res;
	}

	public void reconstruct(String string) {

	}

	public Board getNewBoard() {
		Board b = new Board(0);
		if (board != null) {
			for (int i = 0; i < boardPieces.length; i++) {
				for (int j = 0; j < boardPieces.length; j++) {
					if (boardPieces[i][j] != null)
						b.boardPieces[i][j] = (Pieces) boardPieces[i][j]
								.clone();
					if (b.boardPieces[i][j] != null)
						b.pieces.add(b.boardPieces[i][j]);
				}
			}
		}
		return b;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < Global.boardSize; i++) {
			for (int j = 0; j < Global.boardSize; j++) {
				if (boardPieces[i][j] != null)
					string += boardPieces[i][j].toString();
				else
					string += " ";
			}

		}
		return string;
	}

	public Pieces getPiece(int x, int y, int color) {
		// pieces from one color
		ArrayList<Pieces> pieces = getPiecesFromOneSide(color);
		for (Pieces p : pieces) {
			if (p.getPosition().getX() == x && p.getPosition().getY() == y) {
				return p;
			}
		}
		return null;
	}

	public ArrayList<Pieces> getPiecesFromOneSide(int color) {
		ArrayList<Pieces> sidePieces = new ArrayList<Pieces>();
		// get all pieces
		ArrayList<Pieces> pieces = getPieces();
		for (Pieces p : pieces) {
			if (p.getColor() == color) {
				sidePieces.add(p);
			}
		}
		return sidePieces;
	}

	public boolean removeAI(Position position, int turn) {
		Stack<Pieces> dead = this.getVirtualremoved();
		if (dead.size() > 0
				&& position.compareTo(dead.peek().getPosition()) == 0) {
			Pieces deadp = dead.pop();
			deadp.setPosition(deadp.getPosition());
			deadp.getPosition().setCurrentone(deadp);
			this.getPieces().add(deadp);
			this.updateGameState();
			return true;
		}
		return false;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Board.board = board;
	}

}
