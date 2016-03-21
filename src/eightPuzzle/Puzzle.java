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
		new GPSEngine() {
			
			@Override
			public void addNode(GPSNode node) {
				switch (strategy) {
				case DFS:
					open.add(0, node);
					break;
				case BFS:
					open.add(node);
				default:
					break;
				}
				
			}
		}.engine(new Puzzle(), SearchStrategy.DFS);
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
		List<GPSRule> rules = new LinkedList<GPSRule>();
		for(Direction d : Direction.values()){
			rules.add(new PuzzleRule(d));
		}
		return rules;
	}

	
	//	No se usa (todavia)
	@Override
	public Integer getHValue(GPSState state) {
		return 1;
	}

}
