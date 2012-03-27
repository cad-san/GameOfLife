package jp.gr.java_conf.konkonlab.game_of_life.ui;

import jp.gr.java_conf.konkonlab.game_of_life.R;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;

public class TopActivity extends SherlockActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
    }
}