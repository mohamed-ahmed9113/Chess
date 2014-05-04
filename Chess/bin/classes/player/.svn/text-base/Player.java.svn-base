package player;

public class Player {

	private int side;   //white 0, black 1
	
	public Player() {
		
	}
	
	/**
	 * Constructor which allows to set side.
	 * @param side
	 */
	public Player(int side) {
		setSide(side);
	}
	
	/**
	 * Get side. Black or white.
	 * @return side
	 */
	public int getSide() {
		return side;
	}

	/**
	 * Set side. Black or white.
	 * @param side
	 */
	public void setSide(int side) {
		this.side = side;
	}
	
	
	public int getEnemySide() {
		if (getSide()==0) {
			return 1;
		} else {
			return 0;
		}
	}
}
