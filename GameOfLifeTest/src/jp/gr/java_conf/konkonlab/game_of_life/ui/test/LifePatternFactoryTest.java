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
	private List<LifePattern> lifePatterns;
	
	public LifePatternFactoryTest() {
		super("jp.gr.java_conf.konkonlab.game_of_life.ui", TopActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.activity = getActivity();
		LifePatternFactory factory = new LifePatternFactory();
		factory.addParser(activity.getResources().getXml(R.xml.life_pattern_block));
		factory.parse();
		lifePatterns = factory.getLifePatterns();
	}
	
	public void testBlock() throws Exception {
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
		LifePattern lifeBeehive = lifePatterns.get(1);
		int[][] beehiveCells = {
				{1,0}, {2,0}, {0,1}, {3,1}, {1,2}, {2,2}
		};

		assertEquals("Beehive", lifeBeehive.getName());
		assertEquals(LifePattern.TYPE_STILL_LIFE, lifeBeehive.getType());

		LifePatternTest.assertCellSize(lifeBeehive, 4, 3);
		LifePatternTest.assertCellList(beehiveCells, lifeBeehive);
	}
}
