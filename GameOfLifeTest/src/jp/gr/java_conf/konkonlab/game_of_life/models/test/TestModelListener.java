package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Model;
import jp.gr.java_conf.konkonlab.game_of_life.models.ModelListener;
import junit.framework.TestCase;

public class TestModelListener extends TestCase {

	class MockModelListener implements ModelListener {
		boolean notified = false;

		public void update() {
			notified = true;
		}
	}

	class MockModel extends Model {
		public void step() {
			this.notifyListener();
		}

	}

	MockModelListener modelListener;
	MockModel model;

	@Override
	protected void setUp() throws Exception {
		modelListener = new MockModelListener();
		model = new MockModel();
		model.attachListener(modelListener);
	}

	public void testNotify() throws Exception {
		assertFalse(modelListener.notified);
		model.step();
		assertTrue(modelListener.notified);
	}

	public void testDetachListener() throws Exception {
		model.detachListener(modelListener);
		assertFalse(modelListener.notified);
		model.step();
		assertFalse(modelListener.notified);
	}
}
