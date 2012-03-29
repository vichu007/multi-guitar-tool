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
		mMarkerArray = new Bitmap[11];
		Resources r = this.getContext().getResources();
		setBackgroundDrawable(r.getDrawable(R.drawable.gryf));
		loadMarker(FINGER_1_MARKER, r.getDrawable(R.drawable.marker1));
		loadMarker(FINGER_2_MARKER, r.getDrawable(R.drawable.marker2));
		loadMarker(FINGER_3_MARKER, r.getDrawable(R.drawable.marker3));
		loadMarker(FINGER_4_MARKER, r.getDrawable(R.drawable.marker4));
		loadMarker(BASE_1_MARKER, r.getDrawable(R.drawable.base1));
		loadMarker(BASE_2_MARKER, r.getDrawable(R.drawable.base2));
		loadMarker(BASE_3_MARKER, r.getDrawable(R.drawable.base3));
		loadMarker(BASE_4_MARKER, r.getDrawable(R.drawable.base4));
		loadMarker(BASE_0_MARKER, r.getDrawable(R.drawable.base0));
		loadMarker(OPEN_MARKER, r.getDrawable(R.drawable.marker0));
		loadMarker(X_MARKER, r.getDrawable(R.drawable.markerx));
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
		Bitmap bitmap = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		marker.setBounds(0, 0, 40, 40);
		marker.draw(canvas);

		mMarkerArray[key] = bitmap;
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 5; y++) {
				if (grid[x][y] > -1) {
					canvas.drawBitmap(mMarkerArray[grid[x][y]], 20 + x * 48, 1
							+ y * 80 + y * y * 4, mPaint);
				}
			}
		}
	}

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
		readVariant(var);
	}

	private void readVariant(int var) {
		frets = chord.variants.get(var).getFrets();
		fingers = chord.variants.get(var).getFingers();
		for (int i = 0; i < 6; i++) {
			markString(frets[i], fingers[i], 5 - i);
		}
	}

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
		}
	}

}
