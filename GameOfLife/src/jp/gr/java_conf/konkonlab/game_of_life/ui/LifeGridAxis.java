package jp.gr.java_conf.konkonlab.game_of_life.ui;

import java.util.ArrayList;
import java.util.List;

public class LifeGridAxis {
	int gridWidth;
	int gridHeight;
	int lineWeight;

	List<Integer> lineXAxis;
	List<Integer> lineYAxis;

	public LifeGridAxis(int numCellX, int numCellY, int lineWeight, int gridWidth, int gridHeight) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.lineWeight = lineWeight;
		lineXAxis = createAxis(numCellX, lineWeight, gridWidth);
		lineYAxis = createAxis(numCellY, lineWeight, gridHeight);
	}

	public int getLineXAxisAt(int x) {
		return lineXAxis.get(x);
	}

	public int getLineYAxisAt(int y) {
		return lineYAxis.get(y);
	}

	public int getNumLineX() {
		return lineXAxis.size();
	}

	public int getNumLineY() {
		return lineYAxis.size();
	}

	public int getCellXAxisAt(int x) {
		return lineXAxis.get(x) + lineWeight;
	}

	public int getCellYAxisAt(int y) {
		return lineYAxis.get(y) + lineWeight;
	}

	public int getNumCellX() {
		return lineXAxis.size() - 1;
	}

	public int getNumCellY() {
		return lineYAxis.size() - 1;
	}

	private List<Integer> createAxis(int numOfCell, int lineWeight, int gridBreadth) {
		List<Integer> cellCorrd = new ArrayList<Integer>();

		float cellBreadth = calcCellBreadth(numOfCell, lineWeight, gridBreadth);

		for (int i = 0; i <= numOfCell; i++) {
			cellCorrd.add(calcCellDimension(cellBreadth, i, lineWeight));
		}

		return cellCorrd;
	}

	private int calcCellDimension(float cellBreadth, int cellNo, int lineWeight) {
		return (int) (cellNo * (cellBreadth + lineWeight));
	}

	private float calcCellBreadth(int numCell, int lineWeight, int areaBreadth) {
		return (float) (areaBreadth - (numCell + 1) * lineWeight) / numCell;
	}
}
