package models;

import java.util.ArrayList;

public class Pawns extends Pieces {

	public Pawns(int image, int color, Position current, int n) {
		super(image, color, Global.PAWNS, current, 5, n);
		this.getPosition().setCurrentone(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int enemyColor;
		if (this.getColor() == 0) {
			enemyColor = 1;
		} else {
			enemyColor = 0;
		}

		if (this.getColor() == 0) { // white pawn

			if (x == 6) {
				if (!Board.getInstance().isPieceOnSquare(x - 1, y)) {
					positions.add(Position.getPos(x - 1, y));
					if (!Board.getInstance().isPieceOnSquare(x - 2, y)) {
						positions.add(Position.getPos(x - 2, y));
					}
				}
			} else {
				if (x - 1 >= 0
						&& !Board.getInstance().isPieceOnSquare(x - 1, y)) {
					positions.add(Position.getPos(x - 1, y));
				}
			}
			if (Board.getInstance().isPieceOnSquare(x - 1, y + 1, enemyColor)) { // up
																					// right
				positions.add(Position.getPos(x - 1, y + 1));
			}
			if (Board.getInstance().isPieceOnSquare(x - 1, y - 1, enemyColor)) { // up
																					// left
				positions.add(Position.getPos(x - 1, y - 1));
			}

		} else if (this.getColor() == 1) { // black pawn
			if (x == 1) {
				if (!Board.getInstance().isPieceOnSquare(x + 1, y)) {
					positions.add(Position.getPos(x + 1, y));
					if (!Board.getInstance().isPieceOnSquare(x + 2, y)) {
						positions.add(Position.getPos(x + 2, y));
					}
				}
			} else {
				if (x + 1 < 8 && !Board.getInstance().isPieceOnSquare(x + 1, y)) {
					positions.add(Position.getPos(x + 1, y));
				}
			}

			if (Board.getInstance().isPieceOnSquare(x + 1, y + 1, enemyColor)) {
				positions.add(Position.getPos(x + 1, y + 1));
			}
			if (Board.getInstance().isPieceOnSquare(x + 1, y - 1, enemyColor)) {
				positions.add(Position.getPos(x + 1, y - 1));
			}

		}
		for (int j2 = 0; j2 < positions.size(); j2++) {
			positions.get(j2).setCurrentone(this);
		}
		return positions;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "p";
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new Pawns(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
