package com.example.chess;

import models.Board;
import models.Global;
import models.Pieces;
import models.Position;
import player.Player;
import player.PlayerHuman;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

@SuppressLint("NewApi")
public class GameActivityMulti extends Activity {
	private static Board board;
	private static int currentPlayer = -1;
	private static int activeX = -1;
	private static int activeY = -1;
	private static Pieces[][] grid;
	static PlayerHuman player1;
	static PlayerHuman player2;
	static GameViewMulti view;
	static boolean needHelp = true;
	static int timer = 60;
	static boolean timerWork = false;
	static boolean checkmate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		player1 = new PlayerHuman(0);
		player2 = new PlayerHuman(1);
		currentPlayer = player1.getSide();
		timerWork = getIntent().getBooleanExtra("timer", false);
		board = Board.getInstance();
		grid = board.getBoardPieces();
		view = new GameViewMulti(this);
		setContentView(view);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true && timerWork) {
						Thread.sleep(1000);

						timer--;
						if (timer == 0) {
							changePlayer();
						}
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								view.invalidate();

							}
						});
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Board.setBoard(null);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	public static void getValidPositions(int x, int y) {

		if (activeX == -1 && activeY == -1
				&& board.isPieceOnSquare(x, y, currentPlayer)) {
			activeX = x;
			activeY = y;
			Pieces p = board.getBoardPieces()[x][y];

		} else if ((activeX != x || activeY != y)) {
			if (doMove(x, y, activeX, activeY)) {
				activeX = -1;
				activeY = -1;
			} else {
				// illegalMove=true;
				activeX = -1;
				activeY = -1;
			}
			Global.removedBlack = false;
			Global.removedWhite = false;

		}

		view.invalidate();
	}

	private static boolean doMove(int newX, int newY, int oldX, int oldY) {
		// TODO Auto-generated method stub
		checkmate = false;
		if (board.isPieceOnSquare(oldX, oldY, currentPlayer)) {
			Pieces p = board.getBoardPieces()[oldX][oldY];
			Global.finalMoveX = oldX;
			Global.finalMoveY = oldY;
			if (currentPlayer == player1.getSide()) {

				if (p.doMove(player1, Position.getPos(newX, newY), false, board)) {
					checkmate = checkCheckmate(player1, player2);
					changePlayer();
					return true;
				}
			} else {
				if (p.doMove(player2, Position.getPos(newX, newY), false, board)) {
					checkmate = checkCheckmate(player1, player2);
					changePlayer();
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkCheckmate(Player player1, Player player2) {
		// check if player1 is in checkmate
		if (Pieces.isCheckmate(player1)) {
			String winnerSide;
			if (player1.getSide() == 0) {
				winnerSide = "black";
			} else {
				winnerSide = "white";
			}
			return true;
		} else if (Pieces.isCheckmate(player2)) {
			String winnerSide;
			if (player2.getSide() == 0) {
				winnerSide = "black";
			} else {
				winnerSide = "white";
			}
			return true;
		}
		return false;
	}

	public static void changePlayer() {
		timer = 60;
		if (currentPlayer == player1.getSide()) {
			currentPlayer = player2.getSide();
		} else {
			currentPlayer = player1.getSide();
		}

	}

	public static int getActiveX() {
		return activeX;
	}

	public static int getActiveY() {
		return activeY;
	}

	public static boolean isNeedHelp() {
		return needHelp;
	}

	public static Board getBoard() {
		return board;
	}

	public static boolean isTimerWork() {
		return timerWork;
	}

	public void setTimerWork(boolean timerWork) {
		this.timerWork = timerWork;
	}

	public static int getTimer() {
		return timer;
	}

	public static void setTimer(int timer) {
		GameActivityMulti.timer = timer;
	}

	public static boolean isCheckmate() {
		return checkmate;
	}

	public static void setCheckmate(boolean checkmate) {
		GameActivityMulti.checkmate = checkmate;
	}

}