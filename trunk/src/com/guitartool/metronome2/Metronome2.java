 package com.guitartool.metronome2;

import java.util.ArrayList;

import com.guitartool.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class Metronome2 extends Activity {
	//sta³e
	public static final int MAX_BPM = 250;
	public static final int MIN_BPM = 40;
	public static final long TIME_STEP = 30;
	public static final long MAX_ACCUMULATED_TIME = 1000;
	//zmienne, podstawowe typy
	private boolean run;
	private int tempo;
	private long dt, lastUpdateTime, acc, updateDelay;
	//klasy
	private Button backBtn;
	private ToggleButton powerBtn;
	private Button minusBtn, plusBtn;
	private SeekBar tempoBar;
	private TextView tempoTv;
	private Spinner metrumSp, upbeatSp;
	private MetronomeView mv;
	private RefreshHandler mRedrawHandler = new RefreshHandler();
	
	class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            running();
            //mv.invalidate();
        }

        public void sleep(long delayMillis) {
        	this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

	
	@Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
    setContentView(R.layout.metronome2);
    initLayout();
	}
    
	private void running(){
		mv.invalidate();
		lastUpdateTime = System.currentTimeMillis();
		if(run){
			dt = System.currentTimeMillis();
			lastUpdateTime += dt;
			dt = Math.max(0, dt);
			acc += dt;
			acc = Math.min(MAX_ACCUMULATED_TIME, Math.max(acc, 0));
			
			//GrabInput()
			//while (acc > TIME_STEP){
				//UptadeGame(TIME_STEP);
				//acc -= TIME_STEP;
		//	}
			//mv.refresh();
			
			if (acc > updateDelay) {
                mv.refresh();
                lastUpdateTime = System.currentTimeMillis();
            }
            mRedrawHandler.sleep(updateDelay);
			
		}
	}
	
	private void updateTempoView(){
		//TODO aktualizuj Etykietkê z tempem na podstawie tempa
	}
	private void initLayout(){
		
		tempo = 60;
		updateDelay = 60000/tempo;
	    mv 			= (MetronomeView) this.findViewById(R.id.metronomeView);
	    backBtn	  	= (Button) this.findViewById(R.id.metronomeBackBtn);
	    minusBtn  	= (Button) this.findViewById(R.id.m_minus_btn);
	    plusBtn	  	= (Button) this.findViewById(R.id.m_plus_btn);
	    powerBtn  	= (ToggleButton) this.findViewById(R.id.m_on_off_btn);
	    tempoTv	  	= (TextView) this.findViewById(R.id.m_tempo_tv);
	    tempoBar  	= (SeekBar) this.findViewById(R.id.m_tempo_sb);
	    metrumSp	= (Spinner) this.findViewById(R.id.m_metrum_spinner);
	    upbeatSp	= (Spinner) this.findViewById(R.id.m_upbeat_sp);
	    
	    mv.setTextView((TextView) this.findViewById(R.id.testView));
	    
	    backBtn.setOnClickListener(new OnClickListener()
	    {
	    	public void onClick(View v)
	    	{
	    		finish();
	    	}
	    });
	    
	    powerBtn.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				run = isChecked;
				running();
					
			}});
		
	    tempoBar.setMax(MAX_BPM-MIN_BPM);
		tempoBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar bar, int value, boolean byUser) {
				//clickView.setTempo(value+MIN_BPM);
				updateTempoView();
				//clickView.reset();
			}
			public void onStartTrackingTouch(SeekBar bar) {
				// TODO Auto-generated method stub
			}
			public void onStopTrackingTouch(SeekBar bar) {
				// TODO Auto-generated method stub	
			}
		});
		tempoBar.setProgress(4);
		
		plusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//clickView.increseTempo();
				updateTempoView();
				//clickView.reset();
			}});
		
		minusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//clickView.decreseTempo();
				updateTempoView();
				//clickView.reset();
			}}); 
		
		metrumSp.setOnItemSelectedListener(new OnItemSelectedListener(){
	        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	        	ArrayList<CharSequence> data = new ArrayList<CharSequence>();
	        	switch(pos){
	        	case 0:
	        		//clickView.setCount(1);
	        		//clickView.setBase(4);
	        		for(int i=0; i<1;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 1:
	        	//	clickView.setCount(2);
	        //		clickView.setBase(4);
	        		for(int i=0; i<2;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	            	break;
	        	case 2:
	      //  		clickView.setCount(3);
	    //    		clickView.setBase(4);
	        		for(int i=0; i<3;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	            	break;
	        	case 3:
//	        		clickView.setCount(4);
	  //      		clickView.setBase(4);
	        		for(int i=0; i<4;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 4:
	        		//clickView.setCount(5);
	        		//clickView.setBase(4);
	        		for(int i=0; i<5;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 5:
	        		//clickView.setCount(3);
	        		//clickView.setBase(8);
	        		for(int i=0; i<3;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 6:
	        		//clickView.setCount(5);
	        		//clickView.setBase(8);
	        		for(int i=0; i<5;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 7:
	        		//clickView.setCount(6);
	        		//clickView.setBase(8);
	        		for(int i=0; i<6;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 8:
	        		//clickView.setCount(7);
	        		//clickView.setBase(8);
	        		for(int i=0; i<7;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	case 9:
	        		//clickView.setCount(9);
	        		//clickView.setBase(8);
	        		for(int i=0; i<9;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	default:
	        		//clickView.setCount(4);
	        		//clickView.setBase(4);
	        		for(int i=0; i<4;i++){
	            		String tmp = Integer.toString(i+1);
	            		data.add(tmp);
	            	}
	        		break;
	        	}
	        	ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getBaseContext(), android.R.layout.simple_spinner_item, data);
	        	upbeatSp.setAdapter(adapter);
	        	//clickView.clearGrid();
	        	//clickView.reset();
	        	//clickView.refreshDrawableState();
	        }

	        public void onNothingSelected(AdapterView<?> parent) {
	          // Do nothing.
	        }
	    });
		
		upbeatSp.setOnItemSelectedListener(new OnItemSelectedListener(){

	        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	        	//TODO ustawianie wartoœci upbeat
	        	
	        	mv.setUpbeat(pos);
	        	//mv.reset();
	        }

	        public void onNothingSelected(AdapterView<?> parent) {
	          // Do nothing.
	        }
	    });
	}
}
