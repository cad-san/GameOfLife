package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private List<List<Cell>> cellMatrix;
	private int generation;
	
	public Grid(int numX, int numY) {
		this.generation = 0;
		cellMatrix = createCellMatrix(numX, numY);
	}

	public Grid(List<List<Boolean>> booleanMatrix) {
		this.generation = 0;
		cellMatrix = createCellMatrix(booleanMatrix);
	}

	public Cell getCellAt(int x, int y) {
		return cellMatrix.get(y).get(x);
	}

	public int getNumX() {
		return cellMatrix.get(0).size();
	}

	public int getNumY() {
		return cellMatrix.size();
	}

	private List<List<Cell>> createCellMatrix(int numX, int numY)
	{
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for(int y = 0; y < numY; y++) {
			cellMatrix.add(new ArrayList<Cell>());
			for(int x = 0; x < numX; x++) {
				cellMatrix.get(y).add(new Cell(Cell.DeadCell));
			}
		}
		return cellMatrix;
	}

	private List<List<Cell>> createCellMatrix(List<List<Boolean>> booleanMatrix)
	{
		List<List<Cell>> cellMatrix = new ArrayList<List<Cell>>();
		for(int y = 0; y < booleanMatrix.size(); y++) {
			cellMatrix.add(new ArrayList<Cell>());
			for(int x = 0; x < booleanMatrix.get(y).size(); x++) {
				if(booleanMatrix.get(y).get(x)) {
					cellMatrix.get(y).add(new Cell(Cell.AliveCell));
				}
				else {
					cellMatrix.get(y).add(new Cell(Cell.DeadCell));
				}
			}
		}
		return cellMatrix;
	}
	
	public int getGeneration() {
		return generation;
	}

}
