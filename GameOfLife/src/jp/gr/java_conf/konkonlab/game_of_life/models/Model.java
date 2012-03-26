package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public abstract class Model {
	private List<ModelListener> listeners = new ArrayList<ModelListener>();
	public void attachListener(ModelListener listener){
		listeners.add(listener);
	}
	
	public void detachListener(ModelListener listener) {
		listeners.remove(listener);
	}
	
	public void notifyListener() {
		Iterator<ModelListener> it = listeners.iterator();
		while(it.hasNext()) {
			it.next().update();
		}
	}
}
