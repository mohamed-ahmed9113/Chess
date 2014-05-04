package models;

public class Position implements Comparable<Position> {
	private int x;
	private int y;
	private static Position[][] pool;
	private Pieces currentone;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void initializePositions() {
		pool = new Position[Global.boardSize][Global.boardSize];
		for (int i = 0; i < Global.boardSize; i++) {
			for (int j = 0; j < Global.boardSize; j++) {
				pool[i][j] = new Position(i, j);
			}
		}
	}

	public static Position getPos(int x, int y) {
		return new Position(x, y);
		// return pool[x][y];
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Pieces getCurrentone() {
		return currentone;
	}

	public void setCurrentone(Pieces currentone) {
		this.currentone = currentone;
	}

	@Override
	public int compareTo(Position another) {
		if (this.x == another.x && this.y == another.y)
			return 0;
		else {
			if (this.x < another.x && this.y == another.y)
				return -1;
			else {
				if (this.x == another.x && this.y < another.y)
					return -1;
			}
		}
		return 1;
	}
}
