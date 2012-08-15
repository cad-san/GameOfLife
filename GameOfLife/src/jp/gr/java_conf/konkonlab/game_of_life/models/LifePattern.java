package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

import android.util.Pair;

public class LifePattern {
	/* Constant for type of life patterns */
	/* @formatter:off */
	public static final int TYPE_STILL_LIFE      =  1;
	public static final int TYPE_OSCILLATOR      =  2;
	public static final int TYPE_SPACESHIP       =  3;
	public static final int TYPE_INFINITE_GROWTH =  4;
	public static final int TYPE_METHUSELAH      =  5;
	public static final int TYPE_INVALID_LIFE    = -1;
	/* @formatter:on */

	private String name;
	private int type;
	private int number;

	private List<List<Boolean>> cellMatrix;

	public LifePattern(int number, int type, String name, List<Pair<Integer, Integer>> cells, int x, int y) {
		this.name = name;
		this.type = type;
		this.number = number;
		this.cellMatrix = createCellMatrix(cells, x, y);
	}

	public Boolean isAliveAt(int x, int y) {
		return cellMatrix.get(y).get(x);
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

	public int getNumCellX() {
		return this.cellMatrix.get(0).size();
	}

	public int getNumCellY() {
		return this.cellMatrix.size();
	}

	private List<List<Boolean>> createCellMatrix(List<Pair<Integer, Integer>> cells, int numX, int numY) {
		List<List<Boolean>> cellMatrix = new ArrayList<List<Boolean>>();

		for (int y = 0; y < numY; y++) {
			cellMatrix.add(new ArrayList<Boolean>());
			for (int x = 0; x < numX; x++) {
				cellMatrix.get(y).add(false);
			}
		}

		Collections.sort(cells, new CellComparator());
		Iterator it = cells.iterator();

		while (it.hasNext()) {
			Pair<Integer, Integer> pair = (Pair<Integer, Integer>) it.next();
			cellMatrix.get(pair.second).set(pair.first, true);
		}

		return cellMatrix;
	}

	class CellComparator implements java.util.Comparator {
		public int compare(Object s, Object t) {
			Pair<Integer, Integer> pair_s = (Pair<Integer, Integer>) s;
			Pair<Integer, Integer> pair_t = (Pair<Integer, Integer>) t;
			if (pair_s.first == pair_t.first) {
				return pair_s.first - pair_t.first;
			}
			else {
				return pair_s.second - pair_t.second;
			}
		}
	}
}
