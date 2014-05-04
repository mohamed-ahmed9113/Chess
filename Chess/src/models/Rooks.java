package models;

import java.util.ArrayList;

public class Rooks extends Pieces {

	public Rooks(int image, int color, Position current, int n) {
		super(image, color, Global.ROOKS, current, 25, n);
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
		int i, j;
		// up
		i = x;
		i--;
		while (i >= 0) {
			if (Board.getInstance().isPieceOnSquare(i, y, this.getColor())) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, y, enemyColor)) {
				positions.add(Position.getPos(i, y));
				break;
			} else {
				positions.add(Position.getPos(i, y));
			}
			i--;
		}
		// down
		i = x;
		i++;
		while (i <= Global.boardSize - 1) {
			if (Board.getInstance().isPieceOnSquare(i, y, this.getColor())) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, y, enemyColor)) {
				positions.add(Position.getPos(i, y));

				break;
			} else {
				positions.add(Position.getPos(i, y));
			}
			i++;
		}
		// right
		j = y;
		j++;
		while (j <= Global.boardSize - 1) {
			if (Board.getInstance().isPieceOnSquare(x, j, this.getColor())) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(x, j, enemyColor)) {

				positions.add(Position.getPos(x, j));

				break;
			} else {
				positions.add(Position.getPos(x, j));
			}
			j++;
		}
		// left
		j = y;
		j--;
		while (j >= 0) {
			if (Board.getInstance().isPieceOnSquare(x, j, this.getColor())) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(x, j, enemyColor)) {
				positions.add(Position.getPos(x, j));

				break;
			} else {
				positions.add(Position.getPos(x, j));
			}
			j--;
		}
		for (int j2 = 0; j2 < positions.size(); j2++) {
			positions.get(j2).setCurrentone(this);
		}
		return positions;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "r";
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new Rooks(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
