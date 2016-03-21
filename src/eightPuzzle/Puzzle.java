package eightPuzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class Puzzle implements GPSProblem {

	public static void main(String[] args) {
		//TODO
	}

	@Override
	public GPSState getInitState() {
		Scanner s = new Scanner(System.in);
		int[][] map = new int[3][3];
		int index = 0;
		while (index < 9) {
			map[index / 3][index % 3] = s.nextInt();
			index++;
		}
		s.close();
		return new PuzzleState(map);
	}

	@Override
	public boolean isGoal(GPSState state) {
		return state.equals(PuzzleState.finalState());
	}

	@Override
	public List<GPSRule> getRules() {
		//TODO
		return null;
	}

	// No se usa (todavia)
	@Override
	public Integer getHValue(GPSState state) {
		return 1;
	}

}
