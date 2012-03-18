package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Cell;
import junit.framework.TestCase;

public class CellTest extends TestCase {

	public void testInitAlive() throws Exception {
		Cell cell = new Cell(Cell.AliveCell);
		assertEquals("ALIVE", cell.toString());
		assertTrue(cell.equals(Cell.AliveCell));
	}

	public void testInitDead() throws Exception {
		Cell cell = new Cell(Cell.DeadCell);
		assertEquals("DEAD", cell.toString());
		assertTrue(cell.equals(Cell.DeadCell));
	}
}
