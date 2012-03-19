package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Cell;
import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import junit.framework.TestCase;

public class GridTest extends TestCase {

	public void testFullyInit() throws Exception {
		int numX = 100;
		int numY = 100;
		Grid grid = new Grid(numX, numY);
		for(int x = 0; x < numX; x++) {
			for( int y = 0; y < numY; y++) {
				assertFalse(grid.getCellAt(x,y).isAlive());
			}
		}
	}
}
