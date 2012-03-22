package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import junit.framework.TestCase;

import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifeGame;

public class LifeGameTest extends TestCase {

	public void testInit() throws Exception {
		int numX = 50;
		int numY = 50;
		LifeGame lifeGame = new LifeGame(numX, numY);
		Grid grid = lifeGame.getGrid();
		
		for(int x = 0; x < numX; x++) {
			for( int y = 0; y < numY; y++) {
				assertFalse(grid.getCellAt(x,y).isAlive());
			}
		}
	}
}
