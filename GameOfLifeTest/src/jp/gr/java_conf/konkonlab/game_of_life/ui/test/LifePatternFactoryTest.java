package jp.gr.java_conf.konkonlab.game_of_life.ui.test;


import java.util.List;

import jp.gr.java_conf.konkonlab.game_of_life.R;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifePattern;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifePatternFactory;
import jp.gr.java_conf.konkonlab.game_of_life.ui.TopActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;


public class LifePatternFactoryTest extends ActivityInstrumentationTestCase2<TopActivity> {
	private Activity activity;
	
	public LifePatternFactoryTest() {
		super("jp.gr.java_conf.konkonlab.game_of_life.ui", TopActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.activity = getActivity();
	}
	
	public void testBlock() throws Exception {
		LifePatternFactory factory = new LifePatternFactory();
		factory.addParser(activity.getResources().getXml(R.xml.life_pattern_block));
		List<LifePattern> lifePatterns = factory.parseLifePatterns();
		assertEquals("Block", lifePatterns.get(0).getName());
		assertEquals(LifePattern.TYPE_STILL_LIFE, lifePatterns.get(0).getType());
		assertEquals(2, lifePatterns.get(0).getNumCellX());
		assertEquals(2, lifePatterns.get(0).getNumCellY());
		assertTrue(lifePatterns.get(0).isAliveAt(0, 0));
		assertTrue(lifePatterns.get(0).isAliveAt(0, 1));
		assertTrue(lifePatterns.get(0).isAliveAt(1, 0));
		assertTrue(lifePatterns.get(0).isAliveAt(1, 1));
	}
	
	public void testMultiLife() throws Exception {
		LifePatternFactory factory = new LifePatternFactory();
		factory.addParser(activity.getResources().getXml(R.xml.life_pattern_block));
		List<LifePattern> lifePatterns = factory.parseLifePatterns();
		
		assertEquals(2, lifePatterns.size());
		
		assertEquals("Beehive", lifePatterns.get(1).getName());
		assertEquals(LifePattern.TYPE_STILL_LIFE, lifePatterns.get(1).getType());
		assertEquals(4, lifePatterns.get(1).getNumCellX());
		assertEquals(3, lifePatterns.get(1).getNumCellY());
		assertTrue(lifePatterns.get(1).isAliveAt(1, 0));
		assertTrue(lifePatterns.get(1).isAliveAt(2, 0));
		assertTrue(lifePatterns.get(1).isAliveAt(0, 1));
		assertTrue(lifePatterns.get(1).isAliveAt(3, 1));
		assertTrue(lifePatterns.get(1).isAliveAt(1, 2));
		assertTrue(lifePatterns.get(1).isAliveAt(2, 2));
	}
}
