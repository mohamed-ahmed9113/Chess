package player;

import java.util.ArrayList;

import models.Board;
import models.Pieces;
import models.Position;
import android.util.Log;

public class tempAI {

	private Board bo;
	private PlayerHuman humanPlayer;
	private PlayerAI computerPlayer;
	private int maxDepth = 2;
	private final String TAG = "AI";

	public tempAI(PlayerAI ai, PlayerHuman player, Board b, int de) {
		bo = b.getNewBoard();
		computerPlayer = ai;
		humanPlayer = player;
		bo.updateGameState();
		maxDepth = de;
	}

	public Position play() {
		Position move = minimax();
		return move;
	}

	private Position minimax() {

		ArrayList<Position> sucessors;
		Position move, bestMove = null;
		int value = Integer.MIN_VALUE, maxValue = Integer.MIN_VALUE;
		ArrayList<Pieces> all = bo.getPieces();

		for (int i = 0; i < all.size(); i++) {
			all.get(i).setOldpos(all.get(i).getPosition());
		}

		sucessors = getallmoves(computerPlayer.getSide());
		for (int i = 0; i < sucessors.size(); i++) {
			move = sucessors.get(i);
			Pieces maybe = move.getCurrentone();
			Position or = maybe.getPosition();
			Log.d(TAG, "before " + or.getX() + " :: " + or.getY());
			domove(computerPlayer.getSide(), move);
			value = alphabeta(humanPlayer.getSide(), maxDepth,
					Integer.MIN_VALUE, Integer.MAX_VALUE);
			undomove(computerPlayer.getSide(), move);
			if (value > maxValue) {
				maxValue = value;
				bestMove = move;
				bestMove.setCurrentone(maybe);
			}

		}

		return bestMove;
	}

	int alphabeta(int turn, int depth, int alpha, int beta) {
		if (depth == 0) {
			return (getnumslimes(nextturn(turn)) - getnumslimes(turn));
		}
		ArrayList<Position> move = getallmoves(turn);
		int x, val;
		for (x = 0; x < move.size(); ++x) {
			domove(turn, move.get(x));
			val = -alphabeta(nextturn(turn), depth - 1, -beta, -alpha);
			undomove(turn, move.get(x));
			if (val >= beta) {
				free(move);
				return val;
			} else if (val > alpha)
				alpha = val;
		}
		free(move);
		return alpha;
	}

	private void undomove(int turn, Position position) {
		if (!bo.removeAI(position, nextturn(turn))) {
			ArrayList<Pieces> color = bo.getCertainColor(turn);
			for (int i = 0; i < color.size(); i++) {
				if (color.get(i).getType() == position.getCurrentone()
						.getType()
						&& color.get(i).getNumber() == position.getCurrentone()
								.getNumber()) {
					color.get(i).setPosition(color.get(i).getOldpos());
					color.get(i).getPosition().setCurrentone(color.get(i));
					break;
				}
			}
		}
	}

	private void domove(int turn, Position position) {
		// position.getCurrentone().setOldpos(
		// position.getCurrentone().getPosition());
		if (turn == computerPlayer.getSide()) {
			position.getCurrentone().doMove(computerPlayer, position, true, bo);
		} else {
			position.getCurrentone().doMove(humanPlayer, position, true, bo);
		}
	}

	private void free(ArrayList<Position> move) {
		for (int i = 0; i < move.size(); i++) {
			move.remove(i);
		}
	}

	private ArrayList<Position> getallmoves(int turn) {
		ArrayList<Pieces> color = bo.getCertainColor(turn);
		ArrayList<Position> moves = new ArrayList<Position>();
		for (int i = 0; i < color.size(); i++) {
			moves.addAll(color.get(i).getValidPositions());
		}
		return moves;
	}

	private int nextturn(int turn) {
		int turn1;
		if (turn == computerPlayer.getSide())
			turn1 = humanPlayer.getSide();
		else
			turn1 = computerPlayer.getSide();
		return turn1;
	}

	private int getnumslimes(int turn) {
		int whitec = 0;
		int blackc = 0;
		if (turn == computerPlayer.getSide()) {
			ArrayList<Pieces> color = bo.getCertainColor(turn);
			for (int i = 0; i < color.size(); i++) {
				blackc += color.get(i).getWeight();
			}
		} else {
			ArrayList<Pieces> color = bo.getCertainColor(turn);
			for (int i = 0; i < color.size(); i++) {
				whitec += color.get(i).getWeight();
			}
		}
		return Math.max(whitec, blackc);
	}

}
