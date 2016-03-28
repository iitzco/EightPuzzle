package eightPuzzle;

import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Puzzle implements GPSProblem {

	static GPSEngine pEngine;
	static List<GPSRule> ruleList;

	public static void main(String[] args) {
		pEngine = new PuzzleEngine();
		try {
			pEngine.engine(new Puzzle(), SearchStrategy.DFS);
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}

	@Override
	public GPSState getInitState() {
		Scanner s = new Scanner(System.in);
		int[][] map = new int[PuzzleState.LENGTH][PuzzleState.LENGTH];
		int index = 0;
		while (index < PuzzleState.LENGTH * PuzzleState.LENGTH) {
			map[index / PuzzleState.LENGTH][index % PuzzleState.LENGTH] = s
					.nextInt();
			index++;
		}
		s.close();
		return new PuzzleState(map);
	}

	@Override
	public boolean isGoal(GPSState state) {
		return state.equals(PuzzleState.finalState());
	}

	private static void initRules() {
		ruleList = new LinkedList<>();
		for (Direction dir : Direction.values()) {
			ruleList.add(new PuzzleRule(dir));
		}
	}

	@Override
	public List<GPSRule> getRules() {
		if (Puzzle.ruleList == null) {
			initRules();
		}
		return Puzzle.ruleList;
	}

	@Override
	public Integer getHValue(GPSState state) {
		int h = manhattanPath(state);
		return h;
	}

	public Integer manhattanPath(GPSState state) {
		PuzzleState pState = (PuzzleState) state;
		Point blank = pState.getBlankCoords();
		return (PuzzleState.LENGTH - 1 - blank.x)
				+ (PuzzleState.LENGTH - 1 - blank.y);
	}

	public Integer unplacedPieces(GPSState state) {
		PuzzleState pState = (PuzzleState) state;
		int count = 0;
		for (int i = 0; i < PuzzleState.LENGTH; i++) {
			for (int j = 0; j < PuzzleState.LENGTH; j++) {
				if (i != 2 && j != 2) {
					if (pState.getMap()[i][j] != (i * PuzzleState.LENGTH + j + 1)) {
						count++;
					}
				} else {
					if (pState.getMap()[i][j] != -1) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
