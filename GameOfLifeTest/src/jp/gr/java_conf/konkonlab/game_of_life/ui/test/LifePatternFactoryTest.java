package jp.gr.java_conf.konkonlab.game_of_life.ui.test;


import java.util.List;

import jp.gr.java_conf.konkonlab.game_of_life.R;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifePattern;
import jp.gr.java_conf.konkonlab.game_of_life.models.LifePatternFactory;
import jp.gr.java_conf.konkonlab.game_of_life.models.test.LifePatternTest;
import jp.gr.java_conf.konkonlab.game_of_life.ui.TopActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Pair;


public class LifePatternFactoryTest extends ActivityInstrumentationTestCase2<TopActivity> {
	private Activity activity;
	private LifePatternFactory factory;
	private List<LifePattern> lifePatterns;
	
	public LifePatternFactoryTest() {
		super("jp.gr.java_conf.konkonlab.game_of_life.ui", TopActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.activity = getActivity();
		factory = new LifePatternFactory();
	}

	private List<LifePattern> parseLifePatterns() {
		factory.addParser(activity.getResources().getXml(R.xml.life_pattern_still_life));
		factory.addParser(activity.getResources().getXml(R.xml.life_pattern_oscillator));
		factory.parse();
		return factory.getLifePatterns();
	}
	
	public void testBlock() throws Exception {
		lifePatterns = parseLifePatterns();
		LifePattern lifePattern = lifePatterns.get(0);
		int[][] cells = {
				{0,0}, {0,1}, {1,0}, {1,1}
		};

		assertEquals("Block", lifePattern.getName());
		assertEquals(LifePattern.TYPE_STILL_LIFE, lifePattern.getType());

		LifePatternTest.assertCellSize(lifePattern, 2, 2);
		LifePatternTest.assertCellList(cells, lifePattern);
	}
	
	public void testMultiLife() throws Exception {
		lifePatterns = parseLifePatterns();
		LifePattern lifeBeehive = lifePatterns.get(1);
		int[][] beehiveCells = {
				{1,0}, {2,0}, {0,1}, {3,1}, {1,2}, {2,2}
		};

		assertEquals("Beehive", lifeBeehive.getName());
		assertEquals(LifePattern.TYPE_STILL_LIFE, lifeBeehive.getType());

		LifePatternTest.assertCellSize(lifeBeehive, 4, 3);
		LifePatternTest.assertCellList(beehiveCells, lifeBeehive);
	}
	
	public void testMutipleParser() throws Exception {
		lifePatterns = parseLifePatterns();
		LifePattern lifeBlinker = lifePatterns.get(2);
		int[][] blinkerCells = {
				{1,0}, {1,1}, {1,2}
		};
		assertEquals("Blinker", lifeBlinker.getName());
		assertEquals(LifePattern.TYPE_OSCILLATOR, lifeBlinker.getType());

		LifePatternTest.assertCellSize(lifeBlinker, 3, 3);
		LifePatternTest.assertCellList(blinkerCells, lifeBlinker);
	}
	
	public void testNoParser() throws Exception {
		factory.parse();
		assertTrue(factory.getLifePatterns().isEmpty());
	}
	
	public void testNullParser() throws Exception {
		factory.addParser(null);
		factory.parse();
		assertTrue(factory.getLifePatterns().isEmpty());
	}
}
