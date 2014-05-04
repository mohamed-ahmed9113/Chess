package com.example.chess;

import java.util.StringTokenizer;

import models.Board;
import models.Global;
import models.Pieces;
import models.Position;
import player.Player;
import player.PlayerAI;
import player.PlayerHuman;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

@SuppressLint("NewApi")
public class GameActivityOnline extends Activity {
	private static Board board;
	private static int currentPlayer = -1;
	private static Pieces[][] grid;
	private static int activeX = -1;
	private static int activeY = -1;
	static PlayerHuman player1;
	static PlayerHuman player2;
	static PlayerHuman multiPlayer;
	static PlayerAI ai;
	static GameViewOnline view;
	static boolean needHelp = true;
	static int multiPlayerColor;
	static int otherPlayerColor;
	private Position last = null;
	private Position current = null;
	static boolean checkmate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Position.initializePositions();
		player1 = new PlayerHuman(0);
		player2 = new PlayerHuman(1);
		multiPlayer = new PlayerHuman();
		multiPlayerColor = multiPlayer.getSide();
		if (multiPlayerColor == 0)
			otherPlayerColor = 1;
		else
			otherPlayerColor = 0;
		ai = new PlayerAI(1);
		currentPlayer = multiPlayer.getSide(); // player1.getSide();
		board = Board.getInstance();
		grid = board.getBoardPieces();
		view = new GameViewOnline(this);
		view.setGameActivity(this);
		setContentView(view);
		new Thread(new Runnable() {
			public void run() {
				if (Global.turn == false) {
					try {
						// WILL THIS WORK !!??
						final String t = multiPlayer.getInFromServer()
								.readLine();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								StringTokenizer st = new StringTokenizer(t);
								int oldX = Integer.parseInt(st.nextToken());
								int oldY = Integer.parseInt(st.nextToken());
								int newX = Integer.parseInt(st.nextToken());
								int newY = Integer.parseInt(st.nextToken());

								Pieces move = board.getPiece(oldX, oldY,
										otherPlayerColor);
								move.doMove(multiPlayer,
										Position.getPos(newX, newY), false);
								view.invalidate();

							}
						});
						Global.turn = true;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// } catch (Exception e) {
		//
		// }
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Board.setBoard(null);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void getValidPositions(int x, int y) {
		last = current;
		current = Position.getPos(x, y);
		Player ptemp = multiPlayer;
		/*
		 * if (player1 == null) { ptemp = player2; } else { ptemp = player1; }
		 */
		if (activeX == -1 && activeY == -1
				&& board.isPieceOnSquare(x, y, ptemp.getSide())
				&& currentPlayer == ptemp.getSide()) {
			activeX = x;
			activeY = y;
			Pieces p = board.getBoardPieces()[x][y];

		} else if ((activeX != x || activeY != y)) {
			if (doMove(x, y, activeX, activeY)) {
				activeX = -1;
				activeY = -1;
				try {
					String toSend = last.getX() + " " + last.getY() + " "
							+ current.getX() + " " + current.getY();
					multiPlayer.getDataOutputStream().writeBytes(toSend + "\n");
					Global.turn = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
				new Thread(new Runnable() {
					public void run() {
						if (Global.turn == false) {
							try {
								// WILL THIS WORK !!??
								final String t = multiPlayer.getInFromServer()
										.readLine();
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										StringTokenizer st = new StringTokenizer(
												t);
										int oldX = Integer.parseInt(st
												.nextToken());
										int oldY = Integer.parseInt(st
												.nextToken());
										int newX = Integer.parseInt(st
												.nextToken());
										int newY = Integer.parseInt(st
												.nextToken());

										Pieces move = board.getPiece(oldX,
												oldY, otherPlayerColor);
										move.doMove(multiPlayer,
												Position.getPos(newX, newY),
												false);
										view.invalidate();
									}
								});
								Global.turn = true;

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			} else {
				// illegalMove=true;
				activeX = -1;
				activeY = -1;
			}
			Global.removedBlack = false;
			Global.removedWhite = false;
		}

		view.invalidate();
		// return pos;
	}

	private static boolean doMove(int newX, int newY, int oldX, int oldY) {
		// TODO Auto-generated method stub
		checkmate = false;
		if (board.isPieceOnSquare(oldX, oldY, currentPlayer)) {
			Pieces p = board.getBoardPieces()[oldX][oldY];
			Global.finalMoveX = oldX;
			Global.finalMoveY = oldY;
			// if (currentPlayer == player1.getSide()) {
			if (board.isPieceOnSquare(newX, newY, multiPlayer.getEnemySide())) {
				Pieces q = board.getBoardPieces()[newX][newY];
				if (q.getType() == Global.KING) {
					checkmate = true;
				}
			}

			if (p.doMove(multiPlayer, Position.getPos(newX, newY), false)) {
				checkmate = GameActivityOnline.checkCheckmate(multiPlayer,
						player2);
				changePlayer();
				return true;
				// }
			}
		}
		/*
		 * else { if (p.doMove(player2, Position.getPos(newX, newY), false)) {
		 * changePlayer(); return true; } } }
		 */
		return false;
	}

	public static boolean checkCheckmate(Player player1, Player player2) {
		// check if player1 is in checkmate
		if (player1 != null && Pieces.isCheckmate(multiPlayer)) {
			String winnerSide;
			if (player1.getSide() == 0) {
				winnerSide = "black";
			} else {
				winnerSide = "white";
			}
			return true;
		} else if (player2 != null && Pieces.isCheckmate(player2)) {
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

		// Global.turn=!Global.turn;
		/*
		 * if (currentPlayer == player1.getSide()) {
		 * 
		 * // Socket clientSocket = new Socket("41.46.27.25", 6789); //
		 * DataOutputStream outToServer = new DataOutputStream( //
		 * clientSocket.getOutputStream()); // BufferedReader inFromServer = new
		 * BufferedReader(new // InputStreamReader( //
		 * clientSocket.getInputStream())); // if (player1 != null) { // if
		 * (currentPlayer == player1.getSide()) { // // currentPlayer = -1; //
		 * currentPlayer = 1; // outToServer.writeBytes("7amda" + '\n'); // // }
		 * else { // String next = inFromServer.readLine(); //
		 * System.out.println(next); // currentPlayer = player1.getSide(); // }
		 * // } else { // if (currentPlayer == player2.getSide()) { //
		 * currentPlayer = 0; // outToServer.writeBytes("7amda" + '\n'); // // }
		 * else { // String next = inFromServer.readLine(); //
		 * System.out.println(next); // currentPlayer = player2.getSide(); // }
		 * // } // }
		 * 
		 * currentPlayer = player2.getSide(); tempAI comp = new tempAI(ai,
		 * player1, board); Pieces p = comp.play(); Position orignal =
		 * comp.getOri(); grid = board.getBoardPieces(); Pieces temp =
		 * grid[orignal.getX()][orignal.getY()]; temp.doMove(ai,
		 * p.getPosition(), false); changePlayer(); } else { currentPlayer =
		 * player1.getSide(); }
		 */

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

	public static boolean isCheckmate() {
		return checkmate;
	}

	public static void setCheckmate(boolean checkmate) {
		GameActivityOnline.checkmate = checkmate;
	}

}
