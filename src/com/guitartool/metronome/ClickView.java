package com.guitartool.metronome;

import com.guitartool.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Widok odpowiedzialny za dzia³anie metronomu
 * 
 * @author Kuba
 *
 */
public class ClickView extends View {
	private TextView testView;
	private Bitmap[] mMarkerArray;
	private final Paint mPaint = new Paint();
	private MediaPlayer clickMP, bellMP;
	
	private static final int UPBEAT_OFF = 2;
	private static final int UPBEAT_ON = 3;
	private static final int DOWNBEAT_OFF = 0;
	private static final int DOWNBEAT_ON = 1;

	private int metrum_base, metrum_count, upbeat, xOffset, yOffset, step;
	private int mSize = 40;
	private int[] grid;
	
	public RefreshHandler mRedrawHandler = new RefreshHandler();
	
	class RefreshHandler extends Handler {

		@Override
	    public void handleMessage(Message msg) {			
			ClickView.this.update();
			ClickView.this.invalidate();
		}
	};
	
	public ClickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public ClickView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	private void initialize() {
		step = -1;
		setCount(4);
		upbeat = 0;
		grid = new int[metrum_count];
		mMarkerArray = new Bitmap[4];
		xOffset = (int) (this.getWidth() / 2 - metrum_count*1.2*mSize);
		clearGrid();
		Resources r =this.getContext().getResources();
		loadMarker(UPBEAT_OFF, r.getDrawable(R.drawable.m0red));
		loadMarker(UPBEAT_ON, r.getDrawable(R.drawable.m1red));
		loadMarker(DOWNBEAT_OFF, r.getDrawable(R.drawable.m0green));
		loadMarker(DOWNBEAT_ON, r.getDrawable(R.drawable.m1green));
		clickMP	= MediaPlayer.create(this.getContext(), R.raw.click);
		bellMP	= MediaPlayer.create(this.getContext(), R.raw.bell);
		
		clickMP.setOnCompletionListener(new OnCompletionListener() {
		    public void onCompletion(MediaPlayer arg0) {
		        
		    }
		});
		bellMP.setOnCompletionListener(new OnCompletionListener() {
		    public void onCompletion(MediaPlayer arg0) {
		        //Log.i("loguje","koniec pliku");
		    }
		});
	}
	
	public void setTestView(TextView tv){
		testView = tv;
	}
	public void reset(){
		grid = new int [metrum_count];
		xOffset = (int) ((this.getWidth() / 2) - metrum_count*0.6*mSize);
		if(xOffset < 0) xOffset = 0;
		clearGrid();
		step = -1;
		this.invalidate();
	}

	public void clearGrid() {
		for (int x = 0; x < metrum_count; x++) {
			grid[x] = 0;
			if (x == upbeat){
				grid[x] = 2;
			}
		}
	}

	public void update() {
	    step++;
	    if(!Metronome.TEST){
	    	playBeat();
	    }
	    clearGrid();
	    nextClick();
	}
	
	private void playBeat(){
		if(step % metrum_count == upbeat){
			bellMP.start();
		}else{
			clickMP.start();
		}
	}
	
	private void nextClick(){
		step = step % metrum_count;
		grid[step]++;
		testView.setText(String.valueOf(step));
	}
	public void loadMarker(int key, Drawable marker) {
		Bitmap bitmap = Bitmap.createBitmap(mSize, mSize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		marker.setBounds(0, 0, mSize, mSize);
		marker.draw(canvas);

		mMarkerArray[key] = bitmap;
	}

	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		xOffset = (int) (w / 2 - metrum_count*1.2*mSize);
		yOffset = 0;
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int x = 0; x < metrum_count; x++) {
			if (grid[x] > -1) {
				canvas.drawBitmap(mMarkerArray[grid[x]],
								  (float)(xOffset + 1.2 * x * mSize),
								  yOffset,
								  mPaint);
			}
		}
	}
	public void releaseMP(){
		clickMP.release();
		bellMP.release();
	}
	
	public void setBase(int base){
		metrum_base = base;
	}
	
	public int getBase(){
		return metrum_base;
	}

	public void setUpbeat(int up){
		upbeat = up;
	}
	
	public void setCount(int count){
		metrum_count = count;
		grid = new int [metrum_count];
		xOffset = (int) ((this.getWidth() / 2) - metrum_count*0.6*mSize);
		if(xOffset < 0) xOffset = 0;
	}
	
	public int getCount(){
		return metrum_count;
	}
	
	public int getStep(){
		return step;
	}

}
