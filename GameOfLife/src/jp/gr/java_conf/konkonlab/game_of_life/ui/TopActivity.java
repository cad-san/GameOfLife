package jp.gr.java_conf.konkonlab.game_of_life.ui;

import jp.gr.java_conf.konkonlab.game_of_life.R;
import android.app.Activity;
import android.os.Bundle;

public class TopActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}