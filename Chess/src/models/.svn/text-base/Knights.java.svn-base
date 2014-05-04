package models;

import java.util.ArrayList;

public class Knights extends Pieces {

	public Knights(int image, int color, Position current, int n) {
		super(image, color, Global.KNIGHTS, current, 30, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		// up-right
		if (isOnBoard(x + 2, y - 1)) {
			if (!Board.getInstance().isPieceOnSquare(x + 2, y - 1,
					this.getColor())) {
				positions.add(Position.getPos(x + 2, y - 1));
			}
		}
		if (isOnBoard(x + 1, y - 2)) {
			if (!Board.getInstance().isPieceOnSquare(x + 1, y - 2,
					this.getColor())) {
				positions.add(Position.getPos(x + 1, y - 2));
			}
		}
		// up-left
		if (isOnBoard(x - 2, y - 1)) {
			if (!Board.getInstance().isPieceOnSquare(x - 2, y - 1,
					this.getColor())) {
				positions.add(Position.getPos(x - 2, y - 1));
			}
		}
		if (isOnBoard(x - 1, y - 2)) {
			if (!Board.getInstance().isPieceOnSquare(x - 1, y - 2,
					this.getColor())) {
				positions.add(Position.getPos(x - 1, y - 2));
			}
		}
		// down-right
		if (isOnBoard(x + 2, y + 1)) {
			if (!Board.getInstance().isPieceOnSquare(x + 2, y + 1,
					this.getColor())) {
				positions.add(Position.getPos(x + 2, y + 1));
			}
		}
		if (isOnBoard(x + 1, y + 2)) {
			if (!Board.getInstance().isPieceOnSquare(x + 1, y + 2,
					this.getColor())) {
				positions.add(Position.getPos(x + 1, y + 2));
			}
		}
		// down-left
		if (isOnBoard(x - 2, y + 1)) {
			// if no own piece on the square
			if (!Board.getInstance().isPieceOnSquare(x - 2, y + 1,
					this.getColor())) {
				positions.add(Position.getPos(x - 2, y + 1));
			}
		}
		if (isOnBoard(x - 1, y + 2)) {
			if (!Board.getInstance().isPieceOnSquare(x - 1, y + 2,
					this.getColor())) {
				positions.add(Position.getPos(x - 1, y + 2));
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
		return "k";
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new Knights(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
