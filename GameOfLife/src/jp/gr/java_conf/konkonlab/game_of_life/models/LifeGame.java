package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.List;

public class LifeGame {

	private Grid currGrid;
	private Grid prevGrid;
	private int generation;

	public LifeGame(int numX, int numY) {
		generation = 0;
		currGrid = new Grid(numX, numY);
		prevGrid = null;
	}

	public LifeGame(List<List<Boolean>> initMatrix) {
		generation = 0;
		currGrid = new Grid(initMatrix);
		prevGrid = null;
	}

	public Grid getGrid() {
		return currGrid;
	}

	public int getGeneration() {
		return generation;
	}

	public void step() {
		prevGrid = currGrid;
		currGrid = prevGrid.createNextGeneration();
		generation++;
	}

}
