package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

public class GridTest extends TestCase {

	private Grid createGrid(int[][] templete, int numX, int numY) {
		List<List<Boolean>> initMatrix = new ArrayList<List<Boolean>>();

		for (int y = 0; y < numX; y++) {
			initMatrix.add(new ArrayList<Boolean>());
			for (int x = 0; x < numY; x++) {
				initMatrix.get(y).add(templete[y][x] == 1);
			}
		}

		return new Grid(initMatrix);
	}

	private void judgeExprectedGrid(Grid actualGrid, int[][] expectMatrix) throws Exception {
		for (int x = 0; x < actualGrid.getNumX(); x++) {
			for (int y = 0; y < actualGrid.getNumY(); y++) {
				if (expectMatrix[y][x] == 1) {
					assertTrue(actualGrid.getCellAt(x, y).isAlive());
				}
				else {
					assertFalse(actualGrid.getCellAt(x, y).isAlive());
				}
			}
		}
	}

	public void testFullyInit() throws Exception {
		int numX = 5;
		int numY = 10;
		Grid grid = new Grid(numX, numY);

		assertEquals(numX, grid.getNumX());
		assertEquals(numY, grid.getNumY());

		for (int x = 0; x < numX; x++) {
			for (int y = 0; y < numY; y++) {
				assertFalse(grid.getCellAt(x, y).isAlive());
			}
		}
	}

	public void testInitWithList() throws Exception {
		int numX = 5;
		int numY = 5;

		/* @formatter:off */
		int[][] initMatix = {
				{0, 0, 0, 0, 0},
				{0, 1, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
		};
		/* @formatter:on */

		Grid grid = createGrid(initMatix, numX, numY);

		judgeExprectedGrid(grid, initMatix);
	}

	public void testNumOfNeighbors() throws Exception {
		int numX = 5;
		int numY = 5;

		/* @formatter:off */
		int[][] initMatix = {
				{0, 0, 0, 0, 0},
				{0, 1, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
		};
		/* @formatter:on */

		Grid grid = createGrid(initMatix, numX, numY);
		assertEquals(1, grid.getNumOfNeighborsAt(0, 0));
		assertEquals(2, grid.getNumOfNeighborsAt(1, 1));
		assertEquals(3, grid.getNumOfNeighborsAt(2, 1));
		assertEquals(5, grid.getNumOfNeighborsAt(2, 2));
	}

	public void testCreateNextGeneration() throws Exception {
		int numX = 5;
		int numY = 5;

		/* @formatter:off */
		int[][] initMatix = {
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
		};
		/* @formatter:on */

		Grid initGrid = createGrid(initMatix, numX, numY);

		/* @formatter:off */
		int[][] expectMatix = {
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
		};
		/* @formatter:on */

		Grid nextGenGrid = initGrid.createNextGeneration();

		judgeExprectedGrid(nextGenGrid, expectMatix);
	}
}
