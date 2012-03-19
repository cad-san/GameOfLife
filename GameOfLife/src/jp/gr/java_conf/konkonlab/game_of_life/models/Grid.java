package jp.gr.java_conf.konkonlab.game_of_life.models;

public class Grid {

	public Grid(int numX, int numY) {
	}

	public Cell getCellAt(int x, int y) {
		return new Cell(Cell.DeadCell);
	}

}
