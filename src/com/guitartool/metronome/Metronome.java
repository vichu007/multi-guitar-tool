package com.guitartool.metronome;

import java.util.ArrayList;

import com.guitartool.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Metronome extends Activity {
	static final int MAX_BPM = 250;
	static final int MIN_BPM = 40;
	static final boolean TEST = false;
	
	private int tempo, upbeat;
	
	private Button backBtn;
	private ToggleButton powerBtn;
	private Button minusBtn, plusBtn;
	private SeekBar tempoBar;
	private TextView tempoTv;
	private ClickView clickView;
	private Spinner metrumSp, upbeatSp;
	private MediaPlayer clickMP, bellMP;
	
	private MetronomeThread mThread;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tempo = 60;
        upbeat = 1;
        setContentView(R.layout.metronome);
        
        //teœcik
        if(TEST){
        	clickMP	= MediaPlayer.create(this.getBaseContext(), R.raw.click);
        	bellMP	= MediaPlayer.create(this.getBaseContext(), R.raw.bell);
        }
        clickView 	= (ClickView) this.findViewById(R.id.clickView);
        clickView.setTestView((TextView) this.findViewById(R.id.testView));
        backBtn	  	= (Button) this.findViewById(R.id.metronomeBackBtn);
        minusBtn  	= (Button) this.findViewById(R.id.m_minus_btn);
        plusBtn	  	= (Button) this.findViewById(R.id.m_plus_btn);
        powerBtn  	= (ToggleButton) this.findViewById(R.id.m_on_off_btn);
        tempoTv	  	= (TextView) this.findViewById(R.id.m_tempo_tv);
        tempoBar  	= (SeekBar) this.findViewById(R.id.m_tempo_sb);
        metrumSp	= (Spinner) this.findViewById(R.id.m_metrum_spinner);
        upbeatSp	= (Spinner) this.findViewById(R.id.m_upbeat_sp);
        
        backBtn.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		mThread.cancel(true);
        		clickView.releaseMP();
        		Metronome.this.finish();
        	}
        });
        
        powerBtn.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					mThread = new MetronomeThread();
					mThread.execute(calculateDelay());
				}else{
					mThread.cancel(true);
				}	
			}});
		
        tempoBar.setMax(MAX_BPM-MIN_BPM);
		tempoBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar bar, int value, boolean byUser) {
				tempo = value+MIN_BPM;
				updateTempoView();
				resetThread();
			}
			public void onStartTrackingTouch(SeekBar bar) {
				//empty
			}
			public void onStopTrackingTouch(SeekBar bar) {
				//empty	
			}
		});
		
		tempoBar.setProgress(tempo-MIN_BPM);
		
		plusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(tempo<MAX_BPM){
					tempo++;
				}
				updateTempoView();
				resetThread();
			}});
		
		minusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(tempo>MIN_BPM){
					tempo--;
				}
				updateTempoView();
				resetThread();
			}}); 
		
		metrumSp.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	ArrayList<CharSequence> data = new ArrayList<CharSequence>();
            	boolean checked = false;
            	switch(pos){
            	case 0:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(1);
            		clickView.setBase(4);
            		for(int i=0; i<1;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 1:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(2);
            		clickView.setBase(4);
            		for(int i=0; i<2;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 2:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(3);
            		clickView.setBase(4);
            		for(int i=0; i<3;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 3:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(4);
            		clickView.setBase(4);
            		for(int i=0; i<4;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 4:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(5);
            		clickView.setBase(4);
            		for(int i=0; i<5;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 5:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(3);
            		clickView.setBase(8);
            		for(int i=0; i<3;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 6:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(5);
            		clickView.setBase(8);
            		for(int i=0; i<5;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 7:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(6);
            		clickView.setBase(8);
            		for(int i=0; i<6;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 8:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(7);
            		clickView.setBase(8);
            		for(int i=0; i<7;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	case 9:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(9);
            		clickView.setBase(8);
            		for(int i=0; i<9;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	default:
            		if(powerBtn.isChecked()){
                		mThread.cancel(true);
                		checked = true;
            		}
            		clickView.setCount(4);
            		clickView.setBase(4);
            		for(int i=0; i<4;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		if(checked){
	            		mThread = new MetronomeThread();
						mThread.execute(calculateDelay());
            		}
            		break;
            	}
            	ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getBaseContext(), android.R.layout.simple_spinner_item, data);
            	upbeatSp.setAdapter(adapter);
            	resetThread();
            	clickView.refreshDrawableState();
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    	
    	upbeatSp.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	clickView.setUpbeat(pos);
            	upbeat = pos;
            	resetThread();
            	clickView.refreshDrawableState();
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    }
    
	private void updateTempoView(){
		tempoTv.setText(String.valueOf(tempo));
		tempoBar.setProgress(tempo-MIN_BPM);
	}
	private long calculateDelay(){
		return (long) 60000/tempo;
	}
	private void resetThread(){
		boolean checked = false;
		if(powerBtn.isChecked()){
    		mThread.cancel(true);
    		checked = true;
		}
		clickView.reset();
		if(checked){
    		mThread = new MetronomeThread();
			mThread.execute(calculateDelay());
		}
	}
	
	private class MetronomeThread extends AsyncTask<Long, Void, String> {

		@Override
		protected String doInBackground(Long... arg0) {
			long startTime = System.currentTimeMillis();
			long clickDelay = arg0[0];

			while (isCancelled() == false ) {
				publishProgress();
				long currentTimeBeforeSleep = System.currentTimeMillis();
				long currentLag = (currentTimeBeforeSleep - startTime) % clickDelay;
				long sleepTime = clickDelay - currentLag;
				try {
					Thread.sleep(sleepTime);
					//Log.d("Sleeptime", "st = "+sleepTime +" delay: "+currentLag);
				} catch (InterruptedException ex) {
					Log.d("View Thread", "Interrupted");
				}
			}
			Log.d("View Thread", "Thread end");
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.w("MetronomeThread", "onPostExecute -> skoñczy³em w¹tek, tylko ciekawe jak");
		}

		@Override
		protected void onPreExecute() {
			Log.i("MetronomeThread", "onPreExecute  -> zeruje w¹tek przed startem");
		}

		@Override
		protected void onProgressUpdate(Void... voids) {
			if(TEST){
				if (clickView.getStep() % clickView.getCount() == upbeat){
					bellMP.start();
				}else{
					clickMP.start();
				}
				
			}
			clickView.update();
			clickView.invalidate();
		}
	
		@Override
		protected void onCancelled(){
			clickView.reset();
			Log.i("MetronomeThread", "onCancelled -> anulowa³em w¹tek");
		}
	}//end of MetronomeThread
}//eoact
