package models;

import java.util.ArrayList;

public class Queen extends Pieces {

	public Queen(int image, int color, Position current, int n) {
		super(image, color, Global.QUEEN, current, 45, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		int color = this.getColor();
		int enemyColor;
		if (this.getColor() == 0) {
			enemyColor = 1;
		} else {
			enemyColor = 0;
		}
		// rook acting
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
		// ////////////////////////////////////////
		// bishop acting
		// up-right
		i = x;
		j = y;
		i--;
		j++;
		while (i >= 0 && j <= Global.boardSize - 1) {
			if (Board.getInstance().isPieceOnSquare(i, j, color)) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, j, enemyColor)) {
				positions.add(Position.getPos(i, j));
				break;
			} else {
				positions.add(Position.getPos(i, j));
			}
			i--;
			j++;
		}
		// up-left
		i = x;
		j = y;
		i--;
		j--;
		while (i >= 0 && j >= 0) {
			if (Board.getInstance().isPieceOnSquare(i, j, color)) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, j, enemyColor)) {
				positions.add(Position.getPos(i, j));
				break;
			} else {
				positions.add(Position.getPos(i, j));
			}
			i--;
			j--;
		}
		// down-right
		i = x;
		j = y;
		i++;
		j++;
		while (i <= Global.boardSize - 1 && j <= Global.boardSize - 1) {
			if (Board.getInstance().isPieceOnSquare(i, j, color)) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, j, enemyColor)) {
				positions.add(Position.getPos(i, j));

				break;
			} else {
				positions.add(Position.getPos(i, j));
			}
			i++;
			j++;
		}
		// down-left
		i = x;
		j = y;
		i++;
		j--;
		while (i <= Global.boardSize - 1 && j >= 0) {
			if (Board.getInstance().isPieceOnSquare(i, j, color)) {
				break;
			} else if (Board.getInstance().isPieceOnSquare(i, j, enemyColor)) {
				positions.add(Position.getPos(i, j));
				break;
			} else { // add coordinates
				positions.add(Position.getPos(i, j));
			}
			i++;
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
		return "Q";
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		return new Queen(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
