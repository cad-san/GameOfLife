package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import jp.gr.java_conf.konkonlab.game_of_life.models.Grid;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifeGame;

public class LifeGameTest extends TestCase {

	private LifeGame initLifeGame(int[][] templete, int numX, int numY) {
		List<List<Boolean>> initMatrix = new ArrayList<List<Boolean>>();

		for(int y = 0; y < numX; y++) {
			initMatrix.add(new ArrayList<Boolean>());
			for(int x = 0; x < numY; x++){
				initMatrix.get(y).add(templete[y][x] == 1);
			}
		}
		
		return new LifeGame(initMatrix);
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
		
		LifeGame lifeGame = initLifeGame(initMatix, numX, numY);

		judgeExprectedGrid(lifeGame.getGrid(), initMatix);
	}
	
	public void testStep() throws Exception {
		int numX = 5;
		int numY = 5;
		
		int[][] initMatix = {
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
		};
		
		LifeGame lifeGame = initLifeGame(initMatix, numX, numY);
		
		int[][] expectMatix = {
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
		};
		
		lifeGame.step();
		
		judgeExprectedGrid(lifeGame.getGrid(), expectMatix);
	}
}
