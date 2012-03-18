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
	
	public void testGroupArive() throws Exception {
		Cell cell = new Cell(Cell.AliveCell);
		cell.setGroup(1);
		assertEquals(1, cell.getGroup());
	}
	
	public void testGroupDead() throws Exception {
		Cell cell = new Cell(Cell.DeadCell);
		assertEquals(0, cell.getGroup());
	}
}
