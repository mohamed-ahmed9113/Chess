package models;

import java.util.ArrayList;

public class Bishops extends Pieces {

	public Bishops(int image, int color, Position current,int n) {
		super(image, color, Global.BISHOPS, current, 15, n);
	}

	@Override
	public ArrayList<Position> getValidPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		int color = this.getColor();
		int enemyColor;
		if (color == 0) {
			enemyColor = 1;
		} else {
			enemyColor = 0;
		}

		// up-right
		int i = x, j = y;
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
			} else {
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
		return "b";
	}

	@Override
	protected Object clone() {
		return new Bishops(super.getPiecesImage(), super.getColor(),
				this.getPosition(), this.number);
	}

}
