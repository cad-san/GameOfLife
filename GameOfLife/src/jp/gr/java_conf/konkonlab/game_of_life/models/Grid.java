package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private List<List<Cell>> cellMatrix;

	public Grid(int numX, int numY) {
		cellMatrix = createCellMatrix(numX, numY);
		decideGroups();
	}

	public Grid(List<List<Boolean>> booleanMatrix) {
		cellMatrix = createCellMatrix(booleanMatrix);
		decideGroups();
	}

	public Grid(Grid prevGrid) {
		cellMatrix = createNextCellMatrix(prevGrid);
		decideGroups();
	}

	public Cell getCellAt(int x, int y) {
		return cellMatrix.get(y).get(x);
	}

	public Grid createNextGeneration() {
		return new Grid(this);
	}

	public int getNumOfNeighborsAt(int x, int y) {
		int count = 0;
		for (int targetY = y - 1; targetY <= y + 1; targetY++) {
			for (int targetX = x - 1; targetX <= x + 1; targetX++) {
				if (!isCentralCell(x, y, targetX, targetY)) {
					count += countALiveCell(targetX, targetY);
				}
			}
		}
		return count;
	}

	public int getNumX() {
		return cellMatrix.get(0).size();
	}

	public int getNumY() {
		return cellMatrix.size();
	}

	private List<List<Cell>> createCellMatrix(int numX, int numY) {
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for (int y = 0; y < numY; y++) {
			cellMatrix.add(new ArrayList<Cell>());
			for (int x = 0; x < numX; x++) {
				cellMatrix.get(y).add(Cell.createDeadCell());
			}
		}
		return cellMatrix;
	}

	private List<List<Cell>> createCellMatrix(List<List<Boolean>> booleanMatrix) {
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for (int y = 0; y < booleanMatrix.size(); y++) {
			cellMatrix.add(new ArrayList<Cell>());
			for (int x = 0; x < booleanMatrix.get(y).size(); x++) {
				if (booleanMatrix.get(y).get(x)) {
					cellMatrix.get(y).add(Cell.createAliveCell());
				}
				else {
					cellMatrix.get(y).add(Cell.createDeadCell());
				}
			}
		}
		return cellMatrix;
	}

	private List<List<Cell>> createNextCellMatrix(Grid prevGrid) {
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for (int y = 0; y < prevGrid.getNumY(); y++) {
			cellMatrix.add(new ArrayList<Cell>());
			for (int x = 0; x < prevGrid.getNumX(); x++) {
				cellMatrix.get(y)
						.add(prevGrid.getCellAt(x, y).createNextGeneration(prevGrid.getNumOfNeighborsAt(x, y)));
			}
		}
		return cellMatrix;
	}

	private boolean isCentralCell(int centerX, int centerY, int x, int y) {
		return (x == centerX && y == centerY);
	}

	private boolean isOutOfBoundsInCellMatrix(int x, int y) {
		return (x < 0 || x >= getNumX() || y < 0 || y >= getNumY());
	}

	private int countALiveCell(int x, int y) {
		if (isOutOfBoundsInCellMatrix(x, y)) {
			return 0;
		}
		else {
			return getCellAt(x, y).isAlive() ? 1 : 0;
		}
	}

	private void decideGroups() {
		for (int y = 0; y < this.getNumY(); y++) {
			for (int x = 0; x < this.getNumX(); x++) {
				this.getCellAt(x, y).decideGroup(this.getNumOfNeighborsAt(x, y));
			}
		}
	}
}
