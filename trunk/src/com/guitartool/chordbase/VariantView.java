package com.guitartool.chordbase;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.guitartool.R;
import com.guitartool.chordbase.ChordBase.Chord;

public class VariantView extends View {
	private Chord chord;
	private TextView textView;

	private static final int MARKER_SIZE = 30;
	private static final int FINGER_1_MARKER = 1;
	private static final int FINGER_2_MARKER = 2;
	private static final int FINGER_3_MARKER = 3;
	private static final int FINGER_4_MARKER = 4;
	private static final int BASE_1_MARKER = 6;
	private static final int BASE_2_MARKER = 7;
	private static final int BASE_3_MARKER = 8;
	private static final int BASE_4_MARKER = 9;
	private static final int BASE_0_MARKER = 10;
	private static final int OPEN_MARKER = 0;
	private static final int X_MARKER = 5;

	private Bitmap[] mMarkerArray;
	private int[][] grid;
	private int[] frets, fingers;
	private final Paint mPaint = new Paint();
	private int xOffset, yOffset, xSize, ySize, yFirstRow, picSize;
	
	public void update() {
		clearGrid();
	}

	public VariantView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public VariantView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	private void initialize() {
		picSize = MARKER_SIZE;
		mMarkerArray = new Bitmap[11];
		Resources r = this.getContext().getResources();
		setBackgroundDrawable(r.getDrawable(R.drawable.bckv1));
		loadMarker(FINGER_1_MARKER, r.getDrawable(R.drawable.cb_g1));
		loadMarker(FINGER_2_MARKER, r.getDrawable(R.drawable.cb_g2));
		loadMarker(FINGER_3_MARKER, r.getDrawable(R.drawable.cb_g3));
		loadMarker(FINGER_4_MARKER, r.getDrawable(R.drawable.cb_g4));
		loadMarker(BASE_1_MARKER, r.getDrawable(R.drawable.cb_v1));
		loadMarker(BASE_2_MARKER, r.getDrawable(R.drawable.cb_v2));
		loadMarker(BASE_3_MARKER, r.getDrawable(R.drawable.cb_v3));
		loadMarker(BASE_4_MARKER, r.getDrawable(R.drawable.cb_v4));
		loadMarker(BASE_0_MARKER, r.getDrawable(R.drawable.cb_v0));
		loadMarker(OPEN_MARKER, r.getDrawable(R.drawable.cb_g0));
		loadMarker(X_MARKER, r.getDrawable(R.drawable.cb_x));
		grid = new int[6][5];
		clearGrid();
		frets = new int[6];
		fingers = new int[6];

	}

	private void clearGrid() {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 5; y++) {
				grid[x][y] = -1;
			}
		}
	}

	public void loadMarker(int key, Drawable marker) {
		Bitmap bitmap = Bitmap.createBitmap(picSize, picSize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		marker.setBounds(0, 0, picSize, picSize);
		marker.draw(canvas);

		mMarkerArray[key] = bitmap;
	}

	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		xOffset		= (int) (35 * w / 320) - picSize /2;
		yOffset		= (int) (90 * h /450) - picSize /2;
		xSize		= (int) 50 * w / 320;
		ySize		= (int) 100 * h / 450;
		yFirstRow	= (int) (20 * h / 450) - picSize /2;
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 5; y++) {
				if (y==0){
					if (grid[x][y] > -1) {
						canvas.drawBitmap(mMarkerArray[grid[x][y]],
										  xOffset + x * xSize,
										  yFirstRow, mPaint);
					}
				}
				else{
					if (grid[x][y] > -1) {
						canvas.drawBitmap(mMarkerArray[grid[x][y]],
										  xOffset + x * xSize,
										  yOffset + ((y-1) * ySize),
										  mPaint);
					}
				}
			}//loop on y
		}//loop on x
	}// end of onDraw

	public void setChord(Chord newChord) {
		chord = newChord;
		setVariant(0);
	}

	public void setTextView(TextView tv) {
		textView = tv;
	}

	public void setVariant(int var) {
		this.invalidate();
		textView.setText(String.valueOf(chord.variants.get(var).getPosition()));
		if(chord.variants.get(var).getPosition()>0){
			VariantView.this.setBackgroundDrawable(this.getContext().getResources().getDrawable(R.drawable.bckv0));
		}
		else{
			VariantView.this.setBackgroundDrawable(this.getContext().getResources().getDrawable(R.drawable.bckv1));
		}
		readVariant(var);
	}

	private void readVariant(int var) {
		frets = chord.variants.get(var).getFrets();
		fingers = chord.variants.get(var).getFingers();
		for (int i = 0; i < 6; i++) {
			markString(frets[i], fingers[i], 5 - i);
		}
	}

	/**
	 * Umieszcza odpowiedni znacznik na strunie
	 * @param fret
	 * @param finger
	 * @param string
	 */
	private void markString(int fret, int finger, int string) {
		switch (fret) {
		case -10: // BASE_0_MARKER
			grid[string][0] = 10;
			break;
		case -4:
			grid[string][4] = 5+finger;
			break;
		case -3:
			grid[string][3] = 5+finger;
			break;
		case -2:
			grid[string][2] = 5+finger;
			break;
		case -1:
			grid[string][1] = 5+finger;
			break;
		case 0: // OPEN_MARKER
			grid[string][0] = 0;
			break;
		case 10: // X_MARKER
			grid[string][0] = 5;
			break;
		default:
			grid[string][fret] = finger;
			break;
		}//end of switch
	}//end of markString
	
}//end of class