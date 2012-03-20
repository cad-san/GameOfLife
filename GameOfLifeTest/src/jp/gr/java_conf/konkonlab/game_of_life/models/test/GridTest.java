package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Cell;
import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

public class GridTest extends TestCase {

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
		
		List<List<Boolean>> cellMatrix = new ArrayList<List<Boolean>>();
		
		for(int y = 0; y < numX; y++) {
			cellMatrix.add(new ArrayList<Boolean>());
			for(int x = 0; x < numY; x++){
				cellMatrix.get(y).add(initMatix[y][x] == 1);
			}
		}
		
		Grid grid = new Grid(cellMatrix);

		for(int x = 0; x < numX; x++) {
			for(int y = 0; y < numY; y++) {
				if( initMatix[y][x] == 1) {
					assertTrue(grid.getCellAt(x,y).isAlive());
				}
				else {
					assertFalse(grid.getCellAt(x,y).isAlive());
				}
			}
		}

	}
}
