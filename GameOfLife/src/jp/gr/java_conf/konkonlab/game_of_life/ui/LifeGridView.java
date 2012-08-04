package jp.gr.java_conf.konkonlab.game_of_life.ui;

import java.util.List;

import jp.gr.java_conf.konkonlab.game_of_life.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LifeGridView extends View {

	private static final int NUM_CELL_X = 40;
	private static final int NUM_CELL_Y = 40;
	private static final int LINE_WEIGHT = 1;

	private static final int LINE_COLOR = Color.argb(0xff, 0x9c, 0x9c, 0x9c);
	/* @formatter:off */
	private static final int[] CELL_COLORS = {
		Color.argb(0xff, 0xf5, 0x34, 0x8d),
		Color.argb(0xff, 0x1b, 0xa0, 0xfd),
		Color.argb(0xff, 0xb7, 0xff, 0x0e),
		Color.argb(0xff, 0xfb, 0xdb, 0x0e)
	};
	/* @formatter:on */

	private int numCellX;
	private int numCellY;
	private int lineWeight;

	private int lineColor;
	private int[] cellColors;

	private LifeGridAxis gridAxis;
	private List<List<Integer>> cellMatrix;

	public LifeGridView(Context context, int numCellX, int numCellY, int lineWeight) {
		super(context);
		this.numCellX = numCellX;
		this.numCellY = numCellY;
		this.lineWeight = lineWeight;

		lineColor = LINE_COLOR;
		cellColors = CELL_COLORS;
	}

	public LifeGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		perseAttrs(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);

		width = height = (width > height) ? height : width;
		this.setMeasuredDimension(width, height);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		gridAxis = new LifeGridAxis(numCellX, numCellY, lineWeight, w, h);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawGridLine(canvas, gridAxis, lineColor);

		if (cellMatrix != null) {
			drawCells(canvas, gridAxis, cellMatrix, cellColors);
		}
	}

	public int getNumCellX() {
		return numCellX;
	}

	public int getNumCellY() {
		return numCellY;
	}

	public void setCellMatrix(List<List<Integer>> cellMatrix) {
		this.cellMatrix = cellMatrix;
	}

	private void perseAttrs(Context context, AttributeSet attrs) {
		TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.LifeGridView);
		this.numCellX = tArray.getInteger(R.styleable.LifeGridView_numCellX, NUM_CELL_X);
		this.numCellY = tArray.getInteger(R.styleable.LifeGridView_numCellY, NUM_CELL_Y);
		this.lineWeight = tArray.getDimensionPixelSize(R.styleable.LifeGridView_lineWeight, LINE_WEIGHT);

		this.lineColor = tArray.getColor(R.styleable.LifeGridView_lineColor, LINE_COLOR);
		this.cellColors = new int[4];
		this.cellColors[0] = tArray.getColor(R.styleable.LifeGridView_cellColor1, CELL_COLORS[0]);
		this.cellColors[1] = tArray.getColor(R.styleable.LifeGridView_cellColor2, CELL_COLORS[1]);
		this.cellColors[2] = tArray.getColor(R.styleable.LifeGridView_cellColor3, CELL_COLORS[2]);
		this.cellColors[3] = tArray.getColor(R.styleable.LifeGridView_cellColor4, CELL_COLORS[3]);
	}

	private void drawCells(Canvas canvas, LifeGridAxis gridAxis, List<List<Integer>> cellMatrix, int[] cellColors) {
		Paint rectPaint = new Paint();

		for (int y = 0; y < cellMatrix.size(); y++) {
			for (int x = 0; x < cellMatrix.get(y).size(); x++) {
				if (cellMatrix.get(y).get(x) >= 0 && cellMatrix.get(y).get(x) < cellColors.length) {
					rectPaint.setColor(cellColors[cellMatrix.get(y).get(x)]);
					drawCellAt(canvas, rectPaint, gridAxis, x, y);
				}
			}
		}
	}

	private void drawCellAt(Canvas canvas, Paint paint, LifeGridAxis gridAxis, int x, int y) {
		/* @formatter:off */
		int top    = gridAxis.getCellXAxisAt(x);
		int left   = gridAxis.getCellYAxisAt(y);
		int bottom = gridAxis.getLineXAxisAt(x+1);
		int right  = gridAxis.getLineYAxisAt(y+1);
		/* @formatter:on */

		canvas.drawRect(top, left, bottom, right, paint);
	}

	private void drawGridLine(Canvas canvas, LifeGridAxis gridAxis, int lineColor) {
		int top, left, right, bottom;

		Paint paint = new Paint();
		paint.setColor(lineColor);

		/* 縦軸 */
		top = gridAxis.getLineYAxisAt(0);
		bottom = gridAxis.getCellYAxisAt(gridAxis.getNumCellY());
		for (int x = 0; x < gridAxis.getNumLineX(); x++) {
			left = gridAxis.getLineXAxisAt(x);
			right = gridAxis.getCellXAxisAt(x);
			canvas.drawRect(left, top, right, bottom, paint);
		}

		/* 横軸 */
		left = gridAxis.getLineXAxisAt(0);
		right = gridAxis.getCellXAxisAt(gridAxis.getNumCellX());
		for (int y = 0; y < gridAxis.getNumLineX(); y++) {
			top = gridAxis.getLineYAxisAt(y);
			bottom = gridAxis.getCellYAxisAt(y);
			canvas.drawRect(left, top, right, bottom, paint);
		}
	}
}
