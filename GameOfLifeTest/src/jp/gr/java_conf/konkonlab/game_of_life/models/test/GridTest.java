package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Cell;
import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

public class GridTest extends TestCase {

	private Grid createGrid(int[][] templete, int numX, int numY) {
		List<List<Boolean>> initMatrix = new ArrayList<List<Boolean>>();

		for(int y = 0; y < numX; y++) {
			initMatrix.add(new ArrayList<Boolean>());
			for(int x = 0; x < numY; x++){
				initMatrix.get(y).add(templete[y][x] == 1);
			}
		}
		
		return new Grid(initMatrix);
	}
	
	private void judgeExprectedGrid(Grid actualGrid, int[][] expectMatrix) throws Exception {
		for(int x = 0; x < actualGrid.getNumX(); x++) {
			for(int y = 0; y < actualGrid.getNumY(); y++) {
				if( expectMatrix[y][x] == 1) {
					assertTrue(actualGrid.getCellAt(x,y).isAlive());
				}
				else {
					assertFalse(actualGrid.getCellAt(x,y).isAlive());
				}
			}
		}
	}
	
	public void testFullyInit() throws Exception {
		int numX = 5;
		int numY = 10;
		Grid grid = new Grid(numX, numY);
		
		assertEquals(0, grid.getGeneration());
		assertEquals(numX, grid.getNumX());
		assertEquals(numY, grid.getNumY());
		
		for(int x = 0; x < numX; x++) {
			for( int y = 0; y < numY; y++) {
				assertFalse(grid.getCellAt(x,y).isAlive());
			}
		}
	}
	
	public void testInitWithList() throws Exception {
		int numX = 5;
		int numY = 5;
		
		int[][] initMatix = {
				{0, 0, 0, 0, 0},
				{0, 1, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
		};
		
		Grid grid = createGrid(initMatix, numX, numY);

		judgeExprectedGrid(grid, initMatix);
	}
}
