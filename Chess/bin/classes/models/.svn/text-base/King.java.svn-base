package models;

import java.util.ArrayList;

public class King extends Pieces {

	public King(int image, int color, Position current, int n) {
		super(image, color, Global.KING, current, 50, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int color = this.getColor();

		if (isOnBoard(x, y + 1)
				&& !Board.getInstance().isPieceOnSquare(x, y + 1, color)) { // right
			positions.add(Position.getPos(x, y + 1));
		}

		if (isOnBoard(x, y - 1)
				&& !Board.getInstance().isPieceOnSquare(x, y - 1, color)) { // left
			positions.add(Position.getPos(x, y - 1));
		}
		// vertical
		if (isOnBoard(x - 1, y)
				&& !Board.getInstance().isPieceOnSquare(x - 1, y, color)) { // up
			positions.add(Position.getPos(x - 1, y));
		}
		if (isOnBoard(x + 1, y)
				&& !Board.getInstance().isPieceOnSquare(x + 1, y, color)) { // down
			positions.add(Position.getPos(x + 1, y));
		}
		// up-right
		if (isOnBoard(x - 1, y + 1)
				&& !Board.getInstance().isPieceOnSquare(x - 1, y + 1, color)) {
			positions.add(Position.getPos(x - 1, y + 1));
		}
		// up-left
		if (isOnBoard(x - 1, y - 1)
				&& !Board.getInstance().isPieceOnSquare(x - 1, y - 1, color)) {
			positions.add(Position.getPos(x - 1, y - 1));
		}
		// down-right
		if (isOnBoard(x + 1, y + 1)
				&& !Board.getInstance().isPieceOnSquare(x + 1, y + 1, color)) {
			positions.add(Position.getPos(x + 1, y + 1));
		}
		// down-left
		if (isOnBoard(x + 1, y - 1)
				&& !Board.getInstance().isPieceOnSquare(x + 1, y - 1, color)) {
			positions.add(Position.getPos(x + 1, y - 1));

		}
		for (int j2 = 0; j2 < positions.size(); j2++) {
			positions.get(j2).setCurrentone(this);
		}
		return positions;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "K";
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new King(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
