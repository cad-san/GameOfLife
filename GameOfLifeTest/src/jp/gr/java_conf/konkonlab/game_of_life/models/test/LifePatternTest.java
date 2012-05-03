package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import java.util.ArrayList;
import java.util.List;

import android.util.Pair;

import jp.gr.java_conf.konkonlab.game_of_life.models.LifePattern;
import junit.framework.TestCase;

public class LifePatternTest extends TestCase {

	public void testBlock() throws Exception {
		String name = new String("Block");
		int type = LifePattern.TYPE_STILL_LIFE;
		int numCellX = 2;
		int numCellY = 2;
		
		List<Pair<Integer, Integer>> cells = new ArrayList<Pair<Integer, Integer>>();
		
		cells.add(new Pair<Integer,Integer>(0,0));
		cells.add(new Pair<Integer,Integer>(0,1));
		cells.add(new Pair<Integer,Integer>(1,0));
		cells.add(new Pair<Integer,Integer>(1,1));
		
		LifePattern life = new LifePattern(name,type,cells,numCellX,numCellY);
		
		assertEquals(life.getName(), name);
		assertEquals(life.getType(), type);
		assertEquals(life.getNumCellX(), numCellX);
		assertEquals(life.getNumCellY(), numCellY);
		assertTrue(life.isAliveAt(0, 0));
		assertTrue(life.isAliveAt(0, 1));
		assertTrue(life.isAliveAt(1, 0));
		assertTrue(life.isAliveAt(1, 1));
	}
}
