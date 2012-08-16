package jp.gr.java_conf.konkonlab.game_of_life.ui.test;

import jp.gr.java_conf.konkonlab.game_of_life.ui.LifeGridAxis;
import junit.framework.TestCase;

public class LifeGridAxisTest extends TestCase {

	public void testOneCell() throws Exception {
		int numCellX = 1;
		int numCellY = 1;
		int lineWeight = 1;
		int gridWidth = 3;
		int gridHeight = 3;
		LifeGridAxis gridAxis = new LifeGridAxis(numCellX, numCellY, lineWeight, gridWidth, gridHeight);

		assertNumber(gridAxis, numCellX, numCellY);

		assertXAxis(gridAxis, 1, lineWeight);
		assertYAxis(gridAxis, 1, lineWeight);
	}

	public void testGrid2by2() throws Exception {
		int numCellX = 2;
		int numCellY = 2;
		int lineWeight = 1;
		int gridWidth = 7;
		int gridHeight = 7;
		LifeGridAxis gridAxis = new LifeGridAxis(numCellX, numCellY, lineWeight, gridWidth, gridHeight);

		assertNumber(gridAxis, numCellX, numCellY);

		assertXAxis(gridAxis, 2, lineWeight);
		assertYAxis(gridAxis, 2, lineWeight);
	}

	public void testGrid2by3() throws Exception {
		int numCellX = 2;
		int numCellY = 3;
		int lineWeight = 1;
		int gridWidth = 7;
		int gridHeight = 10;
		LifeGridAxis gridAxis = new LifeGridAxis(numCellX, numCellY, lineWeight, gridWidth, gridHeight);

		assertNumber(gridAxis, numCellX, numCellY);

		assertXAxis(gridAxis, 2, lineWeight);
		assertYAxis(gridAxis, 2, lineWeight);
	}

	private void assertNumber(LifeGridAxis gridAxis, int numCellX, int numCellY) {
		assertEquals(numCellX, gridAxis.getNumCellX());
		assertEquals(numCellY, gridAxis.getNumCellY());
		assertEquals(numCellX + 1, gridAxis.getNumLineX());
		assertEquals(numCellY + 1, gridAxis.getNumLineY());
	}

	private void assertXAxis(LifeGridAxis gridAxis, int cellWeight, int lineWeight) {
		for (int x = 0; x < gridAxis.getNumLineX(); x++) {
			assertEquals(x * (lineWeight + cellWeight), gridAxis.getLineXAxisAt(x));
		}
		for (int x = 0; x < gridAxis.getNumCellX(); x++) {
			assertEquals(lineWeight * (x + 1) + cellWeight * x, cellWeight, gridAxis.getCellXAxisAt(x));
		}
	}

	private void assertYAxis(LifeGridAxis gridAxis, int cellWeight, int lineWeight) {
		for (int y = 0; y < gridAxis.getNumLineY(); y++) {
			assertEquals(y * (lineWeight + cellWeight), gridAxis.getLineYAxisAt(y));
		}
		for (int y = 0; y < gridAxis.getNumCellY(); y++) {
			assertEquals(lineWeight * (y + 1) + cellWeight * y, cellWeight, gridAxis.getCellYAxisAt(y));
		}
	}
}
