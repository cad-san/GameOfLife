package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private List<List<Cell>> cellMatrix;
	private int numX;
	private int numY;
	
	public Grid(int numX, int numY) {
		this.numX = numX;
		this.numY = numY;
		cellMatrix = createCellMatrix(numX, numY);
	}

	public Cell getCellAt(int x, int y) {
		return cellMatrix.get(x).get(y);
	}

	public int getNumX() {
		return numX;
	}

	public int getNumY() {
		return numY;
	}

	private List<List<Cell>> createCellMatrix(int numX, int numY)
	{
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for(int x = 0; x < numX; x++) {
			cellMatrix.add(new ArrayList<Cell>());
			for(int y = 0; y < numY; y++) {
				cellMatrix.get(x).add(new Cell(Cell.DeadCell));
			}
		}
		return cellMatrix;
	}

}
