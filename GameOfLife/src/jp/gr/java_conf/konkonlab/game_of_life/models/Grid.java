package jp.gr.java_conf.konkonlab.game_of_life.models;

public class Grid {

	private int numX;
	private int numY;
	
	public Grid(int numX, int numY) {
		this.numX = numX;
		this.numY = numY;
	}

	public Cell getCellAt(int x, int y) {
		return new Cell(Cell.DeadCell);
	}

	public int getNumX() {
		return numX;
	}

	public int getNumY() {
		return numY;
	}

}
