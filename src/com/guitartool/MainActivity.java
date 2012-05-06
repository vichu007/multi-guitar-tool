package com.guitartool;


import com.guitartool.chordbase.ChordBase;
import com.guitartool.metronome.Metronome;
import com.guitartool.tuner.TunerMain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button metronomeBtn,
				   tunerBtn,
				   chordDbBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/bazgroly_b.ttf");
        
        metronomeBtn = (Button) this.findViewById(R.id.metronomeBtn);
    	tunerBtn	 = (Button) this.findViewById(R.id.tunerBtn);
    	chordDbBtn	 = (Button) this.findViewById(R.id.chordbaseBtn);
    	
    	metronomeBtn.setTextColor(Color.WHITE);
    	metronomeBtn.setTypeface(tf);
    	tunerBtn.setTextColor(Color.WHITE);
    	tunerBtn.setTypeface(tf);
    	chordDbBtn.setTextColor(Color.WHITE);
    	chordDbBtn.setTypeface(tf);
    	
    	initButtonsOnClickListeners();
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
    		MainActivity.this.finish();
    	}
    	return super.onKeyDown(keyCode, msg);
    }
    
	private void initButtonsOnClickListeners() {
		metronomeBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openMetronomeAct();
			}
		});
		tunerBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openTunerAct();
			}
		});
		chordDbBtn.setOnClickListener(new OnClickListener()
		{
			public void  onClick(View v)
			{
				openChordbaseAct();
			}
		});
	}

	private void openMetronomeAct() {
		Intent intent = new Intent(getApplicationContext(), Metronome.class);
		startActivity(intent);
		
	}

	private void openTunerAct() {
		Intent intent = new Intent(getApplicationContext(), TunerMain.class);
		startActivity(intent);
	}
	
	private void openChordbaseAct(){
		Intent intent = new Intent(getApplicationContext(), ChordBase.class);
		startActivity(intent);
	}
}