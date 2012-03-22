package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.List;

public class LifeGame {

	private Grid currGrid;
	private Grid prevGrid;
	
	public LifeGame(int numX, int numY) {
		currGrid = new Grid(numX, numY);
		prevGrid = null;
	}

	public LifeGame(List<List<Boolean>> initMatrix) {
		currGrid = new Grid(initMatrix);
		prevGrid = null;
	}

	public Grid getGrid() {
		return currGrid;
	}

}
