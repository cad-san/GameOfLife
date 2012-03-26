package jp.gr.java_conf.konkonlab.game_of_life.models.test;

import jp.gr.java_conf.konkonlab.game_of_life.models.Model;
import jp.gr.java_conf.konkonlab.game_of_life.models.ModelListener;
import junit.framework.TestCase;

public class TestModelListener extends TestCase {

	class MockModelListener implements ModelListener {
		boolean notified = false;
		
		public void update()
		{
			notified = true;
		}
	}
	
	class MockModel extends Model {
		public void step()
		{
			this.notifyListener();
		}
	}
	
	public void testNotify() throws Exception {
		MockModelListener modelListener = new MockModelListener();
		MockModel model = new MockModel();
		model.setListener(modelListener);

		assertFalse(modelListener.notified);
		model.step();
		assertTrue(modelListener.notified);
	}
	
}
