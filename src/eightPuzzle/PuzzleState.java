package eightPuzzle;

import java.awt.Point;
import java.util.Arrays;

import gps.api.GPSState;

public class PuzzleState implements GPSState {
	static PuzzleState finalState;
	static int LENGTH = 3;
	int[][] map = new int[LENGTH][LENGTH];

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleState other = (PuzzleState) obj;
		if (!Arrays.deepEquals(map, other.map))
			return false;
		return true;
	}

	public int[][] getMap() {
		return map;
	}

	public Point getBlankCoords() {
		int x, y;
		for (x = 0; x < LENGTH; x++) {
			for (y = 0; y < LENGTH; y++) {
				if (map[x][y] == -1) {
					return new Point(x, y);
				}
			}
		}
		throw new IllegalStateException();
	}

	public PuzzleState(int[][] map) {
		this.map = map;
	}

	static public PuzzleState finalState() {
		if (finalState == null) {
			int[][] map = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, -1 } };
			finalState = new PuzzleState(map);
		}
		return finalState;
	}

}
