package models;

import java.util.ArrayList;

import player.Player;

public abstract class Pieces implements Cloneable {

	private Position currentPosition; // x and y position of the current piece
										// on the board
	private int type; // type of the piece (king-queen-knight...)
	private int piecesImage; // image variable
	private int color; // black or white 1 for black .. 0 for white
	private int weight;
	private Position oldpos;
	protected int number = 0;

	public Position getOldpos() {
		return oldpos;
	}

	public void setOldpos(Position oldpos) {
		this.oldpos = oldpos;
		this.oldpos.setCurrentone(this);
	}

	public Pieces(int piecesImage, int color, int type, Position current,
			int w, int n) {
		this.piecesImage = piecesImage;
		this.color = color;
		this.type = type;
		currentPosition = current;
		weight = w;
		number = n;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// // METHOD DRAW WILL BE WRITTEN HERE
	// public void draw(int dx, int dy, Canvas canvas, Context con) {
	// Paint paint = new Paint();
	// Resources res = con.getResources();
	// Bitmap drawnBitmap = BitmapFactory.decodeResource(res, piecesImage);
	// // Bitmap sBackground = Bitmap.createScaledBitmap(background,
	// // getWidth(),
	// // con.getHeight(), true);
	// // canvas.drawColor(0xFFAAAAAA);
	// canvas.drawBitmap(drawnBitmap, dx, dy, paint);
	//
	// }

	public Boolean isOnBoard(int x, int y) {
		if ((x >= 0 && x <= Global.boardSize - 1)
				&& (y >= 0 && y <= Global.boardSize - 1)) {
			return true;
		} else {
			return false;
		}
	}

	// public boolean isPieceOnSquare(int x, int y) {
	// Pieces piece;
	// for (Pieces p : Board.getInstance().getPieces()) {
	// piece = p;
	// if (piece.getPosition().getX() == x
	// && p.getPosition().getY() == y) {
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// // return true if the same color
	// public boolean isPieceOnSquare(int x, int y, int color) {
	// for (Pieces p : Board.getInstance().getPieces()) {
	// if (p.getColor() == color) {
	// if (p.getPosition().getX() == x && p.getPosition().getY() == y) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	public boolean isValidMove(Position newPosition) {
		ArrayList<Position> temp = getValidPositions();
		for (int i = 0; i < temp.size(); i++)
			if (temp.get(i).compareTo((newPosition)) == 0)
				return true;
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doMove(Player player, Position newPosition, boolean isAI,
			Board b) {
		if (b.isPieceOnSquare(newPosition.getX(), newPosition.getY())) {
			Pieces p = b.getPiece(newPosition.getX(), newPosition.getY(),
					player.getEnemySide());
			if (p != null && p.getType() == 0) {
				return false;
			}
		}

		if (isValidMove(newPosition)) {
			if (isAI) {
				b.removePiece(newPosition, true);
			} else {
				b.removePiece(newPosition, false);
			}
			currentPosition = Position.getPos(newPosition.getX(),
					newPosition.getY());
			currentPosition.setCurrentone(this);
			b.updateGameState();
			if (kingMayDie(player)) {
				// undo the play
				Pieces q = b.getBoardPieces()[newPosition.getX()][newPosition
						.getY()];
				q.setPosition(new Position(Global.finalMoveX, Global.finalMoveY));
				if (Global.removedWhite) {
					if (!isAI) {
						Pieces t = b.getRemovedWhite().get(
								b.getRemovedWhite().size() - 1);
						b.getRemovedWhite().remove(
								b.getRemovedWhite().size() - 1);
						t.setPosition(newPosition);
						b.getPieces().add(t);
					}

				} else if (Global.removedBlack) {
					if (!isAI) {
						Pieces t = b.getRemovedBlack().get(
								b.getRemovedBlack().size() - 1);
						b.getRemovedBlack().remove(
								b.getRemovedBlack().size() - 1);
						t.setPosition(newPosition);
						b.getPieces().add(t);
					}
				}
				b.updateGameState();
				return false;
			}

			Board.getInstance().convertPawnsToQueen(player.getSide());
			return true;
		}

		return false;
	}

	public static boolean kingMayDie(Player player) {
		ArrayList<ArrayList<Pieces>> pieces = getPiecesPossibleToCapture(
				player.getEnemySide(), true);
		ArrayList<Pieces> piecesAbleToCapture = pieces.get(1);

		for (Pieces p : piecesAbleToCapture) {
			if (p.getType() == 0) {
				return true;
			}
		}
		return false;

	}

	public boolean doMove(Player player, Position newPosition, boolean isAI) {

		if (isValidMove(newPosition)) {
			if (isAI) {
				Board.getInstance().removePiece(newPosition, true);
			} else {
				Board.getInstance().removePiece(newPosition, false);
			}
			currentPosition = Position.getPos(newPosition.getX(),
					newPosition.getY());
			Board.getInstance().updateGameState();
			// Board.getInstance().convertPawnsToQueen(player.getSide());
			return true;
		}

		return false;
	}

	public static boolean isCheckmate(Player player) {
		// check if King is under attack
		// check if there's safe square to move
		ArrayList<ArrayList<Pieces>> pieces = getPiecesPossibleToCapture(
				player.getEnemySide(), true);
		ArrayList<Pieces> piecesAbleToCapture = pieces.get(1);
		ArrayList<Position> kingMoves = new ArrayList<Position>();
		// check if King is found from the list of pieces enemy is able to
		// capture
		for (Pieces p : piecesAbleToCapture) {
			if (p.getType() == Global.KING) {
				kingMoves = p.getValidPositions();
				break;
			}
		}
		if (!kingMoves.isEmpty()) {
			for (Position pos : kingMoves) {
				// if there's safe square for the King to move
				if (isSquareSafe(pos.getX(), pos.getY(), player)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	// //////////////////////////////////////////////////////////////////

	public static ArrayList<ArrayList<Pieces>> getPiecesPossibleToCapture(
			int side, boolean kingCapture) {
		ArrayList<Pieces> ownPieces = new ArrayList<Pieces>(), enemyPieces = new ArrayList<Pieces>();
		ArrayList<ArrayList<Pieces>> ownAndEnemyPieces = new ArrayList<ArrayList<Pieces>>();
		ArrayList<Pieces> allPiecesFromOneSide = Board.getInstance()
				.getPiecesFromOneSide(side);
		ArrayList<Pieces> allPiecesFromOtherSide = Board.getInstance()
				.getPiecesFromOneSide(side == 0 ? 1 : 0);

		ArrayList<Position> moves = new ArrayList<Position>();
		//		ArrayList<Pieces> pieces = new ArrayList<Pieces>();
		int enemySide;
		for (Pieces ownPiece : allPiecesFromOneSide) {
			moves = ownPiece.getValidPositions();
			// ///////////////////////////////
			// for (int i = 0; i < moves.size(); i++) {
			// if (Board.getInstance().isPieceOnSquare(moves.get(i).getX(),
			// moves.get(i).getY())) {
			// Pieces p = Board.getInstance().getBoardPieces()[moves
			// .get(i).getX()][moves.get(i).getY()];
			// if (p.getType() == Global.KING) {
			// pieces.add(p);
			//
			// }
			// }
			// }

			// ///////////////////////////////
			if (side == 0) {
				enemySide = 1;
			} else {
				enemySide = 0;
			}
			for (Position pos : moves) {
				if (Board.getInstance().isPieceOnSquare(pos.getX(), pos.getY(),
						enemySide)) {
					// add enemy piece to arraylist
					enemyPieces.add(Board.getInstance().getPiece(pos.getX(),
							pos.getY(), enemySide));
					// add own piece to arraylist
					ownPieces.add(ownPiece);
				}
			}
		}
		ownAndEnemyPieces.add(ownPieces);
		ownAndEnemyPieces.add(enemyPieces);
		return ownAndEnemyPieces;
	}

	// //////////////////////////////////////////////////////////////////

	public static boolean isSquareSafe(int x, int y, Player player) {
		ArrayList<Position> possibleEnemyMoves = getAllSquaresPossibleToMove(player
				.getEnemySide());
		for (Position p : possibleEnemyMoves) {
			if (p.getX() == x && p.getY() == y) { // match found
				return false; // not a safe square as enemy piece can move there
			}
		}
		return true;
	}

	// /////////////////////////////////////////////////////////////////

	public static ArrayList<Position> getAllSquaresPossibleToMove(int side) {
		ArrayList<Position> allSquaresPossibleToMove = new ArrayList<Position>();
		// get all pieces from black or white
		ArrayList<Pieces> allPiecesFromOneSide = Board.getInstance()
				.getPiecesFromOneSide(side);
		ArrayList<Position> moves = new ArrayList<Position>();
		for (Pieces ownPiece : allPiecesFromOneSide) {
			// all squares piece can move to
			moves = ownPiece.getValidPositions();
			allSquaresPossibleToMove.addAll(moves);
		}
		return allSquaresPossibleToMove;
	}

	// d el method elli 7tnawar el valid moves !
	// MSH PIECES POSITIONS AW INTEGERS NSHOOF
	// 7N3MELHA EZAY B2A
	public abstract ArrayList<Position> getValidPositions();

	public abstract String toString();

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public void setPosition(Position newPosition) {
		currentPosition = newPosition;
		currentPosition.setCurrentone(this);
	}

	public Position getPosition() {
		return currentPosition;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	protected abstract Object clone();

	public int getPiecesImage() {
		return piecesImage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
